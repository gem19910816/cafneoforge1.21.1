package net.mcreator.dyairdrop.block.model;

import net.mcreator.dyairdrop.block.entity.AirdroplargeTileEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class AirdroplargeBlockModel extends GeoModel<AirdroplargeTileEntity> {
   public ResourceLocation getAnimationResource(AirdroplargeTileEntity animatable) {
      return ResourceLocation.fromNamespaceAndPath("dyairdrop", "animations/airdroplarge.animation.json");
   }

   public ResourceLocation getModelResource(AirdroplargeTileEntity animatable) {
      return ResourceLocation.fromNamespaceAndPath("dyairdrop", "geo/airdroplarge.geo.json");
   }

   public ResourceLocation getTextureResource(AirdroplargeTileEntity animatable) {
      return ResourceLocation.fromNamespaceAndPath("dyairdrop", "textures/block/largeairdrop.png");
   }
}
