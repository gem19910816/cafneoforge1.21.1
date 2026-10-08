package net.mcreator.gore.entity.model;

import net.mcreator.gore.entity.SeveredLegsAndArmHuskEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class SeveredLegsAndArmHuskModel extends GeoModel<SeveredLegsAndArmHuskEntity> {
   public ResourceLocation getAnimationResource(SeveredLegsAndArmHuskEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "animations/zombie_without_legs_and_arm.animation.json");
   }

   public ResourceLocation getModelResource(SeveredLegsAndArmHuskEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "geo/zombie_without_legs_and_arm.geo.json");
   }

   public ResourceLocation getTextureResource(SeveredLegsAndArmHuskEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "textures/entities/" + entity.getTexture() + ".png");
   }
}
