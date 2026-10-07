package com.chaosz.tarkovstamina.stamina;

import com.chaosz.tarkovstamina.StaminaConfig;
import com.chaosz.tarkovstamina.StaminaSystem;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

/**
 * 负重系统
 * <p>
 * 检测玩家背包里的背包/帐篷数量，超过阈值触发超重状态（缓慢 II）。
 * 超重状态存储在 MOD 数据中，供职业系统（木工/石工）读取。
 * </p>
 */
public final class WeightSystem {
    private static final String K_IS_OVERWEIGHT = "isOverweight";

    private static final String[] TENT_IDS = {
            "simplytents:tent", "simplytents:wall_tent", "simplytents:roof_tent",
            "simplytents:zip_tent", "simplytents:duo_roof_tent",
            "simplytents:duo_zip_tent", "simplytents:small_tipi_tent"
    };

    /** 背包标签：Curios 与 Accessories 两家各自定义了一个 "back" 物品标签。 */
    private static final TagKey<Item> CURIOS_BACK =
            ItemTags.create(ResourceLocation.fromNamespaceAndPath("curios", "back"));
    private static final TagKey<Item> ACCESSORIES_BACK =
            ItemTags.create(ResourceLocation.fromNamespaceAndPath("accessories", "back"));

    private WeightSystem() {
    }

    @SubscribeEvent
    public static void onTick(PlayerTickEvent.Post event) {
        if (event.getEntity().level().isClientSide()) {
            return;
        }
        if (event.getEntity().tickCount % 5 != 0) return;

        Player player = event.getEntity();
        CompoundTag state = StaminaSystem.state(player, true);

        int backpackCount = 0;
        int tentCount = 0;

        for (int i = 0; i < 36; i++) {
            var item = player.getInventory().getItem(i);
            if (item.isEmpty()) continue;
            String id = item.getItem().builtInRegistryHolder().key().location().toString();
            // 背包检测
            if (item.is(CURIOS_BACK) || item.is(ACCESSORIES_BACK) || id.contains("backpack")) {
                backpackCount += item.getCount();
            }
            // 帐篷检测
            for (String tent : TENT_IDS) {
                if (id.equals(tent)) {
                    tentCount += item.getCount();
                    break;
                }
            }
        }

        boolean overweight = (backpackCount >= StaminaConfig.OVERWEIGHT_BACKPACK_LIMIT.get())
                || (tentCount >= StaminaConfig.OVERWEIGHT_TENT_LIMIT.get());
        state.putBoolean(K_IS_OVERWEIGHT, overweight);

        // 镜像到顶层键（KJS 木工/石工读取兼容）
        player.getPersistentData().putBoolean("isOverweight", overweight);

        if (overweight) {
            player.addEffect(new net.minecraft.world.effect.MobEffectInstance(
                    net.minecraft.world.effect.MobEffects.MOVEMENT_SLOWDOWN,
                    45, 1, false, false, false));
        }
    }
}
