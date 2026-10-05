package net.mcreator.dyairdrop.procedures;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import java.text.DecimalFormat;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.coordinates.BlockPosArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.LevelAccessor;

public class TestsProcedure {
   public static void execute(LevelAccessor world, final CommandContext<CommandSourceStack> arguments) {
      if (!world.isClientSide() && world.getServer() != null) {
         world.getServer().getPlayerList().broadcastSystemMessage(Component.literal(new DecimalFormat("##.##").format((new Object() {
            public double getX() {
               try {
                  return BlockPosArgument.getLoadedBlockPos(arguments, "b").getX();
               } catch (CommandSyntaxException e) {
                  e.printStackTrace();
                  return 0.0;
               }
            }
         }).getX())), false);
      }
   }
}
