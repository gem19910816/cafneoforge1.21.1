package net.mcreator.gore.entity.model;

import net.mcreator.gore.entity.DismemberedSpiderIICorpseEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class DismemberedSpiderIICorpseModel extends GeoModel<DismemberedSpiderIICorpseEntity> {
   public ResourceLocation getAnimationResource(DismemberedSpiderIICorpseEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "animations/dismembered_spider_2_corpse.animation.json");
   }

   public ResourceLocation getModelResource(DismemberedSpiderIICorpseEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "geo/dismembered_spider_2_corpse.geo.json");
   }

   public ResourceLocation getTextureResource(DismemberedSpiderIICorpseEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "textures/entities/" + entity.getTexture() + ".png");
   }
}
