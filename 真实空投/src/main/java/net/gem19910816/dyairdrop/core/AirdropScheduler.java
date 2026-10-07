package net.gem19910816.dyairdrop.core;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.context.CommandContext;

import net.gem19910816.dyairdrop.configuration.AirdropconfigConfiguration;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.blocks.BlockStateArgument;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;

/**
 * 空投调度：全局定时空投、{@code /airdrop}（定点）与 {@code /airdrop world}（随机玩家）三条入口。
 *
 * <p>取代原 {@code WorldairdropeventsProcedure}、{@code RandomworldairdropProcedure}、
 * {@code FastairdropProcedure}、{@code GetrandomplayerProcedure} 四个类（合计约 360 行）。
 *
 * <p>三处「等级」算法各自保留原样（它们本来就不一致，属于实际战利品等级，不能悄悄统一）：
 * <ul>
 *   <li>全局触发判定：{@code now/gap} 取整前后与原实现相同，仅当 {@code level >= 1 && now % gap == 0} 时投放；</li>
 *   <li>随机空投（{@code world}）：{@code round(now/gap)} 且下限 1、上限 {@code floor(maxlevel)}，并带调试广播；</li>
 *   <li>定点空投（{@code /airdrop}）：{@code floor(now/gap)} 且下限 1，战利品表尾号取 {@code round(level)}。</li>
 * </ul>
 * 其它保留项：漂移区间解析（{@code "min,max"}，缺省 50~100，上限 1024，自动交换顺序）、
 * 高度 / 起飞距离的钳制、可选世界白名单、{@code enableglobalcoordinates} 决定是否附加地图标记参数、
 * 以及「没有合适玩家时的失败广播」原文。
 */
@EventBusSubscriber
public final class AirdropScheduler {

    private static final double TICKS_PER_DAY = 24000.0;
    private static final double DAY_TIME_OFFSET = 100.0;
    private static final double DEFAULT_DRIFT_MIN = 50.0;
    private static final double DEFAULT_DRIFT_MAX = 100.0;
    private static final double MAX_DRIFT = 1024.0;
    private static final double MIN_HEIGHT = 100.0;
    private static final double MAX_HEIGHT = 320.0;
    private static final double MAX_LENGTH = 512.0;
    private static final String LARGE_AIRDROP_BLOCK = "dyairdrop:airdroplarge";
    private static final String RANDOM_FAILED_MESSAGE = "一个随机空投试图投放，但是由于没有玩家在主世界或指定的玩家不在主世界而失败。";
    private static final String NO_PLAYER_MESSAGE = "一个空投尝试投放，但是因为指定的世界没有玩家而失败";

    private AirdropScheduler() {
    }

    // ------------------------------------------------------------------ 全局定时

    /** 世界每 tick：到达 {@code gap} 天的整点（dayTime 相对 100 的整数倍）时投放一次随机空投。 */
    @SubscribeEvent
    public static void onLevelTick(net.neoforged.neoforge.event.tick.LevelTickEvent.Post event) {
        tickGlobal(event.getLevel());
    }

    public static void tickGlobal(LevelAccessor world) {
        if (!Boolean.TRUE.equals(AirdropconfigConfiguration.ENABLEAIRDROPEVENTS.get()) || !isOverworld(world)) {
            return;
        }
        double gap = dayGapTicks();
        double elapsed = elapsedSinceDawn(world);
        double level = elapsed / gap;
        if (level >= 1.0 && elapsed % gap == 0.0) {
            randomWorldAirdrop(world);
        }
    }

    // ------------------------------------------------------------------ /airdrop world

    /** 在配置允许的玩家里随机挑一个，围绕他空投一次大型空投。 */
    public static void randomWorldAirdrop(LevelAccessor world) {
        if (!Boolean.TRUE.equals(AirdropconfigConfiguration.ENABLEAIRDROPEVENTS.get()) || !isOverworld(world)) {
            return;
        }
        double height = clampedHeight();
        double length = clampedStartPosition();
        double[] drift = driftRange();

        double level = Math.round(elapsedSinceDawn(world) / dayGapTicks());
        if (level < 1.0) {
            level = 1.0;
        }
        double maxLevel = Math.floor(AirdropconfigConfiguration.MAXLEVEL.get());
        if (level >= maxLevel) {
            level = maxLevel;
            if (debug()) {
                Chat.broadcast(world, net.minecraft.network.chat.Component.literal("目前天数大于最大值，已削减到" + level));
            }
        }
        if (debug()) {
            Chat.broadcast(world, net.minecraft.network.chat.Component.literal("预计目标战利品表等级：" + level));
        }

        List<String> allowedWorlds = Arrays.asList(AirdropconfigConfiguration.AVAILABLEWORLD.get().split(","));
        Player target = randomPlayer(world, allowedWorlds);
        if (target == null) {
            Chat.broadcast(world, net.minecraft.network.chat.Component.literal(NO_PLAYER_MESSAGE));
            return;
        }
        String lootTable = "\"" + lootPrefix() + ":chests/largeairdrop" + new DecimalFormat("##").format(level) + "\"";
        // 后缀与原实现的两个组合完全等价：
        //   开锁=true、开启全局坐标 → " true true"；开锁=true、未开启 → " true"
        //   开锁=false、开启全局坐标 → " false true"；开锁=false、未开启 → " false"
        String pinFlag = Boolean.TRUE.equals(AirdropconfigConfiguration.ENABLELOCK.get()) ? "true" : "false";
        String suffix = Boolean.TRUE.equals(AirdropconfigConfiguration.ENABLEGLOBALCOORDINATES.get())
                ? " " + pinFlag + " true"
                : " " + pinFlag;

        DecimalFormat format = new DecimalFormat("##");
        Commands.runAs(target, "setairdrop random @s "
                + format.format(height) + " "
                + format.format(length) + " "
                + format.format(drift[0]) + " "
                + format.format(drift[1]) + " \""
                + LARGE_AIRDROP_BLOCK + "\" "
                + lootTable
                + suffix);
    }

    // ------------------------------------------------------------------ /airdrop

    /** {@code /airdrop <player> <blockid> <pin>}：按当前天数等级给指定玩家投放一次。 */
    public static void reAirdrop(LevelAccessor world, CommandContext<CommandSourceStack> arguments) {
        Entity player = CommandArgs.entity(arguments, "player");
        if (player == null) {
            Chat.broadcast(world, net.minecraft.network.chat.Component.literal(RANDOM_FAILED_MESSAGE));
            return;
        }
        String blockId = BuiltInRegistries.BLOCK
                .getKey(BlockStateArgument.getBlock(arguments, "blockid").getState().getBlock())
                .toString();
        String[] parts = blockId.split(":");
        if (parts.length != 2) {
            return;
        }
        double level = elapsedSinceDawn(world) / dayGapTicks();
        level = level >= 1.0 ? Math.floor(level) : 1.0;

        String lootTable = parts[0] + ":chests/" + parts[1].replace("airdrop", "") + "airdrop" + Math.round(level);
        double height = clampedHeight();
        double length = clampedStartPosition();
        boolean pin = BoolArgumentType.getBool(arguments, "pin");

        DecimalFormat format = new DecimalFormat("##");
        Commands.runAs(player, "/setairdrop free "
                + format.format(player.getX()) + " "
                + format.format(player.getZ()) + " "
                + Math.round(height) + " "
                + Math.round(length) + " \""
                + blockId + "\" \""
                + lootTable + "\" "
                + pin);
    }

    // ------------------------------------------------------------------ 工具

    private static boolean isOverworld(LevelAccessor world) {
        return (world instanceof Level level ? level.dimension() : Level.OVERWORLD) == Level.OVERWORLD;
    }

    /** 配置里的间隔天数换算成 tick（不足 1 天按 1 天算）。 */
    private static double dayGapTicks() {
        double configured = AirdropconfigConfiguration.GAP.get();
        double coefficient = configured < 1.0 ? 1.0 : Math.round(configured);
        return coefficient * TICKS_PER_DAY;
    }

    /** 距「黎明后 100 tick」的绝对 tick 数（与原实现一致）。 */
    private static double elapsedSinceDawn(LevelAccessor world) {
        return Math.abs(((Level) world).getDayTime() - (long) DAY_TIME_OFFSET);
    }

    private static boolean debug() {
        return Boolean.TRUE.equals(AirdropconfigConfiguration.DEBUGMODE.get());
    }

    private static String lootPrefix() {
        return ModList.get().isLoaded("zombiekit") ? "zombiekit" : "dyairdrop";
    }

    private static double clampedHeight() {
        double configured = AirdropconfigConfiguration.HEIGHT.get();
        if (configured <= MIN_HEIGHT) {
            return MIN_HEIGHT;
        }
        if (configured >= MAX_HEIGHT) {
            return MAX_HEIGHT;
        }
        return Math.round(configured);
    }

    private static double clampedStartPosition() {
        double configured = AirdropconfigConfiguration.STARTPOSITION.get();
        if (configured <= 0.0) {
            return 0.0;
        }
        return Math.min(configured, MAX_LENGTH);
    }

    /** 解析 {@code drift} 配置（{@code "min,max"}），缺省 50~100，上限 1024，必要时交换。 */
    private static double[] driftRange() {
        String configured = AirdropconfigConfiguration.DRIFT.get();
        String[] parts = configured == null ? new String[0] : configured.split(",");
        double min = DEFAULT_DRIFT_MIN;
        double max = DEFAULT_DRIFT_MAX;
        if (parts.length == 2) {
            min = Numbers.parseDouble(parts[0]);
            max = Numbers.parseDouble(parts[1]);
        }
        if (min <= 0.0) {
            min = 0.0;
        } else if (max >= MAX_DRIFT) {
            max = MAX_DRIFT;
        }
        if (min > max) {
            double swap = min;
            min = max;
            max = swap;
        }
        return new double[]{min, max};
    }

    /** 从配置的世界白名单里随机挑一个玩家。 */
    private static Player randomPlayer(LevelAccessor world, List<String> allowedWorldIds) {
        if (world.isClientSide()) {
            return null;
        }
        MinecraftServer server = world.getServer();
        List<ServerPlayer> candidates = new ArrayList<>();
        for (ServerPlayer player : server.getPlayerList().getPlayers()) {
            if (allowedWorldIds.contains(player.level().dimension().location().toString())) {
                candidates.add(player);
            }
        }
        return candidates.isEmpty() ? null : candidates.get(world.getRandom().nextInt(candidates.size()));
    }
}
