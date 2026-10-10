package com.scarasol.tud.util;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;

/** Stores the legacy selection key in the 1.21 custom-data component. */
public final class GunAmmoSelection {
    private GunAmmoSelection() {}

    public static int get(ItemStack stack) {
        return stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getInt("TudCurrentAmmo");
    }

    public static void set(ItemStack stack, int selection) {
        CustomData.update(DataComponents.CUSTOM_DATA, stack, tag -> tag.putInt("TudCurrentAmmo", selection));
    }
}
