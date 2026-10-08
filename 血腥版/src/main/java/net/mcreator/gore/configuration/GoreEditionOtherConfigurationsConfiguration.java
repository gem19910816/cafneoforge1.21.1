package net.mcreator.gore.configuration;

import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.ModConfigSpec.Builder;
import net.neoforged.neoforge.common.ModConfigSpec.ConfigValue;

public class GoreEditionOtherConfigurationsConfiguration {
   public static final Builder BUILDER = new Builder();
   public static final ConfigValue<Boolean> NATURALLY_TOUGHNESS_CREATURES_SPAWNING = BUILDER.define("Toughness entities naturally spawning", false);
   public static final ConfigValue<Boolean> VANISHED_ITEMS_WHEN_BURNED = BUILDER.define("Vanished items when burned", false);
   public static final ConfigValue<Boolean> TOUGHNESS_VISIBLE = BUILDER.define("Spawn sharpness particles into toughness entities", false);
   public static final ConfigValue<Boolean> SHOW_DAMAGE_NUMBER = BUILDER.define("Show damage number", false);
   public static final ModConfigSpec SPEC = BUILDER.build();

   static {
      BUILDER.push("Debug things");
      BUILDER.pop();
   }
}
