package net.mcreator.survivalinstinct.init;

import net.mcreator.survivalinstinct.client.gui.CashRegisterGUIScreen;
import net.mcreator.survivalinstinct.client.gui.EmptyBagGUIScreen;
import net.mcreator.survivalinstinct.client.gui.GabageBagGUIScreen;
import net.mcreator.survivalinstinct.client.gui.RefrigeratorGUIScreen;
import net.mcreator.survivalinstinct.client.gui.TrashCanGUIScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.EventBusSubscriber.Bus;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

@EventBusSubscriber(
   bus = Bus.MOD,
   value = {Dist.CLIENT}
)
public class SurvivalInstinctModScreens {
   @SubscribeEvent
   public static void clientLoad(RegisterMenuScreensEvent event) {
      event.register(SurvivalInstinctModMenus.REFRIGERATOR_GUI.get(), RefrigeratorGUIScreen::new);
      event.register(SurvivalInstinctModMenus.GABAGE_BAG_GUI.get(), GabageBagGUIScreen::new);
      event.register(SurvivalInstinctModMenus.CASH_REGISTER_GUI.get(), CashRegisterGUIScreen::new);
      event.register(SurvivalInstinctModMenus.TRASH_CAN_GUI.get(), TrashCanGUIScreen::new);
      event.register(SurvivalInstinctModMenus.EMPTY_BAG_GUI.get(), EmptyBagGUIScreen::new);
   }
}
