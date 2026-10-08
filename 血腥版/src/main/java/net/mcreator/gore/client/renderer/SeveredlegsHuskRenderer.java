package net.mcreator.gore.client.renderer;

import net.mcreator.gore.entity.SeveredlegsHuskEntity;
import net.mcreator.gore.entity.model.SeveredlegsHuskModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SeveredlegsHuskRenderer extends GeoEntityRenderer<SeveredlegsHuskEntity> {
   public SeveredlegsHuskRenderer(Context renderManager) {
      super(renderManager, new SeveredlegsHuskModel());
      this.shadowRadius = 0.5F;
   }

   public RenderType getRenderType(SeveredlegsHuskEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }
}
