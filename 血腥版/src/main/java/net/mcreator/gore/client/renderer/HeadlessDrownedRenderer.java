package net.mcreator.gore.client.renderer;

import net.mcreator.gore.entity.HeadlessDrownedEntity;
import net.mcreator.gore.entity.model.HeadlessDrownedModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class HeadlessDrownedRenderer extends GeoEntityRenderer<HeadlessDrownedEntity> {
   public HeadlessDrownedRenderer(Context renderManager) {
      super(renderManager, new HeadlessDrownedModel());
      this.shadowRadius = 0.5F;
   }

   public RenderType getRenderType(HeadlessDrownedEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }
}
