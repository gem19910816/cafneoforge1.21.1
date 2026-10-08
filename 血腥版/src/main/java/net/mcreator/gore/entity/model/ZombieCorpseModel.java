package net.mcreator.gore.entity.model;

import net.mcreator.gore.entity.ZombieCorpseEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ZombieCorpseModel extends GeoModel<ZombieCorpseEntity> {
   public ResourceLocation getAnimationResource(ZombieCorpseEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "animations/zombie_corpse.animation.json");
   }

   public ResourceLocation getModelResource(ZombieCorpseEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "geo/zombie_corpse.geo.json");
   }

   public ResourceLocation getTextureResource(ZombieCorpseEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "textures/entities/" + entity.getTexture() + ".png");
   }
}
