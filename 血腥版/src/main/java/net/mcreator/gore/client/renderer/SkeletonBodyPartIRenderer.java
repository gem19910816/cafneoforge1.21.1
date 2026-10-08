package net.mcreator.gore.client.renderer;

import net.mcreator.gore.entity.SkeletonBodyPartIEntity;
import net.mcreator.gore.entity.model.SkeletonBodyPartIModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SkeletonBodyPartIRenderer extends GeoEntityRenderer<SkeletonBodyPartIEntity> {
   public SkeletonBodyPartIRenderer(Context renderManager) {
      super(renderManager, new SkeletonBodyPartIModel());
      this.shadowRadius = 0.0F;
   }

   public RenderType getRenderType(SkeletonBodyPartIEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }
}
