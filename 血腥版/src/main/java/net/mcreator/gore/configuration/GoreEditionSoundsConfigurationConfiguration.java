package net.mcreator.gore.configuration;

import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.ModConfigSpec.Builder;
import net.neoforged.neoforge.common.ModConfigSpec.ConfigValue;

public class GoreEditionSoundsConfigurationConfiguration {
   public static final Builder BUILDER = new Builder();
   public static final ConfigValue<Double> GORE_DEATH_SOUND = BUILDER.define("gore_death_sound", 1.0);
   public static final ConfigValue<Double> GORE_HURT_SOUND = BUILDER.define("gore_hurt_sound", 1.0);
   public static final ConfigValue<Double> GORE_50_SOUND = BUILDER.define("gore_50_sound", 1.0);
   public static final ConfigValue<Double> GORE_50_BLOOD_SOUND = BUILDER.define("gore_50_blood_sound", 1.0);
   public static final ConfigValue<Double> GORE_CONCENTRED_DAMAGE_SOUND = BUILDER.define("gore_concentred_damage_sound", 1.0);
   public static final ConfigValue<Double> GORE_SKELETON_DEATH_PIECES_SOUND = BUILDER.define("gore_skeleton_death_pieces_sound", 1.0);
   public static final ConfigValue<Double> GORE_SKELETON_HURT_SOUND = BUILDER.define("gore_skeleton_hurt_sound", 1.0);
   public static final ConfigValue<Double> GORE_SKELETON_50_SOUND = BUILDER.define("gore_skeleton_50_sound", 1.0);
   public static final ConfigValue<Double> GORE_SKELETON_CONCENTRED_DAMAGE_SOUND = BUILDER.define("gore_skeleton_concentred_damage_sound", 1.0);
   public static final ConfigValue<Double> GORE_INFERNAL_MACHINE_EXPLODE = BUILDER.define("gore_infernal_machine_explode", 1.0);
   public static final ConfigValue<Double> GORE_METAL_DEATH_PIECES = BUILDER.define("gore_metal_death_pieces", 1.0);
   public static final ConfigValue<Double> GORE_IRON_GOLEM_DEATH_SOUND = BUILDER.define("gore_iron_golem_death_sound", 1.0);
   public static final ConfigValue<Double> GORE_SLIME_HURT_SOUND = BUILDER.define("gore_slime_hurt_sound", 1.0);
   public static final ConfigValue<Double> GORE_SLIME_50_SOUND = BUILDER.define("gore_slime_50_sound", 1.0);
   public static final ConfigValue<Double> DUKEPLUS_GORE_BOUNCE = BUILDER.define("dukeplus_gore_bounce", 1.0);
   public static final ConfigValue<Double> GORE_SKULL_DESTROY = BUILDER.define("gore_skull_destroy", 3.0);
   public static final ConfigValue<Double> GORE_SHORT_POURING_OUT_BLOOD = BUILDER.define("gore_short_pouring_out_blood", 1.0);
   public static final ConfigValue<Double> DUKEPLUS_SQUISHED = BUILDER.define("dukeplus_squished", 1.0);
   public static final ConfigValue<Double> GORE_MULTIPLE_CUTS_SOUND = BUILDER.define("gore_multiple_cuts_sound", 1.0);
   public static final ConfigValue<Double> GORE_DISARMED_ZOMBIE_AMBIENT = BUILDER.define("gore_disarmed_zombie_ambient", 1.0);
   public static final ConfigValue<Double> GORE_BIG_BLOOD_SPLASH = BUILDER.define("gore_big_blood_splash", 1.0);
   public static final ConfigValue<Double> GORE_BIG_POURING_OUT_BLOOD = BUILDER.define("gore_big_pouring_out_blood", 1.0);
   public static final ConfigValue<Double> GORE_OLD_EXPLODED_SOUND = BUILDER.define("gore_old_exploded_sound", 3.0);
   public static final ConfigValue<Double> GORE_SEVERE_DAMAGE_SOUND = BUILDER.define("gore_severe_damage_sound", 1.0);
   public static final ConfigValue<Double> GORE_BRUTAL_HURT_SOUND = BUILDER.define("gore_brutal_hurt_sound", 1.0);
   public static final ConfigValue<Double> GORE_INCINERATED_SOUND = BUILDER.define("gore_incinerated_sound", 1.0);
   public static final ConfigValue<Double> GORE_EXTERNAL_BURNING_HURT_SOUND = BUILDER.define("gore_external_burning_hurt_sound", 1.0);
   public static final ConfigValue<Double> GORE_HURT_SEVERE_SOUND = BUILDER.define("gore_hurt_severe_sound", 1.0);
   public static final ConfigValue<Double> GORE_LEGACY_DEATH_SOUND = BUILDER.define("gore_legacy_death_sound", 2.0);
   public static final ConfigValue<Double> GORE_LEGACY_HURT_SOUND = BUILDER.define("gore_legacy_hurt_sound", 1.0);
   public static final ConfigValue<Double> GORE_BRUTALITY_SERIES_1 = BUILDER.define("gore_brutality_series_1", 0.5);
   public static final ConfigValue<Double> GORE_BITE = BUILDER.define("gore_bite", 1.0);
   public static final ModConfigSpec SPEC = BUILDER.build();

   static {
      BUILDER.push("SFX");
      BUILDER.pop();
   }
}
