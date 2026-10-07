package com.chaosz.tarkovstamina.network;

import com.chaosz.tarkovstamina.TarkovStamina;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

/** 打开 HUD 位置调整界面的包（服务端 → 客户端）。无载荷。 */
public record OpenHudScreenPacket() implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<OpenHudScreenPacket> TYPE =
            new CustomPacketPayload.Type<>(
                    ResourceLocation.fromNamespaceAndPath(TarkovStamina.MOD_ID, "open_hud_screen"));

    /** 无字段的包用 {@code StreamCodec.unit} —— 不必再手写空编解码器。 */
    public static final StreamCodec<RegistryFriendlyByteBuf, OpenHudScreenPacket> STREAM_CODEC =
            StreamCodec.unit(new OpenHudScreenPacket());

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    // 处理逻辑在 com.chaosz.tarkovstamina.client.ClientPacketHandlers#handleHudOpen
}
