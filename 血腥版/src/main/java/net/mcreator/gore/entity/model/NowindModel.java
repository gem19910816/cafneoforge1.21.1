package net.mcreator.gore.entity.model;

import net.mcreator.gore.entity.NowindEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class NowindModel extends GeoModel<NowindEntity> {
   public ResourceLocation getAnimationResource(NowindEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "animations/nowind.animation.json");
   }

   public ResourceLocation getModelResource(NowindEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "geo/nowind.geo.json");
   }

   public ResourceLocation getTextureResource(NowindEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "textures/entities/" + entity.getTexture() + ".png");
   }
}
