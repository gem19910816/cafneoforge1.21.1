package net.mcreator.gore.client.renderer;

import net.mcreator.gore.entity.DismemberedSpiderICorpseEntity;
import net.mcreator.gore.entity.model.DismemberedSpiderICorpseModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class DismemberedSpiderICorpseRenderer extends GeoEntityRenderer<DismemberedSpiderICorpseEntity> {
   public DismemberedSpiderICorpseRenderer(Context renderManager) {
      super(renderManager, new DismemberedSpiderICorpseModel());
      this.shadowRadius = 1.0F;
   }

   public RenderType getRenderType(DismemberedSpiderICorpseEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }

   protected float getDeathMaxRotation(DismemberedSpiderICorpseEntity entityLivingBaseIn) {
      return 0.0F;
   }
}
