package net.mcreator.gore.entity.model;

import net.mcreator.gore.entity.SeveredLegsCordycepsHuskEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class SeveredLegsCordycepsHuskModel extends GeoModel<SeveredLegsCordycepsHuskEntity> {
   public ResourceLocation getAnimationResource(SeveredLegsCordycepsHuskEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "animations/cordyceps_zombie_severed_legs.animation.json");
   }

   public ResourceLocation getModelResource(SeveredLegsCordycepsHuskEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "geo/cordyceps_zombie_severed_legs.geo.json");
   }

   public ResourceLocation getTextureResource(SeveredLegsCordycepsHuskEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "textures/entities/" + entity.getTexture() + ".png");
   }
}
