package com.gearsandflesh.market.network;

import com.gearsandflesh.market.MarketConstants;
import com.gearsandflesh.market.data.MarketQuery;
import com.gearsandflesh.market.service.MarketService;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public final class MarketNetwork {
    /** Game packet protocol version, independent from the website API version. */
    private static final String PROTOCOL = "5";

    private static final Map<UUID, MarketQuery> CURRENT_QUERIES = new ConcurrentHashMap<>();

    private MarketNetwork() {
    }

    public static void register(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar(PROTOCOL);
        registrar.playToServer(MarketQueryC2S.TYPE, MarketQueryC2S.STREAM_CODEC, MarketQueryC2S::handle);
        registrar.playToServer(CreateListingC2S.TYPE, CreateListingC2S.STREAM_CODEC, CreateListingC2S::handle);
        registrar.playToServer(BuyListingC2S.TYPE, BuyListingC2S.STREAM_CODEC, BuyListingC2S::handle);
        registrar.playToServer(CancelListingC2S.TYPE, CancelListingC2S.STREAM_CODEC, CancelListingC2S::handle);
        registrar.playToServer(ClaimMailboxC2S.TYPE, ClaimMailboxC2S.STREAM_CODEC, ClaimMailboxC2S::handle);
        registrar.playToServer(DepositMoneyC2S.TYPE, DepositMoneyC2S.STREAM_CODEC, DepositMoneyC2S::handle);
        registrar.playToServer(WithdrawMoneyC2S.TYPE, WithdrawMoneyC2S.STREAM_CODEC, WithdrawMoneyC2S::handle);
        registrar.playToServer(AdminListingActionC2S.TYPE, AdminListingActionC2S.STREAM_CODEC, AdminListingActionC2S::handle);
        registrar.playToClient(MarketSnapshotS2C.TYPE, MarketSnapshotS2C.STREAM_CODEC, MarketSnapshotS2C::handle);
        registrar.playToClient(MarketNoticeS2C.TYPE, MarketNoticeS2C.STREAM_CODEC, MarketNoticeS2C::handle);
        registrar.playToClient(OpenMarketS2C.TYPE, OpenMarketS2C.STREAM_CODEC, OpenMarketS2C::handle);
    }

    public static MarketQuery currentQuery(ServerPlayer player) {
        return CURRENT_QUERIES.getOrDefault(player.getUUID(), MarketQuery.defaults());
    }

    public static void rememberQuery(ServerPlayer player, MarketQuery query) {
        CURRENT_QUERIES.put(player.getUUID(), query);
    }

    public static void forgetPlayer(UUID playerId) {
        CURRENT_QUERIES.remove(playerId);
    }

    public static void sendSnapshot(ServerPlayer player, MarketQuery query) {
        CURRENT_QUERIES.put(player.getUUID(), query);
        MarketService.fetchSnapshot(player, query);
    }

    public static void pushSnapshot(ServerPlayer player, MarketSnapshotS2C snapshot) {
        PacketDistributor.sendToPlayer(player, snapshot);
    }

    public static void refresh(ServerPlayer player) {
        sendSnapshot(player, currentQuery(player));
    }

    public static void sendNotice(ServerPlayer player, boolean success, String message) {
        PacketDistributor.sendToPlayer(player, new MarketNoticeS2C(success, message));
    }

    public static void open(ServerPlayer player) {
        PacketDistributor.sendToPlayer(player, new OpenMarketS2C(false));
    }

    public static void openAdmin(ServerPlayer player) {
        if (!player.createCommandSourceStack().hasPermission(
                MarketConstants.ADMIN_PERMISSION_LEVEL)) {
            return;
        }
        PacketDistributor.sendToPlayer(player, new OpenMarketS2C(true));
    }
}
