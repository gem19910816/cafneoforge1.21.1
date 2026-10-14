package com.scarasol.zombiekit.network;

import com.scarasol.zombiekit.item.armor.ExoArmor;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import static com.scarasol.zombiekit.ZombieKitMod.MODID;

public record DoubleJumpPacket() implements CustomPacketPayload {

    public static final Type<DoubleJumpPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(MODID, "double_jump"));

    public static final StreamCodec<FriendlyByteBuf, DoubleJumpPacket> CODEC = StreamCodec.of(
            (buf, msg) -> {},
            buf -> new DoubleJumpPacket());

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handler(DoubleJumpPacket msg, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.player() instanceof ServerPlayer player) {
                ServerLevel serverLevel = (ServerLevel) player.level();
                if (ExoArmor.numberOfSuit(player) == 4 && ExoArmor.getPower(player.getItemBySlot(EquipmentSlot.CHEST)) > 0 && !player.onGround() && !player.isFallFlying() && !player.isInWater() && !player.getAbilities().flying && !player.isPassenger() && !player.onClimbable()) {
                    player.jumpFromGround();
                    serverLevel.sendParticles(ParticleTypes.CLOUD, player.getX(), player.getY(), player.getZ(), 100, 0.5, 0.2, 0.5, 0.1);
                    ExoArmor.addPower(player.getItemBySlot(EquipmentSlot.CHEST), -1);
                }
            }
        });
    }
}
