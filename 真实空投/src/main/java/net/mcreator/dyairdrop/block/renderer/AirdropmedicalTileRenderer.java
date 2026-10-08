package net.mcreator.dyairdrop.block.renderer;

import net.mcreator.dyairdrop.block.entity.AirdropmedicalTileEntity;
import net.mcreator.dyairdrop.block.model.AirdropmedicalBlockModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoBlockRenderer;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;

public class AirdropmedicalTileRenderer extends GeoBlockRenderer<AirdropmedicalTileEntity> {
   public AirdropmedicalTileRenderer() {
      super(new AirdropmedicalBlockModel());
      this.addRenderLayer(new AutoGlowingGeoLayer(this));
   }

   public RenderType getRenderType(AirdropmedicalTileEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }
}
