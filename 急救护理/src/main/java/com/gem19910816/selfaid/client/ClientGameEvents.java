package com.gem19910816.selfaid.client;

import com.gem19910816.selfaid.SelfAidMod;
import com.gem19910816.selfaid.registry.SelfAidConfig;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderGuiLayerEvent;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;

/**
 * 客户端游戏总线事件：启用分部位血量时隐藏原版血条（与 FirstAid 行为一致）。
 */
@EventBusSubscriber(modid = SelfAidMod.MODID, value = Dist.CLIENT)
public final class ClientGameEvents {

    @SubscribeEvent
    public static void onRenderGuiLayer(RenderGuiLayerEvent.Pre event) {
        if (!VanillaGuiLayers.PLAYER_HEALTH.equals(event.getName())) {
            return;
        }
        if (!SelfAidConfig.HIDE_VANILLA_HEALTHBAR.get()) {
            return;
        }
        LocalPlayer player = Minecraft.getInstance().player;
        if (player != null && !player.isCreative() && !player.isSpectator()) {
            event.setCanceled(true);
        }
    }

    private ClientGameEvents() {
    }
}
