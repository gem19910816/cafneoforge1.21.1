package net.gem19910816.dyairdrop.block.model;

import net.gem19910816.dyairdrop.block.entity.LockedairdropweaponTileEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class LockedairdropweaponBlockModel extends GeoModel<LockedairdropweaponTileEntity> {
   public ResourceLocation getAnimationResource(LockedairdropweaponTileEntity animatable) {
      return ResourceLocation.fromNamespaceAndPath("dyairdrop", "animations/airdropweaponblock.animation.json");
   }

   public ResourceLocation getModelResource(LockedairdropweaponTileEntity animatable) {
      return ResourceLocation.fromNamespaceAndPath("dyairdrop", "geo/airdropweaponblock.geo.json");
   }

   public ResourceLocation getTextureResource(LockedairdropweaponTileEntity animatable) {
      return ResourceLocation.fromNamespaceAndPath("dyairdrop", "textures/block/millitaryloot.png");
   }
}
