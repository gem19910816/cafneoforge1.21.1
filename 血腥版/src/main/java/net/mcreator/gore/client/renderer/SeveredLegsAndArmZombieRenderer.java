package net.mcreator.gore.client.renderer;

import net.mcreator.gore.entity.SeveredLegsAndArmZombieEntity;
import net.mcreator.gore.entity.model.SeveredLegsAndArmZombieModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SeveredLegsAndArmZombieRenderer extends GeoEntityRenderer<SeveredLegsAndArmZombieEntity> {
   public SeveredLegsAndArmZombieRenderer(Context renderManager) {
      super(renderManager, new SeveredLegsAndArmZombieModel());
      this.shadowRadius = 0.5F;
   }

   public RenderType getRenderType(SeveredLegsAndArmZombieEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }
}
