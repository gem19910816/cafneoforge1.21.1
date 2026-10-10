package net.mcreator.survivalinstinct.item;
import net.mcreator.survivalinstinct.world.inventory.EmptyBagGUIMenu;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
public final class EmptyBagItem extends PortableBagItem {
    @Override protected AbstractContainerMenu menu(int id, Inventory inv, InteractionHand hand) { return new EmptyBagGUIMenu(id,inv,inv.player.blockPosition(),hand); }
}
