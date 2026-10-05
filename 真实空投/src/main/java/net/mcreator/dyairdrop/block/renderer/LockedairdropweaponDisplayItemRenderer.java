package net.mcreator.dyairdrop.block.renderer;

import net.mcreator.dyairdrop.block.display.LockedairdropweaponDisplayItem;
import net.mcreator.dyairdrop.block.model.LockedairdropweaponDisplayModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class LockedairdropweaponDisplayItemRenderer extends GeoItemRenderer<LockedairdropweaponDisplayItem> {
   public LockedairdropweaponDisplayItemRenderer() {
      super(new LockedairdropweaponDisplayModel());
   }

   public RenderType getRenderType(LockedairdropweaponDisplayItem animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }
}
