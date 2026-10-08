package net.mcreator.gore.entity.model;

import net.mcreator.gore.entity.TheExarrackMonsterEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class TheExarrackMonsterModel extends GeoModel<TheExarrackMonsterEntity> {
   public ResourceLocation getAnimationResource(TheExarrackMonsterEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "animations/the_exarrack_monster.animation.json");
   }

   public ResourceLocation getModelResource(TheExarrackMonsterEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "geo/the_exarrack_monster.geo.json");
   }

   public ResourceLocation getTextureResource(TheExarrackMonsterEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "textures/entities/" + entity.getTexture() + ".png");
   }
}
