package net.mcreator.gore.client.renderer;

import net.mcreator.gore.entity.DeformityForAshesEntity;
import net.mcreator.gore.entity.layer.DeformityForAshesLayer;
import net.mcreator.gore.entity.model.DeformityForAshesModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class DeformityForAshesRenderer extends GeoEntityRenderer<DeformityForAshesEntity> {
   public DeformityForAshesRenderer(Context renderManager) {
      super(renderManager, new DeformityForAshesModel());
      this.shadowRadius = 4.0F;
      this.addRenderLayer(new DeformityForAshesLayer(this));
   }

   public RenderType getRenderType(DeformityForAshesEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }
}
