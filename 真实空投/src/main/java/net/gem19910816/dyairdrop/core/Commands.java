package net.gem19910816.dyairdrop.core;

import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

/**
 * 以「虚拟命令源」执行原版命令的工具，等价于 MCreator 生成的那一长串
 * {@code CommandSourceStack(...) + performPrefixedCommand(...) + withSuppressedOutput()}。
 *
 * <p>语义逐条保持不变：命令源为 {@link CommandSource#NULL}、权限等级 4（相当于 OP）、
 * 静默输出、位置即传入坐标（因此命令里的 {@code ~ ~ ~} 相对该点）。
 * 模组用这种方式做 setblock / summon / particle / data 之类的批量操作，行为必须一致。
 */
public final class Commands {

    private Commands() {
    }

    /** 在指定坐标执行命令（命令里的 {@code ~ ~ ~} 相对该坐标）。非服务端调用会被忽略。 */
    public static void run(LevelAccessor world, double x, double y, double z, String command) {
        if (!(world instanceof ServerLevel level) || level.getServer() == null || command == null || command.isEmpty()) {
            return;
        }
        CommandSourceStack source = new CommandSourceStack(
                CommandSource.NULL,
                new Vec3(x, y, z),
                Vec2.ZERO,
                level,
                4,
                "",
                Component.literal(""),
                level.getServer(),
                null)
                .withSuppressedOutput();
        level.getServer().getCommands().performPrefixedCommand(source, command);
    }

    /** 在方块坐标执行命令。 */
    public static void run(LevelAccessor world, BlockPos pos, String command) {
        run(world, pos.getX(), pos.getY(), pos.getZ(), command);
    }

    /** 便捷判断：这个 LevelAccessor 是不是可以执行命令的服务端世界。 */
    public static boolean canRun(LevelAccessor world) {
        return world instanceof Level level && !level.isClientSide() && level.getServer() != null;
    }
}
