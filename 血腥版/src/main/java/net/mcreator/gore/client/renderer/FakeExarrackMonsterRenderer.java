package net.mcreator.gore.client.renderer;

import net.mcreator.gore.entity.FakeExarrackMonsterEntity;
import net.mcreator.gore.entity.model.FakeExarrackMonsterModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class FakeExarrackMonsterRenderer extends GeoEntityRenderer<FakeExarrackMonsterEntity> {
   public FakeExarrackMonsterRenderer(Context renderManager) {
      super(renderManager, new FakeExarrackMonsterModel());
      this.shadowRadius = 7.0F;
   }

   public RenderType getRenderType(FakeExarrackMonsterEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }
}
