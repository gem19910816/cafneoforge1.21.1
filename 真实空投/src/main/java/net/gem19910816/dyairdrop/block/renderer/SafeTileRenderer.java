package net.gem19910816.dyairdrop.block.renderer;

import net.gem19910816.dyairdrop.block.entity.SafeTileEntity;
import net.gem19910816.dyairdrop.block.model.SafeBlockModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class SafeTileRenderer extends GeoBlockRenderer<SafeTileEntity> {
   public SafeTileRenderer() {
      super(new SafeBlockModel());
   }

   public RenderType getRenderType(SafeTileEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }
}
