package net.mcreator.dyairdrop.block.model;

import net.mcreator.dyairdrop.block.display.AirdropmedicalDisplayItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class AirdropmedicalDisplayModel extends GeoModel<AirdropmedicalDisplayItem> {
   public AirdropmedicalDisplayModel() {
   }

   public ResourceLocation getAnimationResource(AirdropmedicalDisplayItem animatable) {
      return ResourceLocation.fromNamespaceAndPath("dyairdrop", "animations/grechest.animation.json");
   }

   public ResourceLocation getModelResource(AirdropmedicalDisplayItem animatable) {
      return ResourceLocation.fromNamespaceAndPath("dyairdrop", "geo/grechest.geo.json");
   }

   public ResourceLocation getTextureResource(AirdropmedicalDisplayItem entity) {
      return ResourceLocation.fromNamespaceAndPath("dyairdrop", "textures/block/gre.png");
   }
}
