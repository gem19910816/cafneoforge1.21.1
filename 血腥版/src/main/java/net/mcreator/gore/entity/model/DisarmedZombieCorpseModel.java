package net.mcreator.gore.entity.model;

import net.mcreator.gore.entity.DisarmedZombieCorpseEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class DisarmedZombieCorpseModel extends GeoModel<DisarmedZombieCorpseEntity> {
   public ResourceLocation getAnimationResource(DisarmedZombieCorpseEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "animations/zombie_without_arms_corpse.animation.json");
   }

   public ResourceLocation getModelResource(DisarmedZombieCorpseEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "geo/zombie_without_arms_corpse.geo.json");
   }

   public ResourceLocation getTextureResource(DisarmedZombieCorpseEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "textures/entities/" + entity.getTexture() + ".png");
   }
}
