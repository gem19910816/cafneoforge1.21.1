package net.mcreator.gore.client.renderer;

import net.mcreator.gore.entity.SeveredlegsZombieEntity;
import net.mcreator.gore.entity.model.SeveredlegsZombieModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SeveredlegsZombieRenderer extends GeoEntityRenderer<SeveredlegsZombieEntity> {
   public SeveredlegsZombieRenderer(Context renderManager) {
      super(renderManager, new SeveredlegsZombieModel());
      this.shadowRadius = 0.5F;
   }

   public RenderType getRenderType(SeveredlegsZombieEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }
}
