package net.mcreator.dyairdrop.init;

import net.mcreator.dyairdrop.client.renderer.AirdropRenderer;
import net.mcreator.dyairdrop.client.renderer.FlareRenderer;
import net.mcreator.dyairdrop.client.renderer.MedicalairdropRenderer;
import net.mcreator.dyairdrop.client.renderer.PlaneRenderer;
import net.mcreator.dyairdrop.client.renderer.SmallairdropRenderer;
import net.mcreator.dyairdrop.client.renderer.TransportplaneRenderer;
import net.mcreator.dyairdrop.client.renderer.WeaponairdropRenderer;
import net.mcreator.dyairdrop.DyairdropMod;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class DyairdropModEntityRenderers {
	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(DyairdropModEntities.PLANE.get(), PlaneRenderer::new);
		event.registerEntityRenderer(DyairdropModEntities.AIRDROP.get(), AirdropRenderer::new);
		event.registerEntityRenderer(DyairdropModEntities.SMALLAIRDROP.get(), SmallairdropRenderer::new);
		event.registerEntityRenderer(DyairdropModEntities.WEAPONAIRDROP.get(), WeaponairdropRenderer::new);
		event.registerEntityRenderer(DyairdropModEntities.MEDICALAIRDROP.get(), MedicalairdropRenderer::new);
		event.registerEntityRenderer(DyairdropModEntities.TRANSPORTPLANE.get(), TransportplaneRenderer::new);
		event.registerEntityRenderer(DyairdropModEntities.FLARE.get(), FlareRenderer::new);
	}
}
