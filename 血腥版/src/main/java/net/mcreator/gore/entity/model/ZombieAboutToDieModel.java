package net.mcreator.gore.entity.model;

import net.mcreator.gore.entity.ZombieAboutToDieEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class ZombieAboutToDieModel extends GeoModel<ZombieAboutToDieEntity> {
   public ResourceLocation getAnimationResource(ZombieAboutToDieEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "animations/zombie_about_to_die.animation.json");
   }

   public ResourceLocation getModelResource(ZombieAboutToDieEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "geo/zombie_about_to_die.geo.json");
   }

   public ResourceLocation getTextureResource(ZombieAboutToDieEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "textures/entities/" + entity.getTexture() + ".png");
   }

   public void setCustomAnimations(ZombieAboutToDieEntity animatable, long instanceId, AnimationState animationState) {
      GeoBone head = this.getAnimationProcessor().getBone("head");
      if (head != null) {
         EntityModelData entityData = (EntityModelData)animationState.getData(DataTickets.ENTITY_MODEL_DATA);
         head.setRotX(entityData.headPitch() * (float) (Math.PI / 180.0));
         head.setRotY(entityData.netHeadYaw() * (float) (Math.PI / 180.0));
      }
   }
}
