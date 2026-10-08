package net.mcreator.gore.init;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class GoreEditionModParticleTypes {
   public static final DeferredRegister<ParticleType<?>> REGISTRY = DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, "gore_edition");
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> GENERIC_DEATH_BLOOD = REGISTRY.register(
      "generic_death_blood", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> GENERIC_HURT_BLOOD = REGISTRY.register(
      "generic_hurt_blood", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> GENERIC_50_BLOOD = REGISTRY.register(
      "generic_50_blood", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> GENERIC_BONE = REGISTRY.register("generic_bone", () -> new SimpleParticleType(true));
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> GENERIC_MEATS = REGISTRY.register(
      "generic_meats", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> SPIDER_HURT_BLOOD = REGISTRY.register(
      "spider_hurt_blood", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> SPIDER_50_BLOOD = REGISTRY.register(
      "spider_50_blood", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> SPIDER_MEATS = REGISTRY.register("spider_meats", () -> new SimpleParticleType(true));
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> SPIDER_DEATH_BLOOD = REGISTRY.register(
      "spider_death_blood", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> SPIDER_LEG = REGISTRY.register("spider_leg", () -> new SimpleParticleType(true));
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> SPIDER_EYE = REGISTRY.register("spider_eye", () -> new SimpleParticleType(true));
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> ENDER_HURT_BLOOD = REGISTRY.register(
      "ender_hurt_blood", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> ENDER_50_BLOOD = REGISTRY.register(
      "ender_50_blood", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> ENDER_DEATH_BLOOD = REGISTRY.register(
      "ender_death_blood", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> ENDER_BONE = REGISTRY.register("ender_bone", () -> new SimpleParticleType(true));
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> ENDER_MEATS = REGISTRY.register("ender_meats", () -> new SimpleParticleType(true));
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> SPECTRAL_BONE = REGISTRY.register(
      "spectral_bone", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> SPECTRAL_HURT_BLOOD = REGISTRY.register(
      "spectral_hurt_blood", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> SPECTRAL_50_BLOOD = REGISTRY.register(
      "spectral_50_blood", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> SPECTRAL_MEATS = REGISTRY.register(
      "spectral_meats", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> WARDEN_DEATH_BLOOD = REGISTRY.register(
      "warden_death_blood", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> WARDEN_BONE = REGISTRY.register("warden_bone", () -> new SimpleParticleType(true));
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> WARDEN_MEATS = REGISTRY.register("warden_meats", () -> new SimpleParticleType(true));
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> WARDEN_HURT_BLOOD = REGISTRY.register(
      "warden_hurt_blood", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> WARDEN_50_BLOOD = REGISTRY.register(
      "warden_50_blood", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> BEE_DEATH_BLOOD = REGISTRY.register(
      "bee_death_blood", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> BEE_LEG = REGISTRY.register("bee_leg", () -> new SimpleParticleType(true));
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> BEE_MEATS = REGISTRY.register("bee_meats", () -> new SimpleParticleType(true));
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> BEE_HURT_BLOOD = REGISTRY.register(
      "bee_hurt_blood", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> BEE_50_BLOOD = REGISTRY.register("bee_50_blood", () -> new SimpleParticleType(true));
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> BEE_BLOOD_DROP = REGISTRY.register(
      "bee_blood_drop", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> BEE_BLOOD_DROP_2 = REGISTRY.register(
      "bee_blood_drop_2", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> BEE_BLOOD_DROP_3 = REGISTRY.register(
      "bee_blood_drop_3", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> SKELETON_HURT_DUST = REGISTRY.register(
      "skeleton_hurt_dust", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> SKELETON_50_DUST = REGISTRY.register(
      "skeleton_50_dust", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> SKELETON_PIECES = REGISTRY.register(
      "skeleton_pieces", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> GENERIC_SKELETON_PIECES = REGISTRY.register(
      "generic_skeleton_pieces", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> WITHER_SKELETON_HURT_DUST = REGISTRY.register(
      "wither_skeleton_hurt_dust", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> WITHER_SKELETON_50_DUST = REGISTRY.register(
      "wither_skeleton_50_dust", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> WITHER_SKELETON_PIECES = REGISTRY.register(
      "wither_skeleton_pieces", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> ENDER_SKELETON_PIECES = REGISTRY.register(
      "ender_skeleton_pieces", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> BLAZE_HURT_DUST = REGISTRY.register(
      "blaze_hurt_dust", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> BLAZE_50_DUST = REGISTRY.register(
      "blaze_50_dust", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> BLAZE_DUST_DROPS = REGISTRY.register(
      "blaze_dust_drops", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> WARDEN_BLOOD_DROPS = REGISTRY.register(
      "warden_blood_drops", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> SPIDER_BLOOD_DROPS = REGISTRY.register(
      "spider_blood_drops", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> ENDER_BLOOD_DROPS = REGISTRY.register(
      "ender_blood_drops", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> GENERIC_BLOOD_DROPS = REGISTRY.register(
      "generic_blood_drops", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> SPECTRAL_BLOOD_DROPS = REGISTRY.register(
      "spectral_blood_drops", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> BLAZE_DEATH_DUST = REGISTRY.register(
      "blaze_death_dust", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> BLAZE_PIECES = REGISTRY.register("blaze_pieces", () -> new SimpleParticleType(true));
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> SLIME_HURT_SLIME = REGISTRY.register(
      "slime_hurt_slime", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> SLIME_50_SLIME = REGISTRY.register(
      "slime_50_slime", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> SLIME_SLIME_DROPS = REGISTRY.register(
      "slime_slime_drops", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> SLIME_DEATH_SLIME = REGISTRY.register(
      "slime_death_slime", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> MAGMA_CUBE_HURT_MAGMA_CUBE = REGISTRY.register(
      "magma_cube_hurt_magma_cube", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> MAGMA_CUBE_50_MAGMA_CUBE = REGISTRY.register(
      "magma_cube_50_magma_cube", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> MAGMA_CUBE_DEATH_MAGMA_CUBE = REGISTRY.register(
      "magma_cube_death_magma_cube", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> MAGMA_CUBE_MAGMA_CUBE_DROPS = REGISTRY.register(
      "magma_cube_magma_cube_drops", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> SKELETON_DUST_DROPS = REGISTRY.register(
      "skeleton_dust_drops", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> WITHER_SKELETON_DUST_DROPS = REGISTRY.register(
      "wither_skeleton_dust_drops", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> SPECTRAL_SKELETON_PIECES = REGISTRY.register(
      "spectral_skeleton_pieces", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> WARDEN_SKELETON_PIECES = REGISTRY.register(
      "warden_skeleton_pieces", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> BEE_PIECES = REGISTRY.register("bee_pieces", () -> new SimpleParticleType(true));
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> IRON_GOLEM_DEATH_DUST = REGISTRY.register(
      "iron_golem_death_dust", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> IRON_GOLEM_DUST_DROPS = REGISTRY.register(
      "iron_golem_dust_drops", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> IRON_GOLEM_HURT_DUST = REGISTRY.register(
      "iron_golem_hurt_dust", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> IRON_GOLEM_50_DUST = REGISTRY.register(
      "iron_golem_50_dust", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> GUARDIAN_HURT_DUST = REGISTRY.register(
      "guardian_hurt_dust", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> GUARDIAN_50_DUST = REGISTRY.register(
      "guardian_50_dust", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> GUARDIAN_DUST_DROPS = REGISTRY.register(
      "guardian_dust_drops", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> GUARDIAN_DEATH_DUST = REGISTRY.register(
      "guardian_death_dust", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> GUARDIAN_SKELETON_PIECES = REGISTRY.register(
      "guardian_skeleton_pieces", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> ELDER_GUARDIAN_HURT_DUST = REGISTRY.register(
      "elder_guardian_hurt_dust", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> ELDER_GUARDIAN_50_DUST = REGISTRY.register(
      "elder_guardian_50_dust", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> ELDER_GUARDIAN_DEATH_DUST = REGISTRY.register(
      "elder_guardian_death_dust", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> ELDER_GUARDIAN_DUST_DROPS = REGISTRY.register(
      "elder_guardian_dust_drops", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> ASHES_PARTICLES = REGISTRY.register(
      "ashes_particles", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> FIRE_PARTICLE = REGISTRY.register(
      "fire_particle", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> GENERIC_TRANSPARENT_DEATH_BLOOD = REGISTRY.register(
      "generic_transparent_death_blood", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> SPECTRAL_DEATH_BLOOD = REGISTRY.register(
      "spectral_death_blood", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> ELDER_GUARDIAN_SKELETON_TAIL = REGISTRY.register(
      "elder_guardian_skeleton_tail", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> GUARDIAN_SKELETON_TAIL = REGISTRY.register(
      "guardian_skeleton_tail", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> GENERIC_LEGACY_HURT_BLOOD = REGISTRY.register(
      "generic_legacy_hurt_blood", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> GENERIC_LEGACY_GORE = REGISTRY.register(
      "generic_legacy_gore", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> ZOMBIE_ARM_PARTICLE = REGISTRY.register(
      "zombie_arm_particle", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> ZOMBIE_LEG_PARTICLE = REGISTRY.register(
      "zombie_leg_particle", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> ZOMBIE_BODY_REMAINS = REGISTRY.register(
      "zombie_body_remains", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> CREEPER_SPORES = REGISTRY.register(
      "creeper_spores", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> MYSTIC_SMOKE = REGISTRY.register("mystic_smoke", () -> new SimpleParticleType(true));
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> SPIRAL_SMOKE = REGISTRY.register("spiral_smoke", () -> new SimpleParticleType(true));
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> SPIDER_LEGACY_HURT_BLOOD = REGISTRY.register(
      "spider_legacy_hurt_blood", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> SPIDER_LEGACY_GORE = REGISTRY.register(
      "spider_legacy_gore", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> ENDER_LEGACY_HURT_BLOOD = REGISTRY.register(
      "ender_legacy_hurt_blood", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> ENDER_LEGACY__GORE = REGISTRY.register(
      "ender_legacy__gore", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> SPECTRAL_LEGACY_HURT_BLOOD = REGISTRY.register(
      "spectral_legacy_hurt_blood", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> SPECTRAL_LEGACY_GORE = REGISTRY.register(
      "spectral_legacy_gore", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> WARDEN_LEGACY_HURT_BLOOD = REGISTRY.register(
      "warden_legacy_hurt_blood", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> WARDEN_LEGACY_GORE = REGISTRY.register(
      "warden_legacy_gore", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> BEE_LEGACY_HURT_BLOOD = REGISTRY.register(
      "bee_legacy_hurt_blood", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> BEE_LEGACY_GORE = REGISTRY.register(
      "bee_legacy_gore", () -> new SimpleParticleType(true)
   );
   public static final DeferredHolder<ParticleType<?>, SimpleParticleType> SKELETON_LEGACY_HURT_DUST = REGISTRY.register(
      "skeleton_legacy_hurt_dust", () -> new SimpleParticleType(true)
   );
}
