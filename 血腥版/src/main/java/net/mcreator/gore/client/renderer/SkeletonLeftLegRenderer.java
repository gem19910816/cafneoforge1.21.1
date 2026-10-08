package net.mcreator.gore.client.renderer;

import net.mcreator.gore.entity.SkeletonLeftLegEntity;
import net.mcreator.gore.entity.model.SkeletonLeftLegModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SkeletonLeftLegRenderer extends GeoEntityRenderer<SkeletonLeftLegEntity> {
   public SkeletonLeftLegRenderer(Context renderManager) {
      super(renderManager, new SkeletonLeftLegModel());
      this.shadowRadius = 0.0F;
   }

   public RenderType getRenderType(SkeletonLeftLegEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }
}
