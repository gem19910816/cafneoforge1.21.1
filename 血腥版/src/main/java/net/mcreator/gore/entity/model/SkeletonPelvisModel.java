package net.mcreator.gore.entity.model;

import net.mcreator.gore.entity.SkeletonPelvisEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class SkeletonPelvisModel extends GeoModel<SkeletonPelvisEntity> {
   public ResourceLocation getAnimationResource(SkeletonPelvisEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "animations/skeleton_body_part_ii.animation.json");
   }

   public ResourceLocation getModelResource(SkeletonPelvisEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "geo/skeleton_body_part_ii.geo.json");
   }

   public ResourceLocation getTextureResource(SkeletonPelvisEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "textures/entities/" + entity.getTexture() + ".png");
   }
}
