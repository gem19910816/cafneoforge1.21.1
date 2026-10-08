package net.mcreator.gore.entity.model;

import net.mcreator.gore.entity.SkeletonCorpseWithoutRightArmEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class SkeletonCorpseWithoutRightArmModel extends GeoModel<SkeletonCorpseWithoutRightArmEntity> {
   public ResourceLocation getAnimationResource(SkeletonCorpseWithoutRightArmEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "animations/skeleton_corpse_without_right_arm.animation.json");
   }

   public ResourceLocation getModelResource(SkeletonCorpseWithoutRightArmEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "geo/skeleton_corpse_without_right_arm.geo.json");
   }

   public ResourceLocation getTextureResource(SkeletonCorpseWithoutRightArmEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "textures/entities/" + entity.getTexture() + ".png");
   }
}
