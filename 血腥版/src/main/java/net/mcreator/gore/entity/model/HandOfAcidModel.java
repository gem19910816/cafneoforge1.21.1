package net.mcreator.gore.entity.model;

import net.mcreator.gore.entity.HandOfAcidEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class HandOfAcidModel extends GeoModel<HandOfAcidEntity> {
   public ResourceLocation getAnimationResource(HandOfAcidEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "animations/hand_of_acid.animation.json");
   }

   public ResourceLocation getModelResource(HandOfAcidEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "geo/hand_of_acid.geo.json");
   }

   public ResourceLocation getTextureResource(HandOfAcidEntity entity) {
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "textures/entities/" + entity.getTexture() + ".png");
   }

   public void setCustomAnimations(HandOfAcidEntity animatable, long instanceId, AnimationState animationState) {
      GeoBone head = this.getAnimationProcessor().getBone("bone");
      if (head != null) {
         EntityModelData entityData = (EntityModelData)animationState.getData(DataTickets.ENTITY_MODEL_DATA);
         head.setRotX(entityData.headPitch() * (float) (Math.PI / 180.0));
         head.setRotY(entityData.netHeadYaw() * (float) (Math.PI / 180.0));
      }
   }
}
