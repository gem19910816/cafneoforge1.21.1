package me.xjqsh.lesraisinsarmor;

import com.mojang.logging.LogUtils;
import me.xjqsh.lesraisinsarmor.config.CommonConfig;
import me.xjqsh.lesraisinsarmor.init.ModCreativeTabs;
import me.xjqsh.lesraisinsarmor.init.ModEffects;
import me.xjqsh.lesraisinsarmor.init.ModItems;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import org.slf4j.Logger;


@Mod("lrarmor")
public class LesRaisinsArmor {
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final String MOD_ID = "lrarmor";

    public LesRaisinsArmor(IEventBus bus, ModContainer container){
        container.registerConfig(ModConfig.Type.COMMON, CommonConfig.init());

        ModItems.REGISTER.register(bus);
        ModEffects.REGISTER.register(bus);
        ModCreativeTabs.TABS.register(bus);
    }
}
