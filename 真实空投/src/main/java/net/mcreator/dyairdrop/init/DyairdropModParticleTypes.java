package net.mcreator.dyairdrop.init;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.registries.DeferredHolder;

public class DyairdropModParticleTypes {
   public static final DeferredRegister<ParticleType<?>> REGISTRY = DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, "dyairdrop");
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> SIGNALSMOKE = REGISTRY.register("signalsmoke", () -> new SimpleParticleType(true));

   public DyairdropModParticleTypes() {
   }
}
