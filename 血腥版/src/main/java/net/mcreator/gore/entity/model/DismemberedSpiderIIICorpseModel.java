package net.mcreator.gore.entity.model;

import net.mcreator.gore.entity.DismemberedSpiderIIICorpseEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class DismemberedSpiderIIICorpseModel extends GeoModel<DismemberedSpiderIIICorpseEntity> {
   public ResourceLocation getAnimationResource(DismemberedSpiderIIICorpseEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "animations/spider_corpse.animation.json");
   }

   public ResourceLocation getModelResource(DismemberedSpiderIIICorpseEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "geo/spider_corpse.geo.json");
   }

   public ResourceLocation getTextureResource(DismemberedSpiderIIICorpseEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "textures/entities/" + entity.getTexture() + ".png");
   }
}
