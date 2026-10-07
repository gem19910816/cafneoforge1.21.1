package net.mcreator.dyairdrop.entity.model;

import software.bernie.geckolib.model.GeoModel;
import net.mcreator.dyairdrop.entity.WeaponairdropEntity;
import net.minecraft.resources.ResourceLocation;

public class WeaponairdropModel extends GeoModel<WeaponairdropEntity> {
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
