package net.mcreator.gore.client.renderer;

import net.mcreator.gore.entity.SpiderCorpseEntity;
import net.mcreator.gore.entity.model.SpiderCorpseModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SpiderCorpseRenderer extends GeoEntityRenderer<SpiderCorpseEntity> {
   public SpiderCorpseRenderer(Context renderManager) {
      super(renderManager, new SpiderCorpseModel());
      this.shadowRadius = 1.0F;
   }

   public RenderType getRenderType(SpiderCorpseEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }

   protected float getDeathMaxRotation(SpiderCorpseEntity entityLivingBaseIn) {
      return 0.0F;
   }
}
