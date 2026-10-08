package net.mcreator.gore.client.renderer;

import net.mcreator.gore.entity.SkeletonWithoutArmEntity;
import net.mcreator.gore.entity.model.SkeletonWithoutArmModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SkeletonWithoutArmRenderer extends GeoEntityRenderer<SkeletonWithoutArmEntity> {
   public SkeletonWithoutArmRenderer(Context renderManager) {
      super(renderManager, new SkeletonWithoutArmModel());
      this.shadowRadius = 0.5F;
   }

   public RenderType getRenderType(SkeletonWithoutArmEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }
}
