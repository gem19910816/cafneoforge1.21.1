package net.mcreator.dyairdrop.block.model;

import net.mcreator.dyairdrop.block.entity.Safe2TileEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class Safe2BlockModel extends GeoModel<Safe2TileEntity> {
   public Safe2BlockModel() {
   }

   public ResourceLocation getAnimationResource(Safe2TileEntity animatable) {
      return ResourceLocation.fromNamespaceAndPath("dyairdrop", "animations/safe.animation.json");
   }

   public ResourceLocation getModelResource(Safe2TileEntity animatable) {
      return ResourceLocation.fromNamespaceAndPath("dyairdrop", "geo/safe.geo.json");
   }

   public ResourceLocation getTextureResource(Safe2TileEntity animatable) {
      return ResourceLocation.fromNamespaceAndPath("dyairdrop", "textures/block/safe.png");
   }
}
