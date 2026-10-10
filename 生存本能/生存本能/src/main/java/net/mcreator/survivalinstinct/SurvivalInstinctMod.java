package net.mcreator.survivalinstinct;

import net.mcreator.survivalinstinct.init.*;
import net.mcreator.survivalinstinct.network.ExoSuitDashMessage;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(SurvivalInstinctMod.MODID)
public final class SurvivalInstinctMod {
    public static final String MODID = "survival_instinct";
    public static final Logger LOGGER = LogManager.getLogger(SurvivalInstinctMod.class);

    public SurvivalInstinctMod(IEventBus bus) {
        SurvivalInstinctModSounds.REGISTRY.register(bus);
        SurvivalInstinctModBlocks.REGISTRY.register(bus);
        SurvivalInstinctModBlockEntities.REGISTRY.register(bus);
        SurvivalInstinctModArmorMaterials.REGISTRY.register(bus);
        SurvivalInstinctModItems.REGISTRY.register(bus);
        SurvivalInstinctModEntities.REGISTRY.register(bus);
        SurvivalInstinctModTabs.REGISTRY.register(bus);
        SurvivalInstinctModMobEffects.REGISTRY.register(bus);
        SurvivalInstinctModMenus.REGISTRY.register(bus);
        bus.addListener(ExoSuitDashMessage::register);
        bus.addListener(PortCapabilities::register);
    }
}
