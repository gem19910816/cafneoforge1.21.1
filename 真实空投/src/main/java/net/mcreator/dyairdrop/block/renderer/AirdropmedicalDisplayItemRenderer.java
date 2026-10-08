package net.mcreator.dyairdrop.block.renderer;

import net.mcreator.dyairdrop.block.display.AirdropmedicalDisplayItem;
import net.mcreator.dyairdrop.block.model.AirdropmedicalDisplayModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class AirdropmedicalDisplayItemRenderer extends GeoItemRenderer<AirdropmedicalDisplayItem> {
   public AirdropmedicalDisplayItemRenderer() {
      super(new AirdropmedicalDisplayModel());
   }

   public RenderType getRenderType(AirdropmedicalDisplayItem animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }
}
