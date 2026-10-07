package net.gem19910816.dyairdrop.block.model;

import net.gem19910816.dyairdrop.block.entity.LockedairdropmedicalTileEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class LockedairdropmedicalBlockModel extends GeoModel<LockedairdropmedicalTileEntity> {
   public ResourceLocation getAnimationResource(LockedairdropmedicalTileEntity animatable) {
      return ResourceLocation.fromNamespaceAndPath("dyairdrop", "animations/grechest.animation.json");
   }

   public ResourceLocation getModelResource(LockedairdropmedicalTileEntity animatable) {
      return ResourceLocation.fromNamespaceAndPath("dyairdrop", "geo/grechest.geo.json");
   }

   public ResourceLocation getTextureResource(LockedairdropmedicalTileEntity animatable) {
      return ResourceLocation.fromNamespaceAndPath("dyairdrop", "textures/block/gre.png");
   }
}
