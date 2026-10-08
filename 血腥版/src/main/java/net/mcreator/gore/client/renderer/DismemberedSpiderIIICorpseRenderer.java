package net.mcreator.gore.client.renderer;

import net.mcreator.gore.entity.DismemberedSpiderIIICorpseEntity;
import net.mcreator.gore.entity.model.DismemberedSpiderIIICorpseModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class DismemberedSpiderIIICorpseRenderer extends GeoEntityRenderer<DismemberedSpiderIIICorpseEntity> {
   public DismemberedSpiderIIICorpseRenderer(Context renderManager) {
      super(renderManager, new DismemberedSpiderIIICorpseModel());
      this.shadowRadius = 1.0F;
   }

   public RenderType getRenderType(DismemberedSpiderIIICorpseEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }

   protected float getDeathMaxRotation(DismemberedSpiderIIICorpseEntity entityLivingBaseIn) {
      return 0.0F;
   }
}
