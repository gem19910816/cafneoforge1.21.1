package net.mcreator.gore.client.renderer;

import net.mcreator.gore.entity.VerticalcuttedHuskEntity;
import net.mcreator.gore.entity.model.VerticalcuttedHuskModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class VerticalcuttedHuskRenderer extends GeoEntityRenderer<VerticalcuttedHuskEntity> {
   public VerticalcuttedHuskRenderer(Context renderManager) {
      super(renderManager, new VerticalcuttedHuskModel());
      this.shadowRadius = 0.5F;
   }

   public RenderType getRenderType(VerticalcuttedHuskEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }
}
