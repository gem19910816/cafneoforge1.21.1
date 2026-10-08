package net.mcreator.gore.client.renderer;

import net.mcreator.gore.entity.AluxinationEntity;
import net.mcreator.gore.entity.layer.AluxinationLayer;
import net.mcreator.gore.entity.model.AluxinationModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class AluxinationRenderer extends GeoEntityRenderer<AluxinationEntity> {
   public AluxinationRenderer(Context renderManager) {
      super(renderManager, new AluxinationModel());
      this.shadowRadius = 0.0F;
      this.addRenderLayer(new AluxinationLayer(this));
   }

   public RenderType getRenderType(AluxinationEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }
}
