package net.mcreator.gore.client.renderer;

import net.mcreator.gore.entity.FleshEaterEntity;
import net.mcreator.gore.entity.model.FleshEaterModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class FleshEaterRenderer extends GeoEntityRenderer<FleshEaterEntity> {
   public FleshEaterRenderer(Context renderManager) {
      super(renderManager, new FleshEaterModel());
      this.shadowRadius = 2.5F;
   }

   public RenderType getRenderType(FleshEaterEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }
}
