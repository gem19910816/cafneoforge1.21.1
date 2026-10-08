package net.mcreator.gore.client.renderer;

import net.mcreator.gore.entity.DismemberedSpiderIICorpseEntity;
import net.mcreator.gore.entity.model.DismemberedSpiderIICorpseModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class DismemberedSpiderIICorpseRenderer extends GeoEntityRenderer<DismemberedSpiderIICorpseEntity> {
   public DismemberedSpiderIICorpseRenderer(Context renderManager) {
      super(renderManager, new DismemberedSpiderIICorpseModel());
      this.shadowRadius = 1.0F;
   }

   public RenderType getRenderType(DismemberedSpiderIICorpseEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }

   protected float getDeathMaxRotation(DismemberedSpiderIICorpseEntity entityLivingBaseIn) {
      return 0.0F;
   }
}
