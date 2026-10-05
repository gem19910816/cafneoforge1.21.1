package com.gearsandflesh.market.data;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.world.item.ItemStack;

import java.util.UUID;

public record MarketTransaction(
        long id,
        ItemStack item,
        long price,
        UUID sellerId,
        String sellerName,
        UUID buyerId,
        String buyerName,
        long timestamp,
        Result result
) {
    public enum Result {
        SOLD,
        CANCELLED,
        EXPIRED,
        ADMIN_COPIED,
        ADMIN_REMOVED,
        ADMIN_DELETED
    }

    public MarketTransaction {
        item = item == null ? ItemStack.EMPTY : item.copy();
        sellerName = sanitizeName(sellerName);
        buyerName = sanitizeName(buyerName);
        result = result == null ? Result.SOLD : result;
    }

    public boolean involves(UUID playerId) {
        return sellerId.equals(playerId) || (buyerId != null && buyerId.equals(playerId));
    }

    public void write(RegistryFriendlyByteBuf buffer) {
        buffer.writeVarLong(id);
        MarketItemCodec.write(buffer, item);
        buffer.writeVarLong(price);
        buffer.writeUUID(sellerId);
        buffer.writeUtf(sellerName, 64);
        buffer.writeBoolean(buyerId != null);
        if (buyerId != null) {
            buffer.writeUUID(buyerId);
        }
        buffer.writeUtf(buyerName, 64);
        buffer.writeVarLong(timestamp);
        buffer.writeEnum(result);
    }

    public static MarketTransaction read(RegistryFriendlyByteBuf buffer) {
        long id = buffer.readVarLong();
        ItemStack item = MarketItemCodec.read(buffer);
        long price = buffer.readVarLong();
        UUID sellerId = buffer.readUUID();
        String sellerName = buffer.readUtf(64);
        UUID buyerId = buffer.readBoolean() ? buffer.readUUID() : null;
        String buyerName = buffer.readUtf(64);
        long timestamp = buffer.readVarLong();
        Result result = buffer.readEnum(Result.class);
        return new MarketTransaction(id, item, price, sellerId, sellerName, buyerId, buyerName, timestamp, result);
    }

    public static Result resultByName(String name) {
        if (name != null) {
            try {
                return Result.valueOf(name);
            } catch (IllegalArgumentException ignored) {
            }
        }
        return Result.SOLD;
    }

    private static String sanitizeName(String value) {
        if (value == null) {
            return "";
        }
        String clean = value.replaceAll("[\\p{Cntrl}]", "");
        return clean.length() <= 64 ? clean : clean.substring(0, 64);
    }
}
