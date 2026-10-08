package net.mcreator.gore.entity.model;

import net.mcreator.gore.entity.ZombieWithoutLegsAndArmCorpseEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ZombieWithoutLegsAndArmCorpseModel extends GeoModel<ZombieWithoutLegsAndArmCorpseEntity> {
   public ResourceLocation getAnimationResource(ZombieWithoutLegsAndArmCorpseEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "animations/zombie_without_legs_and_arm_corpse.animation.json");
   }

   public ResourceLocation getModelResource(ZombieWithoutLegsAndArmCorpseEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "geo/zombie_without_legs_and_arm_corpse.geo.json");
   }

   public ResourceLocation getTextureResource(ZombieWithoutLegsAndArmCorpseEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "textures/entities/" + entity.getTexture() + ".png");
   }
}
