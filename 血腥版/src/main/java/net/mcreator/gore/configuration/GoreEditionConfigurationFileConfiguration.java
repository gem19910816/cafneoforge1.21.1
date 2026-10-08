package net.mcreator.gore.configuration;

import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.ModConfigSpec.Builder;
import net.neoforged.neoforge.common.ModConfigSpec.ConfigValue;

public class GoreEditionConfigurationFileConfiguration {
   public static final Builder BUILDER = new Builder();
   public static final ConfigValue<String> BOSS_DEATH_MODE = BUILDER.comment("(Classic only). Avaible, repeat , fast repeat , big explode , none")
      .define("Mode of the classic death mode", "none");
   public static final ConfigValue<Boolean> RIBS_HURT_HALF = BUILDER.comment("(Classic only)").define("Broken ribs at half health ", true);
   public static final ConfigValue<Double> BLOOD_IN_SCREEN_TICKS = BUILDER.comment(
         "20 = one second. Modify the time it takes to clean a layer of blood without water, seconds x 20. (vanilla = 30)"
      )
      .define("Blood in screen clean ticks", 30.0);
   public static final ConfigValue<Double> XZ_AREA_SIZE = BUILDER.define("Manual XZ area size of particles", 0.3);
   public static final ConfigValue<Double> Y_AREA_SIZE = BUILDER.define("Manual Y area size of particles", 0.5);
   public static final ConfigValue<Double> PARTICLE_MULTIPLICATOR = BUILDER.define("Particles multiplier", 1.1);
   public static final ConfigValue<Double> PARTICLE_SPEED_MULTIPLICATOR = BUILDER.define("Particles speed power multiplier", 0.4);
   public static final ConfigValue<Double> CENTER_Y_MOB = BUILDER.comment("(Y +)").define("Y spawn center of particles for mobs", 1.3);
   public static final ConfigValue<Double> BONES_PARTICLES_MULTIPLICATOR = BUILDER.define("Bones particles multiplier", 3.0);
   public static final ConfigValue<Double> BONES_PARTICLES_SPEED_MULTIPLICATOR = BUILDER.define("Bones particles speed power multiplier", 2.0);
   public static final ConfigValue<Double> BRUTAL_HURT_PARTICLES_MULTIPLICATOR = BUILDER.define("Brutal hurt particles multiplier", 7.0);
   public static final ConfigValue<Double> MAX_HURT_PARTICLES_AMOUNT = BUILDER.define("Max hurt particles spawn", 1000.0);
   public static final ConfigValue<Double> MAX_DEATH_PARTICLES_AMOUNT = BUILDER.define("Max death particles spawn", 1000.0);
   public static final ConfigValue<Double> MAX_BRUTAL_HURT_PARTICLES_AMOUNT = BUILDER.define("Max brutal hurt particles spawn", 1000.0);
   public static final ConfigValue<Double> MAX_BONES_PARTICLES_AMOUNT = BUILDER.define("Max bones particles spawn", 1000.0);
   public static final ConfigValue<Double> MAX_DEATH_DUST_PARTICLES_AMOUNT = BUILDER.define("Max death dust particles spawn", 1000.0);
   public static final ConfigValue<Double> FIRE_PARTICLES_REDUCTOR = BUILDER.define("Fire particles reductor", 1.0);
   public static final ConfigValue<Double> HITBOX_MULTIPLIER_X = BUILDER.define("Particle spawning area hitbox modifier with multiplication X", 1.4);
   public static final ConfigValue<Double> HITBOX_MULTIPLIER_Y = BUILDER.define("Particle spawning area hitbox modifier with multiplication Y", 1.1);
   public static final ConfigValue<Double> EASE_IN_HURT_AMOUNT = BUILDER.define("Ease in hurt amount", 1.9);
   public static final ModConfigSpec SPEC = BUILDER.build();

   static {
      BUILDER.push("FX");
      BUILDER.pop();
      BUILDER.push("PARTICLES - GENERAL");
      BUILDER.pop();
   }
}
