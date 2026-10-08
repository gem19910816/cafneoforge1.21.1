package net.mcreator.gore.entity.model;

import net.mcreator.gore.entity.AshesGatewayEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class AshesGatewayModel extends GeoModel<AshesGatewayEntity> {
   public ResourceLocation getAnimationResource(AshesGatewayEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "animations/ashes_gateway.animation.json");
   }

   public ResourceLocation getModelResource(AshesGatewayEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "geo/ashes_gateway.geo.json");
   }

   public ResourceLocation getTextureResource(AshesGatewayEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "textures/entities/" + entity.getTexture() + ".png");
   }
}
