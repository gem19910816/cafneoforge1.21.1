package com.chaosz.tarkovstamina.client;

import com.chaosz.tarkovstamina.TarkovStamina;
import com.chaosz.tarkovstamina.item.StaminaEntities;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

/**
 * 1.21.1：{@code @Mod.EventBusSubscriber} 变成顶层 {@link EventBusSubscriber}，
 * 总线枚举里的 {@code FORGE} 改名 {@code GAME}（这里是 MOD 总线，不变）。
 */
@EventBusSubscriber(modid = TarkovStamina.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class ClientEvents {
    private ClientEvents() {
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            EntityRenderers.register(StaminaEntities.SHITBALL.get(), ThrownItemRenderer::new);
            HudPositionConfig.load(); // 加载上次保存的 HUD 位置（否则重启后偏移重置为 0,0）
        });
    }
}
