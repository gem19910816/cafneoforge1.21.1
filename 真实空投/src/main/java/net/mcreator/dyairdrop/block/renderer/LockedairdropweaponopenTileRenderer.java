package net.mcreator.dyairdrop.block.renderer;

import net.mcreator.dyairdrop.block.entity.LockedairdropweaponopenTileEntity;
import net.mcreator.dyairdrop.block.model.LockedairdropweaponopenBlockModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class LockedairdropweaponopenTileRenderer extends GeoBlockRenderer<LockedairdropweaponopenTileEntity> {
   public LockedairdropweaponopenTileRenderer() {
      super(new LockedairdropweaponopenBlockModel());
   }

   public RenderType getRenderType(LockedairdropweaponopenTileEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }
}
