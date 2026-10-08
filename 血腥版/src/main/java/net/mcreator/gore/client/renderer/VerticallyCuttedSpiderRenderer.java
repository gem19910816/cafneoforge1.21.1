package net.mcreator.gore.client.renderer;

import net.mcreator.gore.entity.VerticallyCuttedSpiderEntity;
import net.mcreator.gore.entity.layer.VerticallyCuttedSpiderLayer;
import net.mcreator.gore.entity.model.VerticallyCuttedSpiderModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class VerticallyCuttedSpiderRenderer extends GeoEntityRenderer<VerticallyCuttedSpiderEntity> {
   public VerticallyCuttedSpiderRenderer(Context renderManager) {
      super(renderManager, new VerticallyCuttedSpiderModel());
      this.shadowRadius = 1.0F;
      this.addRenderLayer(new VerticallyCuttedSpiderLayer(this));
   }

   public RenderType getRenderType(VerticallyCuttedSpiderEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }
}
