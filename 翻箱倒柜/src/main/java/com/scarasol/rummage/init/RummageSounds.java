package com.scarasol.rummage.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredHolder;
import com.scarasol.rummage.RummageMod;

/**
 * @author Scarasol
 */
public class RummageSounds {
    public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(Registries.SOUND_EVENT, RummageMod.MODID);

    // Rummaging sounds
    public static final DeferredHolder<SoundEvent, SoundEvent> RUMMAGING_DELTAFORCE = register("rummaging_deltaforce");

    // Deltaforce series
    public static final DeferredHolder<SoundEvent, SoundEvent> NORMAL_DELTAFORCE = register("normal_deltaforce");
    public static final DeferredHolder<SoundEvent, SoundEvent> UNCOMMON_DELTAFORCE = register("uncommon_deltaforce");
    public static final DeferredHolder<SoundEvent, SoundEvent> RARE_DELTAFORCE = register("rare_deltaforce");
    public static final DeferredHolder<SoundEvent, SoundEvent> EPIC_DELTAFORCE = register("epic_deltaforce");
    public static final DeferredHolder<SoundEvent, SoundEvent> LEGENDARY_DELTAFORCE = register("legendary_deltaforce");
    public static final DeferredHolder<SoundEvent, SoundEvent> ARTIFACT_DELTAFORCE = register("artifact_deltaforce");

    // Nico series
    public static final DeferredHolder<SoundEvent, SoundEvent> NORMAL_NICO = register("normal_nico");
    public static final DeferredHolder<SoundEvent, SoundEvent> UNCOMMON_NICO = register("uncommon_nico");
    public static final DeferredHolder<SoundEvent, SoundEvent> RARE_NICO = register("rare_nico");
    public static final DeferredHolder<SoundEvent, SoundEvent> EPIC_NICO = register("epic_nico");
    public static final DeferredHolder<SoundEvent, SoundEvent> LEGENDARY_NICO = register("legendary_nico");
    public static final DeferredHolder<SoundEvent, SoundEvent> ARTIFACT_NICO = register("artifact_nico");

    // Misc
    public static final DeferredHolder<SoundEvent, SoundEvent> BACK_UP = register("back_up");

    private static DeferredHolder<SoundEvent, SoundEvent> register(String name) {
        return REGISTRY.register(name, () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(RummageMod.MODID, name)));
    }
}
