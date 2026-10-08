package net.mcreator.gore.entity.model;

import net.mcreator.gore.entity.HeadlessZombieEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class HeadlessZombieModel extends GeoModel<HeadlessZombieEntity> {
   public ResourceLocation getAnimationResource(HeadlessZombieEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "animations/zombie_without_head.animation.json");
   }

   public ResourceLocation getModelResource(HeadlessZombieEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "geo/zombie_without_head.geo.json");
   }

   public ResourceLocation getTextureResource(HeadlessZombieEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "textures/entities/" + entity.getTexture() + ".png");
   }
}
