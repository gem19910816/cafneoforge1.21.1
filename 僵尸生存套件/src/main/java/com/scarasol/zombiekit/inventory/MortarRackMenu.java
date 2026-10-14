package com.scarasol.zombiekit.inventory;

import com.scarasol.zombiekit.init.ZombieKitItems;
import com.scarasol.zombiekit.init.ZombieKitMenus;
import com.scarasol.zombiekit.item.projectile.MortarShell;
import net.minecraft.core.BlockPos;
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

public class MortarRackMenu extends AbstractContainerMenu implements Supplier<Map<Integer, Slot>> {

    public final static HashMap<String, Object> guiState = new HashMap<>();
    public final Level level;
    public final Player player;
    public int x, y, z;
    private final ContainerLevelAccess access;
    private IItemHandler internal;
    private final Map<Integer, Slot> customSlots = new HashMap<>();
    private final BlockEntity boundBlockEntity;

    public MortarRackMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
        this(id, inv, extraData, new SimpleContainerData(2));
    }

    public MortarRackMenu(int id, Inventory inv, FriendlyByteBuf extraData, ContainerData dataAccess) {
        super(ZombieKitMenus.MORTAR_RACK_MENU.get(), id);
        this.player = inv.player;
        this.level = inv.player.level();
        this.internal = new ItemStackHandler(10);
        BlockPos pos = extraData.readBlockPos();
        this.x = pos.getX();
        this.y = pos.getY();
        this.z = pos.getZ();
        access = ContainerLevelAccess.create(level, pos);
        boundBlockEntity = this.level.getBlockEntity(pos);
        if (boundBlockEntity instanceof Container container)
            this.internal = new InvWrapper(container);
        for (int si = 0; si < 3; ++si)
            for (int sj = 0; sj < 3; ++sj)
                this.customSlots.put(sj + si * 3, this.addSlot(new SlotItemHandler(internal, sj + si * 3, 62 + sj * 18, 17 + si * 18) {

                    @Override
                    public void setChanged() {
                        super.setChanged();
                        if (boundBlockEntity != null)
                            boundBlockEntity.setChanged();
                    }

                    @Override
                    public boolean mayPlace(ItemStack stack) {
                        return stack.getItem() instanceof MortarShell;
                    }
                }));

        this.customSlots.put(9, this.addSlot(new SlotItemHandler(internal, 9, 26, 35) {

            @Override
            public void setChanged() {
                super.setChanged();
                if (boundBlockEntity != null)
                    boundBlockEntity.setChanged();
            }

            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.is(ZombieKitItems.SHOOTING_PARAMETER.get());
            }
        }));

        for (int si = 0; si < 3; ++si)
            for (int sj = 0; sj < 9; ++sj)
                this.addSlot(new Slot(inv, sj + (si + 1) * 9, 8 + sj * 18, 84 + si * 18));
        for (int si = 0; si < 9; ++si)
            this.addSlot(new Slot(inv, si, 8 + si * 18, 142));

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
            // 槽位布局：0-9 迫击炮架容器（0-8 炮弹，9 参数表），10-36 玩家背包，37-45 快捷栏
            if (index < 10) {
                if (!this.moveItemStackTo(itemstack1, 10, this.slots.size(), true))
                    return ItemStack.EMPTY;
            } else if (!this.moveItemStackTo(itemstack1, 0, 10, false)) {
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
