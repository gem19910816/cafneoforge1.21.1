package net.mcreator.gore.client.renderer;

import net.mcreator.gore.entity.NowindEntity;
import net.mcreator.gore.entity.model.NowindModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class NowindRenderer extends GeoEntityRenderer<NowindEntity> {
   public NowindRenderer(Context renderManager) {
      super(renderManager, new NowindModel());
      this.shadowRadius = 0.7F;
   }

   public RenderType getRenderType(NowindEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }
}
