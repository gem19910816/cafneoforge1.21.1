package net.gem19910816.dyairdrop.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.gem19910816.dyairdrop.configuration.AirdropconfigConfiguration;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;

/**
 * 「空投吸引敌人」的刷怪逻辑：在空投箱周围的候选格子里随机挑位置，把 {@code enemylist} 里的敌人逐个放出来。
 *
 * <p>取代原 {@code SelectsummonpositionProcedure}（77 行）：
 * <ul>
 *   <li>原实现先把 21×11×21 = 4851 个候选点构造成 {@code double[]} 列表再洗牌（每次空投几千次小数组分配），
 *       现在直接用 {@link BlockPos} 列表 + Fisher–Yates 洗牌；</li>
 *   <li>候选范围（±10 / ±5 / ±10）、最多检查 4000 个点、每个敌人一格、只放在「上方可见天空且
 *       脚下是实心方块或水源」的位置 —— 全部与原实现一致；</li>
 *   <li>召唤命令统一走 {@link Commands}。</li>
 * </ul>
 */
public final class EnemySpawner {

    private static final int RANGE_X = 10;
    private static final int RANGE_Y = 5;
    private static final int RANGE_Z = 10;
    private static final int MAX_ATTEMPTS = 4000;

    private EnemySpawner() {
    }

    /** 在空投箱周围把配置里的敌人全部召唤出来。 */
    public static void summonAround(LevelAccessor world, BlockPos center) {
        String configured = AirdropconfigConfiguration.ENEMYLIST.get();
        if (configured == null || configured.isBlank()) {
            return;
        }
        String[] enemies = configured.split(",");
        List<BlockPos> candidates = candidateSpots(center);
        shuffle(candidates, RandomSource.create());

        int placed = 0;
        int attempts = 0;
        for (BlockPos spot : candidates) {
            if (placed >= enemies.length || attempts >= MAX_ATTEMPTS) {
                break;
            }
            attempts++;
            if (!isSpawnable(world, spot)) {
                continue;
            }
            String entityId = enemies[placed].trim();
            if (!entityId.isEmpty()) {
                Commands.run(world, spot.getX(), spot.getY() + 1, spot.getZ(), "summon " + entityId + " ~ ~ ~");
            }
            placed++;
        }
    }

    private static List<BlockPos> candidateSpots(BlockPos center) {
        List<BlockPos> spots = new ArrayList<>((RANGE_X * 2 + 1) * (RANGE_Y * 2 + 1) * (RANGE_Z * 2 + 1));
        for (int dx = -RANGE_X; dx <= RANGE_X; dx++) {
            for (int dy = -RANGE_Y; dy <= RANGE_Y; dy++) {
                for (int dz = -RANGE_Z; dz <= RANGE_Z; dz++) {
                    spots.add(center.offset(dx, dy, dz));
                }
            }
        }
        return spots;
    }

    /** 上方能看到天空，且脚下是实心方块或水源（与原实现的两个分支等价）。 */
    private static boolean isSpawnable(LevelAccessor world, BlockPos spot) {
        if (!world.canSeeSkyFromBelowWater(spot.above())) {
            return false;
        }
        BlockState state = world.getBlockState(spot);
        return state.canOcclude() || state.getFluidState().isSource();
    }

    private static void shuffle(List<BlockPos> list, RandomSource random) {
        for (int i = list.size() - 1; i > 0; i--) {
            Collections.swap(list, i, random.nextInt(i + 1));
        }
    }
}
