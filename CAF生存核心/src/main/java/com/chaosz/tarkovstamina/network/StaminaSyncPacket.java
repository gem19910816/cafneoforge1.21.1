package com.chaosz.tarkovstamina.network;

import com.chaosz.tarkovstamina.TarkovStamina;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

/**
 * 体力同步包（服务端 → 客户端）。
 *
 * <p>1.21.1 NeoForge 把 Forge 的 {@code SimpleChannel} 换成了
 * {@link CustomPacketPayload} + {@link StreamCodec}：包自己声明类型与编解码器，
 * 注册在 {@code RegisterPayloadHandlersEvent} 里，见 {@link StaminaNetwork}。</p>
 */
public record StaminaSyncPacket(float stamina, float maximum, int cooldown, int maximumCooldown,
                                boolean sprinting, boolean exhausted, boolean hidden)
        implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<StaminaSyncPacket> TYPE =
            new CustomPacketPayload.Type<>(
                    ResourceLocation.fromNamespaceAndPath(TarkovStamina.MOD_ID, "stamina_sync"));

    public static final StreamCodec<RegistryFriendlyByteBuf, StaminaSyncPacket> STREAM_CODEC =
            StreamCodec.of(StaminaSyncPacket::encode, StaminaSyncPacket::decode);

    public static void encode(RegistryFriendlyByteBuf buffer, StaminaSyncPacket packet) {
        buffer.writeFloat(packet.stamina);
        buffer.writeFloat(packet.maximum);
        buffer.writeVarInt(packet.cooldown);
        buffer.writeVarInt(packet.maximumCooldown);
        buffer.writeBoolean(packet.sprinting);
        buffer.writeBoolean(packet.exhausted);
        buffer.writeBoolean(packet.hidden);
    }

    public static StaminaSyncPacket decode(RegistryFriendlyByteBuf buffer) {
        return new StaminaSyncPacket(
                buffer.readFloat(),
                buffer.readFloat(),
                buffer.readVarInt(),
                buffer.readVarInt(),
                buffer.readBoolean(),
                buffer.readBoolean(),
                buffer.readBoolean()
        );
    }

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    // 处理逻辑在 com.chaosz.tarkovstamina.client.ClientPacketHandlers#handleStaminaSync：
    // 那里引用了 net.minecraft.client 的类，服务端一个字节都不碰。
}
