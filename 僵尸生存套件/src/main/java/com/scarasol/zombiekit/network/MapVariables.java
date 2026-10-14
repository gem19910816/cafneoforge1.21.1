package com.scarasol.zombiekit.network;

import com.google.common.collect.Maps;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.saveddata.SavedData;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.Map;
import java.util.UUID;

/**
 * @author Scarasol
 */
public class MapVariables extends SavedData {
    public static final String DATA_NAME = "zombiekit_mapvars";
    public String radioLocation = "";
    public Map<UUID, ChunkPos> mortarPos = Maps.newHashMap();
    static volatile MapVariables clientSide = new MapVariables();

    public static MapVariables load(CompoundTag tag, HolderLookup.Provider provider) {
        MapVariables data = new MapVariables();
        data.read(tag);
        return data;
    }

    public void read(CompoundTag nbt) {
        radioLocation = nbt.getString("radio_location");
        if (nbt.contains("MortarPos")) {
            mortarPos.clear();
            ListTag contentList = nbt.getList("MortarPos", Tag.TAG_COMPOUND);
            for (Tag tag : contentList) {
                if (tag instanceof CompoundTag mortarTag && mortarTag.hasUUID("Id")) {
                    mortarPos.put(mortarTag.getUUID("Id"), new ChunkPos(mortarTag.getInt("X"), mortarTag.getInt("Z")));
                }
            }
        }
    }

    @Override
    public CompoundTag save(CompoundTag nbt, HolderLookup.Provider provider) {
        nbt.putString("radio_location", radioLocation);
        if (!mortarPos.isEmpty()) {
            ListTag contentList = new ListTag();
            for (Map.Entry<UUID, ChunkPos> mortar : mortarPos.entrySet()) {
                CompoundTag mortarTag = new CompoundTag();
                mortarTag.putUUID("Id", mortar.getKey());
                mortarTag.putInt("X", mortar.getValue().x);
                mortarTag.putInt("Z", mortar.getValue().z);
                contentList.add(mortarTag);
            }
            nbt.put("MortarPos", contentList);
        }
        return nbt;
    }

    public void syncData(LevelAccessor world) {
        this.setDirty();
        if (world instanceof ServerLevel level && !world.isClientSide()) {
            PacketDistributor.sendToPlayersInDimension(level, SavedDataSyncPacket.of(this));
        }
    }

    public static MapVariables get(LevelAccessor world) {
        if (world instanceof ServerLevel serverLevelAcc) {
            return serverLevelAcc.getDataStorage().computeIfAbsent(new Factory<>(MapVariables::new, MapVariables::load, null), DATA_NAME);
        } else {
            return clientSide;
        }
    }

}
