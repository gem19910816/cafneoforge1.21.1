package com.scarasol.rummage.compat.petiteinventory;

import com.scarasol.rummage.api.mixin.IRummageable;
import com.sighs.petiteinventory.api.ItemArea;
import com.sighs.petiteinventory.api.PetiteInventoryApi;
import com.sighs.petiteinventory.config.ModConfig;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import java.util.List;

/**
 * 真实物品栏 (Petite Inventory) API 桥接类
 * @author Scarasol
 */
public class PetiteInventoryCompat {

    public static void init() {
        // The 1.21 API no longer exposes the old position-change callback.
    }

    public static boolean isMenuEnabled(AbstractContainerMenu menu) {
        if (menu == null) {
            return false;
        }

        // 玩家自身的背包 UI
        if (ModConfig.ENABLE_INVENTORY.get() && menu instanceof InventoryMenu) {
            return true;
        }

        // 其他容器 UI（如箱子），完全遵循 Petite Inventory 的类名匹配逻辑
        String menuType = menu.getClass().toString();
        List<?> whitelist = ModConfig.WHITELIST.get();
        if (whitelist != null) {
            return whitelist.contains(menuType);
        }

        return false;
    }

    public static int getWidth(ItemStack stack) {
        return PetiteInventoryApi.getItemArea(stack).width();
    }

    public static int getHeight(ItemStack stack) {
        return PetiteInventoryApi.getItemArea(stack).height();
    }

    public static Slot getMappedSlot(Slot slot) {
        return slot;
    }
}
