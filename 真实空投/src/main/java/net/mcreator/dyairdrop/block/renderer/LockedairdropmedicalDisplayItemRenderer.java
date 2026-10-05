package net.mcreator.dyairdrop.block.renderer;

import net.mcreator.dyairdrop.block.display.LockedairdropmedicalDisplayItem;
import net.mcreator.dyairdrop.block.model.LockedairdropmedicalDisplayModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class LockedairdropmedicalDisplayItemRenderer extends GeoItemRenderer<LockedairdropmedicalDisplayItem> {
   public LockedairdropmedicalDisplayItemRenderer() {
      super(new LockedairdropmedicalDisplayModel());
   }

   public RenderType getRenderType(LockedairdropmedicalDisplayItem animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }
}
