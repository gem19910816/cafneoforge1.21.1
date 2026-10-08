package net.mcreator.gore.client.renderer;

import net.mcreator.gore.entity.SeveredLegsCordycepsHuskEntity;
import net.mcreator.gore.entity.model.SeveredLegsCordycepsHuskModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SeveredLegsCordycepsHuskRenderer extends GeoEntityRenderer<SeveredLegsCordycepsHuskEntity> {
   public SeveredLegsCordycepsHuskRenderer(Context renderManager) {
      super(renderManager, new SeveredLegsCordycepsHuskModel());
      this.shadowRadius = 0.5F;
   }

   public RenderType getRenderType(SeveredLegsCordycepsHuskEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }
}
