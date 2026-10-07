package net.mcreator.dyairdrop.block.entity;

import java.util.stream.IntStream;

import javax.annotation.Nullable;

import net.mcreator.dyairdrop.init.DyairdropModBlockEntities;
import net.mcreator.dyairdrop.world.inventory.AirdropGUIMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
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
import io.netty.buffer.Unpooled;
import net.minecraft.network.FriendlyByteBuf;

public class AirdropsmallBlockEntity extends RandomizableContainerBlockEntity implements WorldlyContainer {
   private NonNullList<ItemStack> stacks = NonNullList.withSize(27, ItemStack.EMPTY);

   public AirdropsmallBlockEntity(BlockPos position, BlockState state) {
      super((BlockEntityType)DyairdropModBlockEntities.AIRDROPSMALL.get(), position, state);
   }

   public void loadAdditional(CompoundTag compound, HolderLookup.Provider provider) {
      super.loadAdditional(compound, provider);
      if (!this.tryLoadLootTable(compound)) {
         this.stacks = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
      }

      ContainerHelper.loadAllItems(compound, this.stacks, provider);
   }

   public void saveAdditional(CompoundTag compound, HolderLookup.Provider provider) {
      super.saveAdditional(compound, provider);
      if (!this.trySaveLootTable(compound)) {
         ContainerHelper.saveAllItems(compound, this.stacks, provider);
      }
   }

   public ClientboundBlockEntityDataPacket getUpdatePacket() {
      return ClientboundBlockEntityDataPacket.create(this);
   }

   public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
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
      return Component.literal("airdropsmall");
   }

   public int getMaxStackSize() {
      return 64;
   }

   public AbstractContainerMenu createMenu(int id, Inventory inventory) {
      return new AirdropGUIMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(this.worldPosition));
   }

   public Component getDisplayName() {
      return Component.literal("Airdropsmall");
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
      if (index == 0) {
         return false;
      } else if (index == 1) {
         return false;
      } else if (index == 2) {
         return false;
      } else if (index == 3) {
         return false;
      } else if (index == 4) {
         return false;
      } else if (index == 5) {
         return false;
      } else if (index == 6) {
         return false;
      } else if (index == 7) {
         return false;
      } else if (index == 8) {
         return false;
      } else if (index == 9) {
         return false;
      } else if (index == 10) {
         return false;
      } else if (index == 11) {
         return false;
      } else if (index == 12) {
         return false;
      } else if (index == 13) {
         return false;
      } else if (index == 14) {
         return false;
      } else if (index == 15) {
         return false;
      } else if (index == 16) {
         return false;
      } else if (index == 17) {
         return false;
      } else if (index == 18) {
         return false;
      } else if (index == 19) {
         return false;
      } else if (index == 20) {
         return false;
      } else if (index == 21) {
         return false;
      } else if (index == 22) {
         return false;
      } else if (index == 23) {
         return false;
      } else if (index == 24) {
         return false;
      } else {
         return index == 25 ? false : index != 26;
      }
   }
}
