package net.gem19910816.dyairdrop.block.model;

import net.gem19910816.dyairdrop.block.entity.AirdropmedicalTileEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class AirdropmedicalBlockModel extends GeoModel<AirdropmedicalTileEntity> {
   public ResourceLocation getAnimationResource(AirdropmedicalTileEntity animatable) {
      return ResourceLocation.fromNamespaceAndPath("dyairdrop", "animations/grechest.animation.json");
   }

   public ResourceLocation getModelResource(AirdropmedicalTileEntity animatable) {
      return ResourceLocation.fromNamespaceAndPath("dyairdrop", "geo/grechest.geo.json");
   }

   public ResourceLocation getTextureResource(AirdropmedicalTileEntity animatable) {
      return ResourceLocation.fromNamespaceAndPath("dyairdrop", "textures/block/gre.png");
   }
}
