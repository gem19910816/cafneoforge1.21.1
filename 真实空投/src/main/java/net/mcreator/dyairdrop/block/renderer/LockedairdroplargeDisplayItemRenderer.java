package net.mcreator.dyairdrop.block.renderer;

import net.mcreator.dyairdrop.block.display.LockedairdroplargeDisplayItem;
import net.mcreator.dyairdrop.block.model.LockedairdroplargeDisplayModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class LockedairdroplargeDisplayItemRenderer extends GeoItemRenderer<LockedairdroplargeDisplayItem> {
   public LockedairdroplargeDisplayItemRenderer() {
      super(new LockedairdroplargeDisplayModel());
   }

   public RenderType getRenderType(LockedairdroplargeDisplayItem animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }
}
