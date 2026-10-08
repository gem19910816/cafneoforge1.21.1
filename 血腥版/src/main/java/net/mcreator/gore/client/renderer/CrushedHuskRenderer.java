package net.mcreator.gore.client.renderer;

import net.mcreator.gore.entity.CrushedHuskEntity;
import net.mcreator.gore.entity.model.CrushedHuskModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class CrushedHuskRenderer extends GeoEntityRenderer<CrushedHuskEntity> {
   public CrushedHuskRenderer(Context renderManager) {
      super(renderManager, new CrushedHuskModel());
      this.shadowRadius = 0.5F;
   }

   public RenderType getRenderType(CrushedHuskEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }
}
