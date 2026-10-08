package net.mcreator.dyairdrop.procedures;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.mcreator.dyairdrop.network.DyairdropModVariables;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

public class SetairdropcodeProcedure {
   public SetairdropcodeProcedure() {
   }

   public static void execute(final CommandContext<CommandSourceStack> arguments) {
      Entity target = target(arguments);
      final String lootValue = StringArgumentType.getString(arguments, "loot");
      if (target != null) {
         DyairdropModVariables.with(target, capability -> {
            capability.airdroploot = lootValue;
            capability.syncPlayerVariables(target);
         });
      }

      final String blockValue = StringArgumentType.getString(arguments, "blockid");
      if (target != null) {
         DyairdropModVariables.with(target, capability -> {
            capability.airdropblock = blockValue;
            capability.syncPlayerVariables(target);
         });
      }

      if (target instanceof Player _player && !_player.level().isClientSide()) {
         _player.displayClientMessage(
            Component.literal(
               Component.translatable("message.yourloot").getString()
                  + lootValue
                  + ","
                  + blockValue
            ),
            false
         );
      }
   }

   private static Entity target(CommandContext<CommandSourceStack> arguments) {
      try {
         return EntityArgument.getEntity(arguments, "player");
      } catch (CommandSyntaxException var2) {
         var2.printStackTrace();
         return null;
      }
   }
}
