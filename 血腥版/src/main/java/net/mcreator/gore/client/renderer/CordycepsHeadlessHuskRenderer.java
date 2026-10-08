package net.mcreator.gore.client.renderer;

import net.mcreator.gore.entity.CordycepsHeadlessHuskEntity;
import net.mcreator.gore.entity.model.CordycepsHeadlessHuskModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class CordycepsHeadlessHuskRenderer extends GeoEntityRenderer<CordycepsHeadlessHuskEntity> {
   public CordycepsHeadlessHuskRenderer(Context renderManager) {
      super(renderManager, new CordycepsHeadlessHuskModel());
      this.shadowRadius = 0.5F;
   }

   public RenderType getRenderType(CordycepsHeadlessHuskEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }
}
