package net.mcreator.gore.client.renderer;

import net.mcreator.gore.entity.SkeletonCorpseIIEntity;
import net.mcreator.gore.entity.model.SkeletonCorpseIIModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SkeletonCorpseIIRenderer extends GeoEntityRenderer<SkeletonCorpseIIEntity> {
   public SkeletonCorpseIIRenderer(Context renderManager) {
      super(renderManager, new SkeletonCorpseIIModel());
      this.shadowRadius = 0.0F;
   }

   public RenderType getRenderType(SkeletonCorpseIIEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }

   protected float getDeathMaxRotation(SkeletonCorpseIIEntity entityLivingBaseIn) {
      return 0.0F;
   }
}
