package net.mcreator.dyairdrop.block.renderer;

import net.mcreator.dyairdrop.block.display.LockedairdropmedicalopenDisplayItem;
import net.mcreator.dyairdrop.block.model.LockedairdropmedicalopenDisplayModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class LockedairdropmedicalopenDisplayItemRenderer extends GeoItemRenderer<LockedairdropmedicalopenDisplayItem> {
   public LockedairdropmedicalopenDisplayItemRenderer() {
      super(new LockedairdropmedicalopenDisplayModel());
   }

   public RenderType getRenderType(LockedairdropmedicalopenDisplayItem animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }
}
