package net.mcreator.survivalinstinct.world.inventory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import net.mcreator.survivalinstinct.item.PortableBagItem;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemHandlerCopySlot;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.items.SlotItemHandler;
import net.neoforged.neoforge.items.wrapper.InvWrapper;

public abstract class PortContainerMenu extends AbstractContainerMenu implements Supplier<Map<Integer, Slot>> {
    public final Level world;
    public final Player entity;
    public final int x, y, z;
    private final int storageSlots;
    private final BlockEntity blockEntity;
    private final InteractionHand bagHand;
    private final ItemStack bag;
    private final int lockedInventorySlot;
    private final Map<Integer, Slot> customSlots = new HashMap<>();

    protected PortContainerMenu(MenuType<?> type, int id, Inventory inv, BlockPos pos, InteractionHand hand, int count, int sx, int sy) {
        super(type, id);
        entity = inv.player;
        world = entity.level();
        x = pos.getX(); y = pos.getY(); z = pos.getZ();
        storageSlots = count;
        bagHand = hand;
        bag = hand == null ? ItemStack.EMPTY : entity.getItemInHand(hand);
        lockedInventorySlot = hand == InteractionHand.MAIN_HAND ? inv.selected : -1;
        blockEntity = hand == null ? world.getBlockEntity(pos) : null;
        IItemHandler handler;
        if (hand != null && bag.getItem() instanceof PortableBagItem) handler = PortableBagItem.inventory(bag);
        else if (blockEntity instanceof net.minecraft.world.Container container && container.getContainerSize() == count) handler = new InvWrapper(container);
        else handler = new ItemStackHandler(count);
        for (int slot = 0; slot < count; slot++) {
            int px = sx + slot % 9 * 18, py = sy + slot / 9 * 18;
            Slot entry = hand == null ? new SlotItemHandler(handler, slot, px, py) : new ItemHandlerCopySlot(handler, slot, px, py);
            customSlots.put(slot, addSlot(entry));
        }
        for (int row = 0; row < 3; row++) for (int col = 0; col < 9; col++) addPlayerSlot(inv, col + (row + 1) * 9, 8 + col * 18, 84 + row * 18);
        for (int col = 0; col < 9; col++) addPlayerSlot(inv, col, 8 + col * 18, 142);
    }

    private void addPlayerSlot(Inventory inv, int index, int x, int y) {
        addSlot(new Slot(inv, index, x, y) {
            @Override public boolean mayPickup(Player player) { return index != lockedInventorySlot && super.mayPickup(player); }
            @Override public boolean mayPlace(ItemStack stack) { return index != lockedInventorySlot && super.mayPlace(stack); }
        });
    }

    @Override
    public boolean stillValid(Player player) {
        if (bagHand != null) return bag.getItem() instanceof PortableBagItem && player.getItemInHand(bagHand) == bag;
        return blockEntity instanceof net.minecraft.world.Container container && !blockEntity.isRemoved() && container.stillValid(player);
    }

    @Override
    public void clicked(int slot, int button, ClickType type, Player player) {
        if (bagHand != null && type == ClickType.SWAP && ((bagHand == InteractionHand.OFF_HAND && button == 40) || button == lockedInventorySlot)) return;
        if (slot >= 0 && slot < slots.size() && slots.get(slot).container == player.getInventory() && slots.get(slot).getContainerSlot() == lockedInventorySlot) return;
        super.clicked(slot, button, type, player);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        if (index < 0 || index >= slots.size()) return ItemStack.EMPTY;
        Slot slot = slots.get(index);
        if (!slot.hasItem() || !slot.mayPickup(player)) return ItemStack.EMPTY;
        ItemStack existing = slot.getItem();
        ItemStack copy = existing.copy();
        boolean moved = index < storageSlots ? moveItemStackTo(existing, storageSlots, slots.size(), true) : moveItemStackTo(existing, 0, storageSlots, false);
        if (!moved) return ItemStack.EMPTY;
        slot.setByPlayer(existing.isEmpty() ? ItemStack.EMPTY : existing);
        slot.onTake(player, existing);
        return copy;
    }

    @Override public Map<Integer, Slot> get() { return customSlots; }
}
