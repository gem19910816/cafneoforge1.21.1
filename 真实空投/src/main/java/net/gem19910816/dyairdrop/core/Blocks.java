package net.gem19910816.dyairdrop.core;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;

/**
 * 方块状态相关的小工具。
 *
 * <p>{@link #facingOf(BlockState)} 取代 MCreator 生成的 {@code getDirection} 匿名类：
 * 优先取 {@code facing} 属性，其次由 {@code axis} 推导，都没有则 {@link Direction#NORTH}。
 * 空投箱「解锁后替换成 open 形态」的命令依赖这个方向值，行为必须一致。
 */
public final class Blocks {

    private Blocks() {
    }

    public static String idOf(BlockState state) {
        return BuiltInRegistries.BLOCK.getKey(state.getBlock()).toString();
    }

    /**
     * 设置方块自带的 {@code animation} 属性（0..2）：不存在该属性或取值不在允许范围时什么都不做。
     *
     * <p>空投箱的开箱动画（面板解锁 1、整块替换 2）依赖它，三处调用点原来各写了一份。
     */
    public static void setAnimation(LevelAccessor world, BlockPos pos, int value) {
        BlockState state = world.getBlockState(pos);
        if (state.getBlock().getStateDefinition().getProperty("animation") instanceof IntegerProperty animation
                && animation.getPossibleValues().contains(value)) {
            world.setBlock(pos, state.setValue(animation, value), 3);
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static Direction facingOf(BlockState state) {
        Property<?> facing = state.getBlock().getStateDefinition().getProperty("facing");
        if (facing instanceof DirectionProperty directionProperty) {
            return state.getValue(directionProperty);
        }
        Property<?> axis = state.getBlock().getStateDefinition().getProperty("axis");
        if (axis instanceof EnumProperty enumProperty && enumProperty.getPossibleValues().toArray()[0] instanceof Direction.Axis) {
            return Direction.fromAxisAndDirection((Direction.Axis) state.getValue(enumProperty), Direction.AxisDirection.POSITIVE);
        }
        return Direction.NORTH;
    }
}
