package net.mcreator.gore.client.renderer;

import net.mcreator.gore.entity.HeadlessHuskEntity;
import net.mcreator.gore.entity.model.HeadlessHuskModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class HeadlessHuskRenderer extends GeoEntityRenderer<HeadlessHuskEntity> {
   public HeadlessHuskRenderer(Context renderManager) {
      super(renderManager, new HeadlessHuskModel());
      this.shadowRadius = 0.5F;
   }

   public RenderType getRenderType(HeadlessHuskEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }
}
