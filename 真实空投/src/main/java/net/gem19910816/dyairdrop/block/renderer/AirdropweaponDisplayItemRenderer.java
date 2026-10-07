package net.gem19910816.dyairdrop.block.renderer;

import net.gem19910816.dyairdrop.block.display.AirdropweaponDisplayItem;
import net.gem19910816.dyairdrop.block.model.AirdropweaponDisplayModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class AirdropweaponDisplayItemRenderer extends GeoItemRenderer<AirdropweaponDisplayItem> {
   public AirdropweaponDisplayItemRenderer() {
      super(new AirdropweaponDisplayModel());
   }

   public RenderType getRenderType(AirdropweaponDisplayItem animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }
}
