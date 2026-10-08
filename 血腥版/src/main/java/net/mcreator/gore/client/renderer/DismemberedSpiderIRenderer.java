package net.mcreator.gore.client.renderer;

import net.mcreator.gore.entity.DismemberedSpiderIEntity;
import net.mcreator.gore.entity.layer.DismemberedSpiderILayer;
import net.mcreator.gore.entity.model.DismemberedSpiderIModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class DismemberedSpiderIRenderer extends GeoEntityRenderer<DismemberedSpiderIEntity> {
   public DismemberedSpiderIRenderer(Context renderManager) {
      super(renderManager, new DismemberedSpiderIModel());
      this.shadowRadius = 1.0F;
      this.addRenderLayer(new DismemberedSpiderILayer(this));
   }

   public RenderType getRenderType(DismemberedSpiderIEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }
}
