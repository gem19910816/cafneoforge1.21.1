package net.mcreator.gore.client.renderer;

import net.mcreator.gore.entity.HandOfAcidEntity;
import net.mcreator.gore.entity.model.HandOfAcidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class HandOfAcidRenderer extends GeoEntityRenderer<HandOfAcidEntity> {
   public HandOfAcidRenderer(Context renderManager) {
      super(renderManager, new HandOfAcidModel());
      this.shadowRadius = 0.0F;
   }

   public RenderType getRenderType(HandOfAcidEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }
}
