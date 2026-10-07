package net.gem19910816.dyairdrop.network;

import net.gem19910816.dyairdrop.network.DyairdropModVariables.MapVariables;
import net.gem19910816.dyairdrop.network.DyairdropModVariables.WorldVariables;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record SavedDataSyncPayload(int syncType, CompoundTag data) implements CustomPacketPayload {
	public static final CustomPacketPayload.Type<SavedDataSyncPayload> TYPE = new CustomPacketPayload.Type<>(
			ResourceLocation.fromNamespaceAndPath("dyairdrop", "saved_data_sync"));
	public static final StreamCodec<RegistryFriendlyByteBuf, SavedDataSyncPayload> STREAM_CODEC = StreamCodec.ofMember(
			(SavedDataSyncPayload msg, RegistryFriendlyByteBuf buf) -> {
				buf.writeInt(msg.syncType());
				buf.writeNbt(msg.data());
			},
			buf -> new SavedDataSyncPayload(buf.readInt(), buf.readNbt()));

	@Override
	public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}

	public static void handle(SavedDataSyncPayload message, IPayloadContext context) {
		context.enqueueWork(() -> {
			if (message.data() != null) {
				if (message.syncType() == 0) {
					MapVariables.clientSide.read(message.data());
				} else {
					WorldVariables.clientSide.read(message.data());
				}
			}
		});
	}
}
