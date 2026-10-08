package net.mcreator.gore.item;

import net.mcreator.gore.entity.TripofobicAcidEntity;
import net.mcreator.gore.procedures.TextureOfAshesBlackHoleRangedItemShootsProjectileProcedure;
import net.mcreator.gore.procedures.TpacRightclickedOnBlockProcedure;
import net.mcreator.gore.utils.ItemTagHelper;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow.Pickup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class TpacItem extends Item {
   public TpacItem() {
      super(new Properties().stacksTo(16).rarity(Rarity.EPIC));
   }

   public int getUseDuration(ItemStack itemstack) {
      return 1;
   }

   public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
      InteractionResultHolder<ItemStack> ar = InteractionResultHolder.fail(entity.getItemInHand(hand));
      if (entity.getAbilities().instabuild || this.findAmmo(entity) != ItemStack.EMPTY) {
         ar = InteractionResultHolder.success(entity.getItemInHand(hand));
         entity.startUsingItem(hand);
      }

      return ar;
   }

   public InteractionResult useOn(UseOnContext context) {
      super.useOn(context);
      TpacRightclickedOnBlockProcedure.execute(
         context.getLevel(),
         (double)context.getClickedPos().getX(),
         (double)context.getClickedPos().getY(),
         (double)context.getClickedPos().getZ(),
         context.getPlayer(),
         context.getItemInHand()
      );
      return InteractionResult.SUCCESS;
   }

   public void onUseTick(Level world, LivingEntity entity, ItemStack itemstack, int count) {
      if (!world.isClientSide() && entity instanceof ServerPlayer player) {
         ItemStack stack = this.findAmmo(player);
         if (player.getAbilities().instabuild || stack != ItemStack.EMPTY) {
            TripofobicAcidEntity projectile = TripofobicAcidEntity.shoot(world, entity, world.getRandom());
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

            TextureOfAshesBlackHoleRangedItemShootsProjectileProcedure.execute(entity, itemstack);
         }

         entity.releaseUsingItem();
      }
   }

   private ItemStack findAmmo(Player player) {
      ItemStack stack = ProjectileWeaponItem.getHeldProjectile(player, e -> e.getItem() == TripofobicAcidEntity.PROJECTILE_ITEM.getItem());
      if (stack == ItemStack.EMPTY) {
         for (int i = 0; i < player.getInventory().items.size(); i++) {
            ItemStack teststack = (ItemStack)player.getInventory().items.get(i);
            if (teststack != null && teststack.getItem() == TripofobicAcidEntity.PROJECTILE_ITEM.getItem()) {
               stack = teststack;
               break;
            }
         }
      }

      return stack;
   }
}
