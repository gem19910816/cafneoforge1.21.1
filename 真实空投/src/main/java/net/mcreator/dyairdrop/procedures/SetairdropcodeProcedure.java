package net.mcreator.dyairdrop.procedures;

import net.gem19910816.dyairdrop.core.CommandArgs;

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
   public static void execute(final CommandContext<CommandSourceStack> arguments) {
      String _setval = StringArgumentType.getString(arguments, "loot");
      CommandArgs.entity(arguments, "player").getData(DyairdropModVariables.PLAYER_VARIABLES_ATTACHMENT.get()).ifPresentData(capability -> {
         capability.airdroploot = _setval;
         capability.syncPlayerVariables(CommandArgs.entity(arguments, "player"));
      });
      String _setvalb = StringArgumentType.getString(arguments, "blockid");
      CommandArgs.entity(arguments, "player").getData(DyairdropModVariables.PLAYER_VARIABLES_ATTACHMENT.get()).ifPresentData(capability -> {
         capability.airdropblock = _setval;
         capability.syncPlayerVariables(CommandArgs.entity(arguments, "player"));
      });
      if (CommandArgs.entity(arguments, "player") instanceof Player _player && !_player.level().isClientSide()) {
         _player.displayClientMessage(
            Component.literal(
               Component.translatable("message.yourloot").getString()
                  + StringArgumentType.getString(arguments, "loot")
                  + ","
                  + StringArgumentType.getString(arguments, "blockid")
            ),
            false
         );
      }
   }
}
