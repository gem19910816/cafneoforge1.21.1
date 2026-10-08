package net.mcreator.gore.client.renderer;

import net.mcreator.gore.entity.ExarrackHydraEntity;
import net.mcreator.gore.entity.model.ExarrackHydraModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class ExarrackHydraRenderer extends GeoEntityRenderer<ExarrackHydraEntity> {
   public ExarrackHydraRenderer(Context renderManager) {
      super(renderManager, new ExarrackHydraModel());
      this.shadowRadius = 2.3F;
   }

   public RenderType getRenderType(ExarrackHydraEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }
}
