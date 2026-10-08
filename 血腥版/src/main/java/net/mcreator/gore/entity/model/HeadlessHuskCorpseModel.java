package net.mcreator.gore.entity.model;

import net.mcreator.gore.entity.HeadlessHuskCorpseEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class HeadlessHuskCorpseModel extends GeoModel<HeadlessHuskCorpseEntity> {
   public ResourceLocation getAnimationResource(HeadlessHuskCorpseEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "animations/zombie_without_head_corpse.animation.json");
   }

   public ResourceLocation getModelResource(HeadlessHuskCorpseEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "geo/zombie_without_head_corpse.geo.json");
   }

   public ResourceLocation getTextureResource(HeadlessHuskCorpseEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "textures/entities/" + entity.getTexture() + ".png");
   }
}
