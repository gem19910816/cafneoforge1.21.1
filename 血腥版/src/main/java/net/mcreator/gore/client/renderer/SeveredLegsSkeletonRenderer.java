package net.mcreator.gore.client.renderer;

import net.mcreator.gore.entity.SeveredLegsSkeletonEntity;
import net.mcreator.gore.entity.model.SeveredLegsSkeletonModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SeveredLegsSkeletonRenderer extends GeoEntityRenderer<SeveredLegsSkeletonEntity> {
   public SeveredLegsSkeletonRenderer(Context renderManager) {
      super(renderManager, new SeveredLegsSkeletonModel());
      this.shadowRadius = 0.5F;
   }

   public RenderType getRenderType(SeveredLegsSkeletonEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }
}
