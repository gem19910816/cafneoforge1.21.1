package com.carrion.doomsdaycontainers;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

/**
 * 混入类共用的实现。生成的每个 XxxBlockMixin 只做一件事：把调用转发到这里。
 *
 * 注意：Mixin 只会合并"混入类自己声明的方法"，所以逻辑统一放在这里，
 * 由生成出来的大量一行式混入类分别调用。
 */
public final class ContainerHooks {
    private ContainerHooks() {}

    /** EntityBlock#newBlockEntity */
    public static BlockEntity newBlockEntity(Block self, BlockPos pos, BlockState state) {
        if (!ContainerTargets.isTarget(self)) return null;
        return new GenericContainerBlockEntity(pos, state);
    }

    /** BlockBehaviour#getMenuProvider —— 旁观者等原版/NeoForge 路径也会走这里。 */
    public static MenuProvider getMenuProvider(Block self, BlockState state, Level level, BlockPos pos) {
        return menuProviderAt(level, pos);
    }

    /**
     * BlockBehaviour#useWithoutItem —— 右键开界面。
     * 潜行时不拦截，把右键让给方块本身的装饰交互。
     */
    public static InteractionResult useWithoutItem(Block self, BlockState state, Level level, BlockPos pos,
                                                   Player player, BlockHitResult hit) {
        if (player.isShiftKeyDown()) return InteractionResult.PASS;
        if (level.isClientSide) {
            // 客户端先给出"成功"的预测，避免手部回弹；真正的界面由服务端下发
            return InteractionResult.SUCCESS;
        }
        MenuProvider provider = menuProviderAt(level, pos);
        if (provider == null) {
            // 老存档里已经放下的装饰方块还没有方块实体 —— 第一次右键时补建
            BlockEntity created = newBlockEntity(self, pos, state);
            if (created == null) return InteractionResult.PASS;
            level.setBlockEntity(created);
            provider = menuProviderAt(level, pos);
            if (provider == null) return InteractionResult.PASS;
        }
        if (player instanceof ServerPlayer serverPlayer) {
            serverPlayer.openMenu(provider);
        }
        return InteractionResult.CONSUME;
    }

    /** BlockBehaviour#onRemove —— 破坏方块时把里面的东西吐出来。 */
    public static void onRemove(Block self, BlockState state, Level level, BlockPos pos, BlockState newState,
                                boolean movedByPiston) {
        if (state.getBlock() == newState.getBlock()) return;
        if (level.getBlockEntity(pos) instanceof GenericContainerBlockEntity container) {
            Containers.dropContents(level, pos, container);
            level.updateNeighbourForOutputSignal(pos, self);
        }
    }

    /** BlockBehaviour#hasAnalogOutputSignal */
    public static boolean hasAnalogOutputSignal(Block self, BlockState state) {
        return true;
    }

    /** BlockBehaviour#getAnalogOutputSignal —— 比较器按装满程度输出。 */
    public static int getAnalogOutputSignal(Block self, BlockState state, Level level, BlockPos pos) {
        BlockEntity be = level.getBlockEntity(pos);
        return be instanceof net.minecraft.world.Container container
                ? AbstractContainerMenu.getRedstoneSignalFromContainer(container)
                : 0;
    }

    private static MenuProvider menuProviderAt(Level level, BlockPos pos) {
        BlockEntity be = level.getBlockEntity(pos);
        return be instanceof MenuProvider provider ? provider : null;
    }
}
