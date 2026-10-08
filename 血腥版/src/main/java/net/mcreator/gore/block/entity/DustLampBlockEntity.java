package net.mcreator.gore.block.entity;

import io.netty.buffer.Unpooled;
import java.util.stream.IntStream;
import javax.annotation.Nullable;
import net.mcreator.gore.init.GoreEditionModBlockEntities;
import net.mcreator.gore.world.inventory.AshtrayCycleGuiMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class DustLampBlockEntity extends RandomizableContainerBlockEntity implements WorldlyContainer {
   private NonNullList<ItemStack> stacks = NonNullList.withSize(9, ItemStack.EMPTY);

   public DustLampBlockEntity(BlockPos position, BlockState state) {
      super((BlockEntityType)GoreEditionModBlockEntities.ASHTRAY_CYCLE.get(), position, state);
   }

   protected void loadAdditional(CompoundTag compound, Provider registries) {
      super.loadAdditional(compound, registries);
      if (!this.tryLoadLootTable(compound)) {
         this.stacks = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
      }

      ContainerHelper.loadAllItems(compound, this.stacks, registries);
   }

   protected void saveAdditional(CompoundTag compound, Provider registries) {
      super.saveAdditional(compound, registries);
      if (!this.trySaveLootTable(compound)) {
         ContainerHelper.saveAllItems(compound, this.stacks, registries);
      }
   }

   public ClientboundBlockEntityDataPacket getUpdatePacket() {
      return ClientboundBlockEntityDataPacket.create(this);
   }

   public CompoundTag getUpdateTag(Provider registries) {
      return this.saveWithFullMetadata(registries);
   }

   public int getContainerSize() {
      return this.stacks.size();
   }

   public boolean isEmpty() {
      for (ItemStack itemstack : this.stacks) {
         if (!itemstack.isEmpty()) {
            return false;
         }
      }

      return true;
   }

   public Component getDefaultName() {
      return Component.literal("ashtray_cycle");
   }

   public int getMaxStackSize() {
      return 64;
   }

   public AbstractContainerMenu createMenu(int id, Inventory inventory) {
      return new AshtrayCycleGuiMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(this.worldPosition));
   }

   public Component getDisplayName() {
      return Component.literal("Ashtray Cycle");
   }

   protected NonNullList<ItemStack> getItems() {
      return this.stacks;
   }

   protected void setItems(NonNullList<ItemStack> stacks) {
      this.stacks = stacks;
   }

   public boolean canPlaceItem(int index, ItemStack stack) {
      return true;
   }

   public int[] getSlotsForFace(Direction side) {
      return IntStream.range(0, this.getContainerSize()).toArray();
   }

   public boolean canPlaceItemThroughFace(int index, ItemStack stack, @Nullable Direction direction) {
      return this.canPlaceItem(index, stack);
   }

   public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
      return true;
   }
}
