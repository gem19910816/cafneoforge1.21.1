package net.mcreator.gore.client.renderer;

import net.mcreator.gore.entity.ExplodedHeadSpiderEntity;
import net.mcreator.gore.entity.layer.ExplodedHeadSpiderLayer;
import net.mcreator.gore.entity.model.ExplodedHeadSpiderModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class ExplodedHeadSpiderRenderer extends GeoEntityRenderer<ExplodedHeadSpiderEntity> {
   public ExplodedHeadSpiderRenderer(Context renderManager) {
      super(renderManager, new ExplodedHeadSpiderModel());
      this.shadowRadius = 1.0F;
      this.addRenderLayer(new ExplodedHeadSpiderLayer(this));
   }

   public RenderType getRenderType(ExplodedHeadSpiderEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }
}
