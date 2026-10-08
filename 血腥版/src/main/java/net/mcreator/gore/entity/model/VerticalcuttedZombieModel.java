package net.mcreator.gore.entity.model;

import net.mcreator.gore.entity.VerticalcuttedZombieEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class VerticalcuttedZombieModel extends GeoModel<VerticalcuttedZombieEntity> {
   public ResourceLocation getAnimationResource(VerticalcuttedZombieEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "animations/vertical_cutted_zombie.animation.json");
   }

   public ResourceLocation getModelResource(VerticalcuttedZombieEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "geo/vertical_cutted_zombie.geo.json");
   }

   public ResourceLocation getTextureResource(VerticalcuttedZombieEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "textures/entities/" + entity.getTexture() + ".png");
   }
}
