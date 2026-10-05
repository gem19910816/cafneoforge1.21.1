package net.mcreator.dyairdrop.network;

import net.mcreator.dyairdrop.world.inventory.PannelRE2Menu;
import java.util.HashMap;
import net.mcreator.dyairdrop.procedures.Buttonre1Procedure;
import net.mcreator.dyairdrop.procedures.Buttonre2Procedure;
import net.mcreator.dyairdrop.procedures.Buttonre3Procedure;
import net.mcreator.dyairdrop.procedures.Buttonre4Procedure;
import net.mcreator.dyairdrop.procedures.Buttonre5Procedure;
import net.mcreator.dyairdrop.procedures.Buttonre6Procedure;
import net.mcreator.dyairdrop.procedures.CheckProcedure;
import net.mcreator.dyairdrop.procedures.ChecknewliteProcedure;
import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record PannelRE2ButtonPayload(int buttonID, int x, int y, int z) implements CustomPacketPayload {
	public static final CustomPacketPayload.Type<PannelRE2ButtonPayload> TYPE = new CustomPacketPayload.Type<>(
			ResourceLocation.fromNamespaceAndPath("dyairdrop", "pannel_re2_button"));
	public static final StreamCodec<RegistryFriendlyByteBuf, PannelRE2ButtonPayload> STREAM_CODEC = StreamCodec.ofMember(
			(PannelRE2ButtonPayload msg, RegistryFriendlyByteBuf buf) -> { buf.writeInt(msg.buttonID()); buf.writeInt(msg.x()); buf.writeInt(msg.y()); buf.writeInt(msg.z()); },
			buf -> new PannelRE2ButtonPayload(buf.readInt(), buf.readInt(), buf.readInt(), buf.readInt()));

	@Override
	public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}

	public static void handle(PannelRE2ButtonPayload message, IPayloadContext context) {
		if (context.flow().isServerbound()) {
			Player entity = context.player();
			handleButtonAction(entity, message.buttonID(), message.x(), message.y(), message.z());
		}
	}

	public static void handleButtonAction(Player entity, int buttonID, int x, int y, int z) {
		Level world = entity.level();
		HashMap guistate = PannelRE2Menu.guistate;
		if (world.hasChunkAt(new BlockPos(x, y, z))) {
			if (buttonID == 0) {
				Buttonre1Procedure.execute(world, x, y, z, entity);
			}
			if (buttonID == 1) {
				Buttonre2Procedure.execute(world, x, y, z, entity);
			}
			if (buttonID == 2) {
				Buttonre3Procedure.execute(world, x, y, z, entity);
			}
			if (buttonID == 3) {
				Buttonre4Procedure.execute(world, x, y, z, entity);
			}
			if (buttonID == 4) {
				Buttonre5Procedure.execute(world, x, y, z, entity);
			}
			if (buttonID == 5) {
				Buttonre6Procedure.execute(world, x, y, z, entity);
			}
			if (buttonID == 6) {
				ChecknewliteProcedure.execute(world, x, y, z, entity);
			}

		}
	}
}
