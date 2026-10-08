package net.mcreator.gore.entity.model;

import net.mcreator.gore.entity.HorizontallycuttedZombieEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class HorizontallycuttedZombieModel extends GeoModel<HorizontallycuttedZombieEntity> {
   public ResourceLocation getAnimationResource(HorizontallycuttedZombieEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "animations/cutted_zombie.animation.json");
   }

   public ResourceLocation getModelResource(HorizontallycuttedZombieEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "geo/cutted_zombie.geo.json");
   }

   public ResourceLocation getTextureResource(HorizontallycuttedZombieEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "textures/entities/" + entity.getTexture() + ".png");
   }
}
