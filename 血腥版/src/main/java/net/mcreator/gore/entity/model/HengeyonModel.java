package net.mcreator.gore.entity.model;

import net.mcreator.gore.entity.HengeyonEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class HengeyonModel extends GeoModel<HengeyonEntity> {
   public ResourceLocation getAnimationResource(HengeyonEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "animations/hengeyon.animation.json");
   }

   public ResourceLocation getModelResource(HengeyonEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "geo/hengeyon.geo.json");
   }

   public ResourceLocation getTextureResource(HengeyonEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "textures/entities/" + entity.getTexture() + ".png");
   }
}
