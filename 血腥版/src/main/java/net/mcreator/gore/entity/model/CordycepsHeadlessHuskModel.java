package net.mcreator.gore.entity.model;

import net.mcreator.gore.entity.CordycepsHeadlessHuskEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class CordycepsHeadlessHuskModel extends GeoModel<CordycepsHeadlessHuskEntity> {
   public ResourceLocation getAnimationResource(CordycepsHeadlessHuskEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "animations/cordyceps_headless_zombie.animation.json");
   }

   public ResourceLocation getModelResource(CordycepsHeadlessHuskEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "geo/cordyceps_headless_zombie.geo.json");
   }

   public ResourceLocation getTextureResource(CordycepsHeadlessHuskEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "textures/entities/" + entity.getTexture() + ".png");
   }
}
