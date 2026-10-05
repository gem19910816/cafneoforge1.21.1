package net.mcreator.dyairdrop.block.renderer;

import net.mcreator.dyairdrop.block.entity.SafeopenTileEntity;
import net.mcreator.dyairdrop.block.model.SafeopenBlockModel;
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
