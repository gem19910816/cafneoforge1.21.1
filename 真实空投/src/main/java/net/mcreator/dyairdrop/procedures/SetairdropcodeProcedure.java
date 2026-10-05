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
   public static void execute(final CommandContext<CommandSourceStack> arguments) {
      String _setval = StringArgumentType.getString(arguments, "loot");
      (new Object() {
         public Entity getEntity() {
            try {
               return EntityArgument.getEntity(arguments, "player");
            } catch (CommandSyntaxException e) {
               e.printStackTrace();
               return null;
            }
         }
      }).getEntity().getData(DyairdropModVariables.PLAYER_VARIABLES_ATTACHMENT.get()).ifPresentData(capability -> {
         capability.airdroploot = _setval;
         capability.syncPlayerVariables((new Object() {
            public Entity getEntity() {
               try {
                  return EntityArgument.getEntity(arguments, "player");
               } catch (CommandSyntaxException e) {
                  e.printStackTrace();
                  return null;
               }
            }
         }).getEntity());
      });
      String _setvalb = StringArgumentType.getString(arguments, "blockid");
      (new Object() {
         public Entity getEntity() {
            try {
               return EntityArgument.getEntity(arguments, "player");
            } catch (CommandSyntaxException e) {
               e.printStackTrace();
               return null;
            }
         }
      }).getEntity().getData(DyairdropModVariables.PLAYER_VARIABLES_ATTACHMENT.get()).ifPresentData(capability -> {
         capability.airdropblock = _setval;
         capability.syncPlayerVariables((new Object() {
            public Entity getEntity() {
               try {
                  return EntityArgument.getEntity(arguments, "player");
               } catch (CommandSyntaxException e) {
                  e.printStackTrace();
                  return null;
               }
            }
         }).getEntity());
      });
      if ((new Object() {
         public Entity getEntity() {
            try {
               return EntityArgument.getEntity(arguments, "player");
            } catch (CommandSyntaxException e) {
               e.printStackTrace();
               return null;
            }
         }
      }).getEntity() instanceof Player _player && !_player.level().isClientSide()) {
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
