package net.mcreator.gore.client.renderer;

import net.mcreator.gore.entity.SkeletonRightArmEntity;
import net.mcreator.gore.entity.model.SkeletonRightArmModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SkeletonRightArmRenderer extends GeoEntityRenderer<SkeletonRightArmEntity> {
   public SkeletonRightArmRenderer(Context renderManager) {
      super(renderManager, new SkeletonRightArmModel());
      this.shadowRadius = 0.0F;
   }

   public RenderType getRenderType(SkeletonRightArmEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }
}
