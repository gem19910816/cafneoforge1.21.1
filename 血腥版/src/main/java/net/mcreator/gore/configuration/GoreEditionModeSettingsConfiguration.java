package net.mcreator.gore.configuration;

import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.ModConfigSpec.Builder;
import net.neoforged.neoforge.common.ModConfigSpec.ConfigValue;

public class GoreEditionModeSettingsConfiguration {
   public static final Builder BUILDER = new Builder();
   public static final ConfigValue<Double> HURT_INTENSITY = BUILDER.comment("0 is none, 1 legacy, 2 is anarchy").define("Hurt gore system mode", 2.0);
   public static final ConfigValue<Double> DEATH_INTENSITY = BUILDER.comment("0 is none, 1 legacy, 2 is anarchy").define("Death gore system mode", 2.0);
   public static final ConfigValue<String> EXPLODE_MODE = BUILDER.comment("Only haves classic").define("Explode gore system mode", "classic");
   public static final ConfigValue<Double> TOUGHNESS_ZOMBIE_SPAWN_PROBABILITIES = BUILDER.comment("Now works with %")
      .define("Toughness entities spawn probabilities", 30.0);
   public static final ConfigValue<Double> TOUGHNESS_ZOMBIE_RESURRECTION = BUILDER.define("Toughness entities survive probabilities", 70.0);
   public static final ConfigValue<Boolean> BLOOD_IN_SCREEN = BUILDER.define("Blood in screen", true);
   public static final ConfigValue<Boolean> CORPSES = BUILDER.define("Corpses", true);
   public static final ConfigValue<Boolean> INVISIBILITYWHENENTITYDIES = BUILDER.define("Invisibility when entity dies", true);
   public static final ConfigValue<Boolean> INVISIBLITYBECAUSEENTITYCHANGESTATE = BUILDER.define(
      "Invisibility when toughness reveals or corpses are true", true
   );
   public static final ConfigValue<Boolean> CANCELPARTICLESWHENENTTYCHANGESSTATE = BUILDER.define(
      "Cancel death particles because corpses is true or toughness reveals", true
   );
   public static final ConfigValue<Boolean> CANCELPARTICLESWHENENTTYEXPLODESSSTATE = BUILDER.define(
      "Cancel explode particles because corpses is true or toughness reveals", true
   );
   public static final ConfigValue<Boolean> SHIELDSBLOCKANYEXPLODES = BUILDER.comment(
         "Port of shields blocking explosions from one point nineteen point three and one point nineteen point four"
      )
      .define("One point nineteen point three Shields?", false);
   public static final ConfigValue<Boolean> TOUGHNESS_ZOMBIE_BURN = BUILDER.define("GE undead creatures burns", true);
   public static final ConfigValue<Boolean> CORPSES_CAN_EXPLODE = BUILDER.define("Corpses can explode", true);
   public static final ConfigValue<Double> MIN_TIME_TO_EXPLODE_CORPSE = BUILDER.comment("Used for non toughness corpses")
      .define("Min time required for the corpse can explode", 20.0);
   public static final ConfigValue<Double> MAX_TIME_TO_EXPLODE_CORPSE = BUILDER.comment("Used for toughness corpses")
      .define("Max time required for the corpse can explode", 100.0);
   public static final ConfigValue<Boolean> WARNING_SCREEN = BUILDER.define("Warning screen", false);
   public static final ConfigValue<Boolean> BURNING = BUILDER.define("Smoke particles while burninG. BURNING", true);
   public static final ConfigValue<String> BHURT = BUILDER.comment("Fire , FAshes , None")
      .define("Hurt flames particles because external damage while burning. BHURT", "FAshes");
   public static final ConfigValue<Boolean> FHURT = BUILDER.define("Hurt particles because burning. FHURT", true);
   public static final ConfigValue<String> FDEATH = BUILDER.comment("Fire , FAshes , None").define("Death with burning particles. FDEATH", "Fire");
   public static final ConfigValue<String> BHURT_METHOD = BUILDER.comment("manual , hitbox").define("BHURT METHOD", "hitbox");
   public static final ConfigValue<String> FHURT_METHOD = BUILDER.comment("manual , hitbox").define("FHURT METHOD", "hitbox");
   public static final ConfigValue<String> FDEATH_METHOD = BUILDER.comment("manual , hitbox").define("FDEATH METHOD", "hitbox");
   public static final ConfigValue<String> BURNING_METHOD = BUILDER.comment("manual , hitbox").define("BURNING METHOD", "hitbox");
   public static final ConfigValue<Boolean> INSANE_THINGS = BUILDER.define("Insane things", true);
   public static final ConfigValue<Boolean> CREEPER_CORPSE_EXPLODE_ON_HIT = BUILDER.define("Creeper corpse can explode", true);
   public static final ConfigValue<Boolean> CREEPER_CORPSE_MULTIPLICATION = BUILDER.define("Creeper corpse can plant Creeper Grass", true);
   public static final ConfigValue<Boolean> TOUGHNESS_RAGE = BUILDER.define("Toughness rage", true);
   public static final ModConfigSpec SPEC = BUILDER.build();

   static {
      BUILDER.push("General");
      BUILDER.pop();
      BUILDER.push("Fire");
      BUILDER.pop();
      BUILDER.push("Insane things");
      BUILDER.pop();
   }
}
