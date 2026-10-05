package net.mcreator.dyairdrop.block.model;

import net.mcreator.dyairdrop.block.entity.AirdropweaponTileEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class AirdropweaponBlockModel extends GeoModel<AirdropweaponTileEntity> {
   public ResourceLocation getAnimationResource(AirdropweaponTileEntity animatable) {
      int blockstate = animatable.blockstateNew;
      return blockstate == 1
         ? ResourceLocation.fromNamespaceAndPath("dyairdrop", "animations/airdropweaponblockopen.animation.json")
         : ResourceLocation.fromNamespaceAndPath("dyairdrop", "animations/airdropweaponblock.animation.json");
   }

   public ResourceLocation getModelResource(AirdropweaponTileEntity animatable) {
      int blockstate = animatable.blockstateNew;
      return blockstate == 1
         ? ResourceLocation.fromNamespaceAndPath("dyairdrop", "geo/airdropweaponblockopen.geo.json")
         : ResourceLocation.fromNamespaceAndPath("dyairdrop", "geo/airdropweaponblock.geo.json");
   }

   public ResourceLocation getTextureResource(AirdropweaponTileEntity animatable) {
      int blockstate = animatable.blockstateNew;
      return blockstate == 1
         ? ResourceLocation.fromNamespaceAndPath("dyairdrop", "textures/block/millitaryloot.png")
         : ResourceLocation.fromNamespaceAndPath("dyairdrop", "textures/block/millitaryloot.png");
   }
}
