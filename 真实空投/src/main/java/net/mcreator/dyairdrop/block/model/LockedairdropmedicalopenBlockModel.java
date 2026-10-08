package net.mcreator.dyairdrop.block.model;

import net.mcreator.dyairdrop.block.entity.LockedairdropmedicalopenTileEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class LockedairdropmedicalopenBlockModel extends GeoModel<LockedairdropmedicalopenTileEntity> {
   public LockedairdropmedicalopenBlockModel() {
   }

   public ResourceLocation getAnimationResource(LockedairdropmedicalopenTileEntity animatable) {
      return ResourceLocation.fromNamespaceAndPath("dyairdrop", "animations/grechest.animation.json");
   }

   public ResourceLocation getModelResource(LockedairdropmedicalopenTileEntity animatable) {
      return ResourceLocation.fromNamespaceAndPath("dyairdrop", "geo/grechest.geo.json");
   }

   public ResourceLocation getTextureResource(LockedairdropmedicalopenTileEntity animatable) {
      return ResourceLocation.fromNamespaceAndPath("dyairdrop", "textures/block/gre.png");
   }
}
