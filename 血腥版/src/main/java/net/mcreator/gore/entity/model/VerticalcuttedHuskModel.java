package net.mcreator.gore.entity.model;

import net.mcreator.gore.entity.VerticalcuttedHuskEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class VerticalcuttedHuskModel extends GeoModel<VerticalcuttedHuskEntity> {
   public ResourceLocation getAnimationResource(VerticalcuttedHuskEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "animations/vertical_cutted_zombie.animation.json");
   }

   public ResourceLocation getModelResource(VerticalcuttedHuskEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "geo/vertical_cutted_zombie.geo.json");
   }

   public ResourceLocation getTextureResource(VerticalcuttedHuskEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "textures/entities/" + entity.getTexture() + ".png");
   }
}
