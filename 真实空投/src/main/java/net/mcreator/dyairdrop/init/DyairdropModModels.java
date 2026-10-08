package net.mcreator.dyairdrop.init;

import net.mcreator.dyairdrop.client.model.Modelflare_gun;
import net.mcreator.dyairdrop.client.model.Modelmplane;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.neoforge.client.event.EntityRenderersEvent.RegisterLayerDefinitions;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.EventBusSubscriber.Bus;

@EventBusSubscriber(
   bus = Bus.MOD,
   value = {Dist.CLIENT}
)
public class DyairdropModModels {
   public DyairdropModModels() {
   }

   @SubscribeEvent
   public static void registerLayerDefinitions(RegisterLayerDefinitions event) {
      event.registerLayerDefinition(Modelflare_gun.LAYER_LOCATION, Modelflare_gun::createBodyLayer);
      event.registerLayerDefinition(Modelmplane.LAYER_LOCATION, Modelmplane::createBodyLayer);
   }
}
