package net.mcreator.dyairdrop.block.renderer;

import net.mcreator.dyairdrop.block.display.Safe2DisplayItem;
import net.mcreator.dyairdrop.block.model.Safe2DisplayModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class Safe2DisplayItemRenderer extends GeoItemRenderer<Safe2DisplayItem> {
   public Safe2DisplayItemRenderer() {
      super(new Safe2DisplayModel());
   }

   public RenderType getRenderType(Safe2DisplayItem animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }
}
