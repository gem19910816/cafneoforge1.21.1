package net.mcreator.gore.client.renderer;

import net.mcreator.gore.entity.SeveredLegsHuskCorpseEntity;
import net.mcreator.gore.entity.model.SeveredLegsHuskCorpseModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SeveredLegsHuskCorpseRenderer extends GeoEntityRenderer<SeveredLegsHuskCorpseEntity> {
   public SeveredLegsHuskCorpseRenderer(Context renderManager) {
      super(renderManager, new SeveredLegsHuskCorpseModel());
      this.shadowRadius = 0.6F;
   }

   public RenderType getRenderType(SeveredLegsHuskCorpseEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }

   protected float getDeathMaxRotation(SeveredLegsHuskCorpseEntity entityLivingBaseIn) {
      return 0.0F;
   }
}
