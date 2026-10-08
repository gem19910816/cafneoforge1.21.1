package net.mcreator.gore.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.mcreator.gore.entity.BabySpiderEntity;
import net.minecraft.client.model.SpiderModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;

public class BabySpiderRenderer extends MobRenderer<BabySpiderEntity, SpiderModel<BabySpiderEntity>> {
   public BabySpiderRenderer(Context context) {
      super(context, new SpiderModel(context.bakeLayer(ModelLayers.SPIDER)), 0.5F);
      this.addLayer(
         new RenderLayer<BabySpiderEntity, SpiderModel<BabySpiderEntity>>(this) {
            final ResourceLocation LAYER_TEXTURE = ResourceLocation.parse("gore_edition:textures/entities/spider_eyess.png");

            public void render(
               PoseStack poseStack,
               MultiBufferSource bufferSource,
               int light,
               BabySpiderEntity entity,
               float limbSwing,
               float limbSwingAmount,
               float partialTicks,
               float ageInTicks,
               float netHeadYaw,
               float headPitch
            ) {
               VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.eyes(this.LAYER_TEXTURE));
               ((SpiderModel)this.getParentModel()).renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0.0F), -1);
            }
         }
      );
   }

   protected void scale(BabySpiderEntity entity, PoseStack poseStack, float f) {
      poseStack.scale(0.5F, 0.5F, 0.5F);
   }

   public ResourceLocation getTextureLocation(BabySpiderEntity entity) {
      return ResourceLocation.parse("gore_edition:textures/entities/spider.png");
   }
}
