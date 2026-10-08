package net.mcreator.gore.client.renderer;

import net.mcreator.gore.entity.SkeletonRightLegEntity;
import net.mcreator.gore.entity.model.SkeletonRightLegModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SkeletonRightLegRenderer extends GeoEntityRenderer<SkeletonRightLegEntity> {
   public SkeletonRightLegRenderer(Context renderManager) {
      super(renderManager, new SkeletonRightLegModel());
      this.shadowRadius = 0.0F;
   }

   public RenderType getRenderType(SkeletonRightLegEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }
}
