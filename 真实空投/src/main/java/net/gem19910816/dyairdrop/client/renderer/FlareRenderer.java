package net.gem19910816.dyairdrop.client.renderer;

import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.gem19910816.dyairdrop.entity.FlareEntity;
import net.gem19910816.dyairdrop.client.model.Modelflare_gun;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import com.mojang.math.Axis;
import net.minecraft.util.Mth;

public class FlareRenderer extends EntityRenderer<FlareEntity> {
   private static final ResourceLocation texture = ResourceLocation.parse("dyairdrop:textures/entities/flare.png");
   private final Modelflare_gun model;

   public FlareRenderer(EntityRendererProvider.Context context) {
      super(context);
      this.model = new Modelflare_gun(context.bakeLayer(Modelflare_gun.LAYER_LOCATION));
   }

   public void render(FlareEntity entityIn, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource bufferIn, int packedLightIn) {
      VertexConsumer vb = bufferIn.getBuffer(RenderType.entityCutout(this.getTextureLocation(entityIn)));
      poseStack.pushPose();
      poseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTicks, entityIn.yRotO, entityIn.getYRot()) - 90.0F));
      poseStack.mulPose(Axis.ZP.rotationDegrees(90.0F + Mth.lerp(partialTicks, entityIn.xRotO, entityIn.getXRot())));
      this.model.renderToBuffer(poseStack, vb, packedLightIn, OverlayTexture.NO_OVERLAY, 0x10FFFFFF);
      poseStack.popPose();
      super.render(entityIn, entityYaw, partialTicks, poseStack, bufferIn, packedLightIn);
   }

   public ResourceLocation getTextureLocation(FlareEntity entity) {
      return texture;
   }
}
