package net.mcreator.gore.entity.model;

import net.mcreator.gore.entity.CrushedHuskEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class CrushedHuskModel extends GeoModel<CrushedHuskEntity> {
   public ResourceLocation getAnimationResource(CrushedHuskEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "animations/multiple_cutted_zombie.animation.json");
   }

   public ResourceLocation getModelResource(CrushedHuskEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "geo/multiple_cutted_zombie.geo.json");
   }

   public ResourceLocation getTextureResource(CrushedHuskEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "textures/entities/" + entity.getTexture() + ".png");
   }
}
