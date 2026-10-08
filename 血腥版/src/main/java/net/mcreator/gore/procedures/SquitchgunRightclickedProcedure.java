package net.mcreator.gore.procedures;

import net.mcreator.gore.utils.ItemTagHelper;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class SquitchgunRightclickedProcedure {
   public static void execute(Entity entity, ItemStack itemstack) {
      if (entity != null) {
         if (ItemTagHelper.getBoolean(itemstack, "charged") && !ItemTagHelper.getOrCreateTag(itemstack).getBoolean("shoot")) {
            ItemTagHelper.getOrCreateTag(itemstack).putBoolean("shoot", true);
         }

         if (!ItemTagHelper.getBoolean(itemstack, "charged") && entity instanceof Player _player && !_player.level().isClientSide()) {
            _player.displayClientMessage(Component.literal("§4The geart has died"), true);
         }
      }
   }
}
