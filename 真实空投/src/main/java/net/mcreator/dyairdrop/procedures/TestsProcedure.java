package net.mcreator.dyairdrop.procedures;

import net.gem19910816.dyairdrop.core.CommandArgs;

import com.mojang.brigadier.context.CommandContext;
import java.text.DecimalFormat;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.LevelAccessor;

public class TestsProcedure {
   public static void execute(LevelAccessor world, final CommandContext<CommandSourceStack> arguments) {
      if (!world.isClientSide() && world.getServer() != null) {
         world.getServer().getPlayerList().broadcastSystemMessage(Component.literal(new DecimalFormat("##.##").format(CommandArgs.loadedPos(arguments, "b").getX())), false);
      }
   }
}
