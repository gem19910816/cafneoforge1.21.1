package net.gem19910816.dyairdrop.entity.model;

import software.bernie.geckolib.model.GeoModel;
import net.gem19910816.dyairdrop.entity.SmallairdropEntity;
import net.minecraft.resources.ResourceLocation;

public class SmallairdropModel extends GeoModel<SmallairdropEntity> {
   public ResourceLocation getAnimationResource(SmallairdropEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("dyairdrop", "animations/gairdrop2.animation.json");
   }

   public ResourceLocation getModelResource(SmallairdropEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("dyairdrop", "geo/gairdrop2.geo.json");
   }

   public ResourceLocation getTextureResource(SmallairdropEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("dyairdrop", "textures/entities/" + entity.getTexture() + ".png");
   }
}
