package net.mcreator.gore.entity.model;

import net.mcreator.gore.entity.SkeletonWithoutLeftArmEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class SkeletonWithoutLeftArmModel extends GeoModel<SkeletonWithoutLeftArmEntity> {
   public ResourceLocation getAnimationResource(SkeletonWithoutLeftArmEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "animations/skeleton_without_left_arm.animation.json");
   }

   public ResourceLocation getModelResource(SkeletonWithoutLeftArmEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "geo/skeleton_without_left_arm.geo.json");
   }

   public ResourceLocation getTextureResource(SkeletonWithoutLeftArmEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "textures/entities/" + entity.getTexture() + ".png");
   }
}
