package net.mcreator.gore.entity.model;

import net.mcreator.gore.entity.SkeletonBackboneEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class SkeletonBackboneModel extends GeoModel<SkeletonBackboneEntity> {
   public ResourceLocation getAnimationResource(SkeletonBackboneEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "animations/skeleton_body_part_iii.animation.json");
   }

   public ResourceLocation getModelResource(SkeletonBackboneEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "geo/skeleton_body_part_iii.geo.json");
   }

   public ResourceLocation getTextureResource(SkeletonBackboneEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "textures/entities/" + entity.getTexture() + ".png");
   }
}
