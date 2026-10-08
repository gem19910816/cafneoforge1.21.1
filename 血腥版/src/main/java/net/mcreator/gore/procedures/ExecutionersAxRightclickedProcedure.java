package net.mcreator.gore.procedures;

import net.mcreator.gore.utils.ItemTagHelper;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class ExecutionersAxRightclickedProcedure {
   public static void execute(Entity entity, ItemStack itemstack) {
      if (entity != null) {
         if (ItemTagHelper.getDouble(itemstack, "timer") >= 0.0
            && ItemTagHelper.getOrCreateTag(itemstack).getDouble("timer") <= 20.0
            && entity instanceof Player _player
            && !_player.level().isClientSide()) {
            _player.displayClientMessage(Component.literal("§415 seconds remain"), true);
         }

         if (ItemTagHelper.getDouble(itemstack, "timer") >= 20.0
            && ItemTagHelper.getOrCreateTag(itemstack).getDouble("timer") <= 40.0
            && entity instanceof Player _player
            && !_player.level().isClientSide()) {
            _player.displayClientMessage(Component.literal("§414 seconds remain"), true);
         }

         if (ItemTagHelper.getDouble(itemstack, "timer") >= 40.0
            && ItemTagHelper.getOrCreateTag(itemstack).getDouble("timer") <= 60.0
            && entity instanceof Player _player
            && !_player.level().isClientSide()) {
            _player.displayClientMessage(Component.literal("§413 seconds remain"), true);
         }

         if (ItemTagHelper.getDouble(itemstack, "timer") >= 60.0
            && ItemTagHelper.getOrCreateTag(itemstack).getDouble("timer") <= 80.0
            && entity instanceof Player _player
            && !_player.level().isClientSide()) {
            _player.displayClientMessage(Component.literal("§412 seconds remain"), true);
         }

         if (ItemTagHelper.getDouble(itemstack, "timer") >= 80.0
            && ItemTagHelper.getOrCreateTag(itemstack).getDouble("timer") <= 100.0
            && entity instanceof Player _player
            && !_player.level().isClientSide()) {
            _player.displayClientMessage(Component.literal("§411 seconds remain"), true);
         }

         if (ItemTagHelper.getDouble(itemstack, "timer") >= 100.0
            && ItemTagHelper.getOrCreateTag(itemstack).getDouble("timer") <= 120.0
            && entity instanceof Player _player
            && !_player.level().isClientSide()) {
            _player.displayClientMessage(Component.literal("§410 seconds remain"), true);
         }

         if (ItemTagHelper.getDouble(itemstack, "timer") >= 120.0
            && ItemTagHelper.getOrCreateTag(itemstack).getDouble("timer") <= 140.0
            && entity instanceof Player _player
            && !_player.level().isClientSide()) {
            _player.displayClientMessage(Component.literal("§49 seconds remain"), true);
         }

         if (ItemTagHelper.getDouble(itemstack, "timer") >= 140.0
            && ItemTagHelper.getOrCreateTag(itemstack).getDouble("timer") <= 160.0
            && entity instanceof Player _player
            && !_player.level().isClientSide()) {
            _player.displayClientMessage(Component.literal("§48 seconds remain"), true);
         }

         if (ItemTagHelper.getDouble(itemstack, "timer") >= 160.0
            && ItemTagHelper.getOrCreateTag(itemstack).getDouble("timer") <= 180.0
            && entity instanceof Player _player
            && !_player.level().isClientSide()) {
            _player.displayClientMessage(Component.literal("§47 seconds remain"), true);
         }

         if (ItemTagHelper.getDouble(itemstack, "timer") >= 180.0
            && ItemTagHelper.getOrCreateTag(itemstack).getDouble("timer") <= 200.0
            && entity instanceof Player _player
            && !_player.level().isClientSide()) {
            _player.displayClientMessage(Component.literal("§46 seconds remain"), true);
         }

         if (ItemTagHelper.getDouble(itemstack, "timer") >= 200.0
            && ItemTagHelper.getOrCreateTag(itemstack).getDouble("timer") <= 220.0
            && entity instanceof Player _player
            && !_player.level().isClientSide()) {
            _player.displayClientMessage(Component.literal("§45 seconds remain"), true);
         }

         if (ItemTagHelper.getDouble(itemstack, "timer") >= 220.0
            && ItemTagHelper.getOrCreateTag(itemstack).getDouble("timer") <= 240.0
            && entity instanceof Player _player
            && !_player.level().isClientSide()) {
            _player.displayClientMessage(Component.literal("§44 seconds remain"), true);
         }

         if (ItemTagHelper.getDouble(itemstack, "timer") >= 240.0
            && ItemTagHelper.getOrCreateTag(itemstack).getDouble("timer") <= 260.0
            && entity instanceof Player _player
            && !_player.level().isClientSide()) {
            _player.displayClientMessage(Component.literal("§43 seconds remain"), true);
         }

         if (ItemTagHelper.getDouble(itemstack, "timer") >= 260.0
            && ItemTagHelper.getOrCreateTag(itemstack).getDouble("timer") <= 280.0
            && entity instanceof Player _player
            && !_player.level().isClientSide()) {
            _player.displayClientMessage(Component.literal("§42 seconds remain"), true);
         }

         if (ItemTagHelper.getDouble(itemstack, "timer") >= 280.0
            && ItemTagHelper.getOrCreateTag(itemstack).getDouble("timer") <= 300.0
            && entity instanceof Player _player
            && !_player.level().isClientSide()) {
            _player.displayClientMessage(Component.literal("§41 seconds remain"), true);
         }

         if (ItemTagHelper.getDouble(itemstack, "timer") >= 300.0 && entity instanceof Player _player && !_player.level().isClientSide()) {
            _player.displayClientMessage(Component.literal("§k§4afioasfnfiowaenadsglk"), true);
         }
      }
   }
}
