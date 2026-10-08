package net.mcreator.gore.client.renderer;

import net.mcreator.gore.entity.CordycepsZombieEntity;
import net.mcreator.gore.entity.model.CordycepsZombieModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class CordycepsZombieRenderer extends GeoEntityRenderer<CordycepsZombieEntity> {
   public CordycepsZombieRenderer(Context renderManager) {
      super(renderManager, new CordycepsZombieModel());
      this.shadowRadius = 0.5F;
   }

   public RenderType getRenderType(CordycepsZombieEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }
}
