package net.mcreator.gore.entity.model;

import net.mcreator.gore.entity.SeveredLegsAndArmZombieEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class SeveredLegsAndArmZombieModel extends GeoModel<SeveredLegsAndArmZombieEntity> {
   public ResourceLocation getAnimationResource(SeveredLegsAndArmZombieEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "animations/zombie_without_legs_and_arm.animation.json");
   }

   public ResourceLocation getModelResource(SeveredLegsAndArmZombieEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "geo/zombie_without_legs_and_arm.geo.json");
   }

   public ResourceLocation getTextureResource(SeveredLegsAndArmZombieEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "textures/entities/" + entity.getTexture() + ".png");
   }
}
