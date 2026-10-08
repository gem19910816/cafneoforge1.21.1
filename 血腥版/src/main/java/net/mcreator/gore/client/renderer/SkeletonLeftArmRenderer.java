package net.mcreator.gore.client.renderer;

import net.mcreator.gore.entity.SkeletonLeftArmEntity;
import net.mcreator.gore.entity.model.SkeletonLeftArmModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SkeletonLeftArmRenderer extends GeoEntityRenderer<SkeletonLeftArmEntity> {
   public SkeletonLeftArmRenderer(Context renderManager) {
      super(renderManager, new SkeletonLeftArmModel());
      this.shadowRadius = 0.0F;
   }

   public RenderType getRenderType(SkeletonLeftArmEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }
}
