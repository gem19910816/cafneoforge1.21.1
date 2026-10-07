package net.mcreator.dyairdrop.entity.model;

import software.bernie.geckolib.model.GeoModel;
import net.mcreator.dyairdrop.entity.TransportplaneEntity;
import net.minecraft.resources.ResourceLocation;

public class TransportplaneModel extends GeoModel<TransportplaneEntity> {
   public ResourceLocation getAnimationResource(TransportplaneEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("dyairdrop", "animations/plane.animation.json");
   }

   public ResourceLocation getModelResource(TransportplaneEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("dyairdrop", "geo/plane.geo.json");
   }

   public ResourceLocation getTextureResource(TransportplaneEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("dyairdrop", "textures/entities/" + entity.getTexture() + ".png");
   }
}
