package net.mcreator.dyairdrop.block.model;

import net.mcreator.dyairdrop.block.display.LockedairdropweaponopenDisplayItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class LockedairdropweaponopenDisplayModel extends GeoModel<LockedairdropweaponopenDisplayItem> {
   public LockedairdropweaponopenDisplayModel() {
   }

   public ResourceLocation getAnimationResource(LockedairdropweaponopenDisplayItem animatable) {
      return ResourceLocation.fromNamespaceAndPath("dyairdrop", "animations/airdropweaponblock.animation.json");
   }

   public ResourceLocation getModelResource(LockedairdropweaponopenDisplayItem animatable) {
      return ResourceLocation.fromNamespaceAndPath("dyairdrop", "geo/airdropweaponblock.geo.json");
   }

   public ResourceLocation getTextureResource(LockedairdropweaponopenDisplayItem entity) {
      return ResourceLocation.fromNamespaceAndPath("dyairdrop", "textures/block/millitaryloot.png");
   }
}
