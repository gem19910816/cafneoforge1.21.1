/*
 * Decompiled with CFR 0.152.
 */
package net.mcreator.doomsdaydecoration.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.registries.DeferredHolder;

public class DoomsdayDecorationModSounds {
    public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, (String)"doomsday_decoration");
    public static final DeferredHolder<SoundEvent, SoundEvent> HUZAI = REGISTRY.register("huzai", () -> SoundEvent.createVariableRangeEvent((ResourceLocation)ResourceLocation.fromNamespaceAndPath("doomsday_decoration", "huzai")));
    public static final DeferredHolder<SoundEvent, SoundEvent> OTTO = REGISTRY.register("otto", () -> SoundEvent.createVariableRangeEvent((ResourceLocation)ResourceLocation.fromNamespaceAndPath("doomsday_decoration", "otto")));
    public static final DeferredHolder<SoundEvent, SoundEvent> HHH = REGISTRY.register("hhh", () -> SoundEvent.createVariableRangeEvent((ResourceLocation)ResourceLocation.fromNamespaceAndPath("doomsday_decoration", "hhh")));
    public static final DeferredHolder<SoundEvent, SoundEvent> GGG = REGISTRY.register("ggg", () -> SoundEvent.createVariableRangeEvent((ResourceLocation)ResourceLocation.fromNamespaceAndPath("doomsday_decoration", "ggg")));
    public static final DeferredHolder<SoundEvent, SoundEvent> ROBLOX = REGISTRY.register("roblox", () -> SoundEvent.createVariableRangeEvent((ResourceLocation)ResourceLocation.fromNamespaceAndPath("doomsday_decoration", "roblox")));
}

