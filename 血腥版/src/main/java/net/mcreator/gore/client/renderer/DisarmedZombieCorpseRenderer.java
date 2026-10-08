package net.mcreator.gore.client.renderer;

import net.mcreator.gore.entity.DisarmedZombieCorpseEntity;
import net.mcreator.gore.entity.model.DisarmedZombieCorpseModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class DisarmedZombieCorpseRenderer extends GeoEntityRenderer<DisarmedZombieCorpseEntity> {
   public DisarmedZombieCorpseRenderer(Context renderManager) {
      super(renderManager, new DisarmedZombieCorpseModel());
      this.shadowRadius = 0.6F;
   }

   public RenderType getRenderType(DisarmedZombieCorpseEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }

   protected float getDeathMaxRotation(DisarmedZombieCorpseEntity entityLivingBaseIn) {
      return 0.0F;
   }
}
