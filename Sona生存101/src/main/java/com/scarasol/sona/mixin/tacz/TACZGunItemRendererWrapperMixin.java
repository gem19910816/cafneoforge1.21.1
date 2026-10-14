package com.scarasol.sona.mixin.tacz;

import com.scarasol.sona.client.renderer.SonaRenderType;
import com.scarasol.sona.compat.ShaderCompatUtil;
import com.scarasol.sona.mixin.CompositeRenderTypeAccessor;
import com.scarasol.sona.mixin.CompositeStateAccessor;
import com.scarasol.sona.mixin.TextureStateShardAccessor;
import com.tacz.guns.client.renderer.item.GunItemRendererWrapper;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.fml.ModList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.Optional;

@Mixin(value = GunItemRendererWrapper.class, remap = false)
public abstract class TACZGunItemRendererWrapperMixin {

    @ModifyArg(
            method = "lambda$renderByItem$6",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/tacz/guns/client/model/BedrockGunModel;render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemDisplayContext;Lnet/minecraft/client/renderer/RenderType;II)V"
            ),
            index = 3
    )
    private static RenderType sona$useCamouflageRenderType(RenderType originalRenderType) {
        Float alpha = SonaRenderType.camoAlpha.get();
        if (alpha == null || alpha >= 1.0f) {
            return originalRenderType;
        }

        if (!(originalRenderType instanceof CompositeRenderTypeAccessor accessor)) {
            return originalRenderType;
        }

        // RenderType.CompositeState / RenderStateShard.EmptyTextureStateShard 为 protected 内部类，经 Accessor 以 Object 中转
        Object state = accessor.sona$getState();
        Object textureState = ((CompositeStateAccessor) state).sona$getTextureState();
        if (!(textureState instanceof TextureStateShardAccessor texAccessor)) {
            return originalRenderType;
        }

        Optional<ResourceLocation> texture = texAccessor.sona$getTexture();
        if (texture.isEmpty()) {
            return originalRenderType;
        }

        return ModList.get().isLoaded("oculus") && ShaderCompatUtil.isShaderActive()
                ? RenderType.entityTranslucent(texture.get())
                : SonaRenderType.entityDither(texture.get());
    }
}
