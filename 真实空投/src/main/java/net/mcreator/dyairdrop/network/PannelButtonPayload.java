package net.mcreator.dyairdrop.network;

import net.mcreator.dyairdrop.world.inventory.PannelMenu;
import java.util.HashMap;
import net.mcreator.dyairdrop.procedures.Button0Procedure;
import net.mcreator.dyairdrop.procedures.Button1Procedure;
import net.mcreator.dyairdrop.procedures.Button2Procedure;
import net.mcreator.dyairdrop.procedures.Button3Procedure;
import net.mcreator.dyairdrop.procedures.Button4Procedure;
import net.mcreator.dyairdrop.procedures.Button5Procedure;
import net.mcreator.dyairdrop.procedures.Button6Procedure;
import net.mcreator.dyairdrop.procedures.Button7Procedure;
import net.mcreator.dyairdrop.procedures.Button8Procedure;
import net.mcreator.dyairdrop.procedures.Button9Procedure;
import net.mcreator.dyairdrop.procedures.ButtoncheckProcedure;
import net.mcreator.dyairdrop.procedures.ButtondelateProcedure;
import net.mcreator.dyairdrop.procedures.OpProcedure;
import net.mcreator.dyairdrop.procedures.SetpwProcedure;
import net.mcreator.dyairdrop.procedures.TestpwProcedure;
import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record PannelButtonPayload(int buttonID, int x, int y, int z) implements CustomPacketPayload {
	public static final CustomPacketPayload.Type<PannelButtonPayload> TYPE = new CustomPacketPayload.Type<>(
			ResourceLocation.fromNamespaceAndPath("dyairdrop", "pannel_button"));
	public static final StreamCodec<RegistryFriendlyByteBuf, PannelButtonPayload> STREAM_CODEC = StreamCodec.ofMember(
			(PannelButtonPayload msg, RegistryFriendlyByteBuf buf) -> { buf.writeInt(msg.buttonID()); buf.writeInt(msg.x()); buf.writeInt(msg.y()); buf.writeInt(msg.z()); },
			buf -> new PannelButtonPayload(buf.readInt(), buf.readInt(), buf.readInt(), buf.readInt()));

	@Override
	public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}

	public static void handle(PannelButtonPayload message, IPayloadContext context) {
		if (context.flow().isServerbound()) {
			Player entity = context.player();
			handleButtonAction(entity, message.buttonID(), message.x(), message.y(), message.z());
		}
	}

	public static void handleButtonAction(Player entity, int buttonID, int x, int y, int z) {
		Level world = entity.level();
		HashMap guistate = PannelMenu.guistate;
		if (world.hasChunkAt(new BlockPos(x, y, z))) {
			if (buttonID == 0) {
				Button2Procedure.execute(entity, guistate);
			}
			if (buttonID == 1) {
				Button1Procedure.execute(entity, guistate);
			}
			if (buttonID == 2) {
				Button3Procedure.execute(entity, guistate);
			}
			if (buttonID == 3) {
				Button4Procedure.execute(entity, guistate);
			}
			if (buttonID == 4) {
				Button5Procedure.execute(entity, guistate);
			}
			if (buttonID == 5) {
				Button6Procedure.execute(entity, guistate);
			}
			if (buttonID == 6) {
				Button7Procedure.execute(entity, guistate);
			}
			if (buttonID == 7) {
				Button8Procedure.execute(entity, guistate);
			}
			if (buttonID == 8) {
				Button9Procedure.execute(entity, guistate);
			}
			if (buttonID == 9) {
				ButtondelateProcedure.execute(entity, guistate);
			}
			if (buttonID == 10) {
				Button0Procedure.execute(entity, guistate);
			}
			if (buttonID == 11) {
				ButtoncheckProcedure.execute(world, x, y, z, entity, guistate);
			}
			if (buttonID == 12) {
				OpProcedure.execute(world, x, y, z, entity, guistate);
			}
			if (buttonID == 13) {
				SetpwProcedure.execute(world, x, y, z, entity, guistate);
			}
			if (buttonID == 14) {
				TestpwProcedure.execute(world, x, y, z, entity);
			}

		}
	}
}
