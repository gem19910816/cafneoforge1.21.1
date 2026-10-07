package net.gem19910816.dyairdrop.init;

import net.gem19910816.dyairdrop.DyairdropMod;
import net.gem19910816.dyairdrop.world.inventory.AirdropGUIMenu;
import net.gem19910816.dyairdrop.world.inventory.PannelMenu;
import net.gem19910816.dyairdrop.world.inventory.PannelRE2Menu;
import net.gem19910816.dyairdrop.world.inventory.PannelREMenu;
import net.gem19910816.dyairdrop.world.inventory.PannelREMenu;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class DyairdropModMenus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(Registries.MENU, DyairdropMod.MODID);
	public static final DeferredHolder<MenuType<?>, MenuType<AirdropGUIMenu>> AIRDROP_GUI = REGISTRY.register("airdrop_gui",
			() -> IMenuTypeExtension.create(AirdropGUIMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<PannelMenu>> PANEL = REGISTRY.register("panel",
			() -> IMenuTypeExtension.create(PannelMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<PannelRE2Menu>> PANNEL_RE_2 = REGISTRY.register("pannel_re_2",
			() -> IMenuTypeExtension.create(PannelRE2Menu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<PannelREMenu>> PANNEL_RE = REGISTRY.register("pannel_re",
			() -> IMenuTypeExtension.create(PannelREMenu::new));
}
