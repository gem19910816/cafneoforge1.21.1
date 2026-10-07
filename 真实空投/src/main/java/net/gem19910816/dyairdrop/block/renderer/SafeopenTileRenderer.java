package net.gem19910816.dyairdrop.block.renderer;

import net.gem19910816.dyairdrop.block.entity.SafeopenTileEntity;
import net.gem19910816.dyairdrop.block.model.SafeopenBlockModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class SafeopenTileRenderer extends GeoBlockRenderer<SafeopenTileEntity> {
   public SafeopenTileRenderer() {
      super(new SafeopenBlockModel());
   }

   public RenderType getRenderType(SafeopenTileEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }
}
