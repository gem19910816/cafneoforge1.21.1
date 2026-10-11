package com.scarasol.itemrarity.network;

import com.scarasol.itemrarity.ItemRarityMod;
import com.scarasol.itemrarity.data.RarityGrade;
import com.scarasol.itemrarity.data.RarityManager;
import com.scarasol.itemrarity.util.RarityGradeUtil;
import com.scarasol.itemrarity.util.io.ModGson;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public record RarityGradePacket(String id, List<ResourceLocation> items) implements CustomPacketPayload {
    public static final Type<RarityGradePacket> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath(ItemRarityMod.MODID, "rarity_grade"));
    public static final StreamCodec<RegistryFriendlyByteBuf, RarityGradePacket> STREAM_CODEC =
            StreamCodec.composite(
                    ByteBufCodecs.STRING_UTF8, RarityGradePacket::id,
                    ByteBufCodecs.collection(ArrayList::new, ResourceLocation.STREAM_CODEC), RarityGradePacket::items,
                    RarityGradePacket::new
            );

    @Override
    public Type<RarityGradePacket> type() {
        return TYPE;
    }

    public static void handle(RarityGradePacket msg, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.player() instanceof ServerPlayer sender && !sender.hasPermissions(2)) {
                return;
            }
            if (!RarityGradeUtil.INIT) {
                try {
                    ModGson.INSTANCE.loadAll(FMLPaths.CONFIGDIR.get().resolve(ItemRarityMod.MODID).resolve("grade_register"));
                } catch (IOException e) {
                    ItemRarityMod.LOGGER.error("Unable to load rarity data", e);
                }
                RarityGradeUtil.INIT = true;
            }

            RarityGrade rarityGrade = RarityManager.getSearchableRarityData(
                    RarityGrade.class, Predicate.isEqual(msg.id())
            ).orElse(null);
            if (rarityGrade == null) {
                return;
            }

            rarityGrade.clearAll();
            rarityGrade.addAll(msg.items());

            if (context.player() instanceof ServerPlayer sender && sender.hasPermissions(2)) {
                MinecraftServer server = sender.serverLevel().getServer();
                server.getPlayerList().getPlayers().stream()
                        .filter(player -> !player.equals(sender))
                        .forEach(player -> PacketDistributor.sendToPlayer(player, msg));
                RarityGradeUtil.save(List.of(rarityGrade));
            }
        });
    }
}
