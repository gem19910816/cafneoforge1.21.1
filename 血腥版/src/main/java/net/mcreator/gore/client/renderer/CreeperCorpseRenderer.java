package net.mcreator.gore.client.renderer;

import net.mcreator.gore.entity.CreeperCorpseEntity;
import net.mcreator.gore.entity.model.CreeperCorpseModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class CreeperCorpseRenderer extends GeoEntityRenderer<CreeperCorpseEntity> {
   public CreeperCorpseRenderer(Context renderManager) {
      super(renderManager, new CreeperCorpseModel());
      this.shadowRadius = 0.6F;
   }

   public RenderType getRenderType(CreeperCorpseEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }

   protected float getDeathMaxRotation(CreeperCorpseEntity entityLivingBaseIn) {
      return 0.0F;
   }
}
