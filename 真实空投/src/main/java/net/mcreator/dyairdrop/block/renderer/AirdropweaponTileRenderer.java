package net.mcreator.dyairdrop.block.renderer;

import net.mcreator.dyairdrop.block.entity.AirdropweaponTileEntity;
import net.mcreator.dyairdrop.block.model.AirdropweaponBlockModel;
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
