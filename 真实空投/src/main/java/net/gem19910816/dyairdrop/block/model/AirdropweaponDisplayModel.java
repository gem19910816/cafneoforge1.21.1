package net.gem19910816.dyairdrop.block.model;

import net.gem19910816.dyairdrop.block.display.AirdropweaponDisplayItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class AirdropweaponDisplayModel extends GeoModel<AirdropweaponDisplayItem> {
   public ResourceLocation getAnimationResource(AirdropweaponDisplayItem animatable) {
      return ResourceLocation.fromNamespaceAndPath("dyairdrop", "animations/airdropweaponblock.animation.json");
   }

   public ResourceLocation getModelResource(AirdropweaponDisplayItem animatable) {
      return ResourceLocation.fromNamespaceAndPath("dyairdrop", "geo/airdropweaponblock.geo.json");
   }

   public ResourceLocation getTextureResource(AirdropweaponDisplayItem entity) {
      return ResourceLocation.fromNamespaceAndPath("dyairdrop", "textures/block/millitaryloot.png");
   }
}
