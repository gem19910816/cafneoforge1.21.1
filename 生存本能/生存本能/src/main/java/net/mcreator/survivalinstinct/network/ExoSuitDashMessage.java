package net.mcreator.survivalinstinct.network;

import net.mcreator.survivalinstinct.procedures.ExoSuitDashOnKeyPressedProcedure;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;

public record ExoSuitDashMessage() implements CustomPacketPayload {
    public static final Type<ExoSuitDashMessage> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath("survival_instinct", "exo_dash"));
    public static final StreamCodec<RegistryFriendlyByteBuf, ExoSuitDashMessage> CODEC = StreamCodec.unit(new ExoSuitDashMessage());
    @Override public Type<ExoSuitDashMessage> type() { return TYPE; }

    public static void register(RegisterPayloadHandlersEvent event) {
        event.registrar("1").playToServer(TYPE, CODEC, (message, context) -> {
            if (context.player() instanceof ServerPlayer player) {
                ExoSuitDashOnKeyPressedProcedure.execute(player.level(), player.getX(), player.getY(), player.getZ(), player);
            }
        });
    }
}
