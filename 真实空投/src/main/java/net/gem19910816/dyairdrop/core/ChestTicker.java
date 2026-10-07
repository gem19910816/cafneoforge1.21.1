package net.gem19910816.dyairdrop.core;

import net.gem19910816.dyairdrop.configuration.AirdropconfigConfiguration;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

/**
 * 已落地空投箱的每 tick 逻辑（9 种箱子方块共用）。
 *
 * <p>取代原 {@code AirdroplargeticksProcedure}（94 行，名字还叫 large 却是全部箱子共用）：
 * <ul>
 *   <li>NBT 只取一次句柄；「先 +1 再判断」的时序与原实现完全一致（原实现后面每个判断都重新读一次）；</li>
 *   <li>魔法数字全部提为常量，并把三件事拆成独立方法：
 *       {@link #updateLifetime}（计时 / 到期销毁）、{@link #emitSignalSmoke}（信号烟）、
 *       {@link #checkStolen}（无人看管则判为被抢走）；</li>
 *   <li>「吸引敌人」交给 {@link EnemySpawner}，广播走 {@link Chat}。</li>
 * </ul>
 * 行为逐条保留：timer 从 -1 起算归零、20000 tick 到期消失、timer==1 时置 {@code s=true}、
 * 每 80 tick 且 ≤3000 tick 时喷信号烟、{@code timer/1200 == enemyarrivetime}（分钟）时放敌人、
 * 超过 {@code airdropstolentime} 且每 20 tick 检查一次周围 {@code distance*2} 范围内无玩家则消失并广播。
 */
public final class ChestTicker {

    private static final String TAG_TIMER = "timer";
    private static final String TAG_MARKED = "s";

    private static final double MAX_LIFETIME_TICKS = 20000.0;
    private static final double SMOKE_UNTIL_TICKS = 3000.0;
    private static final double SMOKE_INTERVAL_TICKS = 80.0;
    private static final double SMOKE_HEIGHT_OFFSET = 17.0;
    /** 配置里的时间单位是分钟，内部 tick 换算用 1200（与原实现一致，注意不是 1200 tick/分钟而是 24000/20）。 */
    private static final double TICKS_PER_CONFIG_MINUTE = 1200.0;
    private static final double STOLEN_CHECK_INTERVAL_TICKS = 20.0;
    private static final String SMOKE_COMMAND_PREFIX = "particle dyairdrop:signalsmoke ";

    private ChestTicker() {
    }

    /** 每 tick 调用；{@code x/y/z} 为箱子坐标。 */
    public static void tick(LevelAccessor world, double x, double y, double z) {
        BlockPos pos = BlockPos.containing(x, y, z);
        CompoundTag data = Nbt.tag(world, pos);
        if (data == null) {
            return;
        }
        updateLifetime(world, pos, data);

        double timer = Nbt.getDouble(world, pos, TAG_TIMER);
        if (timer == 1.0 && !world.isClientSide()) {
            Nbt.setBoolean(world, pos, TAG_MARKED, true);
        }
        emitSignalSmoke(world, x, y, z, timer, data);
        summonEnemiesIfDue(world, pos, timer);
        checkStolen(world, x, y, z, pos, timer, data);
    }

    /** 计时：负数归零、到期清空方块、其余情况每 tick +1。 */
    private static void updateLifetime(LevelAccessor world, BlockPos pos, CompoundTag data) {
        double timer = data.getDouble(TAG_TIMER);
        if (timer < 0.0) {
            if (!world.isClientSide()) {
                Nbt.setDouble(world, pos, TAG_TIMER, 0.0);
            }
        } else if (timer >= MAX_LIFETIME_TICKS) {
            world.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
        } else if (!world.isClientSide()) {
            Nbt.setDouble(world, pos, TAG_TIMER, timer + 1.0);
        }
    }

    /** 落地后每 80 tick 喷一次信号烟（到 3000 tick 为止），用于远处定位。 */
    private static void emitSignalSmoke(LevelAccessor world, double x, double y, double z, double timer, CompoundTag data) {
        if (timer % SMOKE_INTERVAL_TICKS != 0.0 || timer > SMOKE_UNTIL_TICKS || !data.getBoolean(TAG_MARKED)) {
            return;
        }
        Commands.run(world, x, y, z, SMOKE_COMMAND_PREFIX + x + " " + (y + SMOKE_HEIGHT_OFFSET) + " " + z + " 2 6 2 0 2000 force");
    }

    /** 到点放敌人：配置单位是分钟，{@code timer/1200} 与之相等的那一 tick 触发。 */
    private static void summonEnemiesIfDue(LevelAccessor world, BlockPos pos, double timer) {
        boolean enabled = Boolean.TRUE.equals(AirdropconfigConfiguration.ENABLEENEMIES.get());
        if (!enabled) {
            return;
        }
        double arriveMinutes = AirdropconfigConfiguration.ENEMYARRIVETIME.get();
        if (timer / TICKS_PER_CONFIG_MINUTE == arriveMinutes) {
            EnemySpawner.summonAround(world, pos);
        }
    }

    /** 无人看管 → 空投被抢走：清空方块并广播（每 20 tick 检查一次，范围 distance*2）。 */
    private static void checkStolen(LevelAccessor world, double x, double y, double z, BlockPos pos, double timer, CompoundTag data) {
        double stolenMinutes = AirdropconfigConfiguration.AIRDROPSTOLENTIME.get();
        if (timer < Math.round(stolenMinutes * TICKS_PER_CONFIG_MINUTE)
                || !data.getBoolean(TAG_MARKED)
                || timer % STOLEN_CHECK_INTERVAL_TICKS != 0.0) {
            return;
        }
        if (!hasPlayerNearby(world, x, y, z)) {
            world.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
            Chat.broadcast(world, "message.airdropstolen");
        }
    }

    private static boolean hasPlayerNearby(LevelAccessor world, double x, double y, double z) {
        double radius = Math.round(AirdropconfigConfiguration.DISTANCE.get() * 2.0);
        AABB area = AABB.ofSize(new Vec3(x, y, z), radius, radius, radius);
        return !world.getEntitiesOfClass(Player.class, area, player -> true).isEmpty();
    }
}
