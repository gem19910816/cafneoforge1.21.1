package net.mcreator.dyairdrop.world.inventory;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.FriendlyByteBuf;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import net.mcreator.dyairdrop.init.DyairdropModMenus;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.items.SlotItemHandler;

public class AirdropGUIMenu extends AbstractContainerMenu implements Supplier<Map<Integer, Slot>> {
   public final Level world;
   public final Player entity;
   public int x;
   public int y;
   public int z;
   private ContainerLevelAccess access = ContainerLevelAccess.NULL;
   private IItemHandler internal;
   private final Map<Integer, Slot> customSlots = new HashMap<>();
   private boolean bound = false;
   private Supplier<Boolean> boundItemMatcher = null;
   private Entity boundEntity = null;
   private BlockEntity boundBlockEntity = null;

   public AirdropGUIMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
      super(DyairdropModMenus.AIRDROP_GUI.get(), id);
      this.entity = inv.player;
      this.world = inv.player.level();
      this.internal = new ItemStackHandler(27);
      BlockPos pos = null;
      if (extraData != null) {
         pos = extraData.readBlockPos();
         this.x = pos.getX();
         this.y = pos.getY();
         this.z = pos.getZ();
         this.access = ContainerLevelAccess.create(this.world, pos);
      }

      if (pos != null) {
         if (extraData.readableBytes() == 1) {
            byte hand = extraData.readByte();
            ItemStack itemstack = hand == 0 ? this.entity.getMainHandItem() : this.entity.getOffhandItem();
            this.boundItemMatcher = () -> itemstack == (hand == 0 ? this.entity.getMainHandItem() : this.entity.getOffhandItem());
            {
               IItemHandler capability = itemstack.getCapability(Capabilities.ItemHandler.ITEM, null);
               if (capability != null) {
                  this.internal = capability;
                  this.bound = true;
               }
               }
         } else if (extraData.readableBytes() > 1) {
            extraData.readByte();
            this.boundEntity = this.world.getEntity(extraData.readVarInt());
            if (this.boundEntity != null) {
               {
                  IItemHandler capability = this.boundEntity.getCapability(Capabilities.ItemHandler.ENTITY, null);
                  if (capability != null) {
                     this.internal = capability;
                     this.bound = true;
                  }
                  }
            }
         } else {
            this.boundBlockEntity = this.world.getBlockEntity(pos);
            if (this.boundBlockEntity != null) {
               {
                  IItemHandler capability = this.world.getCapability(Capabilities.ItemHandler.BLOCK, pos, null);
                  if (capability != null) {
                     this.internal = capability;
                     this.bound = true;
                  }
                  }
            }
         }
      }

      this.customSlots.put(0, this.addSlot(new SlotItemHandler(this.internal, 0, 7, 17) {
         private final int slot = 0;
      }));
      this.customSlots.put(1, this.addSlot(new SlotItemHandler(this.internal, 1, 25, 17) {
         private final int slot = 1;
      }));
      this.customSlots.put(2, this.addSlot(new SlotItemHandler(this.internal, 2, 43, 17) {
         private final int slot = 2;
      }));
      this.customSlots.put(3, this.addSlot(new SlotItemHandler(this.internal, 3, 61, 17) {
         private final int slot = 3;
      }));
      this.customSlots.put(4, this.addSlot(new SlotItemHandler(this.internal, 4, 79, 17) {
         private final int slot = 4;
      }));
      this.customSlots.put(5, this.addSlot(new SlotItemHandler(this.internal, 5, 97, 17) {
         private final int slot = 5;
      }));
      this.customSlots.put(6, this.addSlot(new SlotItemHandler(this.internal, 6, 115, 17) {
         private final int slot = 6;
      }));
      this.customSlots.put(7, this.addSlot(new SlotItemHandler(this.internal, 7, 133, 17) {
         private final int slot = 7;
      }));
      this.customSlots.put(8, this.addSlot(new SlotItemHandler(this.internal, 8, 151, 17) {
         private final int slot = 8;
      }));
      this.customSlots.put(9, this.addSlot(new SlotItemHandler(this.internal, 9, 7, 35) {
         private final int slot = 9;
      }));
      this.customSlots.put(10, this.addSlot(new SlotItemHandler(this.internal, 10, 25, 35) {
         private final int slot = 10;
      }));
      this.customSlots.put(11, this.addSlot(new SlotItemHandler(this.internal, 11, 43, 35) {
         private final int slot = 11;
      }));
      this.customSlots.put(12, this.addSlot(new SlotItemHandler(this.internal, 12, 61, 35) {
         private final int slot = 12;
      }));
      this.customSlots.put(13, this.addSlot(new SlotItemHandler(this.internal, 13, 79, 35) {
         private final int slot = 13;
      }));
      this.customSlots.put(14, this.addSlot(new SlotItemHandler(this.internal, 14, 97, 35) {
         private final int slot = 14;
      }));
      this.customSlots.put(15, this.addSlot(new SlotItemHandler(this.internal, 15, 115, 35) {
         private final int slot = 15;
      }));
      this.customSlots.put(16, this.addSlot(new SlotItemHandler(this.internal, 16, 133, 35) {
         private final int slot = 16;
      }));
      this.customSlots.put(17, this.addSlot(new SlotItemHandler(this.internal, 17, 151, 35) {
         private final int slot = 17;
      }));
      this.customSlots.put(18, this.addSlot(new SlotItemHandler(this.internal, 18, 7, 53) {
         private final int slot = 18;
      }));
      this.customSlots.put(19, this.addSlot(new SlotItemHandler(this.internal, 19, 25, 53) {
         private final int slot = 19;
      }));
      this.customSlots.put(20, this.addSlot(new SlotItemHandler(this.internal, 20, 43, 53) {
         private final int slot = 20;
      }));
      this.customSlots.put(21, this.addSlot(new SlotItemHandler(this.internal, 21, 61, 53) {
         private final int slot = 21;
      }));
      this.customSlots.put(22, this.addSlot(new SlotItemHandler(this.internal, 22, 79, 53) {
         private final int slot = 22;
      }));
      this.customSlots.put(23, this.addSlot(new SlotItemHandler(this.internal, 23, 97, 53) {
         private final int slot = 23;
      }));
      this.customSlots.put(24, this.addSlot(new SlotItemHandler(this.internal, 24, 115, 53) {
         private final int slot = 24;
      }));
      this.customSlots.put(25, this.addSlot(new SlotItemHandler(this.internal, 25, 133, 53) {
         private final int slot = 25;
      }));
      this.customSlots.put(26, this.addSlot(new SlotItemHandler(this.internal, 26, 151, 53) {
         private final int slot = 26;
      }));

      for (int si = 0; si < 3; si++) {
         for (int sj = 0; sj < 9; sj++) {
            this.addSlot(new Slot(inv, sj + (si + 1) * 9, 8 + sj * 18, 84 + si * 18));
         }
      }

      for (int si = 0; si < 9; si++) {
         this.addSlot(new Slot(inv, si, 8 + si * 18, 142));
      }
   }

   public boolean stillValid(Player player) {
      if (this.bound) {
         if (this.boundItemMatcher != null) {
            return this.boundItemMatcher.get();
         }

         if (this.boundBlockEntity != null) {
            return AbstractContainerMenu.stillValid(this.access, player, this.boundBlockEntity.getBlockState().getBlock());
         }

         if (this.boundEntity != null) {
            return this.boundEntity.isAlive();
         }
      }

      return true;
   }

   public ItemStack quickMoveStack(Player playerIn, int index) {
      ItemStack itemstack = ItemStack.EMPTY;
      Slot slot = (Slot)this.slots.get(index);
      if (slot != null && slot.hasItem()) {
         ItemStack itemstack1 = slot.getItem();
         itemstack = itemstack1.copy();
         if (index < 27) {
            if (!this.moveItemStackTo(itemstack1, 27, this.slots.size(), true)) {
               return ItemStack.EMPTY;
            }

            slot.onQuickCraft(itemstack1, itemstack);
         } else if (!this.moveItemStackTo(itemstack1, 0, 27, false)) {
            if (index < 54) {
               if (!this.moveItemStackTo(itemstack1, 54, this.slots.size(), true)) {
                  return ItemStack.EMPTY;
               }
            } else if (!this.moveItemStackTo(itemstack1, 27, 54, false)) {
               return ItemStack.EMPTY;
            }

            return ItemStack.EMPTY;
         }

         if (itemstack1.getCount() == 0) {
            slot.set(ItemStack.EMPTY);
         } else {
            slot.setChanged();
         }

         if (itemstack1.getCount() == itemstack.getCount()) {
            return ItemStack.EMPTY;
         }

         slot.onTake(playerIn, itemstack1);
      }

      return itemstack;
   }

   protected boolean moveItemStackTo(ItemStack p_38904_, int p_38905_, int p_38906_, boolean p_38907_) {
      boolean flag = false;
      int i = p_38905_;
      if (p_38907_) {
         i = p_38906_ - 1;
      }

      if (p_38904_.isStackable()) {
         while (!p_38904_.isEmpty() && (p_38907_ ? i >= p_38905_ : i < p_38906_)) {
            Slot slot = (Slot)this.slots.get(i);
            ItemStack itemstack = slot.getItem();
            if (slot.mayPlace(itemstack) && !itemstack.isEmpty() && ItemStack.isSameItemSameComponents(p_38904_, itemstack)) {
               int j = itemstack.getCount() + p_38904_.getCount();
               int maxSize = Math.min(slot.getMaxStackSize(), p_38904_.getMaxStackSize());
               if (j <= maxSize) {
                  p_38904_.setCount(0);
                  itemstack.setCount(j);
                  slot.set(itemstack);
                  flag = true;
               } else if (itemstack.getCount() < maxSize) {
                  p_38904_.shrink(maxSize - itemstack.getCount());
                  itemstack.setCount(maxSize);
                  slot.set(itemstack);
                  flag = true;
               }
            }

            if (p_38907_) {
               i--;
            } else {
               i++;
            }
         }
      }

      if (!p_38904_.isEmpty()) {
         if (p_38907_) {
            i = p_38906_ - 1;
         } else {
            i = p_38905_;
         }

         while (p_38907_ ? i >= p_38905_ : i < p_38906_) {
            Slot slot1 = (Slot)this.slots.get(i);
            ItemStack itemstack1 = slot1.getItem();
            if (itemstack1.isEmpty() && slot1.mayPlace(p_38904_)) {
               if (p_38904_.getCount() > slot1.getMaxStackSize()) {
                  slot1.setByPlayer(p_38904_.split(slot1.getMaxStackSize()));
               } else {
                  slot1.setByPlayer(p_38904_.split(p_38904_.getCount()));
               }

               slot1.setChanged();
               flag = true;
               break;
            }

            if (p_38907_) {
               i--;
            } else {
               i++;
            }
         }
      }

      return flag;
   }

   public void removed(Player playerIn) {
      super.removed(playerIn);
      if (!this.bound && playerIn instanceof ServerPlayer serverPlayer) {
         if (serverPlayer.isAlive() && !serverPlayer.hasDisconnected()) {
            for (int i = 0; i < this.internal.getSlots(); i++) {
               playerIn.getInventory().placeItemBackInInventory(this.internal.extractItem(i, this.internal.getStackInSlot(i).getCount(), false));
            }
         } else {
            for (int j = 0; j < this.internal.getSlots(); j++) {
               playerIn.drop(this.internal.extractItem(j, this.internal.getStackInSlot(j).getCount(), false), false);
            }
         }
      }
   }

   public Map<Integer, Slot> get() {
      return this.customSlots;
   }
}
