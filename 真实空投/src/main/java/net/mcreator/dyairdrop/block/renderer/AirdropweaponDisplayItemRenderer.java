package net.mcreator.dyairdrop.block.renderer;

import net.mcreator.dyairdrop.block.display.AirdropweaponDisplayItem;
import net.mcreator.dyairdrop.block.model.AirdropweaponDisplayModel;
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
