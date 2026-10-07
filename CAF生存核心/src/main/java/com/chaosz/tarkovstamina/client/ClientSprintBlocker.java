package com.chaosz.tarkovstamina.client;

import com.chaosz.tarkovstamina.TarkovStamina;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

/**
 * 客户端侧疾跑封锁器
 * 体力耗尽时释放疾跑键 + 关闭疾跑标记，阻止客户端进入疾跑状态。
 * Pre 释放键 + 关闭标记（aiStep 前），Post 再压一次（兜底双击 W 等路径）。
 *
 * <p>1.21.1：{@code TickEvent.PlayerTickEvent} 的 Phase 拆成了 Pre / Post 两个事件，
 * {@code TickEvent.ClientTickEvent} 同理只剩 {@code Post}。</p>
 */
@EventBusSubscriber(modid = TarkovStamina.MOD_ID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public final class ClientSprintBlocker {
    private ClientSprintBlocker() {
    }

    /** 体力耗尽 → 本帧不允许疾跑。 */
    private static boolean blocked() {
        return ClientStaminaState.received()
                && !ClientStaminaState.hidden()
                && ClientStaminaState.stamina() <= 0.0F;
    }

    @SubscribeEvent
    public static void onPlayerTickPre(PlayerTickEvent.Pre event) {
        if (!(event.getEntity() instanceof LocalPlayer lp)) return;
        if (!blocked()) return;
        // 释放疾跑键 + 关闭疾跑标记（aiStep 前，阻止 aiStep 重新启动疾跑）
        Minecraft.getInstance().options.keySprint.setDown(false);
        lp.setSprinting(false);
    }

    @SubscribeEvent
    public static void onPlayerTickPost(PlayerTickEvent.Post event) {
        if (!(event.getEntity() instanceof LocalPlayer lp)) return;
        if (!blocked()) return;
        // 再压一次，兜底双击 W 等不经过 Ctrl 键的路径
        lp.setSprinting(false);
    }

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        if (!blocked()) return;

        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.player != null) {
            // 在输入处理完成后再压一次，避免按住 Ctrl 时下一帧重新进入疾跑。
            minecraft.options.keySprint.setDown(false);
            minecraft.player.setSprinting(false);
        }
    }
}
