package net.mcreator.gore.client.renderer;

import net.mcreator.gore.entity.DisarmedZombieEntity;
import net.mcreator.gore.entity.model.DisarmedZombieModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class DisarmedZombieRenderer extends GeoEntityRenderer<DisarmedZombieEntity> {
   public DisarmedZombieRenderer(Context renderManager) {
      super(renderManager, new DisarmedZombieModel());
      this.shadowRadius = 0.5F;
   }

   public RenderType getRenderType(DisarmedZombieEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }
}
