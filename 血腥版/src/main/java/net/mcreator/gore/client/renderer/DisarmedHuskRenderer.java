package net.mcreator.gore.client.renderer;

import net.mcreator.gore.entity.DisarmedHuskEntity;
import net.mcreator.gore.entity.model.DisarmedHuskModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class DisarmedHuskRenderer extends GeoEntityRenderer<DisarmedHuskEntity> {
   public DisarmedHuskRenderer(Context renderManager) {
      super(renderManager, new DisarmedHuskModel());
      this.shadowRadius = 0.5F;
   }

   public RenderType getRenderType(DisarmedHuskEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }
}
