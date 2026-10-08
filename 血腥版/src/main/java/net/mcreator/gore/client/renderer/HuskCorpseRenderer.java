package net.mcreator.gore.client.renderer;

import net.mcreator.gore.entity.HuskCorpseEntity;
import net.mcreator.gore.entity.model.HuskCorpseModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class HuskCorpseRenderer extends GeoEntityRenderer<HuskCorpseEntity> {
   public HuskCorpseRenderer(Context renderManager) {
      super(renderManager, new HuskCorpseModel());
      this.shadowRadius = 0.6F;
   }

   public RenderType getRenderType(HuskCorpseEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }

   protected float getDeathMaxRotation(HuskCorpseEntity entityLivingBaseIn) {
      return 0.0F;
   }
}
