package net.mcreator.gore.entity.model;

import net.mcreator.gore.entity.CollapsingSkeletonEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class CollapsingSkeletonModel extends GeoModel<CollapsingSkeletonEntity> {
   public ResourceLocation getAnimationResource(CollapsingSkeletonEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "animations/collapsing_skeleton.animation.json");
   }

   public ResourceLocation getModelResource(CollapsingSkeletonEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "geo/collapsing_skeleton.geo.json");
   }

   public ResourceLocation getTextureResource(CollapsingSkeletonEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "textures/entities/" + entity.getTexture() + ".png");
   }
}
