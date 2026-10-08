package net.mcreator.gore.entity.model;

import net.mcreator.gore.entity.SkeletonLeftArmEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class SkeletonLeftArmModel extends GeoModel<SkeletonLeftArmEntity> {
   public ResourceLocation getAnimationResource(SkeletonLeftArmEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "animations/skeleton_left_arm.animation.json");
   }

   public ResourceLocation getModelResource(SkeletonLeftArmEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "geo/skeleton_left_arm.geo.json");
   }

   public ResourceLocation getTextureResource(SkeletonLeftArmEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "textures/entities/" + entity.getTexture() + ".png");
   }
}
