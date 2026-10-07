package net.gem19910816.dyairdrop.block.renderer;

import net.gem19910816.dyairdrop.block.entity.AirdropweaponTileEntity;
import net.gem19910816.dyairdrop.block.model.AirdropweaponBlockModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class AirdropweaponTileRenderer extends GeoBlockRenderer<AirdropweaponTileEntity> {
   public AirdropweaponTileRenderer() {
      super(new AirdropweaponBlockModel());
   }

   public RenderType getRenderType(AirdropweaponTileEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }
}
