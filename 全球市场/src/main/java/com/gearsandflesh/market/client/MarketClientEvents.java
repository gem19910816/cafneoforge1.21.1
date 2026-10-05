package com.gearsandflesh.market.client;

import com.gearsandflesh.market.MarketConstants;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import org.lwjgl.glfw.GLFW;

public final class MarketClientEvents {
    private static final KeyMapping OPEN_MARKET = new KeyMapping(
            "key.gearsandflesh_market.open",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_K,
            "key.categories.gearsandflesh_market"
    );

    private MarketClientEvents() {
    }

    @EventBusSubscriber(
            modid = MarketConstants.MOD_ID,
            value = Dist.CLIENT
    )
    public static final class ModBusEvents {
        private ModBusEvents() {
        }

        @SubscribeEvent
        public static void registerKeys(RegisterKeyMappingsEvent event) {
            event.register(OPEN_MARKET);
        }
    }

    @EventBusSubscriber(
            modid = MarketConstants.MOD_ID,
            value = Dist.CLIENT
    )
    public static final class GameBusEvents {
        private GameBusEvents() {
        }

        @SubscribeEvent
        public static void clientTick(ClientTickEvent.Post event) {
            Minecraft minecraft = Minecraft.getInstance();
            while (OPEN_MARKET.consumeClick()) {
                if (minecraft.player != null && minecraft.screen == null) {
                    ClientMarketState.open();
                }
            }
        }
    }
}
