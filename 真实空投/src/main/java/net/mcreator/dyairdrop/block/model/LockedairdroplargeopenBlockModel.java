package net.mcreator.dyairdrop.block.model;

import net.mcreator.dyairdrop.block.entity.LockedairdroplargeopenTileEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class LockedairdroplargeopenBlockModel extends GeoModel<LockedairdroplargeopenTileEntity> {
   public LockedairdroplargeopenBlockModel() {
   }

   public ResourceLocation getAnimationResource(LockedairdroplargeopenTileEntity animatable) {
      return ResourceLocation.fromNamespaceAndPath("dyairdrop", "animations/lockedairdroplargeopen.animation.json");
   }

   public ResourceLocation getModelResource(LockedairdroplargeopenTileEntity animatable) {
      return ResourceLocation.fromNamespaceAndPath("dyairdrop", "geo/lockedairdroplargeopen.geo.json");
   }

   public ResourceLocation getTextureResource(LockedairdroplargeopenTileEntity animatable) {
      return ResourceLocation.fromNamespaceAndPath("dyairdrop", "textures/block/largeairdrop.png");
   }
}
