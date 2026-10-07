package me.xjqsh.lesraisinsarmor.armor;

import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.EnumMap;
import java.util.List;

/**
 * NeoForge 1.21.1 的占位 ArmorMaterial。
 * 真正的属性由 LrArmorItem 通过 ArmorDataManager 注入,这个材质只用于让 ArmorItem 能构造。
 */
public class LrArmorMaterial {
    private static final EnumMap<ArmorItem.Type, Integer> DEFENSE = new EnumMap<>(ArmorItem.Type.class);
    static {
        DEFENSE.put(ArmorItem.Type.BOOTS, 1);
        DEFENSE.put(ArmorItem.Type.LEGGINGS, 2);
        DEFENSE.put(ArmorItem.Type.CHESTPLATE, 3);
        DEFENSE.put(ArmorItem.Type.HELMET, 1);
        DEFENSE.put(ArmorItem.Type.BODY, 3);
    }

    public static final Holder<ArmorMaterial> HOLDER = Holder.direct(new ArmorMaterial(
            DEFENSE,
            5,
            SoundEvents.ARMOR_EQUIP_LEATHER,
            () -> Ingredient.EMPTY,
            List.of(),
            0.0f,
            0.0f
    ));
}
