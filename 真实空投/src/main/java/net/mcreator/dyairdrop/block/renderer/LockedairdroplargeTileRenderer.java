package net.mcreator.dyairdrop.block.renderer;

import net.mcreator.dyairdrop.block.entity.LockedairdroplargeTileEntity;
import net.mcreator.dyairdrop.block.model.LockedairdroplargeBlockModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoBlockRenderer;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;

public class LockedairdroplargeTileRenderer extends GeoBlockRenderer<LockedairdroplargeTileEntity> {
   public LockedairdroplargeTileRenderer() {
      super(new LockedairdroplargeBlockModel());
      this.addRenderLayer(new AutoGlowingGeoLayer(this));
   }

   public RenderType getRenderType(LockedairdroplargeTileEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }
}
