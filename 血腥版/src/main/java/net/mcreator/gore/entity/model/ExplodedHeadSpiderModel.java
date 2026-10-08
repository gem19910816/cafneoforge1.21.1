package net.mcreator.gore.entity.model;

import net.mcreator.gore.entity.ExplodedHeadSpiderEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ExplodedHeadSpiderModel extends GeoModel<ExplodedHeadSpiderEntity> {
   public ResourceLocation getAnimationResource(ExplodedHeadSpiderEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "animations/spider_without_head.animation.json");
   }

   public ResourceLocation getModelResource(ExplodedHeadSpiderEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "geo/spider_without_head.geo.json");
   }

   public ResourceLocation getTextureResource(ExplodedHeadSpiderEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "textures/entities/" + entity.getTexture() + ".png");
   }
}
