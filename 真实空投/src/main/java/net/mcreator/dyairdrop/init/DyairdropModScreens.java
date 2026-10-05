package net.mcreator.dyairdrop.init;

import net.mcreator.dyairdrop.DyairdropMod;
import net.mcreator.dyairdrop.client.gui.AirdropGUIScreen;
import net.mcreator.dyairdrop.client.gui.PannelRE2Screen;
import net.mcreator.dyairdrop.client.gui.PannelREScreen;
import net.mcreator.dyairdrop.client.gui.PannelScreen;
import net.mcreator.dyairdrop.client.gui.TestGUI2Screen;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class DyairdropModScreens {
	@SubscribeEvent
	public static void registerScreens(RegisterMenuScreensEvent event) {
		event.register(DyairdropModMenus.AIRDROP_GUI.get(), AirdropGUIScreen::new);
		event.register(DyairdropModMenus.PANEL.get(), PannelScreen::new);
		event.register(DyairdropModMenus.TEST_GUI_2.get(), TestGUI2Screen::new);
		event.register(DyairdropModMenus.PANNEL_RE_2.get(), PannelRE2Screen::new);
		event.register(DyairdropModMenus.PANNEL_RE.get(), PannelREScreen::new);
	}
}
