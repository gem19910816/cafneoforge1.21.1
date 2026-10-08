package net.mcreator.gore.client.renderer;

import net.mcreator.gore.entity.VerticallyCuttedSkeletonEntity;
import net.mcreator.gore.entity.model.VerticallyCuttedSkeletonModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class VerticallyCuttedSkeletonRenderer extends GeoEntityRenderer<VerticallyCuttedSkeletonEntity> {
   public VerticallyCuttedSkeletonRenderer(Context renderManager) {
      super(renderManager, new VerticallyCuttedSkeletonModel());
      this.shadowRadius = 0.5F;
   }

   public RenderType getRenderType(VerticallyCuttedSkeletonEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }
}
