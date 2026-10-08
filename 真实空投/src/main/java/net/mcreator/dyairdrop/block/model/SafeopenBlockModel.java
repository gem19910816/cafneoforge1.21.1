package net.mcreator.dyairdrop.block.model;

import net.mcreator.dyairdrop.block.entity.SafeopenTileEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class SafeopenBlockModel extends GeoModel<SafeopenTileEntity> {
   public SafeopenBlockModel() {
   }

   public ResourceLocation getAnimationResource(SafeopenTileEntity animatable) {
      return ResourceLocation.fromNamespaceAndPath("dyairdrop", "animations/safeopen.animation.json");
   }

   public ResourceLocation getModelResource(SafeopenTileEntity animatable) {
      return ResourceLocation.fromNamespaceAndPath("dyairdrop", "geo/safeopen.geo.json");
   }

   public ResourceLocation getTextureResource(SafeopenTileEntity animatable) {
      return ResourceLocation.fromNamespaceAndPath("dyairdrop", "textures/block/safe.png");
   }
}
