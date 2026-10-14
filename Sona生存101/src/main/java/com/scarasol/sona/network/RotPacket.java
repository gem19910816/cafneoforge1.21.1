package com.scarasol.sona.network;

import com.scarasol.sona.manager.RotManager;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import static com.scarasol.sona.SonaMod.MODID;

public record RotPacket(double rotValue, int slotInt, boolean isInventory) implements CustomPacketPayload {

    public static final Type<RotPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(MODID, "rot"));

    public static final StreamCodec<FriendlyByteBuf, RotPacket> CODEC = StreamCodec.of(
            (buf, msg) -> {
                buf.writeDouble(msg.rotValue);
                buf.writeInt(msg.slotInt);
                buf.writeBoolean(msg.isInventory);
            },
            buf -> new RotPacket(buf.readDouble(), buf.readInt(), buf.readBoolean()));

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public double getRotValue() {
        return this.rotValue;
    }

    public int getSlotInt() {
        return slotInt;
    }

    public boolean isInventory() {
        return isInventory;
    }

    public static void handler(RotPacket msg, IPayloadContext context) {
        context.enqueueWork(() -> {
            Player player = Minecraft.getInstance().player;
            if (player == null) {
                return;
            }
            ItemStack itemStack;
            if (msg.isInventory()) {
                if (msg.getSlotInt() < 0 || msg.getSlotInt() >= player.getInventory().getContainerSize()) {
                    return;
                }
                itemStack = player.getInventory().getItem(msg.getSlotInt());
            } else {
                if (msg.getSlotInt() < 0 || msg.getSlotInt() >= player.containerMenu.slots.size()) {
                    return;
                }
                itemStack = player.containerMenu.slots.get(msg.getSlotInt()).getItem();
            }
            RotManager.putRot(itemStack, msg.getRotValue());
            if (!msg.isInventory()) {
                player.containerMenu.slots.get(msg.getSlotInt()).setChanged();
            }
        });
    }
}
