package net.mcreator.dyairdrop.init;

import net.mcreator.dyairdrop.client.renderer.AirdropRenderer;
import net.mcreator.dyairdrop.client.renderer.FlareRenderer;
import net.mcreator.dyairdrop.client.renderer.MedicalairdropRenderer;
import net.mcreator.dyairdrop.client.renderer.PlaneRenderer;
import net.mcreator.dyairdrop.client.renderer.SmallairdropRenderer;
import net.mcreator.dyairdrop.client.renderer.TransportplaneRenderer;
import net.mcreator.dyairdrop.client.renderer.WeaponairdropRenderer;
import net.minecraft.world.entity.EntityType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.neoforge.client.event.EntityRenderersEvent.RegisterRenderers;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.EventBusSubscriber.Bus;

@EventBusSubscriber(
   bus = Bus.MOD,
   value = {Dist.CLIENT}
)
public class DyairdropModEntityRenderers {
   public DyairdropModEntityRenderers() {
   }

   @SubscribeEvent
   public static void registerEntityRenderers(RegisterRenderers event) {
      event.registerEntityRenderer((EntityType)DyairdropModEntities.PLANE.get(), PlaneRenderer::new);
      event.registerEntityRenderer((EntityType)DyairdropModEntities.AIRDROP.get(), AirdropRenderer::new);
      event.registerEntityRenderer((EntityType)DyairdropModEntities.SMALLAIRDROP.get(), SmallairdropRenderer::new);
      event.registerEntityRenderer((EntityType)DyairdropModEntities.WEAPONAIRDROP.get(), WeaponairdropRenderer::new);
      event.registerEntityRenderer((EntityType)DyairdropModEntities.MEDICALAIRDROP.get(), MedicalairdropRenderer::new);
      event.registerEntityRenderer((EntityType)DyairdropModEntities.TRANSPORTPLANE.get(), TransportplaneRenderer::new);
      event.registerEntityRenderer((EntityType)DyairdropModEntities.FLARE.get(), FlareRenderer::new);
   }
}
