package net.mcreator.dyairdrop.entity.model;

import software.bernie.geckolib.model.GeoModel;
import net.mcreator.dyairdrop.entity.MedicalairdropEntity;
import net.minecraft.resources.ResourceLocation;

public class MedicalairdropModel extends GeoModel<MedicalairdropEntity> {
   public ResourceLocation getAnimationResource(MedicalairdropEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("dyairdrop", "animations/airdropmedical.animation.json");
   }

   public ResourceLocation getModelResource(MedicalairdropEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("dyairdrop", "geo/airdropmedical.geo.json");
   }

   public ResourceLocation getTextureResource(MedicalairdropEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("dyairdrop", "textures/entities/" + entity.getTexture() + ".png");
   }
}
