package net.mcreator.gore.client.renderer;

import net.mcreator.gore.entity.DisarmedCordycepsHuskEntity;
import net.mcreator.gore.entity.model.DisarmedCordycepsHuskModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class DisarmedCordycepsHuskRenderer extends GeoEntityRenderer<DisarmedCordycepsHuskEntity> {
   public DisarmedCordycepsHuskRenderer(Context renderManager) {
      super(renderManager, new DisarmedCordycepsHuskModel());
      this.shadowRadius = 0.5F;
   }

   public RenderType getRenderType(DisarmedCordycepsHuskEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }
}
