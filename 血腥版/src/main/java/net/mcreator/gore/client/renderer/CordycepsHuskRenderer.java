package net.mcreator.gore.client.renderer;

import net.mcreator.gore.entity.CordycepsHuskEntity;
import net.mcreator.gore.entity.model.CordycepsHuskModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class CordycepsHuskRenderer extends GeoEntityRenderer<CordycepsHuskEntity> {
   public CordycepsHuskRenderer(Context renderManager) {
      super(renderManager, new CordycepsHuskModel());
      this.shadowRadius = 0.5F;
   }

   public RenderType getRenderType(CordycepsHuskEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }
}
