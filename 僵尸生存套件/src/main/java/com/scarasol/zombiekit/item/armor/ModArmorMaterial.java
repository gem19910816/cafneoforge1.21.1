package com.scarasol.zombiekit.item.armor;

import com.scarasol.zombiekit.ZombieKitMod;
import com.scarasol.zombiekit.init.ZombieKitItems;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.EnumMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class ModArmorMaterial {

    private static final Map<Holder<ArmorMaterial>, Integer> DURABILITY_MULTIPLIERS = new IdentityHashMap<>();

    public static final Holder<ArmorMaterial> SKIING = of("skiing", 15, Util.make(new EnumMap<>(ArmorItem.Type.class), (map) -> {
        map.put(ArmorItem.Type.BOOTS, 2);
        map.put(ArmorItem.Type.LEGGINGS, 6);
        map.put(ArmorItem.Type.CHESTPLATE, 5);
        map.put(ArmorItem.Type.HELMET, 2);
    }), 9, SoundEvents.ARMOR_EQUIP_LEATHER, 1f, 0f, () -> Ingredient.of(ZombieKitItems.CLOTH.get()));
    public static final Holder<ArmorMaterial> TACTICAL = of("tactical", 33, Util.make(new EnumMap<>(ArmorItem.Type.class), (map) -> {
        map.put(ArmorItem.Type.BOOTS, 4);
        map.put(ArmorItem.Type.LEGGINGS, 7);
        map.put(ArmorItem.Type.CHESTPLATE, 7);
        map.put(ArmorItem.Type.HELMET, 5);
    }), 10, SoundEvents.ARMOR_EQUIP_DIAMOND, 2f, 0f, () -> Ingredient.of(ZombieKitItems.CLOTH.get()));
    public static final Holder<ArmorMaterial> RIOT = of("riot", 35, Util.make(new EnumMap<>(ArmorItem.Type.class), (map) -> {
        map.put(ArmorItem.Type.BOOTS, 4);
        map.put(ArmorItem.Type.LEGGINGS, 7);
        map.put(ArmorItem.Type.CHESTPLATE, 7);
        map.put(ArmorItem.Type.HELMET, 5);
    }), 10, SoundEvents.ARMOR_EQUIP_DIAMOND, 3f, 0.1f, () -> Ingredient.of(ZombieKitItems.SPECIAL_STEEL_SHEET.get()));
    public static final Holder<ArmorMaterial> BOMB = of("bomb", 40, Util.make(new EnumMap<>(ArmorItem.Type.class), (map) -> {
        map.put(ArmorItem.Type.BOOTS, 11);
        map.put(ArmorItem.Type.LEGGINGS, 15);
        map.put(ArmorItem.Type.CHESTPLATE, 16);
        map.put(ArmorItem.Type.HELMET, 13);
    }), 10, SoundEvents.ARMOR_EQUIP_NETHERITE, 5f, 0.25f, () -> Ingredient.EMPTY);
    public static final Holder<ArmorMaterial> EXO = of("exo", 37, Util.make(new EnumMap<>(ArmorItem.Type.class), (map) -> {
        map.put(ArmorItem.Type.BOOTS, 4);
        map.put(ArmorItem.Type.LEGGINGS, 7);
        map.put(ArmorItem.Type.CHESTPLATE, 8);
        map.put(ArmorItem.Type.HELMET, 6);
    }), 15, SoundEvents.ARMOR_EQUIP_NETHERITE, 4f, 0.2f, () -> Ingredient.of(ZombieKitItems.BULLETPROOF_INSERT.get()));


    private static Holder<ArmorMaterial> of(String name, int durabilityMultiplier, EnumMap<ArmorItem.Type, Integer> defense, int enchantmentValue, Holder<SoundEvent> sound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
        Holder<ArmorMaterial> holder = Holder.direct(new ArmorMaterial(defense, enchantmentValue, sound, repairIngredient,
                List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(ZombieKitMod.MODID, name))), toughness, knockbackResistance));
        DURABILITY_MULTIPLIERS.put(holder, durabilityMultiplier);
        return holder;
    }

    public static int getDurabilityMultiplier(Holder<ArmorMaterial> material) {
        return DURABILITY_MULTIPLIERS.getOrDefault(material, 1);
    }
}
