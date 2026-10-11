package com.scarasol.rummage;

import com.mojang.logging.LogUtils;

import com.scarasol.rummage.compat.ModCompat;
import com.scarasol.rummage.compat.petiteinventory.PetiteInventoryCompat;
import com.scarasol.rummage.configuration.CommonConfig;
import com.scarasol.rummage.init.RummageAttributes;
import com.scarasol.rummage.init.RummageSounds;
import com.scarasol.rummage.network.NetworkHandler;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import org.slf4j.Logger;

/**
 * @author Scarasol
 */
@Mod(RummageMod.MODID)
public class RummageMod
{

    public static final String MODID = "rummage";

    public static final Logger LOGGER = LogUtils.getLogger();

    public RummageMod(IEventBus modEventBus, ModContainer modContainer)
    {
        modContainer.registerConfig(ModConfig.Type.COMMON, CommonConfig.SPEC, "rummage-common.toml");
        RummageSounds.REGISTRY.register(modEventBus);
        RummageAttributes.REGISTRY.register(modEventBus);
        modEventBus.addListener(NetworkHandler::register);
        modEventBus.addListener((net.neoforged.fml.event.config.ModConfigEvent event) -> {
            if (event.getConfig().getSpec() == CommonConfig.SPEC) {
                CommonConfig.markDirty();
            }
        });
        if (ModCompat.isLoadPetiteInventory()) {
            PetiteInventoryCompat.init();
        }
    }


}
