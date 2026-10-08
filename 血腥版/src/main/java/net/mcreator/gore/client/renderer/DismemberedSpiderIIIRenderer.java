package net.mcreator.gore.client.renderer;

import net.mcreator.gore.entity.DismemberedSpiderIIIEntity;
import net.minecraft.client.model.SpiderModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;

public class DismemberedSpiderIIIRenderer extends MobRenderer<DismemberedSpiderIIIEntity, SpiderModel<DismemberedSpiderIIIEntity>> {
   public DismemberedSpiderIIIRenderer(Context context) {
      super(context, new SpiderModel(context.bakeLayer(ModelLayers.SPIDER)), 1.0F);
   }

   public ResourceLocation getTextureLocation(DismemberedSpiderIIIEntity entity) {
      return ResourceLocation.parse("gore_edition:textures/entities/dismembered_spider_3.png");
   }
}
