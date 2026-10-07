package net.gem19910816.dyairdrop.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.gem19910816.dyairdrop.entity.model.AirdropModel;
import net.gem19910816.dyairdrop.entity.AirdropEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class AirdropRenderer extends GeoEntityRenderer<AirdropEntity> {
   public AirdropRenderer(EntityRendererProvider.Context renderManager) {
      super(renderManager, new AirdropModel());
      this.shadowRadius = 1.0F;
   }

   public RenderType getRenderType(AirdropEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }

   public void preRender(
      PoseStack poseStack,
      AirdropEntity entity,
      BakedGeoModel model,
      MultiBufferSource bufferSource,
      VertexConsumer buffer,
      boolean isReRender,
      float partialTick,
      int packedLight,
      int packedOverlay,
      int color
   ) {
      float scale = 1.0F;
      this.scaleHeight = scale;
      this.scaleWidth = scale;
      super.preRender(poseStack, entity, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, color);
   }
}
