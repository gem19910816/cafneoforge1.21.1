package net.mcreator.survivalinstinct.item;

import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.items.ComponentItemHandler;

public abstract class PortableBagItem extends Item {
    protected PortableBagItem() { super(new Properties().stacksTo(1).component(DataComponents.CONTAINER, ItemContainerContents.EMPTY)); }
    protected abstract AbstractContainerMenu menu(int id, Inventory inventory, InteractionHand hand);
    @Override public boolean canFitInsideContainerItems() { return false; }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (player instanceof ServerPlayer serverPlayer) {
            serverPlayer.openMenu(new MenuProvider() {
                @Override public Component getDisplayName() { return player.getItemInHand(hand).getHoverName(); }
                @Override public AbstractContainerMenu createMenu(int id, Inventory inv, Player user) { return menu(id, inv, hand); }
            }, buffer -> { buffer.writeBlockPos(player.blockPosition()); buffer.writeByte(hand == InteractionHand.MAIN_HAND ? 0 : 1); });
        }
        return InteractionResultHolder.sidedSuccess(player.getItemInHand(hand), level.isClientSide());
    }

    public static ComponentItemHandler inventory(ItemStack stack) {
        return new ComponentItemHandler(stack, DataComponents.CONTAINER, 9) {
            @Override public int getSlotLimit(int slot) { return 1; }
            @Override public boolean isItemValid(int slot, ItemStack item) {
                return item.isEmpty() || (!(item.getItem() instanceof PortableBagItem) && item.canFitInsideContainerItems());
            }
        };
    }
}
