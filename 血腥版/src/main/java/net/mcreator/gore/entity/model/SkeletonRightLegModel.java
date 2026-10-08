package net.mcreator.gore.entity.model;

import net.mcreator.gore.entity.SkeletonRightLegEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class SkeletonRightLegModel extends GeoModel<SkeletonRightLegEntity> {
   public ResourceLocation getAnimationResource(SkeletonRightLegEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "animations/skeleton_right_leg.animation.json");
   }

   public ResourceLocation getModelResource(SkeletonRightLegEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "geo/skeleton_right_leg.geo.json");
   }

   public ResourceLocation getTextureResource(SkeletonRightLegEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "textures/entities/" + entity.getTexture() + ".png");
   }
}
