package net.mcreator.gore.entity.model;

import net.mcreator.gore.entity.SeveredlegsHuskEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class SeveredlegsHuskModel extends GeoModel<SeveredlegsHuskEntity> {
   public ResourceLocation getAnimationResource(SeveredlegsHuskEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "animations/zombie_without_legs.animation.json");
   }

   public ResourceLocation getModelResource(SeveredlegsHuskEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "geo/zombie_without_legs.geo.json");
   }

   public ResourceLocation getTextureResource(SeveredlegsHuskEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "textures/entities/" + entity.getTexture() + ".png");
   }
}
