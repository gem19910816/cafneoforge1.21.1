package net.mcreator.dyairdrop.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.mcreator.dyairdrop.entity.TransportplaneEntity;
import net.mcreator.dyairdrop.entity.layer.TransportplaneLayer;
import net.mcreator.dyairdrop.entity.model.TransportplaneModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class TransportplaneRenderer extends GeoEntityRenderer<TransportplaneEntity> {
   public TransportplaneRenderer(Context renderManager) {
      super(renderManager, new TransportplaneModel());
      this.shadowRadius = 2.0F;
      this.addRenderLayer(new TransportplaneLayer(this));
   }

   public RenderType getRenderType(TransportplaneEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }

   public void preRender(
      PoseStack poseStack,
      TransportplaneEntity entity,
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
