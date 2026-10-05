package net.mcreator.dyairdrop.block.model;

import net.mcreator.dyairdrop.block.display.LockedairdropmedicalopenDisplayItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class LockedairdropmedicalopenDisplayModel extends GeoModel<LockedairdropmedicalopenDisplayItem> {
   public ResourceLocation getAnimationResource(LockedairdropmedicalopenDisplayItem animatable) {
      return ResourceLocation.fromNamespaceAndPath("dyairdrop", "animations/grechest.animation.json");
   }

   public ResourceLocation getModelResource(LockedairdropmedicalopenDisplayItem animatable) {
      return ResourceLocation.fromNamespaceAndPath("dyairdrop", "geo/grechest.geo.json");
   }

   public ResourceLocation getTextureResource(LockedairdropmedicalopenDisplayItem entity) {
      return ResourceLocation.fromNamespaceAndPath("dyairdrop", "textures/block/gre.png");
   }
}
