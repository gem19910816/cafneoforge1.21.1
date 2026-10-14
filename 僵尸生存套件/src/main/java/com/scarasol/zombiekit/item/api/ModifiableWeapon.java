package com.scarasol.zombiekit.item.api;

import com.scarasol.zombiekit.item.weapon.parts.BattleParts;
import com.scarasol.zombiekit.item.weapon.parts.ChargingParts;
import com.scarasol.zombiekit.item.weapon.parts.GripParts;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;

import javax.annotation.Nullable;


public interface ModifiableWeapon {

    @Nullable
    default Item getGripParts(ItemStack itemStack) {
        return getParts(itemStack, "GripParts");
    }

    @Nullable
    default Item getChargingParts(ItemStack itemStack) {
        return getParts(itemStack, "ChargingParts");
    }

    @Nullable
    default Item getBattleParts(ItemStack itemStack) {
        return getParts(itemStack, "BattleParts");
    }

    @Nullable
    default Item getParts(ItemStack itemStack, String parts) {
        if (itemStack.getItem() instanceof ModifiableWeapon) {
            var tag = itemStack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();
            if (tag.contains(parts)) {
                String id = tag.getString(parts);
                return BuiltInRegistries.ITEM.get(ResourceLocation.parse(id));
            }
        }
        return null;
    }

    default void clearGripParts(ItemStack itemStack) {
        clearParts(itemStack, "GripParts");
    }

    default void clearChargingParts(ItemStack itemStack) {
        clearParts(itemStack, "ChargingParts");
    }

    default void clearBattleParts(ItemStack itemStack) {
        clearParts(itemStack, "BattleParts");
    }

    default void clearParts(ItemStack itemStack, String parts) {
        if (itemStack.getItem() instanceof ModifiableWeapon) {
            CustomData.update(DataComponents.CUSTOM_DATA, itemStack, tag -> tag.remove(parts));
        }
    }

    default void setGripParts(ItemStack itemStack, GripParts gripParts) {
        setParts(itemStack, "GripParts", BuiltInRegistries.ITEM.getKey(gripParts).toString());
    }

    default void setChargingParts(ItemStack itemStack, ChargingParts gripParts) {
        setParts(itemStack, "ChargingParts", BuiltInRegistries.ITEM.getKey(gripParts).toString());
    }

    default void setBattleParts(ItemStack itemStack, BattleParts gripParts) {
        setParts(itemStack, "BattleParts", BuiltInRegistries.ITEM.getKey(gripParts).toString());
    }

    default void setParts(ItemStack itemStack, String parts, String partsId) {
        if (itemStack.getItem() instanceof ModifiableWeapon) {
            CustomData.update(DataComponents.CUSTOM_DATA, itemStack, tag -> tag.putString(parts, partsId));
        }
    }
}
