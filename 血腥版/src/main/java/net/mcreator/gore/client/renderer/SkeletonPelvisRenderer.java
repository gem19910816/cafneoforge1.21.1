package net.mcreator.gore.client.renderer;

import net.mcreator.gore.entity.SkeletonPelvisEntity;
import net.mcreator.gore.entity.model.SkeletonPelvisModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SkeletonPelvisRenderer extends GeoEntityRenderer<SkeletonPelvisEntity> {
   public SkeletonPelvisRenderer(Context renderManager) {
      super(renderManager, new SkeletonPelvisModel());
      this.shadowRadius = 0.0F;
   }

   public RenderType getRenderType(SkeletonPelvisEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }
}
