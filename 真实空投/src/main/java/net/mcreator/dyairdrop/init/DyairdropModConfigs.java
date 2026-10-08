package net.mcreator.dyairdrop.init;

import net.mcreator.dyairdrop.configuration.AirdropconfigConfiguration;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.config.ModConfig;

public class DyairdropModConfigs {
   public DyairdropModConfigs() {
   }

   public static void register(ModContainer container) {
      container.registerConfig(ModConfig.Type.COMMON, AirdropconfigConfiguration.SPEC, "dyairdrop.toml");
   }
}
