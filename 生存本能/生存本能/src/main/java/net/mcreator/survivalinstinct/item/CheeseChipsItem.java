package net.mcreator.survivalinstinct.item;

import net.minecraft.world.food.FoodProperties.Builder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item.Properties;

public class CheeseChipsItem extends Item {
   public CheeseChipsItem() {
      super(new Properties().stacksTo(64).rarity(Rarity.COMMON).food(new Builder().nutrition(4).saturationModifier(0.6F).build()));
   }

   @Override
   public int getUseDuration(ItemStack itemstack, net.minecraft.world.entity.LivingEntity entity) {
      return 24;
   }
}
