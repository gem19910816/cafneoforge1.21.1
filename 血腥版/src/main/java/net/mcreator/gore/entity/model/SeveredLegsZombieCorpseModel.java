package net.mcreator.gore.entity.model;

import net.mcreator.gore.entity.SeveredLegsZombieCorpseEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class SeveredLegsZombieCorpseModel extends GeoModel<SeveredLegsZombieCorpseEntity> {
   public ResourceLocation getAnimationResource(SeveredLegsZombieCorpseEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "animations/corpse_severed_legs_zombie.animation.json");
   }

   public ResourceLocation getModelResource(SeveredLegsZombieCorpseEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "geo/corpse_severed_legs_zombie.geo.json");
   }

   public ResourceLocation getTextureResource(SeveredLegsZombieCorpseEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "textures/entities/" + entity.getTexture() + ".png");
   }
}
