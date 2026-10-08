package net.mcreator.gore.client.renderer;

import net.mcreator.gore.entity.SkeletonCorpseWithoutRightArmEntity;
import net.mcreator.gore.entity.model.SkeletonCorpseWithoutRightArmModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SkeletonCorpseWithoutRightArmRenderer extends GeoEntityRenderer<SkeletonCorpseWithoutRightArmEntity> {
   public SkeletonCorpseWithoutRightArmRenderer(Context renderManager) {
      super(renderManager, new SkeletonCorpseWithoutRightArmModel());
      this.shadowRadius = 0.0F;
   }

   public RenderType getRenderType(SkeletonCorpseWithoutRightArmEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }

   protected float getDeathMaxRotation(SkeletonCorpseWithoutRightArmEntity entityLivingBaseIn) {
      return 0.0F;
   }
}
