package net.mcreator.gore.entity.model;

import net.mcreator.gore.entity.SkeletonHeadEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class SkeletonHeadModel extends GeoModel<SkeletonHeadEntity> {
   public ResourceLocation getAnimationResource(SkeletonHeadEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "animations/skeleton_head.animation.json");
   }

   public ResourceLocation getModelResource(SkeletonHeadEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "geo/skeleton_head.geo.json");
   }

   public ResourceLocation getTextureResource(SkeletonHeadEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "textures/entities/" + entity.getTexture() + ".png");
   }
}
