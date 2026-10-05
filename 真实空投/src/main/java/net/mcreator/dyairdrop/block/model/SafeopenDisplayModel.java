package net.mcreator.dyairdrop.block.model;

import net.mcreator.dyairdrop.block.display.SafeopenDisplayItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class SafeopenDisplayModel extends GeoModel<SafeopenDisplayItem> {
   public ResourceLocation getAnimationResource(SafeopenDisplayItem animatable) {
      return ResourceLocation.fromNamespaceAndPath("dyairdrop", "animations/safeopen.animation.json");
   }

   public ResourceLocation getModelResource(SafeopenDisplayItem animatable) {
      return ResourceLocation.fromNamespaceAndPath("dyairdrop", "geo/safeopen.geo.json");
   }

   public ResourceLocation getTextureResource(SafeopenDisplayItem entity) {
      return ResourceLocation.fromNamespaceAndPath("dyairdrop", "textures/block/safe.png");
   }
}
