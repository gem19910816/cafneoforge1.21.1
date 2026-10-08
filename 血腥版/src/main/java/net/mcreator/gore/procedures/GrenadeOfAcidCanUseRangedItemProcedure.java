package net.mcreator.gore.procedures;

import net.mcreator.gore.utils.ItemTagHelper;
import net.minecraft.world.item.ItemStack;

public class GrenadeOfAcidCanUseRangedItemProcedure {
   public static boolean execute(ItemStack itemstack) {
      return ItemTagHelper.getOrCreateTag(itemstack).getDouble("click") >= 1.0;
   }
}
