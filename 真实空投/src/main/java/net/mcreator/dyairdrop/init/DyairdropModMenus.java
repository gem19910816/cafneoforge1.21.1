package net.mcreator.dyairdrop.init;

import net.mcreator.dyairdrop.world.inventory.AirdropGUIMenu;
import net.mcreator.dyairdrop.world.inventory.PannelMenu;
import net.mcreator.dyairdrop.world.inventory.PannelRE2Menu;
import net.mcreator.dyairdrop.world.inventory.PannelREMenu;
import net.mcreator.dyairdrop.world.inventory.TestGUI2Menu;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class DyairdropModMenus {
   public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(BuiltInRegistries.MENU, "dyairdrop");
   public static final DeferredHolder<MenuType<?>, MenuType<AirdropGUIMenu>> AIRDROP_GUI = REGISTRY.register(
      "airdrop_gui", () -> IMenuTypeExtension.create(AirdropGUIMenu::new));
   public static final DeferredHolder<MenuType<?>, MenuType<PannelMenu>> PANEL = REGISTRY.register(
      "panel", () -> IMenuTypeExtension.create(PannelMenu::new));
   public static final DeferredHolder<MenuType<?>, MenuType<TestGUI2Menu>> TEST_GUI_2 = REGISTRY.register(
      "test_gui_2", () -> IMenuTypeExtension.create(TestGUI2Menu::new));
   public static final DeferredHolder<MenuType<?>, MenuType<PannelRE2Menu>> PANNEL_RE_2 = REGISTRY.register(
      "pannel_re_2", () -> IMenuTypeExtension.create(PannelRE2Menu::new));
   public static final DeferredHolder<MenuType<?>, MenuType<PannelREMenu>> PANNEL_RE = REGISTRY.register(
      "pannel_re", () -> IMenuTypeExtension.create(PannelREMenu::new));

   public DyairdropModMenus() {
   }
}
