package net.mcreator.dyairdrop.network;

import net.mcreator.dyairdrop.network.DyairdropModVariables.PlayerVariables;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record PlayerVariablesSyncPayload(CompoundTag data) implements CustomPacketPayload {
	public static final CustomPacketPayload.Type<PlayerVariablesSyncPayload> TYPE = new CustomPacketPayload.Type<>(
			ResourceLocation.fromNamespaceAndPath("dyairdrop", "player_variables_sync"));
	public static final StreamCodec<RegistryFriendlyByteBuf, PlayerVariablesSyncPayload> STREAM_CODEC = StreamCodec.ofMember(
			(PlayerVariablesSyncPayload msg, RegistryFriendlyByteBuf buf) -> buf.writeNbt(msg.data()),
			buf -> new PlayerVariablesSyncPayload(buf.readNbt()));

	@Override
	public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}

	public static void handle(PlayerVariablesSyncPayload message, IPayloadContext context) {
		context.enqueueWork(() -> {
			if (Minecraft.getInstance().player != null) {
				Minecraft.getInstance().player.setData(DyairdropModVariables.PLAYER_VARIABLES_ATTACHMENT,
						PlayerVariables.fromNBT(message.data()));
			}
		});
	}
}
