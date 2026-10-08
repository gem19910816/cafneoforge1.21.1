package net.mcreator.gore.entity.model;

import net.mcreator.gore.entity.SeveredLegsAndOneArmCordycepsZombieEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class SeveredLegsAndOneArmCordycepsZombieModel extends GeoModel<SeveredLegsAndOneArmCordycepsZombieEntity> {
   public ResourceLocation getAnimationResource(SeveredLegsAndOneArmCordycepsZombieEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "animations/cordyceps_severed_legs_without_an_arm_zombie.animation.json");
   }

   public ResourceLocation getModelResource(SeveredLegsAndOneArmCordycepsZombieEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "geo/cordyceps_severed_legs_without_an_arm_zombie.geo.json");
   }

   public ResourceLocation getTextureResource(SeveredLegsAndOneArmCordycepsZombieEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "textures/entities/" + entity.getTexture() + ".png");
   }
}
