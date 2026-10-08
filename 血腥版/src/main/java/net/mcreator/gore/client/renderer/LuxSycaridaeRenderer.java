package net.mcreator.gore.client.renderer;

import net.mcreator.gore.entity.LuxSycaridaeEntity;
import net.mcreator.gore.entity.layer.LuxSycaridaeLayer;
import net.mcreator.gore.entity.model.LuxSycaridaeModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class LuxSycaridaeRenderer extends GeoEntityRenderer<LuxSycaridaeEntity> {
   public LuxSycaridaeRenderer(Context renderManager) {
      super(renderManager, new LuxSycaridaeModel());
      this.shadowRadius = 0.0F;
      this.addRenderLayer(new LuxSycaridaeLayer(this));
   }

   public RenderType getRenderType(LuxSycaridaeEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }
}
