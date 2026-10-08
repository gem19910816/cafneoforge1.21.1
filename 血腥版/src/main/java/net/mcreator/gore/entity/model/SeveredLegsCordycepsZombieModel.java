package net.mcreator.gore.entity.model;

import net.mcreator.gore.entity.SeveredLegsCordycepsZombieEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class SeveredLegsCordycepsZombieModel extends GeoModel<SeveredLegsCordycepsZombieEntity> {
   public ResourceLocation getAnimationResource(SeveredLegsCordycepsZombieEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "animations/cordyceps_zombie_severed_legs.animation.json");
   }

   public ResourceLocation getModelResource(SeveredLegsCordycepsZombieEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "geo/cordyceps_zombie_severed_legs.geo.json");
   }

   public ResourceLocation getTextureResource(SeveredLegsCordycepsZombieEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "textures/entities/" + entity.getTexture() + ".png");
   }
}
