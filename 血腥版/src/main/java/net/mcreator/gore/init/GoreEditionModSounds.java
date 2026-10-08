package net.mcreator.gore.init;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class GoreEditionModSounds {
   public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, "gore_edition");
   public static final DeferredHolder<SoundEvent, SoundEvent> GORE_HURT_SOUND = REGISTRY.register(
      "gore_hurt_sound", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "gore_hurt_sound"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> GORE_50_SOUND = REGISTRY.register(
      "gore_50_sound", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "gore_50_sound"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> GORE_50_BLOOD_SOUND = REGISTRY.register(
      "gore_50_blood_sound", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "gore_50_blood_sound"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> GORE_SEVERE_DAMAGE_SOUND = REGISTRY.register(
      "gore_severe_damage_sound", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "gore_severe_damage_sound"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> GORE_SKELETON_HURT_SOUND = REGISTRY.register(
      "gore.skeleton_hurt_sound", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "gore.skeleton_hurt_sound"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> GORE_SKELETON_50_SOUND = REGISTRY.register(
      "gore.skeleton_50_sound", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "gore.skeleton_50_sound"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> GORE_SKELETON_DEATH_PIECES_SOUND = REGISTRY.register(
      "gore.skeleton_death_pieces_sound",
      () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "gore.skeleton_death_pieces_sound"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> GORE_CONCENTRED_DAMAGE_IN_ONE_PIECE_SOUND = REGISTRY.register(
      "gore_concentred_damage_in_one_piece_sound",
      () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "gore_concentred_damage_in_one_piece_sound"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> EXTRA_SMASHED = REGISTRY.register(
      "extra_smashed", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "extra_smashed"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> GORE_INFERNAL_MACHINE_EXPLODE = REGISTRY.register(
      "gore.infernal_machine_explode",
      () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "gore.infernal_machine_explode"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> GORE_SLIME_HURT_SOUND = REGISTRY.register(
      "gore.slime_hurt_sound", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "gore.slime_hurt_sound"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> GORE_SLIME_50_SOUND = REGISTRY.register(
      "gore.slime_50_sound", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "gore.slime_50_sound"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> GORE_SKELETON_CONCENTRED_DAMAGE_IN_ONE_PIECE_SOUND = REGISTRY.register(
      "gore.skeleton_concentred_damage_in_one_piece_sound",
      () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "gore.skeleton_concentred_damage_in_one_piece_sound"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> GORE_METAL_DEATH_PIECES = REGISTRY.register(
      "gore.metal_death_pieces", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "gore.metal_death_pieces"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> GORE_IRON_GOLEM_DEATH_SOUND = REGISTRY.register(
      "gore.iron_golem_death_sound",
      () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "gore.iron_golem_death_sound"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> DUKEPLUS_SQUISHED = REGISTRY.register(
      "dukeplus.squished", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "dukeplus.squished"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> DUKEPLUS_GORE_BOUNCE = REGISTRY.register(
      "dukeplus.gore_bounce", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "dukeplus.gore_bounce"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> GORE_SKULL_DESTROY = REGISTRY.register(
      "gore.skull_destroy", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "gore.skull_destroy"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> GORE_SHORT_POURING_OUT_BLOOD = REGISTRY.register(
      "gore.short_pouring_out_blood",
      () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "gore.short_pouring_out_blood"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> GORE_MULTIPLE_CUTS_SOUND = REGISTRY.register(
      "gore_multiple_cuts_sound", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "gore_multiple_cuts_sound"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> GORE_DISARMED_ZOMBIE_AMBIENT = REGISTRY.register(
      "gore_disarmed_zombie_ambient",
      () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "gore_disarmed_zombie_ambient"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> GORE_BIG_POURING_OUT_BLOOD = REGISTRY.register(
      "gore_big_pouring_out_blood",
      () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "gore_big_pouring_out_blood"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> GORE_BIG_BLOOD_SPLASH = REGISTRY.register(
      "gore_big_blood_splash", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "gore_big_blood_splash"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> GORE_OLD_EXPLODED_SOUND = REGISTRY.register(
      "gore_old_exploded_sound", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "gore_old_exploded_sound"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> GORE_DEATH_SOUND = REGISTRY.register(
      "gore_death_sound", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "gore_death_sound"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> GORE_BRUTAL_HURT_SOUND = REGISTRY.register(
      "gore_brutal_hurt_sound", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "gore_brutal_hurt_sound"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> GORE_INCINERATED_SOUND = REGISTRY.register(
      "gore_incinerated_sound", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "gore_incinerated_sound"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> GORE_EXTERNAL_BURNING_HURT_SOUND = REGISTRY.register(
      "gore_external_burning_hurt_sound",
      () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "gore_external_burning_hurt_sound"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> GORE_EXECUTE_MOB_READY_COOLDOWN_SOUND = REGISTRY.register(
      "gore_execute_mob_ready_cooldown_sound",
      () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "gore_execute_mob_ready_cooldown_sound"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> GORE_HURT_SEVERE_SOUND = REGISTRY.register(
      "gore_hurt_severe_sound", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "gore_hurt_severe_sound"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> GORE_NEEDLE = REGISTRY.register(
      "gore_needle", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "gore_needle"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> GORE_NEEDLE_II = REGISTRY.register(
      "gore_needle_ii", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "gore_needle_ii"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> GORE_NEEDLE_III = REGISTRY.register(
      "gore_needle_iii", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "gore_needle_iii"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> GORE_NEEDLE_III_V = REGISTRY.register(
      "gore_needle_iii_v", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "gore_needle_iii_v"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> GORE_LEGACY_DEATH_SOUND = REGISTRY.register(
      "gore.legacy_death_sound", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "gore.legacy_death_sound"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> GORE_LEGACY_HURT_SOUND = REGISTRY.register(
      "gore.legacy_hurt_sound", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "gore.legacy_hurt_sound"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> GORE_BRUTALITY_SERIES_1 = REGISTRY.register(
      "gore.brutality_series.1", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "gore.brutality_series.1"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> GORE_BITE = REGISTRY.register(
      "gore.bite", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "gore.bite"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_FLESH_EATER_DEATH = REGISTRY.register(
      "entity.flesh_eater.death", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "entity.flesh_eater.death"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_FLESH_EATER_STEPS = REGISTRY.register(
      "entity.flesh_eater.steps", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "entity.flesh_eater.steps"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> GORE_BRUTALITY_SERIES_2 = REGISTRY.register(
      "gore.brutality_series.2", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "gore.brutality_series.2"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> GORE_DEVORATION = REGISTRY.register(
      "gore.devoration", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "gore.devoration"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> OCTARM_SPECTRAL_GIFT_OPEN = REGISTRY.register(
      "octarm_spectral_gift_open",
      () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "octarm_spectral_gift_open"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> GIFT_OPEN = REGISTRY.register(
      "gift_open", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "gift_open"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> SQUITCHGUN_SHOOTING = REGISTRY.register(
      "squitchgun.shooting", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "squitchgun.shooting"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> SQUITCHGUN_RECEIVE_HEART = REGISTRY.register(
      "squitchgun.receive_heart", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "squitchgun.receive_heart"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> SQUITCHGUN_HEART_IMPACT = REGISTRY.register(
      "squitchgun.heart_impact", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "squitchgun.heart_impact"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> GORE_VOMIT = REGISTRY.register(
      "gore.vomit", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "gore.vomit"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> ITEM_GRENADE_PIN_PULL = REGISTRY.register(
      "item.grenade.pin_pull", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "item.grenade.pin_pull"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> SKELETON_BOUNCE = REGISTRY.register(
      "skeleton.bounce", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "skeleton.bounce"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> GRENADE_OF_GREEK_FIRE_SHOOT = REGISTRY.register(
      "grenade_of_greek_fire_shoot",
      () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "grenade_of_greek_fire_shoot"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> GORE_BRUTALITY_STAB = REGISTRY.register(
      "gore.brutality_stab", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "gore.brutality_stab"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> ALUXINATION_AMBIENT = REGISTRY.register(
      "aluxination.ambient", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "aluxination.ambient"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_NOWIND_PREPARING = REGISTRY.register(
      "entity.nowind_preparing", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "entity.nowind_preparing"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_NOWIND_EXPLODE = REGISTRY.register(
      "entity.nowind_explode", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "entity.nowind_explode"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_NOWIND_HURT = REGISTRY.register(
      "entity.nowind_hurt", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "entity.nowind_hurt"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_HENGEYON_AMBIENT = REGISTRY.register(
      "entity.hengeyon_ambient", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "entity.hengeyon_ambient"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_HENGEYON_DIE = REGISTRY.register(
      "entity.hengeyon_die", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "entity.hengeyon_die"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> ITEM_LIGHTNING_ORB_ACTIVATE = REGISTRY.register(
      "item.lightning_orb_activate",
      () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "item.lightning_orb_activate"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_EXARRACK_MONSTER_JUMPSCARE = REGISTRY.register(
      "entity.exarrack_monster_jumpscare",
      () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "entity.exarrack_monster_jumpscare"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> ITEM_CORDYCEPS_PROPAGATING = REGISTRY.register(
      "item.cordyceps_propagating",
      () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "item.cordyceps_propagating"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> ITEM_CORDYCEPS_FINISH = REGISTRY.register(
      "item.cordyceps_finish", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "item.cordyceps_finish"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_HENGEYON_HURT = REGISTRY.register(
      "entity.hengeyon_hurt", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "entity.hengeyon_hurt"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_HENGEYON_WARNING = REGISTRY.register(
      "entity.hengeyon_warning", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "entity.hengeyon_warning"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_HENGEYON_SHOT = REGISTRY.register(
      "entity.hengeyon_shot", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "entity.hengeyon_shot"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_THE_EXARRACK_MONSTER_SCREAM = REGISTRY.register(
      "entity.the_exarrack_monster_scream",
      () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "entity.the_exarrack_monster_scream"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_DEFORMITY_AMBIENT = REGISTRY.register(
      "entity.deformity.ambient", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "entity.deformity.ambient"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_DEFORMITY_HURT = REGISTRY.register(
      "entity.deformity.hurt", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "entity.deformity.hurt"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_DEFORMITY_DIE = REGISTRY.register(
      "entity.deformity.die", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "entity.deformity.die"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_DEFORMITY_SPAWN = REGISTRY.register(
      "entity.deformity.spawn", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "entity.deformity.spawn"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> GORE_STUCKED = REGISTRY.register(
      "gore.stucked", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "gore.stucked"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_HENGEYON_AGGRO = REGISTRY.register(
      "entity.hengeyon_aggro", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "entity.hengeyon_aggro"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> MUSIC_DISC_SHOP = REGISTRY.register(
      "music_disc.shop", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "music_disc.shop"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> AMBIENT_ASHES = REGISTRY.register(
      "ambient.ashes", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "ambient.ashes"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_DEFORMITY_FOR_ASHES_SCREAM = REGISTRY.register(
      "entity.deformity_for_ashes.scream",
      () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "entity.deformity_for_ashes.scream"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> MUSIC_DISC_INTERNAL_INSANITY = REGISTRY.register(
      "music_disc.internal_insanity",
      () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "music_disc.internal_insanity"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> MUSIC_DISC_COLLAPSE = REGISTRY.register(
      "music_disc.collapse", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "music_disc.collapse"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> GORE_BRUTALITY_SERIES_3 = REGISTRY.register(
      "gore.brutality_series.3", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "gore.brutality_series.3"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> AMBIENT_ASHES_WIND = REGISTRY.register(
      "ambient.ashes_wind", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "ambient.ashes_wind"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_EXARRACK_THINGS_IDLE = REGISTRY.register(
      "entity.exarrack_things.idle",
      () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "entity.exarrack_things.idle"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_EXARRACK_DEMON_IDLE = REGISTRY.register(
      "entity.exarrack_demon.idle",
      () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "entity.exarrack_demon.idle"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_EXARRACK_MONSTER_ANGRY = REGISTRY.register(
      "entity.exarrack_monster.angry",
      () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "entity.exarrack_monster.angry"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> GORE_BRUTALITY_SERIES_4 = REGISTRY.register(
      "gore.brutality_series.4", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "gore.brutality_series.4"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> PORTAL_ASHES_GATEWAY_AMBIENT = REGISTRY.register(
      "portal.ashes_gateway.ambient",
      () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "portal.ashes_gateway.ambient"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> PORTAL_ASHES_GATEWAY_OPEN = REGISTRY.register(
      "portal.ashes_gateway.open",
      () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "portal.ashes_gateway.open"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> MUSIC_DISC__0 = REGISTRY.register(
      "music_disc.-0", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "music_disc.-0"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> AMBIENT_VALLEY_OF_ASHES = REGISTRY.register(
      "ambient.valley_of_ashes", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "ambient.valley_of_ashes"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> MUSIC_ASHES = REGISTRY.register(
      "music.ashes", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "music.ashes"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_EXARRACK_HYDRA_SCREAM = REGISTRY.register(
      "entity.exarrack_hydra.scream",
      () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "entity.exarrack_hydra.scream"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> DAMAGE_SPIRAL_TWISTED_DIE = REGISTRY.register(
      "damage.spiral_twisted.die",
      () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "damage.spiral_twisted.die"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> DAMAGE_SPIRAL_TWISTED_HURT = REGISTRY.register(
      "damage.spiral_twisted.hurt",
      () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "damage.spiral_twisted.hurt"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> ITEM_SPIRAL_TORNADO_SUMMONED = REGISTRY.register(
      "item.spiral.tornado_summoned",
      () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "item.spiral.tornado_summoned"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_SPIRAL_TORNADO_IDLE = REGISTRY.register(
      "entity.spiral_tornado.idle",
      () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "entity.spiral_tornado.idle"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_SPIRAL_TORNADO_HURT = REGISTRY.register(
      "entity.spiral_tornado.hurt",
      () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "entity.spiral_tornado.hurt"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_SPIRAL_TORNADO_DIE = REGISTRY.register(
      "entity.spiral_tornado.die",
      () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "entity.spiral_tornado.die"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> ITEM_SPIRAL_RELEASE = REGISTRY.register(
      "item.spiral.release", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "item.spiral.release"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> ITEM_SPIRAL_ATTRACT = REGISTRY.register(
      "item.spiral.attract", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "item.spiral.attract"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> BLOCK_ASHTRAY_CYCLE_CHARGED = REGISTRY.register(
      "block.ashtray_cycle.charged",
      () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "block.ashtray_cycle.charged"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_HENGEYON_SCREAM = REGISTRY.register(
      "entity.hengeyon.scream", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "entity.hengeyon.scream"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_ASHES_BLACK_HOLE_SPAWN = REGISTRY.register(
      "entity.ashes_black_hole.spawn",
      () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "entity.ashes_black_hole.spawn"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> BLOCK_EXARRACK_STEP = REGISTRY.register(
      "block.exarrack.step", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "block.exarrack.step"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> BLOCK_EXARRACK_PLACE = REGISTRY.register(
      "block.exarrack.place", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("gore_edition", "block.exarrack.place"))
   );
}
