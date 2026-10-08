package net.mcreator.gore.entity.model;

import net.mcreator.gore.entity.HorizontallyCuttedHuskEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class HorizontallyCuttedHuskModel extends GeoModel<HorizontallyCuttedHuskEntity> {
   public ResourceLocation getAnimationResource(HorizontallyCuttedHuskEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "animations/cutted_zombie.animation.json");
   }

   public ResourceLocation getModelResource(HorizontallyCuttedHuskEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "geo/cutted_zombie.geo.json");
   }

   public ResourceLocation getTextureResource(HorizontallyCuttedHuskEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "textures/entities/" + entity.getTexture() + ".png");
   }
}
