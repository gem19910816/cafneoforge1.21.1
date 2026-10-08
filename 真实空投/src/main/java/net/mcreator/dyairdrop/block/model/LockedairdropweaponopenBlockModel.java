package net.mcreator.dyairdrop.block.model;

import net.mcreator.dyairdrop.block.entity.LockedairdropweaponopenTileEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class LockedairdropweaponopenBlockModel extends GeoModel<LockedairdropweaponopenTileEntity> {
   public LockedairdropweaponopenBlockModel() {
   }

   public ResourceLocation getAnimationResource(LockedairdropweaponopenTileEntity animatable) {
      int blockstate = animatable.blockstateNew;
      return blockstate == 1
         ? ResourceLocation.fromNamespaceAndPath("dyairdrop", "animations/airdropweaponblockopen.animation.json")
         : ResourceLocation.fromNamespaceAndPath("dyairdrop", "animations/airdropweaponblock.animation.json");
   }

   public ResourceLocation getModelResource(LockedairdropweaponopenTileEntity animatable) {
      int blockstate = animatable.blockstateNew;
      return blockstate == 1
         ? ResourceLocation.fromNamespaceAndPath("dyairdrop", "geo/airdropweaponblockopen.geo.json")
         : ResourceLocation.fromNamespaceAndPath("dyairdrop", "geo/airdropweaponblock.geo.json");
   }

   public ResourceLocation getTextureResource(LockedairdropweaponopenTileEntity animatable) {
      int blockstate = animatable.blockstateNew;
      return blockstate == 1
         ? ResourceLocation.fromNamespaceAndPath("dyairdrop", "textures/block/millitaryloot.png")
         : ResourceLocation.fromNamespaceAndPath("dyairdrop", "textures/block/millitaryloot.png");
   }
}
