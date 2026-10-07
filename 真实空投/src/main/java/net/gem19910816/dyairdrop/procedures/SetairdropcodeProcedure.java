package net.gem19910816.dyairdrop.procedures;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;

import net.gem19910816.dyairdrop.core.CommandArgs;
import net.gem19910816.dyairdrop.core.Vars;
import net.gem19910816.dyairdrop.network.DyairdropModVariables;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

/**
 * {@code /setairdropcode <loot> <blockid>}：把「信号枪召唤用的掉落表与空投箱类型」写到执行者身上。
 *
 * <p>重构点：
 * <ul>
 *   <li>用 {@link Vars} 直接读写玩家数据，去掉 4 处重复的命令参数解析（原来同一个实体参数被解析了 6 次）；</li>
 *   <li><b>修掉一个原实现的 bug</b>：旧代码把 {@code loot} 同时写进了 {@code airdropblock}，
 *       导致命令的第二个参数 {@code blockid} 完全失效（而 {@code airdropblock} 是
 *       {@code FlaregunlootsetProcedure} / {@code FlareticksProcedure} 真正读取的字段，
 *       决定信号枪召唤哪种空投箱）。现在按命令语义写入 {@code blockid}；</li>
 *   <li>参数解析失败时（{@code player} 不存在）安全返回，不再 NPE。</li>
 * </ul>
 */
public class SetairdropcodeProcedure {

    public static void execute(final CommandContext<CommandSourceStack> arguments) {
        String loot = StringArgumentType.getString(arguments, "loot");
        String blockId = StringArgumentType.getString(arguments, "blockid");

        Entity entity = CommandArgs.entity(arguments, "player");
        if (entity == null) {
            return;
        }

        DyairdropModVariables.PlayerVariables vars = Vars.of(entity);
        vars.airdroploot = loot;
        vars.airdropblock = blockId;
        Vars.sync(entity);

        if (entity instanceof Player player && !player.level().isClientSide()) {
            player.displayClientMessage(
                    Component.literal(Component.translatable("message.yourloot").getString() + loot + "," + blockId),
                    false);
        }
    }
}
