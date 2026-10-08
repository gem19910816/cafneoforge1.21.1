package net.mcreator.gore.client.renderer;

import net.mcreator.gore.entity.SeveredLegsAndArmHuskEntity;
import net.mcreator.gore.entity.model.SeveredLegsAndArmHuskModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SeveredLegsAndArmHuskRenderer extends GeoEntityRenderer<SeveredLegsAndArmHuskEntity> {
   public SeveredLegsAndArmHuskRenderer(Context renderManager) {
      super(renderManager, new SeveredLegsAndArmHuskModel());
      this.shadowRadius = 0.5F;
   }

   public RenderType getRenderType(SeveredLegsAndArmHuskEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }
}
