package net.mcreator.gore.entity.model;

import net.mcreator.gore.entity.FleshEaterEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class FleshEaterModel extends GeoModel<FleshEaterEntity> {
   public ResourceLocation getAnimationResource(FleshEaterEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "animations/flesh_eater.animation.json");
   }

   public ResourceLocation getModelResource(FleshEaterEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "geo/flesh_eater.geo.json");
   }

   public ResourceLocation getTextureResource(FleshEaterEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "textures/entities/" + entity.getTexture() + ".png");
   }
}
