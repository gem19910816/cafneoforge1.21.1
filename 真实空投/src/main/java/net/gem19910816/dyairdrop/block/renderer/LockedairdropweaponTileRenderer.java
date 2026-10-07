package net.gem19910816.dyairdrop.block.renderer;

import net.gem19910816.dyairdrop.block.entity.LockedairdropweaponTileEntity;
import net.gem19910816.dyairdrop.block.model.LockedairdropweaponBlockModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class LockedairdropweaponTileRenderer extends GeoBlockRenderer<LockedairdropweaponTileEntity> {
   public LockedairdropweaponTileRenderer() {
      super(new LockedairdropweaponBlockModel());
   }

   public RenderType getRenderType(LockedairdropweaponTileEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }
}
