package net.mcreator.dyairdrop.block.renderer;

import net.mcreator.dyairdrop.block.display.LockedairdroplargeopenDisplayItem;
import net.mcreator.dyairdrop.block.model.LockedairdroplargeopenDisplayModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class LockedairdroplargeopenDisplayItemRenderer extends GeoItemRenderer<LockedairdroplargeopenDisplayItem> {
   public LockedairdroplargeopenDisplayItemRenderer() {
      super(new LockedairdroplargeopenDisplayModel());
   }

   public RenderType getRenderType(LockedairdroplargeopenDisplayItem animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }
}
