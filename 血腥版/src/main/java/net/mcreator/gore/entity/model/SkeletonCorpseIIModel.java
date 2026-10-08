package net.mcreator.gore.entity.model;

import net.mcreator.gore.entity.SkeletonCorpseIIEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class SkeletonCorpseIIModel extends GeoModel<SkeletonCorpseIIEntity> {
   public ResourceLocation getAnimationResource(SkeletonCorpseIIEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "animations/skeleton_corpse_disarmed_and_no_head.animation.json");
   }

   public ResourceLocation getModelResource(SkeletonCorpseIIEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "geo/skeleton_corpse_disarmed_and_no_head.geo.json");
   }

   public ResourceLocation getTextureResource(SkeletonCorpseIIEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "textures/entities/" + entity.getTexture() + ".png");
   }
}
