package net.mcreator.gore.client.renderer;

import net.mcreator.gore.entity.HorizontallyCuttedSpiderEntity;
import net.mcreator.gore.entity.layer.HorizontallyCuttedSpiderLayer;
import net.mcreator.gore.entity.model.HorizontallyCuttedSpiderModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class HorizontallyCuttedSpiderRenderer extends GeoEntityRenderer<HorizontallyCuttedSpiderEntity> {
   public HorizontallyCuttedSpiderRenderer(Context renderManager) {
      super(renderManager, new HorizontallyCuttedSpiderModel());
      this.shadowRadius = 1.0F;
      this.addRenderLayer(new HorizontallyCuttedSpiderLayer(this));
   }

   public RenderType getRenderType(HorizontallyCuttedSpiderEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }
}
