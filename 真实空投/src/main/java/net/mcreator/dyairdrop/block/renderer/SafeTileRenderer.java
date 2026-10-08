package net.mcreator.dyairdrop.block.renderer;

import net.mcreator.dyairdrop.block.entity.SafeTileEntity;
import net.mcreator.dyairdrop.block.model.SafeBlockModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class SafeTileRenderer extends GeoBlockRenderer<SafeTileEntity> {
   public SafeTileRenderer() {
      super(new SafeBlockModel());
   }

   public RenderType getRenderType(SafeTileEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }
}
