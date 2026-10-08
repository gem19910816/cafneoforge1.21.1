package net.mcreator.gore.entity.model;

import net.mcreator.gore.entity.HeadlessDrownedEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class HeadlessDrownedModel extends GeoModel<HeadlessDrownedEntity> {
   public ResourceLocation getAnimationResource(HeadlessDrownedEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "animations/drowned_without_head.animation.json");
   }

   public ResourceLocation getModelResource(HeadlessDrownedEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "geo/drowned_without_head.geo.json");
   }

   public ResourceLocation getTextureResource(HeadlessDrownedEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "textures/entities/" + entity.getTexture() + ".png");
   }
}
