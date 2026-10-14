package com.scarasol.zombiekit.data;

import com.google.common.collect.Sets;
import com.scarasol.zombiekit.api.MortarLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.ChunkPos;

import java.util.*;

public class LaunchSchedule {
    private final long createTime;
    private final UUID id;
    private int acceptCount;
    private int completeCount;
    private final BlockPos coordinate;
    private final ChunkPos chunk;

    private final Set<ChunkPos> mortarPos = Sets.newHashSet();
    private static final int MAX_TIME = 600;

    public LaunchSchedule(Long gameTime, BlockPos coordinate) {
        this.createTime = gameTime;
        this.id = UUID.randomUUID();
        this.coordinate = coordinate;
        this.chunk = new ChunkPos(coordinate);
    }


    public void completeSchedule(ServerLevel serverLevel) {
        this.completeCount++;
        if (isScheduleCompletion()) {
            if (serverLevel instanceof MortarLevel level)
                level.getMortarManager().abortSchedule(this);
        }

    }

    public void acceptSchedule() {
        this.acceptCount++;
    }

    public void failSchedule() {
        this.acceptCount--;
    }

    public boolean isScheduleCompletion() {
        return this.completeCount == acceptCount;
    }

    public boolean isTimeout(long gameTime) {
        return gameTime - this.createTime > MAX_TIME;
    }

    public BlockPos getCoordinate() {
        return this.coordinate;
    }

    public long getCreateTime() {
        return createTime;
    }

    public Set<ChunkPos> getMortarPos() {
        return mortarPos;
    }

    // 同时比较区块与目标坐标：同区块内不同坐标的炮击调度不应被视为同一对象
    // （旧实现只比较 chunk，导致同区块第二次炮击被 Set 静默去重丢弃）
    @Override
    public boolean equals(Object launchSchedule) {
        return launchSchedule instanceof LaunchSchedule l && l.chunk.equals(this.chunk) && l.coordinate.equals(this.coordinate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.chunk, this.coordinate);
    }
}
