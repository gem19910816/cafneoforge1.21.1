package com.chaosz.tarkovstamina.item;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * 物品注册
 * <p>
 * 注册到 {@code caf} 命名空间，与现有 KJS 物品 ID 完全一致。
 * 所有物品通过 {@link DeferredRegister} 延迟创建。
 * </p>
 *
 * <p>1.21.1：{@code ForgeRegistries.ITEMS} 已移除，注册表统一走
 * {@link BuiltInRegistries}；{@code RegistryObject} 改名 {@link DeferredHolder}。</p>
 */
public final class StaminaItems {
    public static final DeferredRegister<Item> REGISTRY =
            DeferredRegister.create(BuiltInRegistries.ITEM, "caf");

    // ── 体力核心物品 ──
    public static final DeferredHolder<Item, Item> SPECIAL_STRENGTH_INJECTION = REGISTRY
            .register("special_strength_injection",
                    () -> new Item(new Item.Properties().stacksTo(1)));

    public static final DeferredHolder<Item, Item> BODY_MONITOR = REGISTRY
            .register("body_monitor",
                    () -> new Item(new Item.Properties().stacksTo(1)));

    // ── 生存物品 ──
    public static final DeferredHolder<Item, Item> SHIT = REGISTRY
            .register("shit",
                    () -> new Item(new Item.Properties().stacksTo(64)));

    // ── 功能物品 ──
    public static final DeferredHolder<Item, Item> LAPTOP = REGISTRY
            .register("bijibendiannao",
                    () -> new Item(new Item.Properties().stacksTo(1)));

    private StaminaItems() {
    }
}
