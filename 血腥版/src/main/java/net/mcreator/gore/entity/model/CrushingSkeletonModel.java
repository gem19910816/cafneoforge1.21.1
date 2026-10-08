package net.mcreator.gore.entity.model;

import net.mcreator.gore.entity.CrushingSkeletonEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class CrushingSkeletonModel extends GeoModel<CrushingSkeletonEntity> {
   public ResourceLocation getAnimationResource(CrushingSkeletonEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "animations/crushing_skeleton.animation.json");
   }

   public ResourceLocation getModelResource(CrushingSkeletonEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "geo/crushing_skeleton.geo.json");
   }

   public ResourceLocation getTextureResource(CrushingSkeletonEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "textures/entities/" + entity.getTexture() + ".png");
   }
}
