package net.mcreator.gore.entity.model;

import net.mcreator.gore.entity.FakeExarrackMonsterEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class FakeExarrackMonsterModel extends GeoModel<FakeExarrackMonsterEntity> {
   public ResourceLocation getAnimationResource(FakeExarrackMonsterEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "animations/fake_flesh_eater.animation.json");
   }

   public ResourceLocation getModelResource(FakeExarrackMonsterEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "geo/fake_flesh_eater.geo.json");
   }

   public ResourceLocation getTextureResource(FakeExarrackMonsterEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "textures/entities/" + entity.getTexture() + ".png");
   }
}
