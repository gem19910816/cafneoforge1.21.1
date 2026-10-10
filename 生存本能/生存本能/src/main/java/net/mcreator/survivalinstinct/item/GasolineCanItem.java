package net.mcreator.survivalinstinct.item;

import net.mcreator.survivalinstinct.procedures.GasolineCanOnPlayerStoppedUsingProcedure;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties.Builder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.level.Level;

public class GasolineCanItem extends Item {
   public GasolineCanItem() {
      super(new Properties().durability(3).rarity(Rarity.COMMON).food(new Builder().nutrition(12).saturationModifier(0.1F).alwaysEdible().build()));
   }

   @Override
   public UseAnim getUseAnimation(ItemStack itemstack) {
      return UseAnim.DRINK;
   }

   @Override
   public boolean hasCraftingRemainingItem() {
      return true;
   }

   @Override
   public ItemStack getCraftingRemainingItem(ItemStack itemstack) {
      ItemStack retval = new ItemStack(this);
      retval.setDamageValue(itemstack.getDamageValue() + 1);
      return retval.getDamageValue() >= retval.getMaxDamage() ? ItemStack.EMPTY : retval;
   }

   @Override
   public boolean isRepairable(ItemStack itemstack) {
      return false;
   }

   @Override
   public int getUseDuration(ItemStack itemstack, net.minecraft.world.entity.LivingEntity entity) {
      return 42;
   }

   @Override
   public ItemStack finishUsingItem(ItemStack itemstack, Level world, LivingEntity entity) {
      ItemStack retval = super.finishUsingItem(itemstack, world, entity);
      double x = entity.getX();
      double y = entity.getY();
      double z = entity.getZ();
      GasolineCanOnPlayerStoppedUsingProcedure.execute(entity);
      return retval;
   }
}
