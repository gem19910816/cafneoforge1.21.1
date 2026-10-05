package net.mcreator.dyairdrop.block.renderer;

import net.mcreator.dyairdrop.block.display.AirdroplargeDisplayItem;
import net.mcreator.dyairdrop.block.model.AirdroplargeDisplayModel;
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
