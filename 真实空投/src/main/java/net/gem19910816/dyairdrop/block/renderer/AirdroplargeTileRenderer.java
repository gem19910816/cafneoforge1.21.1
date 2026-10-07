package net.gem19910816.dyairdrop.block.renderer;

import net.gem19910816.dyairdrop.block.entity.AirdroplargeTileEntity;
import net.gem19910816.dyairdrop.block.model.AirdroplargeBlockModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoBlockRenderer;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;

public class AirdroplargeTileRenderer extends GeoBlockRenderer<AirdroplargeTileEntity> {
   public AirdroplargeTileRenderer() {
      super(new AirdroplargeBlockModel());
      this.addRenderLayer(new AutoGlowingGeoLayer(this));
   }

   public RenderType getRenderType(AirdroplargeTileEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }
}
