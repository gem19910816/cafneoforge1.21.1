package net.mcreator.gore.configuration;

import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.ModConfigSpec.Builder;
import net.neoforged.neoforge.common.ModConfigSpec.ConfigValue;

public class GeSpiralsConfiguration {
   public static final Builder BUILDER = new Builder();
   public static final ConfigValue<Double> SPIRAL_TORNADO_FIELD = BUILDER.define("Spiral tornado, field particle amount", 70.0);
   public static final ConfigValue<Double> SPIRAL_TORNADO_EYE = BUILDER.define("Spiral tornado, eye particle amount", 46.0);
   public static final ConfigValue<Double> LEVEL = BUILDER.define("Amount of particles with level one", 40.0);
   public static final ConfigValue<Double> LEVEL_II = BUILDER.define("Amount of particles with level two", 125.0);
   public static final ConfigValue<Double> LEVEL_III = BUILDER.define("Amount of particles with level three", 4.0);
   public static final ConfigValue<Double> REQUIRED_CHARGE = BUILDER.comment("Canon is five").define("Required charge of Ashtray Cycle", 5.0);
   public static final ModConfigSpec SPEC = BUILDER.build();

   static {
      BUILDER.push("Spiral");
      BUILDER.pop();
      BUILDER.push("Vulnerability effect");
      BUILDER.pop();
      BUILDER.push("Ashes");
      BUILDER.pop();
   }
}
