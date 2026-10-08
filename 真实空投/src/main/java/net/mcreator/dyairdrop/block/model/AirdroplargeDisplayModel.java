package net.mcreator.dyairdrop.block.model;

import net.mcreator.dyairdrop.block.display.AirdroplargeDisplayItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class AirdroplargeDisplayModel extends GeoModel<AirdroplargeDisplayItem> {
   public AirdroplargeDisplayModel() {
   }

   public ResourceLocation getAnimationResource(AirdroplargeDisplayItem animatable) {
      return ResourceLocation.fromNamespaceAndPath("dyairdrop", "animations/airdroplarge.animation.json");
   }

   public ResourceLocation getModelResource(AirdroplargeDisplayItem animatable) {
      return ResourceLocation.fromNamespaceAndPath("dyairdrop", "geo/airdroplarge.geo.json");
   }

   public ResourceLocation getTextureResource(AirdroplargeDisplayItem entity) {
      return ResourceLocation.fromNamespaceAndPath("dyairdrop", "textures/block/largeairdrop.png");
   }
}
