package net.mcreator.gore.entity.model;

import net.mcreator.gore.entity.SkeletonCorpseEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class SkeletonCorpseModel extends GeoModel<SkeletonCorpseEntity> {
   public ResourceLocation getAnimationResource(SkeletonCorpseEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "animations/skeleton_corpse.animation.json");
   }

   public ResourceLocation getModelResource(SkeletonCorpseEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "geo/skeleton_corpse.geo.json");
   }

   public ResourceLocation getTextureResource(SkeletonCorpseEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "textures/entities/" + entity.getTexture() + ".png");
   }
}
