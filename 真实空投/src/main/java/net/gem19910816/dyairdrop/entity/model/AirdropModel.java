package net.gem19910816.dyairdrop.entity.model;

import software.bernie.geckolib.model.GeoModel;
import net.gem19910816.dyairdrop.entity.AirdropEntity;
import net.minecraft.resources.ResourceLocation;

public class AirdropModel extends GeoModel<AirdropEntity> {
   public ResourceLocation getAnimationResource(AirdropEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("dyairdrop", "animations/gairdrop.animation.json");
   }

   public ResourceLocation getModelResource(AirdropEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("dyairdrop", "geo/gairdrop.geo.json");
   }

   public ResourceLocation getTextureResource(AirdropEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("dyairdrop", "textures/entities/" + entity.getTexture() + ".png");
   }
}
