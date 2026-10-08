package net.mcreator.gore.client.renderer;

import net.mcreator.gore.entity.SkeletonBackboneEntity;
import net.mcreator.gore.entity.model.SkeletonBackboneModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SkeletonBackboneRenderer extends GeoEntityRenderer<SkeletonBackboneEntity> {
   public SkeletonBackboneRenderer(Context renderManager) {
      super(renderManager, new SkeletonBackboneModel());
      this.shadowRadius = 0.0F;
   }

   public RenderType getRenderType(SkeletonBackboneEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }
}
