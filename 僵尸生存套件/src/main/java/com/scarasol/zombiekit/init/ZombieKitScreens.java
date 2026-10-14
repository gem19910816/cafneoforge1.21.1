package com.scarasol.zombiekit.init;

import com.scarasol.zombiekit.ZombieKitMod;
import com.scarasol.zombiekit.client.screen.*;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

@EventBusSubscriber(modid = ZombieKitMod.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ZombieKitScreens {
    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event) {
        event.register(ZombieKitMenus.SHORTWAVE_RADIO_GUI.get(), ShortwaveRadioScreen::new);
        event.register(ZombieKitMenus.WEAPON_MODIFICATION_GUI.get(), WeaponModificationScreen::new);
        event.register(ZombieKitMenus.VACUUM_PACKAGING_MACHINE_GUI.get(), VacuumPackagingMachineScreen::new);
        event.register(ZombieKitMenus.MORTAR_RACK_MENU.get(), MortarRackScreen::new);
    }

}
