package net.mcreator.gore.entity.model;

import net.mcreator.gore.entity.SeveredLegsHuskCorpseEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class SeveredLegsHuskCorpseModel extends GeoModel<SeveredLegsHuskCorpseEntity> {
   public ResourceLocation getAnimationResource(SeveredLegsHuskCorpseEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "animations/corpse_severed_legs_zombie.animation.json");
   }

   public ResourceLocation getModelResource(SeveredLegsHuskCorpseEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "geo/corpse_severed_legs_zombie.geo.json");
   }

   public ResourceLocation getTextureResource(SeveredLegsHuskCorpseEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "textures/entities/" + entity.getTexture() + ".png");
   }
}
