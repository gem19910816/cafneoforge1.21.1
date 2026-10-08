package net.mcreator.gore.entity.model;

import net.mcreator.gore.entity.DismemberedSpiderIEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class DismemberedSpiderIModel extends GeoModel<DismemberedSpiderIEntity> {
   public ResourceLocation getAnimationResource(DismemberedSpiderIEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "animations/dismembered_spider_1.animation.json");
   }

   public ResourceLocation getModelResource(DismemberedSpiderIEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "geo/dismembered_spider_1.geo.json");
   }

   public ResourceLocation getTextureResource(DismemberedSpiderIEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "textures/entities/" + entity.getTexture() + ".png");
   }
}
