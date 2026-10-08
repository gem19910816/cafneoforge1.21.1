package net.mcreator.gore.entity.model;

import net.mcreator.gore.entity.DismemberedSpiderIIEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class DismemberedSpiderIIModel extends GeoModel<DismemberedSpiderIIEntity> {
   public ResourceLocation getAnimationResource(DismemberedSpiderIIEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "animations/dismembered_spider_2.animation.json");
   }

   public ResourceLocation getModelResource(DismemberedSpiderIIEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "geo/dismembered_spider_2.geo.json");
   }

   public ResourceLocation getTextureResource(DismemberedSpiderIIEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "textures/entities/" + entity.getTexture() + ".png");
   }
}
