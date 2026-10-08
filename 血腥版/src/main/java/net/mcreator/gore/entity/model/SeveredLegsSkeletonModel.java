package net.mcreator.gore.entity.model;

import net.mcreator.gore.entity.SeveredLegsSkeletonEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class SeveredLegsSkeletonModel extends GeoModel<SeveredLegsSkeletonEntity> {
   public ResourceLocation getAnimationResource(SeveredLegsSkeletonEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "animations/skeleton_severed_legs.animation.json");
   }

   public ResourceLocation getModelResource(SeveredLegsSkeletonEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "geo/skeleton_severed_legs.geo.json");
   }

   public ResourceLocation getTextureResource(SeveredLegsSkeletonEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "textures/entities/" + entity.getTexture() + ".png");
   }
}
