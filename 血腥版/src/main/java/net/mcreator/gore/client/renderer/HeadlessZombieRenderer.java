package net.mcreator.gore.client.renderer;

import net.mcreator.gore.entity.HeadlessZombieEntity;
import net.mcreator.gore.entity.model.HeadlessZombieModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class HeadlessZombieRenderer extends GeoEntityRenderer<HeadlessZombieEntity> {
   public HeadlessZombieRenderer(Context renderManager) {
      super(renderManager, new HeadlessZombieModel());
      this.shadowRadius = 0.5F;
   }

   public RenderType getRenderType(HeadlessZombieEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }
}
