package net.mcreator.gore.entity.model;

import net.mcreator.gore.entity.LuxEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class LuxModel extends GeoModel<LuxEntity> {
   public ResourceLocation getAnimationResource(LuxEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "animations/lux.animation.json");
   }

   public ResourceLocation getModelResource(LuxEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "geo/lux.geo.json");
   }

   public ResourceLocation getTextureResource(LuxEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "textures/entities/" + entity.getTexture() + ".png");
   }
}
