package net.mcreator.gore.entity.model;

import net.mcreator.gore.entity.HeadlessZombieCorpseEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class HeadlessZombieCorpseModel extends GeoModel<HeadlessZombieCorpseEntity> {
   public ResourceLocation getAnimationResource(HeadlessZombieCorpseEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "animations/zombie_without_head_corpse.animation.json");
   }

   public ResourceLocation getModelResource(HeadlessZombieCorpseEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "geo/zombie_without_head_corpse.geo.json");
   }

   public ResourceLocation getTextureResource(HeadlessZombieCorpseEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "textures/entities/" + entity.getTexture() + ".png");
   }
}
