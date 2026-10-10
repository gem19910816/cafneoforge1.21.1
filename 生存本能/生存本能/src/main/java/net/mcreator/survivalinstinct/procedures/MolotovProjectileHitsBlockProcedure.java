package net.mcreator.survivalinstinct.procedures;

import net.mcreator.survivalinstinct.init.SurvivalInstinctModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BaseFireBlock;

public final class MolotovProjectileHitsBlockProcedure {
    public static void execute(LevelAccessor world, double x, double y, double z) {
        if (!(world instanceof ServerLevel level)) return;
        BlockPos center = BlockPos.containing(x, y, z);
        level.playSound(null, center, SurvivalInstinctModSounds.EXPLOTE_MOLOTOV.get(), SoundSource.NEUTRAL, 1, 1);
        for (int dx = -2; dx <= 2; dx++) for (int dz = -2; dz <= 2; dz++) {
            if (Math.abs(dx) + Math.abs(dz) > 2) continue;
            BlockPos pos = center.offset(dx, 1, dz);
            if (level.isEmptyBlock(pos) && BaseFireBlock.canBePlacedAt(level, pos, net.minecraft.core.Direction.UP)) {
                level.setBlock(pos, BaseFireBlock.getState(level, pos), 3);
            }
        }
    }
}
