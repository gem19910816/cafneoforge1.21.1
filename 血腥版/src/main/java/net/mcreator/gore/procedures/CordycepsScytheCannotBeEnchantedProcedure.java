package net.mcreator.gore.procedures;

import net.mcreator.gore.init.GoreEditionModItems;
import net.mcreator.gore.utils.ItemTagHelper;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

public class CordycepsScytheCannotBeEnchantedProcedure {
   public static void execute(Entity entity, ItemStack itemstack) {
      if (entity != null) {
         double particleRadius = 0.0;
         double particleAmount = 0.0;
         if (entity instanceof Player _playerHasItem && _playerHasItem.getInventory().contains(new ItemStack((ItemLike)GoreEditionModItems.NEEDLE.get()))) {
            ItemTagHelper.putBoolean(itemstack, "munition", true);
            return;
         }

         ItemTagHelper.putBoolean(itemstack, "munition", false);
      }
   }
}
