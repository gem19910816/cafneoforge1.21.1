package net.mcreator.gore.client.renderer;

import net.mcreator.gore.entity.HeadlessHuskCorpseEntity;
import net.mcreator.gore.entity.model.HeadlessHuskCorpseModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class HeadlessHuskCorpseRenderer extends GeoEntityRenderer<HeadlessHuskCorpseEntity> {
   public HeadlessHuskCorpseRenderer(Context renderManager) {
      super(renderManager, new HeadlessHuskCorpseModel());
      this.shadowRadius = 0.6F;
   }

   public RenderType getRenderType(HeadlessHuskCorpseEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }

   protected float getDeathMaxRotation(HeadlessHuskCorpseEntity entityLivingBaseIn) {
      return 0.0F;
   }
}
