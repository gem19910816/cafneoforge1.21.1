package com.chaosz.tarkovstamina.item;

import com.chaosz.tarkovstamina.network.StaminaNetwork;
import com.chaosz.tarkovstamina.network.StatusScreenPacket;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

/**
 * 身体监测终端
 * <p>
 * 右键 {@code caf:body_monitor} 发送 StatusScreenPacket 打开 /caf 面板
 * </p>
 *
 * <p>1.21.1：{@code ForgeRegistries.ITEMS} 换成 {@link BuiltInRegistries#ITEM}；
 * 原来用 {@code event.getSide().isClient()} 判断，这里直接看实体所在的世界 ——
 * 等价且不依赖那个已被挪走的辅助方法。</p>
 */
public final class BodyMonitorHandler {
    private static final ResourceLocation BODY_MONITOR_ID =
            ResourceLocation.fromNamespaceAndPath("caf", "body_monitor");

    private BodyMonitorHandler() {
    }

    @SubscribeEvent
    public static void onRightClick(PlayerInteractEvent.RightClickItem event) {
        if (event.getEntity().level().isClientSide()) return;
        if (!(event.getEntity() instanceof ServerPlayer player)) return;
        var stack = event.getItemStack();
        if (stack.isEmpty()) return;
        ResourceLocation id = BuiltInRegistries.ITEM.getKey(stack.getItem());
        if (!BODY_MONITOR_ID.equals(id)) return;
        StaminaNetwork.sendStatus(player, StatusScreenPacket.from(player));
        event.setCanceled(true);
    }
}
