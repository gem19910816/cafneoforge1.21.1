package net.mcreator.dyairdrop.block.entity;

import io.netty.buffer.Unpooled;
import java.util.stream.IntStream;
import javax.annotation.Nullable;
import net.mcreator.dyairdrop.block.AirdropweaponBlock;
import net.mcreator.dyairdrop.init.DyairdropModBlockEntities;
import net.mcreator.dyairdrop.world.inventory.AirdropGUIMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
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
import net.minecraft.world.level.block.state.properties.IntegerProperty;
// UNPORTED-IMPORT import net.minecraftforge.common.capabilities.Capability;
// UNPORTED-IMPORT import net.minecraftforge.common.capabilities.ForgeCapabilities;
// UNPORTED-IMPORT import net.minecraftforge.common.util.LazyOptional;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.wrapper.SidedInvWrapper;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.animation.AnimatableManager.ControllerRegistrar;
import software.bernie.geckolib.animation.AnimationController.State;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;
import net.minecraft.core.HolderLookup;

public class AirdropweaponTileEntity extends RandomizableContainerBlockEntity implements GeoBlockEntity, WorldlyContainer {
   private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
   private NonNullList<ItemStack> stacks = NonNullList.withSize(27, ItemStack.EMPTY);
   public int blockstateNew = (Integer)this.getBlockState().getValue(AirdropweaponBlock.BLOCKSTATE);
   private int blockstateOld = (Integer)this.getBlockState().getValue(AirdropweaponBlock.BLOCKSTATE);

   public AirdropweaponTileEntity(BlockPos pos, BlockState state) {
      super((BlockEntityType)DyairdropModBlockEntities.AIRDROPWEAPON.get(), pos, state);
   }

   private PlayState predicate(AnimationState event) {
      this.blockstateNew = (Integer)this.getBlockState().getValue(AirdropweaponBlock.BLOCKSTATE);
      if (this.blockstateOld != this.blockstateNew) {
         event.getController().forceAnimationReset();
         this.blockstateOld = this.blockstateNew;
         return PlayState.STOP;
      } else {
         String animationprocedure = this.getBlockState().getValue(AirdropweaponBlock.ANIMATION) + "";
         return animationprocedure.equals("0") ? event.setAndContinue(RawAnimation.begin().thenLoop(animationprocedure)) : PlayState.STOP;
      }
   }

   private PlayState procedurePredicate(AnimationState event) {
      String animationprocedure = this.getBlockState().getValue(AirdropweaponBlock.ANIMATION) + "";
      if (!animationprocedure.equals("0") && event.getController().getAnimationState() == State.STOPPED) {
         event.getController().setAnimation(RawAnimation.begin().thenPlay(animationprocedure));
         if (event.getController().getAnimationState() == State.STOPPED) {
            if (this.getBlockState().getBlock().getStateDefinition().getProperty("animation") instanceof IntegerProperty _integerProp) {
               this.level.setBlock(this.getBlockPos(), (BlockState)this.getBlockState().setValue(_integerProp, 0), 3);
            }

            event.getController().forceAnimationReset();
         }
      } else if (animationprocedure.equals("0")) {
         return PlayState.STOP;
      }

      return PlayState.CONTINUE;
   }

   public void registerControllers(ControllerRegistrar data) {
      data.add(new AnimationController[]{new AnimationController(this, "controller", 0, this::predicate)});
      data.add(new AnimationController[]{new AnimationController(this, "procedurecontroller", 0, this::procedurePredicate)});
   }

   public AnimatableInstanceCache getAnimatableInstanceCache() {
      return this.cache;
   }

   protected void loadAdditional(CompoundTag compound, HolderLookup.Provider registries) {
      super.loadAdditional(compound, registries);
      if (!this.tryLoadLootTable(compound)) {
         this.stacks = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
      }

      ContainerHelper.loadAllItems(compound, this.stacks, registries);
   }

   protected void saveAdditional(CompoundTag compound, HolderLookup.Provider registries) {
      super.saveAdditional(compound, registries);
      if (!this.trySaveLootTable(compound)) {
         ContainerHelper.saveAllItems(compound, this.stacks, registries);
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
      return Component.literal("airdropweapon");
   }

   public int getMaxStackSize() {
      return 64;
   }

   public AbstractContainerMenu createMenu(int id, Inventory inventory) {
      return new AirdropGUIMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(this.worldPosition));
   }

   public Component getDisplayName() {
      return Component.literal("airdropweapon");
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
