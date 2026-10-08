package net.mcreator.gore.procedures;

import net.mcreator.gore.utils.ItemTagHelper;
import net.minecraft.world.item.ItemStack;

public class SpectralExecutionerAxeGiftSpecialInformationProcedure {
   public static String execute(ItemStack itemstack) {
      return ItemTagHelper.getString(itemstack, "item").equals("") ? "§fnull" : "§f" + ItemTagHelper.getOrCreateTag(itemstack).getString("item");
   }
}
