package com.scarasol.zombiekit.block;

import com.scarasol.zombiekit.config.CommonConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

public interface BombBlock {

    default void exploded(Level level, int x, int y, int z, int amplifier){
        level.explode(null, x, y, z, amplifier, Level.ExplosionInteraction.NONE);
        if (level.getBlockState(new BlockPos(x, y, z)).getBlock() instanceof BombBlock)
            level.destroyBlock(new BlockPos(x, y, z), false);
        if (CommonConfig.LANDMINE_CHAIN.get()){
            int range = CommonConfig.CHAIN_RANGE.get();
            BlockPos center = new BlockPos(x, y, z);
            for (BlockPos cursor : BlockPos.betweenClosed(center.offset(-range, -range, -range), center.offset(range, range, range))){
                if (level.getBlockState(cursor).getBlock() instanceof BombBlock block)
                    exploded(level, cursor.getX(), cursor.getY(), cursor.getZ(), block.getExplodeLevel());
            }
        }

    }

    int getExplodeLevel();
}
