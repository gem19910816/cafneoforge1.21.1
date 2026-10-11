package net.mcreator.gore.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import java.util.IdentityHashMap;
import java.util.Map;
import net.mcreator.gore.client.ZombieDownedRenderHandler;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = LivingEntityRenderer.class, remap = false)
public abstract class ZombieShoveRendererMixin {
   @Shadow protected EntityModel<?> model;
   @Unique private final Map<LivingEntity, Runnable> gore$restore = new IdentityHashMap<>();
   @Unique private static final String GORE_RENDER =
      "render(Lnet/minecraft/world/entity/LivingEntity;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V";

   @Inject(method = GORE_RENDER, at = @At("HEAD"))
   private void gore$freezeFacing(LivingEntity entity, float yaw, float partialTick,
      PoseStack stack, MultiBufferSource buffers, int light, CallbackInfo ci) {
      ZombieDownedRenderHandler.freezeFacing(entity);
   }

   @Inject(method = GORE_RENDER, at = @At(value = "INVOKE",
      target = "Lcom/mojang/blaze3d/vertex/PoseStack;pushPose()V", shift = At.Shift.AFTER))
   private void gore$rootPose(LivingEntity entity, float yaw, float partialTick,
      PoseStack stack, MultiBufferSource buffers, int light, CallbackInfo ci) {
      LivingEntityRenderer<?, ?> renderer = (LivingEntityRenderer<?, ?>) (Object) this;
      net.mcreator.gore.client.ZombieAppearanceClient.capture(entity,
         getTexture(renderer, entity));
      ZombieDownedRenderHandler.applyLivingPose(stack, entity, partialTick);
   }

   @Unique
   @SuppressWarnings({"rawtypes", "unchecked"})
   private static net.minecraft.resources.ResourceLocation getTexture(LivingEntityRenderer renderer, LivingEntity entity) {
      return renderer.getTextureLocation(entity);
   }

   @Inject(method = GORE_RENDER, at = @At(value = "INVOKE",
      target = "Lnet/minecraft/client/model/EntityModel;setupAnim(Lnet/minecraft/world/entity/Entity;FFFFF)V",
      shift = At.Shift.AFTER))
   private void gore$jointPose(LivingEntity entity, float yaw, float partialTick,
      PoseStack stack, MultiBufferSource buffers, int light, CallbackInfo ci) {
      if (model instanceof HumanoidModel<?> humanoid) {
         Runnable restore = ZombieDownedRenderHandler.applyHumanoidPose(humanoid, entity, partialTick);
         if (restore != null) {
            gore$restore.put(entity, restore);
         }
      }
   }

   @Inject(method = GORE_RENDER, at = @At("RETURN"))
   private void gore$restorePose(LivingEntity entity, float yaw, float partialTick,
      PoseStack stack, MultiBufferSource buffers, int light, CallbackInfo ci) {
      Runnable restore = gore$restore.remove(entity);
      if (restore != null) {
         restore.run();
      }
   }
}
