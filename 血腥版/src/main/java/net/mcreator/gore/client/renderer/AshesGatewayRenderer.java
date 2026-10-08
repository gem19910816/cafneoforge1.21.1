package net.mcreator.gore.client.renderer;

import net.mcreator.gore.entity.AshesGatewayEntity;
import net.mcreator.gore.entity.model.AshesGatewayModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class AshesGatewayRenderer extends GeoEntityRenderer<AshesGatewayEntity> {
   public AshesGatewayRenderer(Context renderManager) {
      super(renderManager, new AshesGatewayModel());
      this.shadowRadius = 0.0F;
   }

   public RenderType getRenderType(AshesGatewayEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }
}
