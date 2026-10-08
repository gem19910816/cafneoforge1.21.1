package net.mcreator.gore.client.renderer;

import net.mcreator.gore.entity.TheExarrackMonsterEntity;
import net.mcreator.gore.entity.model.TheExarrackMonsterModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class TheExarrackMonsterRenderer extends GeoEntityRenderer<TheExarrackMonsterEntity> {
   public TheExarrackMonsterRenderer(Context renderManager) {
      super(renderManager, new TheExarrackMonsterModel());
      this.shadowRadius = 4.0F;
   }

   public RenderType getRenderType(TheExarrackMonsterEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }
}
