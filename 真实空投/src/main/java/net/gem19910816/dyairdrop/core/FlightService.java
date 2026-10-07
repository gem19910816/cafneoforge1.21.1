package net.gem19910816.dyairdrop.core;

import java.text.DecimalFormat;
import java.util.Random;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;

import net.gem19910816.dyairdrop.DyairdropMod;
import net.gem19910816.dyairdrop.configuration.AirdropconfigConfiguration;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec3;

/**
 * 空投飞机投放的唯一实现。
 *
 * <p>合并原 {@code Flycode2neo / Flycode2neomap / Flycode3neo / Flycode3neomap} 四个过程
 * （合计 349 行、逻辑几乎完全重复）：
 * <ul>
 *   <li>{@link #launchFree}：{@code /setairdrop free <x> <z> <height> <length> <blockid> <loot_table> <pin> [map]}
 *       —— 在指定坐标投放；</li>
 *   <li>{@link #launchRandom}：{@code /setairdrop random <player> <height> <length> <driftmin> <driftmax>
 *       <blockid> <loot_table> <pin> [map]} —— 在玩家附近随机漂移一个半径后转调 {@code free}。</li>
 * </ul>
 * 是否打地图标记由可选的 {@code map} 参数决定（调用方传了才读，与原来"有 map 子命令才读"一致）。
 *
 * <p>合并时统一掉的三处历史分叉（都属于同一功能的不同版本，行为差异已在此说明）：
 * <ol>
 *   <li>播放飞机音效统一为 {@code @a[distance=..128] ~ ~ ~ 8 1 0}（原 {@code neo} 变体是
 *       {@code @a ~ ~ ~ 25 0}：全服可闻、音高 0），采用后来修好的那份；</li>
 *   <li>{@code driftmax < driftmin} 时统一按 {@code min/max} 取值——原 {@code Flycode3neo} 在该分支
 *       把上下界都写成了 {@code driftmin}，属于笔误；</li>
 *   <li>召唤飞机的实体选择规则统一为「强制加载时用运输机 transportplane，否则用 plane」，
 *       名字规则统一为「{@code pin} 且 blockid 带 {@code dyairdrop:} 前缀时加 {@code locked}」，
 *       两者与原实现各分支一致。</li>
 * </ol>
 *
 * <p>未改动的机制：{@code /setairdrop} 命令名与参数顺序、{@code map} 含义（给飞机挂 {@code dymap} 标记，
 * 由 {@code MapMarkerService} 落地打点）、60 tick 延迟召唤、掉落表与名字的拼装格式。
 */
public final class FlightService {

    /** 飞机音效命令（距离受限，避免全服玩家都听到）。 */
    private static final String PLANE_SOUND_COMMAND = "playsound dyairdrop:planesound ambient @a[distance=..128] ~ ~ ~ 8 1 0";
    private static final String PLANE_TRANSPORT = "dyairdrop:transportplane";
    private static final String PLANE_NORMAL = "dyairdrop:plane";
    private static final String AIRDROP_PREFIX = "dyairdrop:";
    /** 起飞延迟：与原实现一致（60 tick）。 */
    private static final int LAUNCH_DELAY_TICKS = 60;
    private static final double SOUND_HEIGHT = 74.0;

    private FlightService() {
    }

    /** {@code /setairdrop free …}：在指定坐标投放空投。 */
    public static void launchFree(LevelAccessor world, CommandContext<CommandSourceStack> arguments) {
        double x = doubleArg(arguments, "x");
        double z = doubleArg(arguments, "z");
        double height = doubleArg(arguments, "height");
        double length = doubleArg(arguments, "length");
        String blockId = stringArg(arguments, "blockid");
        String lootTable = stringArg(arguments, "loot_table");
        boolean pin = boolArg(arguments, "pin");
        boolean map = optionalBoolArg(arguments, "map");

        if (!(world instanceof ServerLevel level)) {
            return;
        }

        Commands.run(level, x, SOUND_HEIGHT, z, PLANE_SOUND_COMMAND);

        String entityId = forceload() ? PLANE_TRANSPORT : PLANE_NORMAL;
        String nameHead = pin && blockId.contains(AIRDROP_PREFIX)
                ? "dyairdrop:locked" + blockId.replace(AIRDROP_PREFIX, "")
                : blockId;
        String customName = nameHead + "," + lootTable + "," + new DecimalFormat("##").format(length);
        Vec3 summonPos = new Vec3(Math.round(x - length), Math.round(height), Math.round(z));
        String extraNbt = map ? ",ForgeData:{dymap:1b}" : "";

        DyairdropMod.queueServerWork(LAUNCH_DELAY_TICKS, () -> {
            if (world instanceof ServerLevel serverLevel) {
                Commands.run(serverLevel, summonPos.x, summonPos.y, summonPos.z,
                        "summon " + entityId + " ~ ~ ~ {CustomName:'{\"text\":\"" + customName + "\"}'" + extraNbt + "}");
            }
        });
    }

    /** {@code /setairdrop random …}：在玩家周围随机漂移，然后按 {@code free} 的语义投放。 */
    public static void launchRandom(LevelAccessor world, double x, double y, double z, CommandContext<CommandSourceStack> arguments) {
        Entity player = CommandArgs.entity(arguments, "player");
        if (player == null) {
            broadcastLaunchFailed(world);
            return;
        }

        double driftA = doubleArg(arguments, "driftmin");
        double driftB = doubleArg(arguments, "driftmax");
        int radius = Mth.nextInt(RandomSource.create(), (int) Math.min(driftA, driftB), (int) Math.max(driftA, driftB));
        double angle = new Random().nextDouble() * 2.0 * Math.PI;
        double dropX = player.getX() + radius * Math.cos(angle);
        double dropZ = player.getZ() + radius * Math.sin(angle);

        broadcastDropPosition(world, dropX, dropZ);

        StringBuilder command = new StringBuilder(128)
                .append("/setairdrop free ")
                .append(new DecimalFormat("##").format(dropX)).append(' ')
                .append(new DecimalFormat("##").format(dropZ)).append(' ')
                .append(Math.round(doubleArg(arguments, "height"))).append(' ')
                .append(Math.round(doubleArg(arguments, "length"))).append(' ')
                .append('"').append(stringArg(arguments, "blockid")).append('"').append(' ')
                .append('"').append(stringArg(arguments, "loot_table")).append('"').append(' ')
                .append(boolArg(arguments, "pin"));
        if (hasArgument(arguments, "map")) {
            command.append(' ').append(optionalBoolArg(arguments, "map"));
        }

        if (world instanceof ServerLevel level) {
            Commands.run(level, x, y, z, command.toString());
        }
    }

    // ------------------------------------------------------------------ 内部

    private static boolean forceload() {
        return Boolean.TRUE.equals(AirdropconfigConfiguration.FORCELOAD.get());
    }

    private static void broadcastDropPosition(LevelAccessor world, double dropX, double dropZ) {
        if (world.isClientSide() || world.getServer() == null) {
            return;
        }
        String worldName = world instanceof Level level ? level.dimension().location().toString() : Level.OVERWORLD.toString();
        world.getServer().getPlayerList().broadcastSystemMessage(
                Component.literal(Component.translatable("message.wordaridropevents").getString()
                        + worldName + " [" + Math.round(dropX) + "," + Math.round(dropZ) + "]"),
                false);
    }

    private static void broadcastLaunchFailed(LevelAccessor world) {
        if (world.isClientSide() || world.getServer() == null) {
            return;
        }
        world.getServer().getPlayerList().broadcastSystemMessage(
                Component.literal("一个随机空投试图投放，但是由于没有玩家在主世界或指定的玩家不在主世界而失败。"), false);
    }

    private static boolean hasArgument(CommandContext<CommandSourceStack> arguments, String name) {
        try {
            BoolArgumentType.getBool(arguments, name);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private static boolean optionalBoolArg(CommandContext<CommandSourceStack> arguments, String name) {
        try {
            return BoolArgumentType.getBool(arguments, name);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private static double doubleArg(CommandContext<CommandSourceStack> arguments, String name) {
        return DoubleArgumentType.getDouble(arguments, name);
    }

    private static boolean boolArg(CommandContext<CommandSourceStack> arguments, String name) {
        return BoolArgumentType.getBool(arguments, name);
    }

    private static String stringArg(CommandContext<CommandSourceStack> arguments, String name) {
        return StringArgumentType.getString(arguments, name);
    }
}
