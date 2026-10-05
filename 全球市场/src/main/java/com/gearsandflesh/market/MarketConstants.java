package com.gearsandflesh.market;

import net.minecraft.resources.ResourceLocation;

public final class MarketConstants {
    public static final String MOD_ID = "gearsandflesh_market";
    /** Currency id, registered by this mod and used by the website. */
    public static final ResourceLocation MONEY_ID = ResourceLocation.fromNamespaceAndPath("caf", "money");
    public static final int ADMIN_PERMISSION_LEVEL = 2;

    public static final int PAGE_SIZE = 9;
    public static final int MAX_ITEM_NBT_BYTES = 128 * 1024;
    public static final long MAX_PRICE = 1_000_000_000L;

    // The website enforces the real limits; these mirrors are only used to
    // render the quota/fee preview in the market screen.
    public static final int MAX_ACTIVE_LISTINGS = 30;
    public static final int MAX_LISTINGS_PER_HOUR = 10;
    public static final int LISTING_FEE_PERCENT = 2;
    public static final long MIN_LISTING_FEE = 5L;
    public static final int SALE_FEE_PERCENT = 3;

    /** API version the mod speaks. The website keeps /api/v1 stable. */
    public static final String API_PROTOCOL = "1";

    private MarketConstants() {
    }
}
