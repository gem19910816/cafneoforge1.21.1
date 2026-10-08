package net.mcreator.gore.entity.model;

import net.mcreator.gore.entity.SkeletonRightArmEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class SkeletonRightArmModel extends GeoModel<SkeletonRightArmEntity> {
   public ResourceLocation getAnimationResource(SkeletonRightArmEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "animations/skeleton_right_arm.animation.json");
   }

   public ResourceLocation getModelResource(SkeletonRightArmEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "geo/skeleton_right_arm.geo.json");
   }

   public ResourceLocation getTextureResource(SkeletonRightArmEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "textures/entities/" + entity.getTexture() + ".png");
   }
}
