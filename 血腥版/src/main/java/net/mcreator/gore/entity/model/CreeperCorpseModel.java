package net.mcreator.gore.entity.model;

import net.mcreator.gore.entity.CreeperCorpseEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class CreeperCorpseModel extends GeoModel<CreeperCorpseEntity> {
   public ResourceLocation getAnimationResource(CreeperCorpseEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "animations/corpse_creeper.animation.json");
   }

   public ResourceLocation getModelResource(CreeperCorpseEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "geo/corpse_creeper.geo.json");
   }

   public ResourceLocation getTextureResource(CreeperCorpseEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "textures/entities/" + entity.getTexture() + ".png");
   }
}
