package net.mcreator.gore.client.renderer;

import net.mcreator.gore.entity.CordycepsHeadlessZombieEntity;
import net.mcreator.gore.entity.model.CordycepsHeadlessZombieModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class CordycepsHeadlessZombieRenderer extends GeoEntityRenderer<CordycepsHeadlessZombieEntity> {
   public CordycepsHeadlessZombieRenderer(Context renderManager) {
      super(renderManager, new CordycepsHeadlessZombieModel());
      this.shadowRadius = 0.5F;
   }

   public RenderType getRenderType(CordycepsHeadlessZombieEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }
}
