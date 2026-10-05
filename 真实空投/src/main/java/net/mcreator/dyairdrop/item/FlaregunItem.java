package net.mcreator.dyairdrop.item;

import java.util.List;

import net.mcreator.dyairdrop.entity.FlareEntity;
import net.mcreator.dyairdrop.procedures.FlaregunlootsetProcedure;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow.Pickup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class FlaregunItem extends Item {
   public FlaregunItem() {
      super(new Properties().stacksTo(1).rarity(Rarity.COMMON));
   }

   public int getUseDuration(ItemStack itemstack, LivingEntity entity) {
      return 72000;
   }

   

   public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
      InteractionResultHolder<ItemStack> ar = InteractionResultHolder.success(entity.getItemInHand(hand));
      entity.startUsingItem(hand);
      return ar;
   }

   public void releaseUsing(ItemStack itemstack, Level world, LivingEntity entity, int time) {
      if (!world.isClientSide() && entity instanceof ServerPlayer player) {
         ItemStack stack = ProjectileWeaponItem.getHeldProjectile(entity, e -> e.getItem() == FlareEntity.PROJECTILE_ITEM.getItem());
         if (stack == ItemStack.EMPTY) {
            for (int i = 0; i < player.getInventory().items.size(); i++) {
               ItemStack teststack = (ItemStack)player.getInventory().items.get(i);
               if (teststack != null && teststack.getItem() == FlareEntity.PROJECTILE_ITEM.getItem()) {
                  stack = teststack;
                  break;
               }
            }
         }

         if (player.getAbilities().instabuild || stack != ItemStack.EMPTY) {
            FlareEntity projectile = FlareEntity.shoot(world, entity, world.getRandom());
            itemstack.hurtAndBreak(1, entity, LivingEntity.getSlotForHand(entity.getUsedItemHand()));
            if (player.getAbilities().instabuild) {
               projectile.pickup = Pickup.CREATIVE_ONLY;
            } else if (stack.isDamageableItem()) {
               stack.hurtAndBreak(1, player.serverLevel(), player, item -> {
               });
               if (stack.isEmpty()) {
                  player.getInventory().removeItem(stack);
               }
            } else {
               stack.shrink(1);
               if (stack.isEmpty()) {
                  player.getInventory().removeItem(stack);
               }
            }
         }
      }
   }
}
