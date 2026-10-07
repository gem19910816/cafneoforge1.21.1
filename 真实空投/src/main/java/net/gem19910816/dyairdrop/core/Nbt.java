package net.gem19910816.dyairdrop.core;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

/**
 * 方块实体持久数据的安全读写工具。
 *
 * <p>取代 MCreator 生成的这种写法（每次读取都要 new 一个匿名类，tick 路径上产生大量垃圾对象）：
 * <pre>
 * (new Object() {
 *    public double getValue(LevelAccessor world, BlockPos pos, String tag) {
 *       BlockEntity blockEntity = world.getBlockEntity(pos);
 *       return blockEntity != null ? blockEntity.getPersistentData().getDouble(tag) : -1.0;
 *    }
 * }).getValue(world, BlockPos.containing(x, y, z), "timer")
 * </pre>
 *
 * <p>默认值与旧实现保持一致：{@code getDouble} 为 {@code -1.0}，{@code getString} 为 {@code ""}，
 * {@code getBoolean} 为 {@code false} —— 缺省值的语义是空投逻辑的一部分，不能改。
 */
public final class Nbt {

    public static final double DEFAULT_DOUBLE = -1.0;

    private Nbt() {
    }

    /** 取方块实体；方块不存在或不是方块实体时返回 null。 */
    public static BlockEntity blockEntity(LevelAccessor world, BlockPos pos) {
        return world == null || pos == null ? null : world.getBlockEntity(pos);
    }

    public static CompoundTag tag(LevelAccessor world, BlockPos pos) {
        BlockEntity be = blockEntity(world, pos);
        return be == null ? null : be.getPersistentData();
    }

    // ---------------------------------------------------------------- 读

    public static double getDouble(LevelAccessor world, BlockPos pos, String key) {
        CompoundTag tag = tag(world, pos);
        return tag == null ? DEFAULT_DOUBLE : tag.getDouble(key);
    }

    public static double getDouble(LevelAccessor world, double x, double y, double z, String key) {
        return getDouble(world, BlockPos.containing(x, y, z), key);
    }

    public static String getString(LevelAccessor world, BlockPos pos, String key) {
        CompoundTag tag = tag(world, pos);
        return tag == null ? "" : tag.getString(key);
    }

    public static String getString(LevelAccessor world, double x, double y, double z, String key) {
        return getString(world, BlockPos.containing(x, y, z), key);
    }

    public static boolean getBoolean(LevelAccessor world, BlockPos pos, String key) {
        CompoundTag tag = tag(world, pos);
        return tag != null && tag.getBoolean(key);
    }

    public static boolean getBoolean(LevelAccessor world, double x, double y, double z, String key) {
        return getBoolean(world, BlockPos.containing(x, y, z), key);
    }

    // ---------------------------------------------------------------- 写（带方块同步）

    /** 写字符串；值没变则不做任何事，避免无意义的数据包与 {@code setChanged()}。 */
    public static void setString(LevelAccessor world, BlockPos pos, String key, String value) {
        BlockEntity be = blockEntity(world, pos);
        if (be == null || be.getPersistentData().getString(key).equals(value)) {
            return;
        }
        be.getPersistentData().putString(key, value);
        markUpdated(world, pos, be);
    }

    public static void setDouble(LevelAccessor world, BlockPos pos, String key, double value) {
        BlockEntity be = blockEntity(world, pos);
        if (be == null || be.getPersistentData().getDouble(key) == value) {
            return;
        }
        be.getPersistentData().putDouble(key, value);
        markUpdated(world, pos, be);
    }

    public static void setBoolean(LevelAccessor world, BlockPos pos, String key, boolean value) {
        BlockEntity be = blockEntity(world, pos);
        if (be == null || be.getPersistentData().getBoolean(key) == value) {
            return;
        }
        be.getPersistentData().putBoolean(key, value);
        markUpdated(world, pos, be);
    }

    /** 与 MCreator 旧写法一致：写完后 {@code sendBlockUpdated(..., 3)} 并标脏。 */
    private static void markUpdated(LevelAccessor world, BlockPos pos, BlockEntity be) {
        if (world instanceof Level level) {
            BlockState state = level.getBlockState(pos);
            level.sendBlockUpdated(pos, state, state, 3);
        }
        be.setChanged();
    }
}
