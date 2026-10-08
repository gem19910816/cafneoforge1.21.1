package net.mcreator.gore.client.renderer;

import net.mcreator.gore.entity.ZombieWithoutLegsAndArmCorpseEntity;
import net.mcreator.gore.entity.model.ZombieWithoutLegsAndArmCorpseModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class ZombieWithoutLegsAndArmCorpseRenderer extends GeoEntityRenderer<ZombieWithoutLegsAndArmCorpseEntity> {
   public ZombieWithoutLegsAndArmCorpseRenderer(Context renderManager) {
      super(renderManager, new ZombieWithoutLegsAndArmCorpseModel());
      this.shadowRadius = 0.6F;
   }

   public RenderType getRenderType(ZombieWithoutLegsAndArmCorpseEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }

   protected float getDeathMaxRotation(ZombieWithoutLegsAndArmCorpseEntity entityLivingBaseIn) {
      return 0.0F;
   }
}
