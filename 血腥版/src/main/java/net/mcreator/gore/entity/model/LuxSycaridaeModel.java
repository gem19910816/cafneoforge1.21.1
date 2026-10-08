package net.mcreator.gore.entity.model;

import net.mcreator.gore.entity.LuxSycaridaeEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class LuxSycaridaeModel extends GeoModel<LuxSycaridaeEntity> {
   public ResourceLocation getAnimationResource(LuxSycaridaeEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "animations/lux_sycaride.animation.json");
   }

   public ResourceLocation getModelResource(LuxSycaridaeEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "geo/lux_sycaride.geo.json");
   }

   public ResourceLocation getTextureResource(LuxSycaridaeEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "textures/entities/" + entity.getTexture() + ".png");
   }
}
