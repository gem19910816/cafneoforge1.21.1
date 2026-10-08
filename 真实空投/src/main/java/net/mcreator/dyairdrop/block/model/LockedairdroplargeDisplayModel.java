package net.mcreator.dyairdrop.block.model;

import net.mcreator.dyairdrop.block.display.LockedairdroplargeDisplayItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class LockedairdroplargeDisplayModel extends GeoModel<LockedairdroplargeDisplayItem> {
   public LockedairdroplargeDisplayModel() {
   }

   public ResourceLocation getAnimationResource(LockedairdroplargeDisplayItem animatable) {
      return ResourceLocation.fromNamespaceAndPath("dyairdrop", "animations/airdroplarge.animation.json");
   }

   public ResourceLocation getModelResource(LockedairdroplargeDisplayItem animatable) {
      return ResourceLocation.fromNamespaceAndPath("dyairdrop", "geo/airdroplarge.geo.json");
   }

   public ResourceLocation getTextureResource(LockedairdroplargeDisplayItem entity) {
      return ResourceLocation.fromNamespaceAndPath("dyairdrop", "textures/block/largeairdrop.png");
   }
}
