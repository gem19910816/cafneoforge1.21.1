package com.chaosz.tarkovstamina.client;

import com.chaosz.tarkovstamina.network.OpenHudScreenPacket;
import com.chaosz.tarkovstamina.network.ResetHudPositionPacket;
import com.chaosz.tarkovstamina.network.StaminaSyncPacket;
import com.chaosz.tarkovstamina.network.StatusScreenPacket;
import com.chaosz.tarkovstamina.ui.StatusScreen;
import net.minecraft.client.Minecraft;
import net.neoforged.neoforge.network.handling.IPayloadContext;

/**
 * 纯客户端数据包处理器。
 *
 * <p>这些处理器引用了 {@code net.minecraft.client} 的类（{@link Minecraft}、
 * {@code Screen} 等）。专用服务器一旦加载本类就会 NoClassDefFoundError，
 * 所以 {@code StaminaNetwork} 里的处理入口先判 dist 才调到这儿。</p>
 *
 * <p>1.21.1：处理器签名从 Forge 的 {@code Supplier<NetworkEvent.Context>}
 * 改成 {@link IPayloadContext}，并且「切回主线程」这件事由调用方
 * （StaminaNetwork 里的 {@code context.enqueueWork}）统一做了，
 * 这里不再自己调度。</p>
 */
public final class ClientPacketHandlers {

    private ClientPacketHandlers() {
    }

    public static void handleStaminaSync(StaminaSyncPacket packet, IPayloadContext context) {
        ClientStaminaState.accept(packet);
    }

    public static void handleStatus(StatusScreenPacket pkt, IPayloadContext ctx) {
        Minecraft.getInstance().setScreen(new StatusScreen(pkt));
    }

    public static void handleHudOpen(OpenHudScreenPacket pkt, IPayloadContext ctx) {
        HudPositionConfig.load();
        Minecraft.getInstance().setScreen(new HudPositionScreen());
    }

    public static void handleHudReset(ResetHudPositionPacket pkt, IPayloadContext ctx) {
        HudPositionConfig.reset();
    }
}
