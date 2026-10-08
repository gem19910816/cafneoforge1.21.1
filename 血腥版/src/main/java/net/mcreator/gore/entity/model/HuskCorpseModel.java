package net.mcreator.gore.entity.model;

import net.mcreator.gore.entity.HuskCorpseEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class HuskCorpseModel extends GeoModel<HuskCorpseEntity> {
   public ResourceLocation getAnimationResource(HuskCorpseEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "animations/zombie_corpse.animation.json");
   }

   public ResourceLocation getModelResource(HuskCorpseEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "geo/zombie_corpse.geo.json");
   }

   public ResourceLocation getTextureResource(HuskCorpseEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "textures/entities/" + entity.getTexture() + ".png");
   }
}
