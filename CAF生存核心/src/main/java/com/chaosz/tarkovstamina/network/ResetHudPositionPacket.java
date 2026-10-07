package com.chaosz.tarkovstamina.network;

import com.chaosz.tarkovstamina.TarkovStamina;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

/** 重置 HUD 体力条位置到默认（服务端 → 客户端）。无载荷。 */
public record ResetHudPositionPacket() implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<ResetHudPositionPacket> TYPE =
            new CustomPacketPayload.Type<>(
                    ResourceLocation.fromNamespaceAndPath(TarkovStamina.MOD_ID, "reset_hud_position"));

    public static final StreamCodec<RegistryFriendlyByteBuf, ResetHudPositionPacket> STREAM_CODEC =
            StreamCodec.unit(new ResetHudPositionPacket());

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    // 处理逻辑在 com.chaosz.tarkovstamina.client.ClientPacketHandlers#handleHudReset
}
