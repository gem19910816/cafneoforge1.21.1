package net.mcreator.gore.procedures;

import net.mcreator.gore.utils.ItemTagHelper;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class ExecutionersAxToolInInventoryTickProcedure {
   public static void execute(Entity entity, ItemStack itemstack) {
      if (entity != null) {
         ItemTagHelper.putDouble(itemstack, "timer", ItemTagHelper.getOrCreateTag(itemstack).getDouble("timer") + 1.0);
         if (ItemTagHelper.getDouble(itemstack, "timer") >= 300.0 && entity instanceof Player _player) {
            _player.getInventory().clearOrCountMatchingItems(p -> itemstack.getItem() == p.getItem(), 1, _player.inventoryMenu.getCraftSlots());
         }
      }
   }
}
