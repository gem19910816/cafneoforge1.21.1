package com.gearsandflesh.market.config;

import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.StringUtils;

public final class MarketConfig {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.ConfigValue<String> API_BASE_URL = BUILDER
            .comment("Base URL of the official website market API, no trailing slash.",
                    "Example: https://www.clyfr.cn/market-api")
            .define("apiBaseUrl", "https://www.clyfr.cn/market-api");

    public static final ModConfigSpec.ConfigValue<String> SERVER_ID = BUILDER
            .comment("Identifier of this server/singleplayer instance reported to the API.",
                    "Leave as-is unless the website asks you to change it.")
            .define("serverId", "default");

    public static final ModConfigSpec.IntValue REQUEST_TIMEOUT_SECONDS = BUILDER
            .comment("HTTP timeout for market API calls.")
            .defineInRange("requestTimeoutSeconds", 15, 3, 120);

    public static final ModConfigSpec.BooleanValue REQUIRE_TERMINAL = BUILDER
            .comment("When true, players may only open the market by right-clicking a",
                    "Market Terminal block. /market stays admin-only.",
                    "When false, /market also opens the market for normal players.")
            .define("requireTerminalBlock", true);

    public static final ModConfigSpec SPEC = BUILDER.build();

    private MarketConfig() {
    }

    public static String apiBaseUrl() {
        String url = API_BASE_URL.get();
        return StringUtils.appendIfMissing(url, "").replaceAll("/+$", "");
    }
}
