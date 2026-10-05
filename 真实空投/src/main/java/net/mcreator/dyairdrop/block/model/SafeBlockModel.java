package net.mcreator.dyairdrop.block.model;

import net.mcreator.dyairdrop.block.entity.SafeTileEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class SafeBlockModel extends GeoModel<SafeTileEntity> {
   public ResourceLocation getAnimationResource(SafeTileEntity animatable) {
      return ResourceLocation.fromNamespaceAndPath("dyairdrop", "animations/safe.animation.json");
   }

   public ResourceLocation getModelResource(SafeTileEntity animatable) {
      return ResourceLocation.fromNamespaceAndPath("dyairdrop", "geo/safe.geo.json");
   }

   public ResourceLocation getTextureResource(SafeTileEntity animatable) {
      return ResourceLocation.fromNamespaceAndPath("dyairdrop", "textures/block/safe.png");
   }
}
