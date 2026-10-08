package net.mcreator.gore.entity.model;

import net.mcreator.gore.entity.SeveredlegsZombieEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class SeveredlegsZombieModel extends GeoModel<SeveredlegsZombieEntity> {
   public ResourceLocation getAnimationResource(SeveredlegsZombieEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "animations/zombie_without_legs.animation.json");
   }

   public ResourceLocation getModelResource(SeveredlegsZombieEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "geo/zombie_without_legs.geo.json");
   }

   public ResourceLocation getTextureResource(SeveredlegsZombieEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "textures/entities/" + entity.getTexture() + ".png");
   }
}
