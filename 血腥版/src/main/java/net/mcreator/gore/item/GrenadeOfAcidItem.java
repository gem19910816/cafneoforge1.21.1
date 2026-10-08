package net.mcreator.gore.item;

import java.util.List;
import net.mcreator.gore.entity.InsuredAcidgrenadeEntity;
import net.mcreator.gore.procedures.GrenadeOfAcidCanUseRangedItemProcedure;
import net.mcreator.gore.procedures.GrenadeOfAcidItemInInventoryTickProcedure;
import net.mcreator.gore.procedures.GrenadeOfAcidPlayerFinishesUsingItemProcedure;
import net.mcreator.gore.utils.ItemTagHelper;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow.Pickup;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.Item.TooltipContext;
import net.minecraft.world.level.Level;

public class GrenadeOfAcidItem extends Item {
   public GrenadeOfAcidItem() {
      super(new Properties().stacksTo(1).rarity(Rarity.EPIC));
   }

   public UseAnim getUseAnimation(ItemStack itemstack) {
      return UseAnim.BOW;
   }

   public int getUseDuration(ItemStack itemstack) {
      return 40;
   }

   public void appendHoverText(ItemStack itemstack, TooltipContext level, List<Component> list, TooltipFlag flag) {
      super.appendHoverText(itemstack, level, list, flag);
      list.add(Component.literal("Discarted"));
   }

   public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
      InteractionResultHolder<ItemStack> ar = InteractionResultHolder.fail(entity.getItemInHand(hand));
      if (GrenadeOfAcidCanUseRangedItemProcedure.execute((ItemStack)ar.getObject())
         && (entity.getAbilities().instabuild || this.findAmmo(entity) != ItemStack.EMPTY)) {
         ar = InteractionResultHolder.success(entity.getItemInHand(hand));
         entity.startUsingItem(hand);
      }

      return ar;
   }

   public ItemStack finishUsingItem(ItemStack itemstack, Level world, LivingEntity entity) {
      ItemStack retval = super.finishUsingItem(itemstack, world, entity);
      double x = entity.getX();
      double y = entity.getY();
      double z = entity.getZ();
      GrenadeOfAcidPlayerFinishesUsingItemProcedure.execute(world, entity, itemstack);
      return retval;
   }

   public void inventoryTick(ItemStack itemstack, Level world, Entity entity, int slot, boolean selected) {
      super.inventoryTick(itemstack, world, entity, slot, selected);
      GrenadeOfAcidItemInInventoryTickProcedure.execute(world, entity.getX(), entity.getY(), entity.getZ(), entity, itemstack);
   }

   public void releaseUsing(ItemStack itemstack, Level world, LivingEntity entity, int time) {
      if (!world.isClientSide() && entity instanceof ServerPlayer player) {
         float pullingPower = BowItem.getPowerForTime(this.getUseDuration(itemstack) - time);
         if ((double)pullingPower < 0.1) {
            return;
         }

         ItemStack stack = this.findAmmo(player);
         if (player.getAbilities().instabuild || stack != ItemStack.EMPTY) {
            InsuredAcidgrenadeEntity projectile = InsuredAcidgrenadeEntity.shoot(world, entity, world.getRandom(), pullingPower);
            if (player.getAbilities().instabuild) {
               projectile.pickup = Pickup.CREATIVE_ONLY;
            } else if (stack.isDamageableItem()) {
               if (ItemTagHelper.damageItem(stack, player)) {
                  stack.shrink(1);
                  stack.setDamageValue(0);
                  if (stack.isEmpty()) {
                     player.getInventory().removeItem(stack);
                  }
               }
            } else {
               stack.shrink(1);
               if (stack.isEmpty()) {
                  player.getInventory().removeItem(stack);
               }
            }

            GrenadeOfAcidPlayerFinishesUsingItemProcedure.execute(world, entity, itemstack);
         }
      }
   }

   private ItemStack findAmmo(Player player) {
      ItemStack stack = ProjectileWeaponItem.getHeldProjectile(player, e -> e.getItem() == InsuredAcidgrenadeEntity.PROJECTILE_ITEM.getItem());
      if (stack == ItemStack.EMPTY) {
         for (int i = 0; i < player.getInventory().items.size(); i++) {
            ItemStack teststack = (ItemStack)player.getInventory().items.get(i);
            if (teststack != null && teststack.getItem() == InsuredAcidgrenadeEntity.PROJECTILE_ITEM.getItem()) {
               stack = teststack;
               break;
            }
         }
      }

      return stack;
   }
}
