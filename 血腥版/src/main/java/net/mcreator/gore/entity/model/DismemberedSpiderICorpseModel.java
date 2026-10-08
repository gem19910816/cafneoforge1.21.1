package net.mcreator.gore.entity.model;

import net.mcreator.gore.entity.DismemberedSpiderICorpseEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class DismemberedSpiderICorpseModel extends GeoModel<DismemberedSpiderICorpseEntity> {
   public ResourceLocation getAnimationResource(DismemberedSpiderICorpseEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "animations/dismembered_spider_1_corpse.animation.json");
   }

   public ResourceLocation getModelResource(DismemberedSpiderICorpseEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "geo/dismembered_spider_1_corpse.geo.json");
   }

   public ResourceLocation getTextureResource(DismemberedSpiderICorpseEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "textures/entities/" + entity.getTexture() + ".png");
   }
}
