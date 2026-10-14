package com.scarasol.sona.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.scarasol.sona.accessor.mixin.ILivingEntityAccessor;
import com.scarasol.sona.client.layer.InfectionLayer;
import com.scarasol.sona.client.renderer.SonaRenderType;
import com.scarasol.sona.configuration.CommonConfig;
import com.scarasol.sona.manager.InfectionManager;
import com.scarasol.sona.compat.ShaderCompatUtil;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.fml.ModList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * @author Scarasol
 */
@Mixin(LivingEntityRenderer.class)
public abstract class LivingEntityRendererMixin<T extends LivingEntity, M extends EntityModel<T>> extends EntityRenderer<T> implements RenderLayerParent<T, M> {

    // 缓存模组加载状态，避免每帧查询 ModList
    private static final boolean OCULUS_LOADED = ModList.get().isLoaded("oculus");

    @Shadow public abstract boolean addLayer(RenderLayer<T, M> p_115327_);

    @Unique
    private float sona$alpha;

    protected LivingEntityRendererMixin(EntityRendererProvider.Context context) {
        super(context);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void sona$livingEntityRenderer(EntityRendererProvider.Context p_174289_, EntityModel<T> p_174290_, float p_174291_, CallbackInfo ci) {
        addLayer(new InfectionLayer<>(this));
    }

    @Inject(method = "isShaking", cancellable = true, at = @At("RETURN"))
    protected void sona$isShaking(T livingEntity, CallbackInfoReturnable<Boolean> cir) {
        if (livingEntity instanceof ILivingEntityAccessor survivalEntity) {
            cir.setReturnValue(livingEntity.isFullyFrozen() || (InfectionManager.getInfection(survivalEntity) > 70 && CommonConfig.INFECTION_OPEN.get()));
        }
    }

    @Inject(method = "getRenderType", cancellable = true, at = @At("HEAD"))
    private void sona$getRenderType(T livingEntity, boolean p_115323_, boolean p_115324_, boolean p_115325_, CallbackInfoReturnable<RenderType> cir) {
        if (!livingEntity.isInvisible() && livingEntity instanceof ILivingEntityAccessor accessor) {
            if (accessor.getCamouflageAmplifier() > 0) {
                ResourceLocation resourcelocation = this.getTextureLocation(livingEntity);

                // 【核心修改】：在这里引入光影检测
                if (OCULUS_LOADED && ShaderCompatUtil.isShaderActive()) {
                    cir.setReturnValue(RenderType.entityTranslucent(resourcelocation));
                } else {
                    cir.setReturnValue(SonaRenderType.entityDither(resourcelocation));
                }
            }
        }
    }

    @Inject(method = "render(Lnet/minecraft/world/entity/LivingEntity;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V", at = @At("HEAD"))
    private void sona$render(T entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, CallbackInfo ci) {
        if (entity instanceof ILivingEntityAccessor accessor && accessor.getCamouflageAmplifier() > 0) {
            sona$alpha = accessor.getCamouflageAlpha();
            SonaRenderType.camoAlpha.set(sona$alpha);
        } else {
            sona$alpha = 1f;
        }
    }

    @Inject(method = "render(Lnet/minecraft/world/entity/LivingEntity;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V", at = @At("RETURN"))
    private void sona$clearState(T entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, CallbackInfo ci) {
        SonaRenderType.camoAlpha.remove();
    }

    @WrapOperation(
            method = "render(Lnet/minecraft/world/entity/LivingEntity;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/model/EntityModel;renderToBuffer(Lcom/mojang/blaze3d/vertex/PoseStack;Lcom/mojang/blaze3d/vertex/VertexConsumer;III)V"
            )
    )
    private void sona$renderToBuffer(EntityModel model, PoseStack modelPoseStack, VertexConsumer vertexConsumer, int light, int overlay, int color, Operation<Void> original) {
        int a = color >>> 24;
        // 原 1.20 逻辑：仅当 alpha 为 1 时替换为迷彩透明度；1.21 颜色打包为 ARGB int
        if (a == 255 && sona$alpha < 1f) {
            a = (int) (sona$alpha * 255);
            color = (color & 0xFFFFFF) | (a << 24);
        }
        original.call(model, modelPoseStack, vertexConsumer, light, overlay, color);
    }
}
