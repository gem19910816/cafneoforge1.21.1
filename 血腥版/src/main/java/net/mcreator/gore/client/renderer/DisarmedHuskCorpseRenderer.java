package net.mcreator.gore.client.renderer;

import net.mcreator.gore.entity.DisarmedHuskCorpseEntity;
import net.mcreator.gore.entity.model.DisarmedHuskCorpseModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class DisarmedHuskCorpseRenderer extends GeoEntityRenderer<DisarmedHuskCorpseEntity> {
   public DisarmedHuskCorpseRenderer(Context renderManager) {
      super(renderManager, new DisarmedHuskCorpseModel());
      this.shadowRadius = 0.6F;
   }

   public RenderType getRenderType(DisarmedHuskCorpseEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }

   protected float getDeathMaxRotation(DisarmedHuskCorpseEntity entityLivingBaseIn) {
      return 0.0F;
   }
}
