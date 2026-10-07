package com.gem19910816.selfaid.client;

import com.gem19910816.selfaid.SelfAidMod;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;

/** 客户端注册：血量 HUD 绘制层。 */
@EventBusSubscriber(modid = SelfAidMod.MODID, value = Dist.CLIENT)
public final class SelfAidClient {

    @SubscribeEvent
    public static void onRegisterGuiLayers(RegisterGuiLayersEvent event) {
        event.registerAboveAll(
                ResourceLocation.fromNamespaceAndPath(SelfAidMod.MODID, "body_health_hud"), BodyHudLayer::render);
    }

    private SelfAidClient() {
    }
}
