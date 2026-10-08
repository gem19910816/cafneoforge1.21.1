package net.mcreator.gore.client.renderer;

import net.mcreator.gore.entity.DismemberedSpiderIIEntity;
import net.mcreator.gore.entity.layer.DismemberedSpiderIILayer;
import net.mcreator.gore.entity.model.DismemberedSpiderIIModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class DismemberedSpiderIIRenderer extends GeoEntityRenderer<DismemberedSpiderIIEntity> {
   public DismemberedSpiderIIRenderer(Context renderManager) {
      super(renderManager, new DismemberedSpiderIIModel());
      this.shadowRadius = 1.0F;
      this.addRenderLayer(new DismemberedSpiderIILayer(this));
   }

   public RenderType getRenderType(DismemberedSpiderIIEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }
}
