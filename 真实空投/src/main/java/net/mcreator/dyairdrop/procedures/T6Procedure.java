package net.mcreator.dyairdrop.procedures;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.MessageArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;

public class T6Procedure {
   public T6Procedure() {
   }

   public static void execute(LevelAccessor world, final CommandContext<CommandSourceStack> arguments, Entity entity) {
      if (entity != null) {
         String xyz = "";
         String structure = "";
         Entity player = null;
         xyz = "";
         structure = (new Object() {
            public String getMessage() {
               try {
                  return MessageArgument.getMessage(arguments, "structure").getString();
               } catch (CommandSyntaxException var2) {
                  return "";
               }
            }
         }).getMessage();
         xyz = FindNearestStructureProcedure.findNearestStructure(entity, structure);
         if (!world.isClientSide() && world.getServer() != null) {
            world.getServer().getPlayerList().broadcastSystemMessage(Component.literal(xyz), false);
         }
      }
   }
}
