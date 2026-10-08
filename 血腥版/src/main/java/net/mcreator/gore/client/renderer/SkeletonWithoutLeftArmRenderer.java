package net.mcreator.gore.client.renderer;

import net.mcreator.gore.entity.SkeletonWithoutLeftArmEntity;
import net.mcreator.gore.entity.model.SkeletonWithoutLeftArmModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SkeletonWithoutLeftArmRenderer extends GeoEntityRenderer<SkeletonWithoutLeftArmEntity> {
   public SkeletonWithoutLeftArmRenderer(Context renderManager) {
      super(renderManager, new SkeletonWithoutLeftArmModel());
      this.shadowRadius = 0.5F;
   }

   public RenderType getRenderType(SkeletonWithoutLeftArmEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }
}
