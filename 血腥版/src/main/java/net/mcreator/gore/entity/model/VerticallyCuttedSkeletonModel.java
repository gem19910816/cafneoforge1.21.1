package net.mcreator.gore.entity.model;

import net.mcreator.gore.entity.VerticallyCuttedSkeletonEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class VerticallyCuttedSkeletonModel extends GeoModel<VerticallyCuttedSkeletonEntity> {
   public ResourceLocation getAnimationResource(VerticallyCuttedSkeletonEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "animations/vertical_cutted_skeleton.animation.json");
   }

   public ResourceLocation getModelResource(VerticallyCuttedSkeletonEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "geo/vertical_cutted_skeleton.geo.json");
   }

   public ResourceLocation getTextureResource(VerticallyCuttedSkeletonEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "textures/entities/" + entity.getTexture() + ".png");
   }
}
