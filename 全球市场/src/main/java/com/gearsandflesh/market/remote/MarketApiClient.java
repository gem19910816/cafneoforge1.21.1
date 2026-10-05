package com.gearsandflesh.market.remote;

import com.gearsandflesh.market.GlobalMarketMod;
import com.gearsandflesh.market.MarketConstants;
import com.gearsandflesh.market.config.MarketConfig;
import com.gearsandflesh.market.data.MarketQuery;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.Duration;
import java.util.HexFormat;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Async HTTPS client for the official website market API (v1).
 *
 * Authentication: every authenticated call carries the player UUID plus the
 * per-account token issued by /bind, and an HMAC-SHA256 signature over
 * "uuid\nnonce\ntimestamp\nmethod\npath\nbodySha256" so replayed or tampered
 * requests are rejected by the website.
 */
public final class MarketApiClient {
    private static final Gson GSON = new Gson();
    private static final Executor EXECUTOR = Executors.newCachedThreadPool(runnable -> {
        Thread thread = new Thread(runnable, "MarketApiClient");
        thread.setDaemon(true);
        return thread;
    });
    private static volatile HttpClient client;

    private MarketApiClient() {
    }

    public record ApiResponse(boolean ok, String error, JsonObject body) {
        public static ApiResponse error(String message) {
            return new ApiResponse(false, message, new JsonObject());
        }

        public boolean has(String member) {
            return body != null && body.has(member);
        }
    }

    private static HttpClient client() {
        HttpClient current = client;
        if (current == null) {
            synchronized (MarketApiClient.class) {
                if (client == null) {
                    client = HttpClient.newBuilder()
                            .executor(EXECUTOR)
                            .connectTimeout(Duration.ofSeconds(
                                    MarketConfig.REQUEST_TIMEOUT_SECONDS.get()))
                            .build();
                }
                current = client;
            }
        }
        return current;
    }

    // ------------------------------------------------------------------
    // Endpoints
    // ------------------------------------------------------------------

    /** Exchanges a website bind code for a per-player API token. */
    public static CompletableFuture<ApiResponse> bind(UUID playerId, String playerName, String code) {
        JsonObject payload = new JsonObject();
        payload.addProperty("uuid", playerId.toString());
        payload.addProperty("name", playerName);
        payload.addProperty("code", code);
        payload.addProperty("serverId", MarketConfig.SERVER_ID.get());
        return send("POST", "/bind", null, null, payload);
    }

    public static CompletableFuture<ApiResponse> snapshot(UUID playerId, String token, MarketQuery query) {
        String path = "/snapshot?view=" + query.view().name()
                + "&category=" + query.category().name()
                + "&sort=" + query.sort().name()
                + "&page=" + query.page()
                + "&search=" + urlEncode(query.search());
        return send("GET", path, playerId, token, null);
    }

    public static CompletableFuture<ApiResponse> createListing(
            UUID playerId, String token, String itemJson, long price, boolean worldClean) {
        JsonObject payload = new JsonObject();
        payload.addProperty("item", itemJson);
        payload.addProperty("price", price);
        payload.addProperty("worldClean", worldClean);
        return send("POST", "/listings", playerId, token, payload);
    }

    public static CompletableFuture<ApiResponse> buy(UUID playerId, String token, long listingId) {
        return send("POST", "/listings/" + listingId + "/buy", playerId, token, new JsonObject());
    }

    public static CompletableFuture<ApiResponse> cancel(UUID playerId, String token, long listingId) {
        return send("POST", "/listings/" + listingId + "/cancel", playerId, token, new JsonObject());
    }

    /** Claims due mailbox deliveries. Items arrive as serialized JSON stacks. */
    public static CompletableFuture<ApiResponse> claimMailbox(UUID playerId, String token) {
        return send("POST", "/mailbox/claim", playerId, token, new JsonObject());
    }

    public static CompletableFuture<ApiResponse> confirmClaim(
            UUID playerId, String token, long[] deliveryIds) {
        JsonObject payload = new JsonObject();
        var array = new com.google.gson.JsonArray();
        for (long id : deliveryIds) {
            array.add(id);
        }
        payload.add("deliveryIds", array);
        return send("POST", "/mailbox/confirm", playerId, token, payload);
    }

    public static CompletableFuture<ApiResponse> deposit(UUID playerId, String token, long amount) {
        JsonObject payload = new JsonObject();
        payload.addProperty("amount", amount);
        return send("POST", "/wallet/deposit", playerId, token, payload);
    }

    public static CompletableFuture<ApiResponse> withdraw(UUID playerId, String token, long amount) {
        JsonObject payload = new JsonObject();
        payload.addProperty("amount", amount);
        return send("POST", "/wallet/withdraw", playerId, token, payload);
    }

    public static CompletableFuture<ApiResponse> adminAction(
            UUID playerId, String token, long listingId, String action) {
        return send("POST", "/admin/listings/" + listingId + "/" + action,
                playerId, token, new JsonObject());
    }

    // ------------------------------------------------------------------
    // Transport
    // ------------------------------------------------------------------

    private static CompletableFuture<ApiResponse> send(
            String method, String path, UUID playerId, String token, JsonObject payload) {
        String body = payload == null ? "" : GSON.toJson(payload);
        String base = MarketConfig.apiBaseUrl() + "/api/v" + MarketConstants.API_PROTOCOL;

        HttpRequest.Builder request = HttpRequest.newBuilder()
                .uri(URI.create(base + path))
                .timeout(Duration.ofSeconds(MarketConfig.REQUEST_TIMEOUT_SECONDS.get()))
                .header("Content-Type", "application/json; charset=utf-8")
                .header("Accept", "application/json")
                .header("X-GM-Protocol", MarketConstants.API_PROTOCOL)
                .header("X-GM-Server-Id", MarketConfig.SERVER_ID.get());

        if (playerId != null && token != null) {
            String nonce = UUID.randomUUID().toString();
            String timestamp = Long.toString(System.currentTimeMillis());
            request.header("X-GM-UUID", playerId.toString())
                    .header("X-GM-Timestamp", timestamp)
                    .header("X-GM-Nonce", nonce)
                    .header("X-GM-Sign", sign(playerId, nonce, timestamp, method, path, body, token));
        }

        request.method(method, body.isEmpty()
                ? HttpRequest.BodyPublishers.noBody()
                : HttpRequest.BodyPublishers.ofString(body, StandardCharsets.UTF_8));

        return client().sendAsync(request.build(), HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8))
                .thenApply(MarketApiClient::parse)
                .exceptionally(failure -> {
                    GlobalMarketMod.LOGGER.warn("Market API call {} {} failed: {}",
                            method, path, failure.toString());
                    return ApiResponse.error("全球市场服务暂时不可用，请稍后再试");
                });
    }

    private static ApiResponse parse(HttpResponse<String> response) {
        String text = response.body() == null ? "" : response.body();
        try {
            JsonObject body = JsonParser.parseString(text).getAsJsonObject();
            boolean ok = body.has("ok") && body.get("ok").getAsBoolean();
            String error = body.has("error") && !body.get("error").isJsonNull()
                    ? body.get("error").getAsString() : null;
            if (response.statusCode() / 100 != 2 && error == null) {
                error = "全球市场服务返回错误 (" + response.statusCode() + ")";
                ok = false;
            }
            return new ApiResponse(ok, error, body);
        } catch (RuntimeException parseFailure) {
            return ApiResponse.error("全球市场服务返回了无法识别的数据");
        }
    }

    private static String sign(
            UUID playerId, String nonce, String timestamp, String method,
            String path, String body, String token) {
        try {
            String bodyHash = HexFormat.of().formatHex(
                    MessageDigest.getInstance("SHA-256")
                            .digest(body.getBytes(StandardCharsets.UTF_8)));
            String canonical = playerId + "\n" + nonce + "\n" + timestamp + "\n"
                    + method + "\n" + path + "\n" + bodyHash;
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(token.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
            return HexFormat.of().formatHex(mac.doFinal(canonical.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception failure) {
            throw new IllegalStateException("Unable to sign market request", failure);
        }
    }

    private static String urlEncode(String value) {
        return java.net.URLEncoder.encode(value, StandardCharsets.UTF_8);
    }
}
