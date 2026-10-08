package net.mcreator.gore.entity.model;

import net.mcreator.gore.entity.AluxinationEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class AluxinationModel extends GeoModel<AluxinationEntity> {
   public ResourceLocation getAnimationResource(AluxinationEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "animations/aluxination.animation.json");
   }

   public ResourceLocation getModelResource(AluxinationEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "geo/aluxination.geo.json");
   }

   public ResourceLocation getTextureResource(AluxinationEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "textures/entities/" + entity.getTexture() + ".png");
   }
}
