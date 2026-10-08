package net.mcreator.gore.procedures;

import net.mcreator.gore.utils.ItemTagHelper;
import net.minecraft.world.item.ItemStack;

public class VoodooRabbitPropertyValueProviderProcedure {
   public static double execute(ItemStack itemstack) {
      return ItemTagHelper.getDouble(itemstack, "click");
   }
}
