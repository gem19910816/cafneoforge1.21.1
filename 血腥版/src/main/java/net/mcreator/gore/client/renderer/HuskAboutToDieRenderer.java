package net.mcreator.gore.client.renderer;

import net.mcreator.gore.entity.HuskAboutToDieEntity;
import net.mcreator.gore.entity.model.HuskAboutToDieModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class HuskAboutToDieRenderer extends GeoEntityRenderer<HuskAboutToDieEntity> {
   public HuskAboutToDieRenderer(Context renderManager) {
      super(renderManager, new HuskAboutToDieModel());
      this.shadowRadius = 0.5F;
   }

   public RenderType getRenderType(HuskAboutToDieEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }
}
