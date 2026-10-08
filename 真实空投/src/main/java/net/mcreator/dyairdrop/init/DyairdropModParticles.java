package net.mcreator.dyairdrop.init;

import net.mcreator.dyairdrop.client.particle.SignalairParticle;
import net.minecraft.core.particles.ParticleType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.EventBusSubscriber.Bus;

@EventBusSubscriber(
   bus = Bus.MOD,
   value = {Dist.CLIENT}
)
public class DyairdropModParticles {
   public DyairdropModParticles() {
   }

   @SubscribeEvent
   public static void registerParticles(RegisterParticleProvidersEvent event) {
      event.registerSpriteSet((ParticleType)DyairdropModParticleTypes.SIGNALSMOKE.get(), SignalairParticle::provider);
   }
}
