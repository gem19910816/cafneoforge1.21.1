package net.gem19910816.dyairdrop.block.renderer;

import net.gem19910816.dyairdrop.block.display.AirdroplargeDisplayItem;
import net.gem19910816.dyairdrop.block.model.AirdroplargeDisplayModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class AirdroplargeDisplayItemRenderer extends GeoItemRenderer<AirdroplargeDisplayItem> {
   public AirdroplargeDisplayItemRenderer() {
      super(new AirdroplargeDisplayModel());
   }

   public RenderType getRenderType(AirdroplargeDisplayItem animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }
}
