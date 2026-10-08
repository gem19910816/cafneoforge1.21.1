package net.mcreator.gore.client.renderer;

import net.mcreator.gore.entity.SeveredLegsCordycepsZombieEntity;
import net.mcreator.gore.entity.model.SeveredLegsCordycepsZombieModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SeveredLegsCordycepsZombieRenderer extends GeoEntityRenderer<SeveredLegsCordycepsZombieEntity> {
   public SeveredLegsCordycepsZombieRenderer(Context renderManager) {
      super(renderManager, new SeveredLegsCordycepsZombieModel());
      this.shadowRadius = 0.5F;
   }

   public RenderType getRenderType(SeveredLegsCordycepsZombieEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }
}
