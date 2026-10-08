package net.mcreator.gore.init;

import net.mcreator.gore.configuration.GeSpiralsConfiguration;
import net.mcreator.gore.configuration.GoreEditionConfigurationFileConfiguration;
import net.mcreator.gore.configuration.GoreEditionModeSettingsConfiguration;
import net.mcreator.gore.configuration.GoreEditionOtherConfigurationsConfiguration;
import net.mcreator.gore.configuration.GoreEditionSoundsConfigurationConfiguration;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.EventBusSubscriber.Bus;
import net.neoforged.fml.config.ModConfig.Type;
import net.neoforged.fml.event.lifecycle.FMLConstructModEvent;

@EventBusSubscriber(
   modid = "gore_edition",
   bus = Bus.MOD
)
public class GoreEditionModConfigs {
   @SubscribeEvent
   public static void register(FMLConstructModEvent event) {
      event.enqueueWork(() -> {
         ModLoadingContext.get().getActiveContainer().registerConfig(Type.COMMON, GoreEditionConfigurationFileConfiguration.SPEC, "GE_FX.toml");
         ModLoadingContext.get().getActiveContainer().registerConfig(Type.COMMON, GoreEditionSoundsConfigurationConfiguration.SPEC, "GE_SOUND.toml");
         ModLoadingContext.get().getActiveContainer().registerConfig(Type.COMMON, GoreEditionModeSettingsConfiguration.SPEC, "GE-SWITCHES.toml");
         ModLoadingContext.get().getActiveContainer().registerConfig(Type.COMMON, GoreEditionOtherConfigurationsConfiguration.SPEC, "GE-TRASH.toml");
         ModLoadingContext.get().getActiveContainer().registerConfig(Type.COMMON, GeSpiralsConfiguration.SPEC, "GE-SPIRALS.toml");
      });
   }
}
