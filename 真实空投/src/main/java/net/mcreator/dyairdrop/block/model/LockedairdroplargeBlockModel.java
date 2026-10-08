package net.mcreator.dyairdrop.block.model;

import net.mcreator.dyairdrop.block.entity.LockedairdroplargeTileEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class LockedairdroplargeBlockModel extends GeoModel<LockedairdroplargeTileEntity> {
   public LockedairdroplargeBlockModel() {
   }

   public ResourceLocation getAnimationResource(LockedairdroplargeTileEntity animatable) {
      return ResourceLocation.fromNamespaceAndPath("dyairdrop", "animations/airdroplarge.animation.json");
   }

   public ResourceLocation getModelResource(LockedairdroplargeTileEntity animatable) {
      return ResourceLocation.fromNamespaceAndPath("dyairdrop", "geo/airdroplarge.geo.json");
   }

   public ResourceLocation getTextureResource(LockedairdroplargeTileEntity animatable) {
      return ResourceLocation.fromNamespaceAndPath("dyairdrop", "textures/block/largeairdrop.png");
   }
}
