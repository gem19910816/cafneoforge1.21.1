package net.mcreator.gore.entity.model;

import net.mcreator.gore.entity.SkeletonBodyPartIEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class SkeletonBodyPartIModel extends GeoModel<SkeletonBodyPartIEntity> {
   public ResourceLocation getAnimationResource(SkeletonBodyPartIEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "animations/skeleton_body_part_i.animation.json");
   }

   public ResourceLocation getModelResource(SkeletonBodyPartIEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "geo/skeleton_body_part_i.geo.json");
   }

   public ResourceLocation getTextureResource(SkeletonBodyPartIEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "textures/entities/" + entity.getTexture() + ".png");
   }
}
