package net.mcreator.gore.entity.model;

import net.mcreator.gore.entity.HeadlessHuskEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class HeadlessHuskModel extends GeoModel<HeadlessHuskEntity> {
   public ResourceLocation getAnimationResource(HeadlessHuskEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "animations/zombie_without_head.animation.json");
   }

   public ResourceLocation getModelResource(HeadlessHuskEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "geo/zombie_without_head.geo.json");
   }

   public ResourceLocation getTextureResource(HeadlessHuskEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "textures/entities/" + entity.getTexture() + ".png");
   }
}
