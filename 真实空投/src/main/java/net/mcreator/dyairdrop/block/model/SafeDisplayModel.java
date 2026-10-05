package net.mcreator.dyairdrop.block.model;

import net.mcreator.dyairdrop.block.display.SafeDisplayItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class SafeDisplayModel extends GeoModel<SafeDisplayItem> {
   public ResourceLocation getAnimationResource(SafeDisplayItem animatable) {
      return ResourceLocation.fromNamespaceAndPath("dyairdrop", "animations/safe.animation.json");
   }

   public ResourceLocation getModelResource(SafeDisplayItem animatable) {
      return ResourceLocation.fromNamespaceAndPath("dyairdrop", "geo/safe.geo.json");
   }

   public ResourceLocation getTextureResource(SafeDisplayItem entity) {
      return ResourceLocation.fromNamespaceAndPath("dyairdrop", "textures/block/safe.png");
   }
}
