package net.mcreator.survivalinstinct.block.entity;
import net.mcreator.survivalinstinct.init.SurvivalInstinctModBlockEntities;
import net.mcreator.survivalinstinct.world.inventory.RefrigeratorGUIMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
public final class RefrigeratorBlockEntity extends RandomizableContainerBlockEntity implements WorldlyContainer {
    private static final int[] SLOTS = java.util.stream.IntStream.range(0, 27).toArray();
    private NonNullList<ItemStack> stacks = NonNullList.withSize(27, ItemStack.EMPTY);
    public RefrigeratorBlockEntity(BlockPos pos, BlockState state) { super(SurvivalInstinctModBlockEntities.REFRIGERATOR.get(),pos,state); }
    @Override protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag,registries);
        stacks=NonNullList.withSize(27,ItemStack.EMPTY);
        if(!tryLoadLootTable(tag)) ContainerHelper.loadAllItems(tag,stacks,registries);
    }
    @Override protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag,registries);
        if(!trySaveLootTable(tag)) ContainerHelper.saveAllItems(tag,stacks,registries);
    }
    @Override public int getContainerSize() { return 27; }
    @Override protected Component getDefaultName() { return Component.translatable("block.survival_instinct.refrigerator"); }
    @Override protected NonNullList<ItemStack> getItems() { return stacks; }
    @Override protected void setItems(NonNullList<ItemStack> items) { stacks=items; }
    @Override protected AbstractContainerMenu createMenu(int id, Inventory inv) { return new RefrigeratorGUIMenu(id,inv,worldPosition,null); }
    @Override public int[] getSlotsForFace(Direction side) { return SLOTS; }
    @Override public boolean canPlaceItemThroughFace(int slot,ItemStack item,Direction side) { return true; }
    @Override public boolean canTakeItemThroughFace(int slot,ItemStack item,Direction side) { return false; }
}
