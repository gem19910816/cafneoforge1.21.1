package net.mcreator.gore.procedures;

import net.mcreator.gore.init.GoreEditionModItems;
import net.mcreator.gore.network.GoreEditionModVariables;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.items.ItemHandlerHelper;

public class ThirdhandZClickedProcedure {
   public static void execute(Entity entity) {
      if (entity != null
         && entity instanceof Player _playerHasItem
         && _playerHasItem.getInventory().contains(new ItemStack((ItemLike)GoreEditionModItems.THIRD_HAND.get()))
         && entity.isShiftKeyDown()
         && entity.getXRot() <= -80.0F
         && entity.getXRot() >= -90.0F
         && !GoreEditionModVariables.getPlayerVariables(entity).third_hand_boolean_cooldown) {
         if (entity instanceof Player _player) {
            _player.getCooldowns().addCooldown((Item)GoreEditionModItems.THIRD_HAND.get(), 10);
         }

         boolean _setval = true;
         GoreEditionModVariables.PlayerVariables capability = GoreEditionModVariables.getPlayerVariables(entity);
         capability.third_hand_boolean_cooldown = _setval;
         capability.syncPlayerVariables(entity);
         if (GoreEditionModVariables.getPlayerVariables(entity).third_hand_itemstack_store.getItem() == Blocks.AIR.asItem()
            && (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() != Blocks.AIR.asItem()) {
            ItemStack _setvalx = entity instanceof LivingEntity _livEntx ? _livEntx.getMainHandItem() : ItemStack.EMPTY;
            GoreEditionModVariables.PlayerVariables capabilityx = GoreEditionModVariables.getPlayerVariables(entity);
            capabilityx.third_hand_itemstack_store = _setvalx;
            capabilityx.syncPlayerVariables(entity);
            if (entity instanceof Player _player) {
               ItemStack _stktoremove;
               ItemStack var20 = _stktoremove = entity instanceof LivingEntity _livEntxx ? _livEntxx.getMainHandItem() : ItemStack.EMPTY;
               _player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1, _player.inventoryMenu.getCraftSlots());
            }

            if (entity instanceof Player _player && !_player.level().isClientSide()) {
               _player.displayClientMessage(Component.literal("1"), false);
            }
         }

         if (GoreEditionModVariables.getPlayerVariables(entity).third_hand_itemstack_store.getItem() != Blocks.AIR.asItem()
            && (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == Blocks.AIR.asItem()) {
            if (entity instanceof Player _player) {
               ItemStack _setstack = GoreEditionModVariables.getPlayerVariables(entity).third_hand_itemstack_store.copy();
               _setstack.setCount(1);
               ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
            }

            if (entity instanceof Player _player && !_player.level().isClientSide()) {
               _player.displayClientMessage(Component.literal("2"), false);
            }

            ItemStack _setvalxx = new ItemStack(Blocks.AIR);
            GoreEditionModVariables.PlayerVariables capabilityxx = GoreEditionModVariables.getPlayerVariables(entity);
            capabilityxx.third_hand_itemstack_store = _setvalxx;
            capabilityxx.syncPlayerVariables(entity);
         }
      }
   }
}
