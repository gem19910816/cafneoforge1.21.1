package net.mcreator.gore.client.renderer;

import net.mcreator.gore.entity.SkeletonCorpseEntity;
import net.mcreator.gore.entity.model.SkeletonCorpseModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SkeletonCorpseRenderer extends GeoEntityRenderer<SkeletonCorpseEntity> {
   public SkeletonCorpseRenderer(Context renderManager) {
      super(renderManager, new SkeletonCorpseModel());
      this.shadowRadius = 0.0F;
   }

   public RenderType getRenderType(SkeletonCorpseEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }

   protected float getDeathMaxRotation(SkeletonCorpseEntity entityLivingBaseIn) {
      return 0.0F;
   }
}
