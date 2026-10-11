package com.scarasol.itemrarity.compat.petiteinventory;

import com.sighs.petiteinventory.api.ItemArea;
import com.sighs.petiteinventory.api.PetiteInventoryApi;
import net.minecraft.util.Tuple;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

/**
 * @author Scarasol
 */
public class PetiteInventoryCompat {

    public static Tuple<Integer, Integer> getArea(Slot slot) {
        ItemStack itemStack = slot.getItem();
        return getArea(itemStack);
    }

    public static Tuple<Integer, Integer> getArea(ItemStack itemStack) {
        ItemArea area = PetiteInventoryApi.getItemArea(itemStack);
        return new Tuple<>(area.width(), area.height());
    }
}
