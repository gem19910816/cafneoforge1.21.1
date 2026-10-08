package net.mcreator.gore.entity.model;

import net.mcreator.gore.entity.SkeletonWithoutArmEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class SkeletonWithoutArmModel extends GeoModel<SkeletonWithoutArmEntity> {
   public ResourceLocation getAnimationResource(SkeletonWithoutArmEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "animations/skeleton_without_arm.animation.json");
   }

   public ResourceLocation getModelResource(SkeletonWithoutArmEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "geo/skeleton_without_arm.geo.json");
   }

   public ResourceLocation getTextureResource(SkeletonWithoutArmEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "textures/entities/" + entity.getTexture() + ".png");
   }
}
