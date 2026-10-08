package net.mcreator.gore.client.renderer;

import net.mcreator.gore.entity.CollapsingSkeletonEntity;
import net.mcreator.gore.entity.model.CollapsingSkeletonModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class CollapsingSkeletonRenderer extends GeoEntityRenderer<CollapsingSkeletonEntity> {
   public CollapsingSkeletonRenderer(Context renderManager) {
      super(renderManager, new CollapsingSkeletonModel());
      this.shadowRadius = 0.5F;
   }

   public RenderType getRenderType(CollapsingSkeletonEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }
}
