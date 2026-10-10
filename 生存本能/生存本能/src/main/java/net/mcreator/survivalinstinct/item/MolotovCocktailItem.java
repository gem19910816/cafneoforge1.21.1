package net.mcreator.survivalinstinct.item;

import net.mcreator.survivalinstinct.entity.MolotovEntity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow.Pickup;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.level.Level;

public class MolotovCocktailItem extends Item {
   public MolotovCocktailItem() {
      super(new Properties().stacksTo(16).rarity(Rarity.COMMON));
   }

   @Override
   public UseAnim getUseAnimation(ItemStack itemstack) {
      return UseAnim.SPEAR;
   }

   @Override
   public int getUseDuration(ItemStack itemstack, net.minecraft.world.entity.LivingEntity entity) {
      return 72000;
   }

   @Override
   public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
      InteractionResultHolder<ItemStack> ar = InteractionResultHolder.fail(entity.getItemInHand(hand));
      if (entity.getAbilities().instabuild || !this.findAmmo(entity).isEmpty()) {
         ar = InteractionResultHolder.success(entity.getItemInHand(hand));
         entity.startUsingItem(hand);
      }

      return ar;
   }

   @Override
   public void releaseUsing(ItemStack itemstack, Level world, LivingEntity entity, int time) {
      if (!world.isClientSide() && entity instanceof ServerPlayer player) {
         float pullingPower = BowItem.getPowerForTime(this.getUseDuration(itemstack, entity) - time);
         if ((double)pullingPower < 0.1) {
            return;
         }

         ItemStack stack = this.findAmmo(player);
         if (player.getAbilities().instabuild || !stack.isEmpty()) {
            MolotovEntity projectile = MolotovEntity.shoot(world, entity, world.getRandom(), pullingPower);
            if (player.getAbilities().instabuild) {
               projectile.pickup = Pickup.CREATIVE_ONLY;
            } else {
               stack.shrink(1);
               if (stack.isEmpty()) {
                  player.getInventory().removeItem(stack);
               }
            }
         }
      }
   }

   private ItemStack findAmmo(Player player) {
      ItemStack stack = ProjectileWeaponItem.getHeldProjectile(player, e -> e.getItem() == MolotovEntity.PROJECTILE_ITEM.getItem());
      if (stack.isEmpty()) {
         for (int i = 0; i < player.getInventory().items.size(); i++) {
            ItemStack teststack = player.getInventory().items.get(i);
            if (teststack != null && teststack.getItem() == MolotovEntity.PROJECTILE_ITEM.getItem()) {
               stack = teststack;
               break;
            }
         }
      }

      return stack;
   }
}
