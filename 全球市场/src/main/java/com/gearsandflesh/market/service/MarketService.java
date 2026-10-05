package com.gearsandflesh.market.service;

import com.gearsandflesh.market.GlobalMarketMod;
import com.gearsandflesh.market.MarketConstants;
import com.gearsandflesh.market.data.MarketCategory;
import com.gearsandflesh.market.data.MarketItemCodec;
import com.gearsandflesh.market.data.MarketListing;
import com.gearsandflesh.market.data.MarketQuery;
import com.gearsandflesh.market.data.MarketTransaction;
import com.gearsandflesh.market.data.MarketWorldData;
import com.gearsandflesh.market.network.AdminListingActionC2S;
import com.gearsandflesh.market.network.MarketNetwork;
import com.gearsandflesh.market.network.MarketSnapshotS2C;
import com.gearsandflesh.market.remote.MarketApiClient;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Market orchestration. All market state lives on the official website; this
 * class validates locally, talks to the API asynchronously, then applies the
 * confirmed inventory changes back on the server thread.
 *
 * Golden rule: items and money are only deducted AFTER the website confirms.
 */
public final class MarketService {
    private MarketService() {
    }

    // ------------------------------------------------------------------
    // Snapshot
    // ------------------------------------------------------------------

    public static void fetchSnapshot(ServerPlayer player, MarketQuery query) {
        MinecraftServer server = player.server;
        MarketWorldData world = MarketWorldData.get(server);
        String token = world.token(player.getUUID());
        boolean uploadsAllowed = !world.isCreativeUsed();

        if (token == null) {
            MarketNetwork.pushSnapshot(player, new MarketSnapshotS2C(
                    query, 0, 1, 0, List.of(),
                    0, 0, 0, 0, 0, 0, 0, 0, 0,
                    new int[MarketCategory.values().length], List.of(),
                    false, uploadsAllowed
            ));
            return;
        }

        MarketApiClient.snapshot(player.getUUID(), token, query)
                .thenAccept(response -> server.execute(() -> {
                    if (!response.ok()) {
                        MarketNetwork.sendNotice(player, false, errorOf(response));
                        return;
                    }
                    MarketNetwork.pushSnapshot(player, parseSnapshot(player, query, response.body(), uploadsAllowed));
                }));
    }

    // ------------------------------------------------------------------
    // Listing lifecycle
    // ------------------------------------------------------------------

    public static void createListing(ServerPlayer seller, int slot, int count, long price) {
        MinecraftServer server = seller.server;
        MarketWorldData world = MarketWorldData.get(server);
        String token = requireToken(seller, world);
        if (token == null) {
            return;
        }
        if (world.isCreativeUsed()) {
            MarketNetwork.sendNotice(seller, false, "本存档开启过创造模式，永久禁止上传商品");
            return;
        }
        if (price <= 0L || price > MarketConstants.MAX_PRICE) {
            MarketNetwork.sendNotice(seller, false,
                    "价格必须在 1 到 " + MarketConstants.MAX_PRICE + " 之间");
            return;
        }

        Inventory inventory = seller.getInventory();
        if (slot < 0 || slot >= inventory.getContainerSize()) {
            MarketNetwork.sendNotice(seller, false, "无效的背包槽位");
            return;
        }
        ItemStack source = inventory.getItem(slot);
        if (source.isEmpty() || count <= 0 || count > source.getCount()) {
            MarketNetwork.sendNotice(seller, false, "上架数量无效，物品可能已发生变化");
            return;
        }
        if (source.is(moneyItem())) {
            MarketNetwork.sendNotice(seller, false, "市场货币本身不能上架，请使用存入功能");
            return;
        }

        ItemStack escrow = source.copy();
        escrow.setCount(count);
        String problem = MarketItemCodec.validationProblem(seller.registryAccess(), escrow);
        if (problem != null) {
            MarketNetwork.sendNotice(seller, false, problem);
            return;
        }

        String itemJson;
        try {
            itemJson = MarketItemCodec.toJson(seller.registryAccess(), escrow).toString();
        } catch (RuntimeException failure) {
            MarketNetwork.sendNotice(seller, false, "物品数据无法序列化");
            return;
        }

        MarketNetwork.sendNotice(seller, true, "正在提交挂单…");
        MarketApiClient.createListing(playerId(seller), token, itemJson, price, true)
                .thenAccept(response -> server.execute(() -> {
                    if (!response.ok()) {
                        MarketNetwork.sendNotice(seller, false, errorOf(response));
                        MarketNetwork.refresh(seller);
                        return;
                    }
                    // The website escrowed the item. Now deduct locally; if the
                    // slot changed in the meantime, undo the listing instead.
                    ItemStack current = seller.getInventory().getItem(slot);
                    if (current.isEmpty() || current.getCount() < count
                            || !ItemStack.isSameItemSameComponents(current, escrow)) {
                        long listingId = response.body().has("listingId")
                                ? response.body().get("listingId").getAsLong() : -1L;
                        if (listingId > 0L) {
                            MarketApiClient.cancel(playerId(seller), token, listingId);
                        }
                        MarketNetwork.sendNotice(seller, false, "物品已移动，上架已取消");
                        MarketNetwork.refresh(seller);
                        return;
                    }
                    current.shrink(count);
                    if (current.isEmpty()) {
                        seller.getInventory().setItem(slot, ItemStack.EMPTY);
                    }
                    seller.getInventory().setChanged();
                    seller.containerMenu.broadcastChanges();
                    boolean pending = response.body().has("status")
                            && "PENDING_REVIEW".equalsIgnoreCase(
                                    response.body().get("status").getAsString());
                    MarketNetwork.sendNotice(seller, true, pending
                            ? "商品已提交，等待管理员审核"
                            : "商品已上架");
                    MarketNetwork.refresh(seller);
                }));
    }

    public static void buyListing(ServerPlayer buyer, long listingId) {
        MinecraftServer server = buyer.server;
        String token = requireToken(buyer, MarketWorldData.get(server));
        if (token == null) {
            return;
        }
        MarketApiClient.buy(playerId(buyer), token, listingId)
                .thenAccept(response -> server.execute(() -> {
                    MarketNetwork.sendNotice(buyer, response.ok(), response.ok()
                            ? "购买成功，物品将在 10 分钟后送达邮箱"
                            : errorOf(response));
                    MarketNetwork.refresh(buyer);
                }));
    }

    public static void cancelListing(ServerPlayer seller, long listingId) {
        MinecraftServer server = seller.server;
        String token = requireToken(seller, MarketWorldData.get(server));
        if (token == null) {
            return;
        }
        MarketApiClient.cancel(playerId(seller), token, listingId)
                .thenAccept(response -> server.execute(() -> {
                    MarketNetwork.sendNotice(seller, response.ok(), response.ok()
                            ? "挂单已取消，物品将退回邮箱"
                            : errorOf(response));
                    MarketNetwork.refresh(seller);
                }));
    }

    // ------------------------------------------------------------------
    // Mailbox
    // ------------------------------------------------------------------

    public static void claimMailbox(ServerPlayer player) {
        MinecraftServer server = player.server;
        String token = requireToken(player, MarketWorldData.get(server));
        if (token == null) {
            return;
        }
        MarketApiClient.claimMailbox(playerId(player), token)
                .thenAccept(response -> server.execute(() -> {
                    if (!response.ok()) {
                        MarketNetwork.sendNotice(player, false, errorOf(response));
                        return;
                    }
                    JsonArray deliveries = response.body().has("deliveries")
                            && response.body().get("deliveries").isJsonArray()
                            ? response.body().getAsJsonArray("deliveries")
                            : new JsonArray();

                    int received = 0;
                    List<Long> confirmed = new ArrayList<>();
                    for (JsonElement element : deliveries) {
                        JsonObject delivery = element.getAsJsonObject();
                        long deliveryId = delivery.get("deliveryId").getAsLong();
                        String itemJson = delivery.get("item").getAsString();
                        var parsed = MarketItemCodec.fromJson(player.registryAccess(), itemJson);
                        if (parsed.isEmpty()) {
                            GlobalMarketMod.LOGGER.warn(
                                    "Skipping undeliverable market mailbox item {}", deliveryId);
                            continue;
                        }
                        ItemStack stack = parsed.get();
                        boolean fullyAdded = player.getInventory().add(stack);
                        if (!fullyAdded && !stack.isEmpty()) {
                            player.drop(stack, false);
                        }
                        confirmed.add(deliveryId);
                        received++;
                    }
                    if (!confirmed.isEmpty()) {
                        long[] ids = confirmed.stream().mapToLong(Long::longValue).toArray();
                        MarketApiClient.confirmClaim(playerId(player), token, ids);
                    }
                    MarketNetwork.sendNotice(player, true, received > 0
                            ? "已领取 " + received + " 件邮件物品"
                            : "邮箱暂无可领取的物品");
                    MarketNetwork.refresh(player);
                }));
    }

    // ------------------------------------------------------------------
    // Wallet (physical caf:money <-> website balance)
    // ------------------------------------------------------------------

    public static void deposit(ServerPlayer player, int amount) {
        MinecraftServer server = player.server;
        String token = requireToken(player, MarketWorldData.get(server));
        if (token == null) {
            return;
        }
        Item money = moneyItem();
        if (money == Items.AIR) {
            MarketNetwork.sendNotice(player, false, "未找到货币 caf:money，无法存入");
            return;
        }
        if (amount <= 0 || countMoney(player, money) < amount) {
            MarketNetwork.sendNotice(player, false, "背包中的 caf:money 不足");
            return;
        }

        MarketApiClient.deposit(playerId(player), token, amount)
                .thenAccept(response -> server.execute(() -> {
                    if (!response.ok()) {
                        MarketNetwork.sendNotice(player, false, errorOf(response));
                        return;
                    }
                    if (countMoney(player, money) < amount) {
                        // Money vanished mid-flight; undo the credit.
                        MarketApiClient.withdraw(playerId(player), token, amount);
                        MarketNetwork.sendNotice(player, false, "货币数量已变化，存入取消");
                        return;
                    }
                    deductMoney(player, money, amount);
                    MarketNetwork.sendNotice(player, true, "已存入 " + amount + " 枚 caf:money");
                    MarketNetwork.refresh(player);
                }));
    }

    public static void withdraw(ServerPlayer player, long amount) {
        MinecraftServer server = player.server;
        String token = requireToken(player, MarketWorldData.get(server));
        if (token == null) {
            return;
        }
        if (amount <= 0 || amount > MarketConstants.MAX_PRICE) {
            MarketNetwork.sendNotice(player, false, "提取数量无效");
            return;
        }
        MarketApiClient.withdraw(playerId(player), token, amount)
                .thenAccept(response -> server.execute(() -> {
                    MarketNetwork.sendNotice(player, response.ok(), response.ok()
                            ? "已提取 " + amount + " 枚，货币将送达邮箱"
                            : errorOf(response));
                    MarketNetwork.refresh(player);
                }));
    }

    // ------------------------------------------------------------------
    // Admin
    // ------------------------------------------------------------------

    public static void adminAction(
            ServerPlayer admin, long listingId, AdminListingActionC2S.Action action) {
        MinecraftServer server = admin.server;
        String token = requireToken(admin, MarketWorldData.get(server));
        if (token == null) {
            return;
        }
        MarketApiClient.adminAction(playerId(admin), token, listingId, action.apiName())
                .thenAccept(response -> server.execute(() -> {
                    if (!response.ok()) {
                        MarketNetwork.sendNotice(admin, false, errorOf(response));
                        MarketNetwork.refresh(admin);
                        return;
                    }
                    if (action == AdminListingActionC2S.Action.COPY_ITEM
                            && response.has("item")) {
                        MarketItemCodec.fromJson(
                                        admin.registryAccess(),
                                        response.body().get("item").getAsString())
                                .ifPresent(stack -> {
                                    if (!admin.getInventory().add(stack)) {
                                        admin.drop(stack, false);
                                    }
                                });
                    }
                    MarketNetwork.sendNotice(admin, true, "管理操作已执行");
                    MarketNetwork.refresh(admin);
                }));
    }

    // ------------------------------------------------------------------
    // Snapshot parsing
    // ------------------------------------------------------------------

    private static MarketSnapshotS2C parseSnapshot(
            ServerPlayer player, MarketQuery query, JsonObject body, boolean uploadsAllowed) {
        int page = optInt(body, "page", 0);
        int totalPages = Math.max(1, optInt(body, "totalPages", 1));
        int totalMatches = optInt(body, "totalMatches", 0);

        List<MarketListing> listings = new ArrayList<>();
        if (body.has("listings") && body.get("listings").isJsonArray()) {
            for (JsonElement element : body.getAsJsonArray("listings")) {
                JsonObject row = element.getAsJsonObject();
                MarketItemCodec.fromJson(
                                player.registryAccess(), row.get("item").getAsString())
                        .ifPresent(stack -> listings.add(new MarketListing(
                                row.get("id").getAsLong(),
                                UUID.fromString(row.get("sellerId").getAsString()),
                                row.has("sellerName") ? row.get("sellerName").getAsString() : "",
                                stack,
                                row.get("price").getAsLong(),
                                row.get("createdAt").getAsLong(),
                                row.get("expiresAt").getAsLong(),
                                row.has("status") && "PENDING_REVIEW".equalsIgnoreCase(
                                        row.get("status").getAsString())
                                        ? MarketListing.ListingStatus.PENDING_REVIEW
                                        : MarketListing.ListingStatus.ACTIVE
                        )));
            }
        }

        int[] categoryCounts = new int[MarketCategory.values().length];
        if (body.has("categoryCounts") && body.get("categoryCounts").isJsonObject()) {
            JsonObject counts = body.getAsJsonObject("categoryCounts");
            MarketCategory[] categories = MarketCategory.values();
            for (int i = 0; i < categories.length; i++) {
                if (counts.has(categories[i].name())) {
                    categoryCounts[i] = counts.get(categories[i].name()).getAsInt();
                }
            }
        }

        List<MarketTransaction> history = new ArrayList<>();
        if (body.has("history") && body.get("history").isJsonArray()) {
            for (JsonElement element : body.getAsJsonArray("history")) {
                JsonObject row = element.getAsJsonObject();
                MarketItemCodec.fromJson(
                                player.registryAccess(), row.get("item").getAsString())
                        .ifPresent(stack -> history.add(new MarketTransaction(
                                row.get("id").getAsLong(),
                                stack,
                                row.get("price").getAsLong(),
                                UUID.fromString(row.get("sellerId").getAsString()),
                                row.has("sellerName") ? row.get("sellerName").getAsString() : "",
                                row.has("buyerId") && !row.get("buyerId").isJsonNull()
                                        ? UUID.fromString(row.get("buyerId").getAsString()) : null,
                                row.has("buyerName") ? row.get("buyerName").getAsString() : "",
                                row.get("timestamp").getAsLong(),
                                MarketTransaction.resultByName(
                                        row.has("result") ? row.get("result").getAsString() : null)
                        )));
            }
        }

        return new MarketSnapshotS2C(
                query, page, totalPages, totalMatches, listings,
                optLong(body, "balance", 0L),
                optLong(body, "pendingMoney", 0L),
                optInt(body, "pendingItems", 0),
                optInt(body, "inTransitItems", 0),
                optLong(body, "inTransitMoney", 0L),
                optLong(body, "nextDeliveryAt", 0L),
                optInt(body, "listingAttemptsUsed", 0),
                optInt(body, "listingAttemptsRemaining", 0),
                optLong(body, "listingQuotaResetAt", 0L),
                categoryCounts, history,
                true, uploadsAllowed
        );
    }

    // ------------------------------------------------------------------
    // Helpers
    // ------------------------------------------------------------------

    public static boolean isMoneyAvailable() {
        return moneyItem() != Items.AIR;
    }

    // ------------------------------------------------------------------
    // Fee previews (the website is authoritative; these mirror its rules)
    // ------------------------------------------------------------------

    public static long listingFee(long price) {
        long percent = price * MarketConstants.LISTING_FEE_PERCENT / 100L;
        return Math.max(MarketConstants.MIN_LISTING_FEE, percent);
    }

    public static long saleFee(long price) {
        return price * MarketConstants.SALE_FEE_PERCENT / 100L;
    }

    public static long sellerNet(long price) {
        return Math.max(0L, price - saleFee(price));
    }

    /** The caf:money item registered by this mod. */
    public static Item moneyItem() {
        return GlobalMarketMod.MONEY.get();
    }

    private static UUID playerId(ServerPlayer player) {
        return player.getUUID();
    }

    private static String requireToken(ServerPlayer player, MarketWorldData world) {
        String token = world.token(player.getUUID());
        if (token == null) {
            MarketNetwork.sendNotice(player, false,
                    "尚未绑定官网账号，请先使用 /market bind <绑定码>");
        }
        return token;
    }

    private static String errorOf(MarketApiClient.ApiResponse response) {
        return response.error() != null && !response.error().isBlank()
                ? response.error()
                : "全球市场服务暂时不可用，请稍后再试";
    }

    private static int countMoney(ServerPlayer player, Item money) {
        int total = 0;
        Inventory inventory = player.getInventory();
        for (int i = 0; i < inventory.getContainerSize(); i++) {
            ItemStack stack = inventory.getItem(i);
            if (stack.is(money)) {
                total += stack.getCount();
            }
        }
        return total;
    }

    private static void deductMoney(ServerPlayer player, Item money, long amount) {
        long remaining = amount;
        Inventory inventory = player.getInventory();
        for (int i = 0; i < inventory.getContainerSize() && remaining > 0; i++) {
            ItemStack stack = inventory.getItem(i);
            if (stack.is(money)) {
                int taken = (int) Math.min(stack.getCount(), remaining);
                stack.shrink(taken);
                if (stack.isEmpty()) {
                    inventory.setItem(i, ItemStack.EMPTY);
                }
                remaining -= taken;
            }
        }
        inventory.setChanged();
        player.containerMenu.broadcastChanges();
    }

    private static int optInt(JsonObject body, String key, int fallback) {
        return body.has(key) && !body.get(key).isJsonNull()
                ? body.get(key).getAsInt() : fallback;
    }

    private static long optLong(JsonObject body, String key, long fallback) {
        return body.has(key) && !body.get(key).isJsonNull()
                ? body.get(key).getAsLong() : fallback;
    }
}
