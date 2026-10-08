package net.mcreator.gore.client.renderer;

import net.mcreator.gore.entity.SeveredLegsZombieCorpseEntity;
import net.mcreator.gore.entity.model.SeveredLegsZombieCorpseModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SeveredLegsZombieCorpseRenderer extends GeoEntityRenderer<SeveredLegsZombieCorpseEntity> {
   public SeveredLegsZombieCorpseRenderer(Context renderManager) {
      super(renderManager, new SeveredLegsZombieCorpseModel());
      this.shadowRadius = 0.6F;
   }

   public RenderType getRenderType(SeveredLegsZombieCorpseEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }

   protected float getDeathMaxRotation(SeveredLegsZombieCorpseEntity entityLivingBaseIn) {
      return 0.0F;
   }
}
