package net.mcreator.gore.client.renderer;

import net.mcreator.gore.entity.SeveredLegsAndOneArmCordycepsHuskEntity;
import net.mcreator.gore.entity.model.SeveredLegsAndOneArmCordycepsHuskModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SeveredLegsAndOneArmCordycepsHuskRenderer extends GeoEntityRenderer<SeveredLegsAndOneArmCordycepsHuskEntity> {
   public SeveredLegsAndOneArmCordycepsHuskRenderer(Context renderManager) {
      super(renderManager, new SeveredLegsAndOneArmCordycepsHuskModel());
      this.shadowRadius = 0.5F;
   }

   public RenderType getRenderType(
      SeveredLegsAndOneArmCordycepsHuskEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick
   ) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }
}
