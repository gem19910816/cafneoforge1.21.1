package net.mcreator.dyairdrop.block.renderer;

import net.mcreator.dyairdrop.block.entity.Safe2TileEntity;
import net.mcreator.dyairdrop.block.model.Safe2BlockModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class Safe2TileRenderer extends GeoBlockRenderer<Safe2TileEntity> {
   public Safe2TileRenderer() {
      super(new Safe2BlockModel());
   }

   public RenderType getRenderType(Safe2TileEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }
}
