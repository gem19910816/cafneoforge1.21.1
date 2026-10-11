package net.mcreator.gore.configuration;

import net.neoforged.neoforge.common.ModConfigSpec;

public final class ZombieShoveConfiguration {
   private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
   public static final ModConfigSpec.DoubleValue REACH =
      BUILDER.comment("Horizontal reach from the player, in blocks.")
         .defineInRange("reach", 2.25D, 0.5D, 4.0D);
   public static final ModConfigSpec.DoubleValue HALF_ANGLE =
      BUILDER.comment("Half angle of the forward shove cone, in degrees.")
         .defineInRange("half_angle", 50.0D, 10.0D, 85.0D);
   public static final ModConfigSpec.IntValue MAX_TARGETS =
      BUILDER.defineInRange("max_targets", 3, 1, 8);
   public static final ModConfigSpec.DoubleValue DOWNED_CHANCE =
      BUILDER.comment("Base knockdown probability. Failed rolls stagger instead.")
         .defineInRange("knockdown_chance", 0.35D, 0.0D, 1.0D);
   public static final ModConfigSpec.DoubleValue STRENGTH =
      BUILDER.defineInRange("push_strength", 0.45D, 0.05D, 1.0D);
   public static final ModConfigSpec.DoubleValue SOUND_VOLUME =
      BUILDER.comment("Set to zero to mute shove, impact, and recovery sounds.")
         .defineInRange("sound_volume", 0.75D, 0.0D, 2.0D);
   public static final ModConfigSpec SPEC = BUILDER.build();

   private ZombieShoveConfiguration() {
   }
}
