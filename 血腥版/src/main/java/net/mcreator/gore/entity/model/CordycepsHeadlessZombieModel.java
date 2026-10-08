package net.mcreator.gore.entity.model;

import net.mcreator.gore.entity.CordycepsHeadlessZombieEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class CordycepsHeadlessZombieModel extends GeoModel<CordycepsHeadlessZombieEntity> {
   public ResourceLocation getAnimationResource(CordycepsHeadlessZombieEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "animations/cordyceps_headless_zombie.animation.json");
   }

   public ResourceLocation getModelResource(CordycepsHeadlessZombieEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "geo/cordyceps_headless_zombie.geo.json");
   }

   public ResourceLocation getTextureResource(CordycepsHeadlessZombieEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "textures/entities/" + entity.getTexture() + ".png");
   }
}
