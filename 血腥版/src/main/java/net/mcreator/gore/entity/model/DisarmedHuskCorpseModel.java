package net.mcreator.gore.entity.model;

import net.mcreator.gore.entity.DisarmedHuskCorpseEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class DisarmedHuskCorpseModel extends GeoModel<DisarmedHuskCorpseEntity> {
   public ResourceLocation getAnimationResource(DisarmedHuskCorpseEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "animations/zombie_without_arms_corpse.animation.json");
   }

   public ResourceLocation getModelResource(DisarmedHuskCorpseEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "geo/zombie_without_arms_corpse.geo.json");
   }

   public ResourceLocation getTextureResource(DisarmedHuskCorpseEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "textures/entities/" + entity.getTexture() + ".png");
   }
}
