package net.mcreator.gore.client.renderer;

import net.mcreator.gore.entity.ZombieCorpseEntity;
import net.mcreator.gore.entity.model.ZombieCorpseModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class ZombieCorpseRenderer extends GeoEntityRenderer<ZombieCorpseEntity> {
   public ZombieCorpseRenderer(Context renderManager) {
      super(renderManager, new ZombieCorpseModel());
      this.shadowRadius = 0.6F;
   }

   public RenderType getRenderType(ZombieCorpseEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }

   protected float getDeathMaxRotation(ZombieCorpseEntity entityLivingBaseIn) {
      return 0.0F;
   }
}
