package net.mcreator.gore.entity.model;

import net.mcreator.gore.entity.HorizontallyCuttedSpiderEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class HorizontallyCuttedSpiderModel extends GeoModel<HorizontallyCuttedSpiderEntity> {
   public ResourceLocation getAnimationResource(HorizontallyCuttedSpiderEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "animations/horizontal_cutted_spider.animation.json");
   }

   public ResourceLocation getModelResource(HorizontallyCuttedSpiderEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "geo/horizontal_cutted_spider.geo.json");
   }

   public ResourceLocation getTextureResource(HorizontallyCuttedSpiderEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "textures/entities/" + entity.getTexture() + ".png");
   }
}
