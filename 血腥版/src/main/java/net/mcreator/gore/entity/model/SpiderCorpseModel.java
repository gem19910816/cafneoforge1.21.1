package net.mcreator.gore.entity.model;

import net.mcreator.gore.entity.SpiderCorpseEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class SpiderCorpseModel extends GeoModel<SpiderCorpseEntity> {
   public ResourceLocation getAnimationResource(SpiderCorpseEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "animations/spider_corpse.animation.json");
   }

   public ResourceLocation getModelResource(SpiderCorpseEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "geo/spider_corpse.geo.json");
   }

   public ResourceLocation getTextureResource(SpiderCorpseEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "textures/entities/" + entity.getTexture() + ".png");
   }
}
