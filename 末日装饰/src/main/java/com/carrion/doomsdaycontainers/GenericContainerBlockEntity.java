package com.carrion.doomsdaycontainers;

import java.util.stream.IntStream;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.util.Mth;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

/**
 * 通用容器方块实体：所有被"容器化"的末日装饰方块共用这一个实现。
 * 格子数按方块 id 从 container_targets.json 取，界面用原版箱子界面（1~6 行自动匹配）。
 */
public class GenericContainerBlockEntity extends RandomizableContainerBlockEntity implements WorldlyContainer {
    private NonNullList<ItemStack> items;

    public GenericContainerBlockEntity(BlockPos pos, BlockState state) {
        this(ContainerTargets.typeFor(state.getBlock()), pos, state);
    }

    public GenericContainerBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        if (type == null) {
            // 只会在"清单里有、但方块实体没注册成功"的异常情况下发生；给出可读的错误而不是 NPE
            throw new IllegalStateException("方块 " + state.getBlock() + " 没有对应的容器方块实体类型");
        }
        this.items = NonNullList.withSize(ContainerTargets.slotsFor(state.getBlock()), ItemStack.EMPTY);
    }

    // ------------------------------------------------------------------ 存档

    @Override
    public void loadAdditional(CompoundTag compound, HolderLookup.Provider registries) {
        super.loadAdditional(compound, registries);
        int size = ContainerTargets.slotsFor(this.getBlockState().getBlock());
        this.items = NonNullList.withSize(size, ItemStack.EMPTY);
        if (!this.tryLoadLootTable(compound)) {
            ContainerHelper.loadAllItems(compound, this.items, registries);
        }
    }

    @Override
    public void saveAdditional(CompoundTag compound, HolderLookup.Provider registries) {
        super.saveAdditional(compound, registries);
        if (!this.trySaveLootTable(compound)) {
            ContainerHelper.saveAllItems(compound, this.items, registries);
        }
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return this.saveWithFullMetadata(registries);
    }

    // ------------------------------------------------------------------ 容器

    @Override
    public int getContainerSize() {
        return this.items.size();
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack stack : this.items) {
            if (!stack.isEmpty()) return false;
        }
        return true;
    }

    @Override
    public int getMaxStackSize() {
        return 64;
    }

    @Override
    protected Component getDefaultName() {
        // 直接用方块自己的名字（"柜子"、"保险箱"、"金属桶"…）
        return Component.translatable(this.getBlockState().getBlock().getDescriptionId());
    }

    @Override
    protected AbstractContainerMenu createMenu(int id, Inventory inventory) {
        int rows = Mth.clamp((this.getContainerSize() + 8) / 9, 1, 6);
        MenuType<?> type = switch (rows) {
            case 1 -> MenuType.GENERIC_9x1;
            case 2 -> MenuType.GENERIC_9x2;
            case 3 -> MenuType.GENERIC_9x3;
            case 4 -> MenuType.GENERIC_9x4;
            case 5 -> MenuType.GENERIC_9x5;
            default -> MenuType.GENERIC_9x6;
        };
        return new ChestMenu(type, id, inventory, this, rows);
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.items;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> items) {
        this.items = items;
    }

    @Override
    public boolean canPlaceItem(int index, ItemStack stack) {
        return true;
    }

    // ------------------------------------------------------------------ 侧面访问

    @Override
    public int[] getSlotsForFace(Direction side) {
        return IntStream.range(0, this.getContainerSize()).toArray();
    }

    @Override
    public boolean canPlaceItemThroughFace(int index, ItemStack stack, @Nullable Direction direction) {
        return this.canPlaceItem(index, stack);
    }

    @Override
    public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
        return true;
    }

    /** 原模组是 1.20.1 反编译移植，这里提供一个兼容取方块实体的入口，方便调试命令使用。 */
    public static BlockEntity create(BlockPos pos, BlockState state) {
        return new GenericContainerBlockEntity(pos, state);
    }

    /** 供自检命令使用：外部包无法直接调用 protected 的 createMenu。 */
    public AbstractContainerMenu buildMenu(int id, Inventory inventory) {
        return this.createMenu(id, inventory);
    }

    // ------------------------------------------------------------------ 开关音效

    /** 当前有几个人开着这个容器（只在服务端有意义，不存档）。 */
    private int openCount;

    @Override
    public void startOpen(Player player) {
        if (this.remove || player.isSpectator()) return;
        // 只有 0 -> 1 的时候响一次，和原版箱子/木桶一致
        if (this.openCount++ == 0) {
            ContainerSounds.playOpen(this);
        }
    }

    @Override
    public void stopOpen(Player player) {
        if (this.remove || player.isSpectator()) return;
        if (this.openCount > 0 && --this.openCount == 0) {
            ContainerSounds.playClose(this);
        }
    }
}
