package net.mcreator.dyairdrop.init;

import net.mcreator.dyairdrop.DyairdropMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class DyairdropModSounds {
	public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(Registries.SOUND_EVENT, DyairdropMod.MODID);
	public static final DeferredHolder<SoundEvent, SoundEvent> PLANESOUND = REGISTRY.register("planesound",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("dyairdrop", "planesound")));
	public static final DeferredHolder<SoundEvent, SoundEvent> PWCORRECT = REGISTRY.register("pwcorrect",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("dyairdrop", "pwcorrect")));
	public static final DeferredHolder<SoundEvent, SoundEvent> PWWRONG = REGISTRY.register("pwwrong",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("dyairdrop", "pwwrong")));
	public static final DeferredHolder<SoundEvent, SoundEvent> CHECK = REGISTRY.register("check",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("dyairdrop", "check")));
}
