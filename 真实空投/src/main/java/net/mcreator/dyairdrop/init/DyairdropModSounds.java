package net.mcreator.dyairdrop.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.registries.DeferredHolder;

public class DyairdropModSounds {
   public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, "dyairdrop");
   public static final DeferredHolder<SoundEvent, SoundEvent> PLANESOUND = REGISTRY.register(
      "planesound", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("dyairdrop", "planesound"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> PWCORRECT = REGISTRY.register(
      "pwcorrect", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("dyairdrop", "pwcorrect"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> PWWRONG = REGISTRY.register(
      "pwwrong", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("dyairdrop", "pwwrong"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> CHECK = REGISTRY.register("check", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("dyairdrop", "check")));

   public DyairdropModSounds() {
   }
}
