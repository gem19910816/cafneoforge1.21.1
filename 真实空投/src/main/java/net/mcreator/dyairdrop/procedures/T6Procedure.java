package net.mcreator.dyairdrop.procedures;

import net.gem19910816.dyairdrop.core.CommandArgs;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.MessageArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;

public class T6Procedure {
   public static void execute(LevelAccessor world, final CommandContext<CommandSourceStack> arguments, Entity entity) {
      if (entity != null) {
         String xyz = "";
         String structure = "";
         Entity player = null;
         xyz = "";
         structure = CommandArgs.message(arguments, "structure");
         player = entity;
         xyz = FindNearestStructureProcedure.findNearestStructure(player, structure);
         if (!world.isClientSide() && world.getServer() != null) {
            world.getServer().getPlayerList().broadcastSystemMessage(Component.literal(xyz), false);
         }
      }
   }
}
