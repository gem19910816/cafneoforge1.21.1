package com.gearsandflesh.market.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.datafix.DataFixTypes;
import net.minecraft.world.level.saveddata.SavedData;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Per-world market state: website account tokens per player and the permanent
 * "creative was used in this world" flag. A world that ever touched creative
 * mode (or was created with cheats enabled) can never upload listings.
 */
public final class MarketWorldData extends SavedData {
    private static final String DATA_NAME = "gearsandflesh_market_world";

    private final Map<UUID, String> playerTokens = new HashMap<>();
    private boolean creativeUsed;

    public static MarketWorldData get(MinecraftServer server) {
        return server.overworld().getDataStorage().computeIfAbsent(
                new SavedData.Factory<>(
                        MarketWorldData::new,
                        MarketWorldData::load,
                        DataFixTypes.LEVEL
                ),
                DATA_NAME
        );
    }

    public static MarketWorldData load(CompoundTag tag, HolderLookup.Provider registries) {
        MarketWorldData data = new MarketWorldData();
        data.creativeUsed = tag.getBoolean("CreativeUsed");
        ListTag tokens = tag.getList("PlayerTokens", Tag.TAG_COMPOUND);
        for (int i = 0; i < tokens.size(); i++) {
            CompoundTag entry = tokens.getCompound(i);
            if (entry.hasUUID("Id")) {
                data.playerTokens.put(entry.getUUID("Id"), entry.getString("Token"));
            }
        }
        return data;
    }

    @Override
    public CompoundTag save(CompoundTag tag, HolderLookup.Provider registries) {
        tag.putBoolean("CreativeUsed", creativeUsed);
        ListTag tokens = new ListTag();
        playerTokens.forEach((id, token) -> {
            CompoundTag entry = new CompoundTag();
            entry.putUUID("Id", id);
            entry.putString("Token", token);
            tokens.add(entry);
        });
        tag.put("PlayerTokens", tokens);
        return tag;
    }

    // ------------------------------------------------------------------
    // Creative flag
    // ------------------------------------------------------------------

    public boolean isCreativeUsed() {
        return creativeUsed;
    }

    public void markCreativeUsed() {
        if (!creativeUsed) {
            creativeUsed = true;
            setDirty();
        }
    }

    // ------------------------------------------------------------------
    // Player tokens
    // ------------------------------------------------------------------

    public String token(UUID playerId) {
        String token = playerTokens.get(playerId);
        return token == null || token.isBlank() ? null : token;
    }

    public boolean isBound(UUID playerId) {
        return token(playerId) != null;
    }

    public void bind(UUID playerId, String token) {
        playerTokens.put(playerId, token);
        setDirty();
    }

    public void unbind(UUID playerId) {
        if (playerTokens.remove(playerId) != null) {
            setDirty();
        }
    }
}
