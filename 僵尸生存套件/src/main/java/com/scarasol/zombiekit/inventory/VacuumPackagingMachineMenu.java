package com.scarasol.zombiekit.inventory;

import com.scarasol.zombiekit.ZombieKitMod;
import com.scarasol.zombiekit.init.ZombieKitItems;
import com.scarasol.zombiekit.init.ZombieKitMenus;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.items.SlotItemHandler;
import net.neoforged.neoforge.items.wrapper.InvWrapper;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class VacuumPackagingMachineMenu extends AbstractContainerMenu implements Supplier<Map<Integer, Slot>> {

    public final static HashMap<String, Object> guiState = new HashMap<>();
    public final Level level;
    public final Player player;
    public int x, y, z;
    private final ContainerLevelAccess access;
    private final ContainerData data;
    private IItemHandler internal;
    private final Map<Integer, Slot> customSlots = new HashMap<>();
    private final BlockEntity boundBlockEntity;


    public VacuumPackagingMachineMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
        this(id, inv, extraData, new SimpleContainerData(2));
    }

    public VacuumPackagingMachineMenu(int id, Inventory inv, FriendlyByteBuf extraData, ContainerData dataAccess) {
        super(ZombieKitMenus.VACUUM_PACKAGING_MACHINE_GUI.get(), id);
        this.player = inv.player;
        this.level = inv.player.level();
        this.internal = new ItemStackHandler(4);
        this.data = dataAccess;
        BlockPos pos = extraData.readBlockPos();
        this.x = pos.getX();
        this.y = pos.getY();
        this.z = pos.getZ();
        access = ContainerLevelAccess.create(level, pos);
        boundBlockEntity = this.level.getBlockEntity(pos);
        if (boundBlockEntity instanceof Container container)
            this.internal = new InvWrapper(container);
        this.customSlots.put(0, this.addSlot(new SlotItemHandler(internal, 0, 31, 21) {

            @Override
            public void setChanged() {
                super.setChanged();
                if (boundBlockEntity != null)
                    boundBlockEntity.setChanged();
            }

            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.has(DataComponents.FOOD);
            }
        }));

        this.customSlots.put(1, this.addSlot(new SlotItemHandler(internal, 1, 81, 40) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }
        }));

        this.customSlots.put(2, this.addSlot(new SlotItemHandler(internal, 2, 31, 61) {
            @Override
            public void setChanged() {
                super.setChanged();
                if (boundBlockEntity != null)
                    boundBlockEntity.setChanged();
            }

            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.getItem() == ZombieKitItems.PLASTIC_BAG.get();
            }
        }));

        this.customSlots.put(3, this.addSlot(new SlotItemHandler(internal, 3, 133, 61) {

            @Override
            public void setChanged() {
                super.setChanged();
                if (boundBlockEntity != null)
                    boundBlockEntity.setChanged();
            }

            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.getItem() == ZombieKitItems.BATTERY.get();
            }
        }));

        for (int si = 0; si < 3; ++si)
            for (int sj = 0; sj < 9; ++sj)
                this.addSlot(new Slot(inv, sj + (si + 1) * 9, 2 + 8 + sj * 18, 9 + 84 + si * 18));
        for (int si = 0; si < 9; ++si)
            this.addSlot(new Slot(inv, si, 2 + 8 + si * 18, 9 + 142));
        this.addDataSlots(dataAccess);
    }

    public double getPackagingProgress() {
        double i = this.data.get(0);
        double j = this.data.get(1);
        return i != 0 && j != 0 ? i / j : 0;
    }

    @Override
    public boolean stillValid(Player player) {
        if (this.boundBlockEntity != null)
            return AbstractContainerMenu.stillValid(this.access, player, this.boundBlockEntity.getBlockState().getBlock());
        return player.isAlive();
    }

    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            // 槽位布局：0-3 机器容器，4-30 玩家背包，31-39 快捷栏
            if (index < 4) {
                if (!this.moveItemStackTo(itemstack1, 4, this.slots.size(), true))
                    return ItemStack.EMPTY;
            } else if (!this.moveItemStackTo(itemstack1, 0, 4, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.getCount() == 0)
                slot.set(ItemStack.EMPTY);
            else
                slot.setChanged();
            if (itemstack1.getCount() == itemstack.getCount())
                return ItemStack.EMPTY;
            slot.onTake(playerIn, itemstack1);
        }
        return itemstack;
    }

    @Override
    public Map<Integer, Slot> get() {
        return customSlots;
    }

}
