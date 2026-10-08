package net.mcreator.gore.init;

import net.mcreator.gore.potion.AcidEffectMobEffect;
import net.mcreator.gore.potion.DeafnessMobEffect;
import net.mcreator.gore.potion.DeformityMobEffect;
import net.mcreator.gore.potion.ExplosiveMobEffect;
import net.mcreator.gore.potion.VulnerabilityMobEffect;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class GoreEditionModMobEffects {
   public static final DeferredRegister<MobEffect> REGISTRY = DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, "gore_edition");
   public static final DeferredHolder<MobEffect, MobEffect> XASH_ACID = REGISTRY.register("xash_acid", () -> new AcidEffectMobEffect());
   public static final DeferredHolder<MobEffect, MobEffect> VULNERABILITY = REGISTRY.register("vulnerability", () -> new VulnerabilityMobEffect());
   public static final DeferredHolder<MobEffect, MobEffect> EXPLODING = REGISTRY.register("exploding", () -> new ExplosiveMobEffect());
   public static final DeferredHolder<MobEffect, MobEffect> SPIRAL_TWIST = REGISTRY.register("spiral_twist", () -> new DeformityMobEffect());
   public static final DeferredHolder<MobEffect, MobEffect> DEAFNESS = REGISTRY.register("deafness", () -> new DeafnessMobEffect());
}
