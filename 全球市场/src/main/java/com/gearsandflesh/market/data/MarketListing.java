package com.gearsandflesh.market.data;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.world.item.ItemStack;

import java.util.UUID;

public record MarketListing(
        long id,
        UUID sellerId,
        String sellerName,
        ItemStack item,
        long price,
        long createdAt,
        long expiresAt,
        ListingStatus status
) {
    public enum ListingStatus {
        ACTIVE,
        PENDING_REVIEW
    }

    public MarketListing {
        sellerName = sanitizeName(sellerName);
        item = item == null ? ItemStack.EMPTY : item.copy();
        status = status == null ? ListingStatus.ACTIVE : status;
    }

    public MarketListing(long id, UUID sellerId, String sellerName, ItemStack item,
                         long price, long createdAt, long expiresAt) {
        this(id, sellerId, sellerName, item, price, createdAt, expiresAt, ListingStatus.ACTIVE);
    }

    public boolean isExpired(long now) {
        return expiresAt <= now;
    }

    public void write(RegistryFriendlyByteBuf buffer) {
        buffer.writeVarLong(id);
        buffer.writeUUID(sellerId);
        buffer.writeUtf(sellerName, 64);
        MarketItemCodec.write(buffer, item);
        buffer.writeVarLong(price);
        buffer.writeVarLong(createdAt);
        buffer.writeVarLong(expiresAt);
        buffer.writeEnum(status);
    }

    public static MarketListing read(RegistryFriendlyByteBuf buffer) {
        return new MarketListing(
                buffer.readVarLong(),
                buffer.readUUID(),
                buffer.readUtf(64),
                MarketItemCodec.read(buffer),
                buffer.readVarLong(),
                buffer.readVarLong(),
                buffer.readVarLong(),
                buffer.readEnum(ListingStatus.class)
        );
    }

    private static String sanitizeName(String value) {
        if (value == null) {
            return "";
        }
        String clean = value.replaceAll("[\\p{Cntrl}]", "");
        return clean.length() <= 64 ? clean : clean.substring(0, 64);
    }
}
