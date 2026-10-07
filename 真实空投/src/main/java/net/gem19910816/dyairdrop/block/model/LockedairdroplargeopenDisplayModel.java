package net.gem19910816.dyairdrop.block.model;

import net.gem19910816.dyairdrop.block.display.LockedairdroplargeopenDisplayItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class LockedairdroplargeopenDisplayModel extends GeoModel<LockedairdroplargeopenDisplayItem> {
   public ResourceLocation getAnimationResource(LockedairdroplargeopenDisplayItem animatable) {
      return ResourceLocation.fromNamespaceAndPath("dyairdrop", "animations/lockedairdroplargeopen.animation.json");
   }

   public ResourceLocation getModelResource(LockedairdroplargeopenDisplayItem animatable) {
      return ResourceLocation.fromNamespaceAndPath("dyairdrop", "geo/lockedairdroplargeopen.geo.json");
   }

   public ResourceLocation getTextureResource(LockedairdroplargeopenDisplayItem entity) {
      return ResourceLocation.fromNamespaceAndPath("dyairdrop", "textures/block/largeairdrop.png");
   }
}
