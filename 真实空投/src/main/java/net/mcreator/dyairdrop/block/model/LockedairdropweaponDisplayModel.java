package net.mcreator.dyairdrop.block.model;

import net.mcreator.dyairdrop.block.display.LockedairdropweaponDisplayItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class LockedairdropweaponDisplayModel extends GeoModel<LockedairdropweaponDisplayItem> {
   public LockedairdropweaponDisplayModel() {
   }

   public ResourceLocation getAnimationResource(LockedairdropweaponDisplayItem animatable) {
      return ResourceLocation.fromNamespaceAndPath("dyairdrop", "animations/airdropweaponblock.animation.json");
   }

   public ResourceLocation getModelResource(LockedairdropweaponDisplayItem animatable) {
      return ResourceLocation.fromNamespaceAndPath("dyairdrop", "geo/airdropweaponblock.geo.json");
   }

   public ResourceLocation getTextureResource(LockedairdropweaponDisplayItem entity) {
      return ResourceLocation.fromNamespaceAndPath("dyairdrop", "textures/block/millitaryloot.png");
   }
}
