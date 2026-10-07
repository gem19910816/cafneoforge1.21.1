package net.gem19910816.dyairdrop.init;

import net.gem19910816.dyairdrop.DyairdropMod;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class DyairdropModParticleTypes {
	public static final DeferredRegister<net.minecraft.core.particles.ParticleType<?>> REGISTRY = DeferredRegister.create(Registries.PARTICLE_TYPE, DyairdropMod.MODID);
	public static final DeferredHolder<net.minecraft.core.particles.ParticleType<?>, SimpleParticleType> SIGNALSMOKE = REGISTRY.register("signalsmoke",
			() -> new SimpleParticleType(true));
}
