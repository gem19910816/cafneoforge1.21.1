package net.mcreator.gore.client.renderer;

import net.mcreator.gore.entity.HeadlessZombieCorpseEntity;
import net.mcreator.gore.entity.model.HeadlessZombieCorpseModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class HeadlessZombieCorpseRenderer extends GeoEntityRenderer<HeadlessZombieCorpseEntity> {
   public HeadlessZombieCorpseRenderer(Context renderManager) {
      super(renderManager, new HeadlessZombieCorpseModel());
      this.shadowRadius = 0.6F;
   }

   public RenderType getRenderType(HeadlessZombieCorpseEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }

   protected float getDeathMaxRotation(HeadlessZombieCorpseEntity entityLivingBaseIn) {
      return 0.0F;
   }
}
