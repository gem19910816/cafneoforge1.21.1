package net.gem19910816.dyairdrop.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.gem19910816.dyairdrop.entity.PlaneEntity;
import net.gem19910816.dyairdrop.client.model.Modelmplane;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class PlaneRenderer extends MobRenderer<PlaneEntity, Modelmplane<PlaneEntity>> {
   public PlaneRenderer(EntityRendererProvider.Context context) {
      super(context, new Modelmplane(context.bakeLayer(Modelmplane.LAYER_LOCATION)), 5.0F);
   }

   public ResourceLocation getTextureLocation(PlaneEntity entity) {
      return ResourceLocation.parse("dyairdrop:textures/entities/plane.png");
   }
}
