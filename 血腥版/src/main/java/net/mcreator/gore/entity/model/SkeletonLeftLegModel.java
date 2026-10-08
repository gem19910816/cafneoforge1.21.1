package net.mcreator.gore.entity.model;

import net.mcreator.gore.entity.SkeletonLeftLegEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class SkeletonLeftLegModel extends GeoModel<SkeletonLeftLegEntity> {
   public ResourceLocation getAnimationResource(SkeletonLeftLegEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "animations/skeleton_left_leg.animation.json");
   }

   public ResourceLocation getModelResource(SkeletonLeftLegEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "geo/skeleton_left_leg.geo.json");
   }

   public ResourceLocation getTextureResource(SkeletonLeftLegEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "textures/entities/" + entity.getTexture() + ".png");
   }
}
