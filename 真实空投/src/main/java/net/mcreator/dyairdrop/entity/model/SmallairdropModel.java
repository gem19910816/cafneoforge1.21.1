package net.mcreator.dyairdrop.entity.model;

import net.mcreator.dyairdrop.entity.SmallairdropEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class SmallairdropModel extends GeoModel<SmallairdropEntity> {
   public SmallairdropModel() {
   }

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
