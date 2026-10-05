package net.mcreator.dyairdrop.block.renderer;

import net.mcreator.dyairdrop.block.entity.LockedairdropmedicalopenTileEntity;
import net.mcreator.dyairdrop.block.model.LockedairdropmedicalopenBlockModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoBlockRenderer;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;

public class LockedairdropmedicalopenTileRenderer extends GeoBlockRenderer<LockedairdropmedicalopenTileEntity> {
   public LockedairdropmedicalopenTileRenderer() {
      super(new LockedairdropmedicalopenBlockModel());
      this.addRenderLayer(new AutoGlowingGeoLayer(this));
   }

   public RenderType getRenderType(LockedairdropmedicalopenTileEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }
}
