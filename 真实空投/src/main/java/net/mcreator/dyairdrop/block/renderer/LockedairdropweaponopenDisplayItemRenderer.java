package net.mcreator.dyairdrop.block.renderer;

import net.mcreator.dyairdrop.block.display.LockedairdropweaponopenDisplayItem;
import net.mcreator.dyairdrop.block.model.LockedairdropweaponopenDisplayModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class LockedairdropweaponopenDisplayItemRenderer extends GeoItemRenderer<LockedairdropweaponopenDisplayItem> {
   public LockedairdropweaponopenDisplayItemRenderer() {
      super(new LockedairdropweaponopenDisplayModel());
   }

   public RenderType getRenderType(LockedairdropweaponopenDisplayItem animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }
}
