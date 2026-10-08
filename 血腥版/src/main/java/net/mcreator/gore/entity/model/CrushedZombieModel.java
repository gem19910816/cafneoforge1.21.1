package net.mcreator.gore.entity.model;

import net.mcreator.gore.entity.CrushedZombieEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class CrushedZombieModel extends GeoModel<CrushedZombieEntity> {
   public ResourceLocation getAnimationResource(CrushedZombieEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "animations/multiple_cutted_zombie.animation.json");
   }

   public ResourceLocation getModelResource(CrushedZombieEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "geo/multiple_cutted_zombie.geo.json");
   }

   public ResourceLocation getTextureResource(CrushedZombieEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "textures/entities/" + entity.getTexture() + ".png");
   }
}
