package net.gem19910816.dyairdrop.core;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

/**
 * 音效播放工具：把 MCreator 到处重复的「服务端 playSound / 客户端 playLocalSound」二选一写法收敛成一处。
 *
 * <p>与旧实现一致：服务端 {@link Level#playSound}（{@code null} 播放者、方块音源），
 * 客户端 {@link Level#playLocalSound}；音量传入、音高固定 1.0。
 */
public final class Sounds {

    private Sounds() {
    }

    public static void play(LevelAccessor world, double x, double y, double z, SoundEvent sound, float volume) {
        play(world, x, y, z, sound, SoundSource.BLOCKS, volume);
    }

    /** 指定音源版本（例如信号枪用的是 {@link SoundSource#NEUTRAL}）。 */
    public static void play(LevelAccessor world, double x, double y, double z, SoundEvent sound, SoundSource source, float volume) {
        if (sound == null || !(world instanceof Level level)) {
            return;
        }
        BlockPos pos = BlockPos.containing(x, y, z);
        if (!level.isClientSide()) {
            level.playSound(null, pos, sound, source, volume, 1.0F);
        } else {
            level.playLocalSound(x, y, z, sound, source, volume, 1.0F, false);
        }
    }
}
