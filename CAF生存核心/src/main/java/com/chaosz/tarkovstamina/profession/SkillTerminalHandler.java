package com.chaosz.tarkovstamina.profession;

import com.chaosz.tarkovstamina.network.StaminaNetwork;
import com.chaosz.tarkovstamina.network.StatusScreenPacket;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

/**
 * 技能终端兼容：右键 caf:bijibendiannao 打开 /caf 面板
 */
public final class SkillTerminalHandler {
    private static final ResourceLocation LAPTOP_ID =
            ResourceLocation.fromNamespaceAndPath("caf", "bijibendiannao");

    private SkillTerminalHandler() {
    }

    @SubscribeEvent
    public static void onRightClick(PlayerInteractEvent.RightClickItem event) {
        if (event.getEntity().level().isClientSide()) return;
        if (!(event.getEntity() instanceof ServerPlayer player)) return;
        var stack = event.getItemStack();
        if (stack.isEmpty()) return;
        ResourceLocation id = BuiltInRegistries.ITEM.getKey(stack.getItem());
        if (!LAPTOP_ID.equals(id)) return;
        StaminaNetwork.sendStatus(player, StatusScreenPacket.from(player));
        event.setCanceled(true);
    }
}
