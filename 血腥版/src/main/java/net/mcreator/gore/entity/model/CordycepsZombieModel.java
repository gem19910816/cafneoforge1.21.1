package net.mcreator.gore.entity.model;

import net.mcreator.gore.entity.CordycepsZombieEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class CordycepsZombieModel extends GeoModel<CordycepsZombieEntity> {
   public ResourceLocation getAnimationResource(CordycepsZombieEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "animations/cordyceps_zombie.animation.json");
   }

   public ResourceLocation getModelResource(CordycepsZombieEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "geo/cordyceps_zombie.geo.json");
   }

   public ResourceLocation getTextureResource(CordycepsZombieEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "textures/entities/" + entity.getTexture() + ".png");
   }

   public void setCustomAnimations(CordycepsZombieEntity animatable, long instanceId, AnimationState animationState) {
      GeoBone head = this.getAnimationProcessor().getBone("head");
      if (head != null) {
         EntityModelData entityData = (EntityModelData)animationState.getData(DataTickets.ENTITY_MODEL_DATA);
         head.setRotX(entityData.headPitch() * (float) (Math.PI / 180.0));
         head.setRotY(entityData.netHeadYaw() * (float) (Math.PI / 180.0));
      }
   }
}
