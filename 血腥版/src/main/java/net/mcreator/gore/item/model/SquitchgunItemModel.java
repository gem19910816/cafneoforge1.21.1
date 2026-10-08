package net.mcreator.gore.item.model;

import net.mcreator.gore.item.SquitchgunItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class SquitchgunItemModel extends GeoModel<SquitchgunItem> {
   public ResourceLocation getAnimationResource(SquitchgunItem animatable) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "animations/squitchgun.animation.json");
   }

   public ResourceLocation getModelResource(SquitchgunItem animatable) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "geo/squitchgun.geo.json");
   }

   public ResourceLocation getTextureResource(SquitchgunItem animatable) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "textures/item/squitchgun.png");
   }
}
