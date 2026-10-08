package net.mcreator.gore.client.renderer;

import net.mcreator.gore.entity.AshesWitherSneerEntity;
import net.mcreator.gore.entity.model.AshesWitherSneerModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class AshesWitherSneerRenderer extends GeoEntityRenderer<AshesWitherSneerEntity> {
   public AshesWitherSneerRenderer(Context renderManager) {
      super(renderManager, new AshesWitherSneerModel());
      this.shadowRadius = 0.5F;
   }

   public RenderType getRenderType(AshesWitherSneerEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }
}
