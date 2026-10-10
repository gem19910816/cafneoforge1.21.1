package net.mcreator.survivalinstinct.item;

import net.mcreator.survivalinstinct.init.SurvivalInstinctModItems;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties.Builder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.level.Level;

public class TomatoSoupItem extends Item {
   public TomatoSoupItem() {
      super(new Properties().stacksTo(16).rarity(Rarity.COMMON).food(new Builder().nutrition(7).saturationModifier(0.8F).build()));
   }

   @Override
   public UseAnim getUseAnimation(ItemStack itemstack) {
      return UseAnim.DRINK;
   }

   @Override
   public int getUseDuration(ItemStack itemstack, net.minecraft.world.entity.LivingEntity entity) {
      return 42;
   }

   @Override
   public ItemStack finishUsingItem(ItemStack itemstack, Level world, LivingEntity entity) {
      ItemStack retval = new ItemStack(SurvivalInstinctModItems.EMPTY_CAN.get());
      super.finishUsingItem(itemstack, world, entity);
      if (itemstack.isEmpty()) {
         return retval;
      } else {
         if (entity instanceof Player player && !player.getAbilities().instabuild && !player.getInventory().add(retval)) {
            player.drop(retval, false);
         }

         return itemstack;
      }
   }
}
