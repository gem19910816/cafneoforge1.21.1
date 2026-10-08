package net.mcreator.gore.client.renderer;

import net.mcreator.gore.entity.CrushedZombieEntity;
import net.mcreator.gore.entity.model.CrushedZombieModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class CrushedZombieRenderer extends GeoEntityRenderer<CrushedZombieEntity> {
   public CrushedZombieRenderer(Context renderManager) {
      super(renderManager, new CrushedZombieModel());
      this.shadowRadius = 0.5F;
   }

   public RenderType getRenderType(CrushedZombieEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }
}
