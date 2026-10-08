package net.mcreator.gore.client.renderer;

import net.mcreator.gore.entity.HuskWithoutLegsAndArmCorpseEntity;
import net.mcreator.gore.entity.model.HuskWithoutLegsAndArmCorpseModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class HuskWithoutLegsAndArmCorpseRenderer extends GeoEntityRenderer<HuskWithoutLegsAndArmCorpseEntity> {
   public HuskWithoutLegsAndArmCorpseRenderer(Context renderManager) {
      super(renderManager, new HuskWithoutLegsAndArmCorpseModel());
      this.shadowRadius = 0.6F;
   }

   public RenderType getRenderType(HuskWithoutLegsAndArmCorpseEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }

   protected float getDeathMaxRotation(HuskWithoutLegsAndArmCorpseEntity entityLivingBaseIn) {
      return 0.0F;
   }
}
