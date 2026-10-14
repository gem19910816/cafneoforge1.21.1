package com.scarasol.zombiekit;

import com.mojang.logging.LogUtils;

import com.scarasol.zombiekit.config.CommonConfig;
import com.scarasol.zombiekit.init.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import org.slf4j.Logger;

@Mod(ZombieKitMod.MODID)
public class ZombieKitMod
{
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final String MODID = "zombiekit";


    public ZombieKitMod(IEventBus bus, ModContainer container) {
        container.registerConfig(ModConfig.Type.COMMON, CommonConfig.SPEC, "zombiekit-common.toml");
        ZombieKitSounds.REGISTRY.register(bus);
        ZombieKitParticleTypes.REGISTRY.register(bus);
        ZombieKitBlocks.REGISTRY.register(bus);
        ZombieKitItems.REGISTRY.register(bus);
        ZombieKitEntities.REGISTRY.register(bus);
        ZombieKitBlockEntities.REGISTRY.register(bus);
        ZombieKitMenus.REGISTRY.register(bus);
        ZombieKitTabs.REGISTRY.register(bus);
    }
}
