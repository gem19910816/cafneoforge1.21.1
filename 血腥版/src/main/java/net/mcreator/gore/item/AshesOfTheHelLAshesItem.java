package net.mcreator.gore.item;

import net.mcreator.gore.procedures.HellOrbLivingEntityIsHitWithItemProcedure;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item.Properties;

public class AshesOfTheHelLAshesItem extends Item {
   public AshesOfTheHelLAshesItem() {
      super(new Properties().stacksTo(16).fireResistant().rarity(Rarity.COMMON));
   }

   public boolean hurtEnemy(ItemStack itemstack, LivingEntity entity, LivingEntity sourceentity) {
      boolean retval = super.hurtEnemy(itemstack, entity, sourceentity);
      HellOrbLivingEntityIsHitWithItemProcedure.execute(entity.level(), entity.getX(), entity.getY(), entity.getZ(), entity, sourceentity, itemstack);
      return retval;
   }
}
