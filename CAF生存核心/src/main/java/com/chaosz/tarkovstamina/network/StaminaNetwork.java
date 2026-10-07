package com.chaosz.tarkovstamina.network;

import com.chaosz.tarkovstamina.backpack.BackpackNetwork;
import com.chaosz.tarkovstamina.client.ClientPacketHandlers;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

/**
 * 全部数据包的注册与发送。
 *
 * <p>1.21.1 NeoForge 去掉了 Forge 的 {@code SimpleChannel} / {@code NetworkRegistry} /
 * {@code PacketDistributor.PLAYER.with(...)}，改成在
 * {@link RegisterPayloadHandlersEvent} 里一次性登记「类型 + 编解码器 + 处理器」，
 * 发送走静态的 {@link PacketDistributor}。</p>
 *
 * <p>{@link BackpackNetwork} 的收发方法签名保持不变，所以它的调用方一行都不用改。</p>
 */
public final class StaminaNetwork {
    private static final String PROTOCOL = "1";

    private StaminaNetwork() {
    }

    /**
     * 注册入口：由主类在 mod 事件总线上挂
     * {@code modBus.addListener(StaminaNetwork::register)}。
     *
     * <p>六个包（含背包那两个）都在这儿登记，{@code registrar()} 只调用一次 ——
     * 分两处登记会让同一个协议版本被声明两遍。</p>
     */
    public static void register(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar(PROTOCOL);

        // ── 服务端 → 客户端 ──
        registrar.playToClient(StaminaSyncPacket.TYPE, StaminaSyncPacket.STREAM_CODEC,
                StaminaNetwork::onStaminaSync);
        registrar.playToClient(StatusScreenPacket.TYPE, StatusScreenPacket.STREAM_CODEC,
                StaminaNetwork::onStatus);
        registrar.playToClient(OpenHudScreenPacket.TYPE, OpenHudScreenPacket.STREAM_CODEC,
                StaminaNetwork::onHudOpen);
        registrar.playToClient(ResetHudPositionPacket.TYPE, ResetHudPositionPacket.STREAM_CODEC,
                StaminaNetwork::onHudReset);

        // ── 客户端 → 服务端（背包开合与滚动）──
        registrar.playToServer(BackpackNetwork.OpenBackpackPacket.TYPE,
                BackpackNetwork.OpenBackpackPacket.STREAM_CODEC, BackpackNetwork::onOpenBackpack);
        registrar.playToServer(BackpackNetwork.ScrollBackpackPacket.TYPE,
                BackpackNetwork.ScrollBackpackPacket.STREAM_CODEC, BackpackNetwork::onScrollBackpack);
    }

    // ═══════════════════════════════════════════════════════════════
    //  客户端处理器的 dist 门卫
    // ═══════════════════════════════════════════════════════════════
    //
    // ClientPacketHandlers 引用了 net.minecraft.client 的类，专用服务器上加载它会
    // NoClassDefFoundError。这里先判 dist，服务端连那个类的符号都不会去解析。

    private static void onStaminaSync(StaminaSyncPacket packet, IPayloadContext context) {
        if (!FMLEnvironment.dist.isClient()) return;
        context.enqueueWork(() -> ClientPacketHandlers.handleStaminaSync(packet, context));
    }

    private static void onStatus(StatusScreenPacket packet, IPayloadContext context) {
        if (!FMLEnvironment.dist.isClient()) return;
        context.enqueueWork(() -> ClientPacketHandlers.handleStatus(packet, context));
    }

    private static void onHudOpen(OpenHudScreenPacket packet, IPayloadContext context) {
        if (!FMLEnvironment.dist.isClient()) return;
        context.enqueueWork(() -> ClientPacketHandlers.handleHudOpen(packet, context));
    }

    private static void onHudReset(ResetHudPositionPacket packet, IPayloadContext context) {
        if (!FMLEnvironment.dist.isClient()) return;
        context.enqueueWork(() -> ClientPacketHandlers.handleHudReset(packet, context));
    }

    // ═══════════════════════════════════════════════════════════════
    //  发送
    // ═══════════════════════════════════════════════════════════════

    public static void send(ServerPlayer player, StaminaSyncPacket packet) {
        PacketDistributor.sendToPlayer(player, packet);
    }

    public static void sendStatus(ServerPlayer player, StatusScreenPacket packet) {
        PacketDistributor.sendToPlayer(player, packet);
    }

    public static void sendHudOpen(ServerPlayer player) {
        PacketDistributor.sendToPlayer(player, new OpenHudScreenPacket());
    }

    public static void sendHudReset(ServerPlayer player) {
        PacketDistributor.sendToPlayer(player, new ResetHudPositionPacket());
    }
}
