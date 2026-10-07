package cn.blockforge.generated.cropexpansion;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.BlockEvent;

/**
 * 药草种子自然掉落：
 * 破坏草（minecraft:short_grass）或高草丛（minecraft:tall_grass）时，
 * 有 5% 概率掉落 1 个药草种子（crop_expansion:herb_seeds）。
 * 只在服务端生效（避免客户端重复生成掉落物）。
 *
 * <p>移植注意：1.20.1 的 {@code Blocks.GRASS} 在 1.21 已改名为
 * {@code Blocks.SHORT_GRASS}（注册名也从 grass 变成 short_grass）。
 *
 * <p>这里刻意<b>不写</b> {@code bus = EventBusSubscriber.Bus.GAME}：
 * NeoForge 21.1 起已标记为待删除（编译会报 [removal] 弃用警告）。
 * {@code BlockEvent.BreakEvent} 不是 {@code IModBusEvent}，会被自动挂到游戏事件总线。
 */
@EventBusSubscriber(modid = CropExpansion.MOD_ID)
public final class HerbSeedDropHandler {

    /** 掉落概率：5% */
    private static final double DROP_CHANCE = 0.05;

    private HerbSeedDropHandler() {
    }

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        // BreakEvent.getLevel() 返回的是 LevelAccessor，这里要按 Level 用（原版行为一致）
        Level level = (Level) event.getLevel();
        if (level.isClientSide) {
            return;
        }

        BlockState state = event.getState();
        boolean isGrass = state.is(Blocks.SHORT_GRASS);
        boolean isTallGrass = state.is(Blocks.TALL_GRASS);
        if (!isGrass && !isTallGrass) {
            return;
        }

        // 5% 概率掉落 1 个药草种子（不是每次破坏都掉落）
        if (level.random.nextDouble() < DROP_CHANCE) {
            BlockPos pos = event.getPos();
            Block.popResource(level, pos, new ItemStack(ModContent.HERB_SEEDS.get(), 1));
        }
    }
}
