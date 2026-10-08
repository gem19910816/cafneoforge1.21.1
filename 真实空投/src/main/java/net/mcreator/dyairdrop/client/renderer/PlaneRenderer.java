package net.mcreator.dyairdrop.client.renderer;

import net.mcreator.dyairdrop.client.model.Modelmplane;
import net.mcreator.dyairdrop.entity.PlaneEntity;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;

public class PlaneRenderer extends MobRenderer<PlaneEntity, Modelmplane<PlaneEntity>> {
   public PlaneRenderer(Context context) {
      super(context, new Modelmplane(context.bakeLayer(Modelmplane.LAYER_LOCATION)), 5.0F);
   }

   public ResourceLocation getTextureLocation(PlaneEntity entity) {
      return ResourceLocation.parse("dyairdrop:textures/entities/plane.png");
   }
}
