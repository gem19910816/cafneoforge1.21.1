package net.gem19910816.dyairdrop.block.renderer;

import net.gem19910816.dyairdrop.block.display.SafeopenDisplayItem;
import net.gem19910816.dyairdrop.block.model.SafeopenDisplayModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class SafeopenDisplayItemRenderer extends GeoItemRenderer<SafeopenDisplayItem> {
   public SafeopenDisplayItemRenderer() {
      super(new SafeopenDisplayModel());
   }

   public RenderType getRenderType(SafeopenDisplayItem animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }
}
