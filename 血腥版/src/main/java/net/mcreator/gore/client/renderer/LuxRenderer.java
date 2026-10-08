package net.mcreator.gore.client.renderer;

import net.mcreator.gore.entity.LuxEntity;
import net.mcreator.gore.entity.layer.LuxLayer;
import net.mcreator.gore.entity.model.LuxModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class LuxRenderer extends GeoEntityRenderer<LuxEntity> {
   public LuxRenderer(Context renderManager) {
      super(renderManager, new LuxModel());
      this.shadowRadius = 0.0F;
      this.addRenderLayer(new LuxLayer(this));
   }

   public RenderType getRenderType(LuxEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }
}
