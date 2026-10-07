package net.gem19910816.dyairdrop.core;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.arguments.MessageArgument;
import net.minecraft.commands.arguments.coordinates.BlockPosArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;

/**
 * 命令参数的安全取值工具。
 *
 * <p>取代 MCreator 在每个命令过程里重复生成的匿名类：
 * <pre>
 * (new Object() {
 *    public Entity getEntity() {
 *       try { return EntityArgument.getEntity(arguments, "player"); }
 *       catch (CommandSyntaxException e) { e.printStackTrace(); return null; }
 *    }
 * }).getEntity()
 * </pre>
 * 失败时的返回值与旧实现一致（实体 {@code null}、消息 {@code ""}、坐标 {@link BlockPos#ZERO}）。
 */
public final class CommandArgs {

    private CommandArgs() {
    }

    /** 取实体参数；解析失败返回 {@code null}（旧实现同时打印堆栈，这里保留该行为）。 */
    public static Entity entity(CommandContext<CommandSourceStack> arguments, String name) {
        try {
            return EntityArgument.getEntity(arguments, name);
        } catch (CommandSyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }

    /** 取已加载的方块坐标；解析失败返回 {@link BlockPos#ZERO}（等价于旧实现里的 0.0）。 */
    public static BlockPos loadedPos(CommandContext<CommandSourceStack> arguments, String name) {
        try {
            return BlockPosArgument.getLoadedBlockPos(arguments, name);
        } catch (CommandSyntaxException e) {
            e.printStackTrace();
            return BlockPos.ZERO;
        }
    }

    /** 取文本参数；解析失败返回空串（旧实现静默返回）。 */
    public static String message(CommandContext<CommandSourceStack> arguments, String name) {
        try {
            return MessageArgument.getMessage(arguments, name).getString();
        } catch (CommandSyntaxException ignored) {
            return "";
        }
    }
}
