package net.mcreator.gore.client.renderer;

import net.mcreator.gore.entity.SeveredLegsAndOneArmCordycepsZombieEntity;
import net.mcreator.gore.entity.model.SeveredLegsAndOneArmCordycepsZombieModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SeveredLegsAndOneArmCordycepsZombieRenderer extends GeoEntityRenderer<SeveredLegsAndOneArmCordycepsZombieEntity> {
   public SeveredLegsAndOneArmCordycepsZombieRenderer(Context renderManager) {
      super(renderManager, new SeveredLegsAndOneArmCordycepsZombieModel());
      this.shadowRadius = 0.5F;
   }

   public RenderType getRenderType(
      SeveredLegsAndOneArmCordycepsZombieEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick
   ) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }
}
