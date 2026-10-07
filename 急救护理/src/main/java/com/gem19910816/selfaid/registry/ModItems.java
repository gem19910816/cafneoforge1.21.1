package com.gem19910816.selfaid.registry;

import com.gem19910816.selfaid.SelfAidMod;
import com.gem19910816.selfaid.item.HealingItem;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.UseAnim;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public final class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(net.minecraft.core.registries.Registries.ITEM,
            SelfAidMod.MODID);

    /** 绷带：包扎一条四肢，持续 6 秒共回复 3 点（1.5 颗心）。 */
    public static final Supplier<Item> BANDAGE = ITEMS.register("bandage",
            () -> new HealingItem(new HealingItem.Properties()
                    .useTicks(40)
                    .animation(UseAnim.EAT)
                    .mode(HealingItem.HealMode.RANDOM_LIMB)
                    .totalHeal(3.0F)
                    .healDurationTicks(120)
                    .stackSize(16)));

    /** 创可贴：贴头部或躯干较伤的一处，持续 3 秒共回复 2 点。 */
    public static final Supplier<Item> PLASTER = ITEMS.register("plaster",
            () -> new HealingItem(new HealingItem.Properties()
                    .useTicks(20)
                    .animation(UseAnim.EAT)
                    .mode(HealingItem.HealMode.HEAD_TORSO)
                    .totalHeal(2.0F)
                    .healDurationTicks(60)
                    .stackSize(16)));

    /** 吗啡：缓解疼痛（清除负面效果），10 秒内共回复 6 点，全身分摊。 */
    public static final Supplier<Item> MORPHINE = ITEMS.register("morphine",
            () -> new HealingItem(new HealingItem.Properties()
                    .useTicks(32)
                    .animation(UseAnim.DRINK)
                    .mode(HealingItem.HealMode.ALL_PARTS)
                    .totalHeal(6.0F)
                    .healDurationTicks(200)
                    .clearNegativeEffects()
                    .rarity(Rarity.UNCOMMON)
                    .stackSize(8)));

    /** 急救包：清除负面效果，15 秒内全身共回复 12 点。 */
    public static final Supplier<Item> FIRST_AID_KIT = ITEMS.register("first_aid_kit",
            () -> new HealingItem(new HealingItem.Properties()
                    .useTicks(60)
                    .animation(UseAnim.EAT)
                    .mode(HealingItem.HealMode.ALL_PARTS)
                    .totalHeal(12.0F)
                    .healDurationTicks(300)
                    .clearNegativeEffects()
                    .rarity(Rarity.RARE)
                    .stackSize(4)));

    private ModItems() {
    }
}
