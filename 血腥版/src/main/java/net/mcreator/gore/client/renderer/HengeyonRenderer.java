package net.mcreator.gore.client.renderer;

import net.mcreator.gore.entity.HengeyonEntity;
import net.mcreator.gore.entity.model.HengeyonModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class HengeyonRenderer extends GeoEntityRenderer<HengeyonEntity> {
   public HengeyonRenderer(Context renderManager) {
      super(renderManager, new HengeyonModel());
      this.shadowRadius = 0.0F;
   }

   public RenderType getRenderType(HengeyonEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }
}
