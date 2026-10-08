package net.mcreator.dyairdrop.entity.model;

import net.mcreator.dyairdrop.entity.WeaponairdropEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class WeaponairdropModel extends GeoModel<WeaponairdropEntity> {
   public WeaponairdropModel() {
   }

   public ResourceLocation getAnimationResource(WeaponairdropEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("dyairdrop", "animations/airdropweapon.animation.json");
   }

   public ResourceLocation getModelResource(WeaponairdropEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("dyairdrop", "geo/airdropweapon.geo.json");
   }

   public ResourceLocation getTextureResource(WeaponairdropEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("dyairdrop", "textures/entities/" + entity.getTexture() + ".png");
   }
}
