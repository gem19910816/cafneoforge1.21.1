package net.mcreator.survivalinstinct.item;

import net.mcreator.survivalinstinct.init.SurvivalInstinctModItems;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.crafting.Ingredient;

public class SwatShieldItem extends ShieldItem {
   public SwatShieldItem() {
      super(new Properties().durability(641));
   }

   @Override
   public boolean isValidRepairItem(ItemStack itemstack, ItemStack repairitem) {
      return Ingredient.of(new ItemStack(SurvivalInstinctModItems.STEELLIUM.get())).test(repairitem);
   }
}
