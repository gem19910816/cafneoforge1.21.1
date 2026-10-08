package net.mcreator.gore.entity.model;

import net.mcreator.gore.entity.ExarrackHydraEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ExarrackHydraModel extends GeoModel<ExarrackHydraEntity> {
   public ResourceLocation getAnimationResource(ExarrackHydraEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "animations/exarrack_hydra.animation.json");
   }

   public ResourceLocation getModelResource(ExarrackHydraEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "geo/exarrack_hydra.geo.json");
   }

   public ResourceLocation getTextureResource(ExarrackHydraEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "textures/entities/" + entity.getTexture() + ".png");
   }
}
