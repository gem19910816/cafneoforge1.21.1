package net.mcreator.dyairdrop.block.model;

import net.mcreator.dyairdrop.block.display.Safe2DisplayItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class Safe2DisplayModel extends GeoModel<Safe2DisplayItem> {
   public ResourceLocation getAnimationResource(Safe2DisplayItem animatable) {
      return ResourceLocation.fromNamespaceAndPath("dyairdrop", "animations/safe.animation.json");
   }

   public ResourceLocation getModelResource(Safe2DisplayItem animatable) {
      return ResourceLocation.fromNamespaceAndPath("dyairdrop", "geo/safe.geo.json");
   }

   public ResourceLocation getTextureResource(Safe2DisplayItem entity) {
      return ResourceLocation.fromNamespaceAndPath("dyairdrop", "textures/block/safe.png");
   }
}
