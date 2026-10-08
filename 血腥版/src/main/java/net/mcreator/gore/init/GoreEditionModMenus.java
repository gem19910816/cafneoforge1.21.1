package net.mcreator.gore.init;

import net.mcreator.gore.world.inventory.AshtrayCycleGuiMenu;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class GoreEditionModMenus {
   public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(BuiltInRegistries.MENU, "gore_edition");
   public static final DeferredHolder<MenuType<?>, MenuType<AshtrayCycleGuiMenu>> ASHTRAY_CYCLE_GUI = REGISTRY.register(
      "ashtray_cycle_gui", () -> IMenuTypeExtension.create(AshtrayCycleGuiMenu::new)
   );
}
