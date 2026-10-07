package net.gem19910816.dyairdrop.init;

import net.gem19910816.dyairdrop.client.gui.AirdropGUIScreen;
import net.gem19910816.dyairdrop.client.gui.PannelRE2Screen;
import net.gem19910816.dyairdrop.client.gui.PannelREScreen;
import net.gem19910816.dyairdrop.client.gui.PannelScreen;
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
		event.register(DyairdropModMenus.PANNEL_RE_2.get(), PannelRE2Screen::new);
		event.register(DyairdropModMenus.PANNEL_RE.get(), PannelREScreen::new);
	}
}
