package net.mcreator.gore.entity.model;

import net.mcreator.gore.entity.VerticallyCuttedSpiderEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class VerticallyCuttedSpiderModel extends GeoModel<VerticallyCuttedSpiderEntity> {
   public ResourceLocation getAnimationResource(VerticallyCuttedSpiderEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "animations/vertical_cutted_spider.animation.json");
   }

   public ResourceLocation getModelResource(VerticallyCuttedSpiderEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "geo/vertical_cutted_spider.geo.json");
   }

   public ResourceLocation getTextureResource(VerticallyCuttedSpiderEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "textures/entities/" + entity.getTexture() + ".png");
   }
}
