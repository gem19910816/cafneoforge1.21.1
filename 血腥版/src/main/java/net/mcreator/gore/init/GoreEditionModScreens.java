package net.mcreator.gore.init;

import net.mcreator.gore.client.gui.AshtrayCycleGuiScreen;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.EventBusSubscriber.Bus;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

@EventBusSubscriber(
   bus = Bus.MOD,
   value = {Dist.CLIENT}
)
public class GoreEditionModScreens {
   @SubscribeEvent
   public static void clientLoad(RegisterMenuScreensEvent event) {
      event.register((MenuType)GoreEditionModMenus.ASHTRAY_CYCLE_GUI.get(), AshtrayCycleGuiScreen::new);
   }
}
