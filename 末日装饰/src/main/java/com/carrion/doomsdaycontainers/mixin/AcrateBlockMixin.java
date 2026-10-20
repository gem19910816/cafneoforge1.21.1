package com.carrion.doomsdaycontainers.mixin;

import com.carrion.doomsdaycontainers.ContainerHooks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;

/**
 * 自动生成，请勿手改 —— 改 tools/gen_mixins.ps1 后重跑。
 * 目标：doomsday_decoration:acrate（板条箱），9 格
 *
 * 让这个原本只是装饰的方块变成真正的容器：方块实体 + 原版箱子界面 + 物品管道能力。
 */
@Mixin(targets = "net.mcreator.doomsdaydecoration.block.AcrateBlock", remap = false)
public abstract class AcrateBlockMixin {
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hit) {
        return ContainerHooks.useWithoutItem((Block) (Object) this, state, level, pos, player, hit);
    }
}
