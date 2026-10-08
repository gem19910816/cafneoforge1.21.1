package net.mcreator.dyairdrop.block.renderer;

import net.mcreator.dyairdrop.block.display.SafeDisplayItem;
import net.mcreator.dyairdrop.block.model.SafeDisplayModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class SafeDisplayItemRenderer extends GeoItemRenderer<SafeDisplayItem> {
   public SafeDisplayItemRenderer() {
      super(new SafeDisplayModel());
   }

   public RenderType getRenderType(SafeDisplayItem animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }
}
