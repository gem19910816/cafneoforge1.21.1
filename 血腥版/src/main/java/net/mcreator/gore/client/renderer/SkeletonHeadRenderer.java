package net.mcreator.gore.client.renderer;

import net.mcreator.gore.entity.SkeletonHeadEntity;
import net.mcreator.gore.entity.model.SkeletonHeadModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SkeletonHeadRenderer extends GeoEntityRenderer<SkeletonHeadEntity> {
   public SkeletonHeadRenderer(Context renderManager) {
      super(renderManager, new SkeletonHeadModel());
      this.shadowRadius = 0.0F;
   }

   public RenderType getRenderType(SkeletonHeadEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }
}
