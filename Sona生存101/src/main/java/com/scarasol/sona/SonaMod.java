package com.scarasol.sona;

import com.mojang.logging.LogUtils;
import com.scarasol.sona.configuration.CommonConfig;
import com.scarasol.sona.init.SonaEntities;
import com.scarasol.sona.init.SonaMobEffects;
import com.scarasol.sona.init.SonaSounds;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import org.slf4j.Logger;

@Mod(SonaMod.MODID)
public class SonaMod
{
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final String MODID = "sona";

    public SonaMod(IEventBus modEventBus, ModContainer container)
    {
        container.registerConfig(ModConfig.Type.COMMON, CommonConfig.SPEC, "sona-common.toml");
        SonaSounds.REGISTRY.register(modEventBus);
        SonaMobEffects.REGISTRY.register(modEventBus);
        SonaEntities.REGISTRY.register(modEventBus);
    }
}
