package net.mcreator.gore.procedures;

import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.mcreator.gore.network.GoreEditionModVariables;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.world.entity.Entity;

public class PlayerYCenterCommandNumberProcedure {
   public static void execute(CommandContext<CommandSourceStack> arguments, Entity entity) {
      if (entity != null) {
         double _setval = DoubleArgumentType.getDouble(arguments, "y");
         GoreEditionModVariables.PlayerVariables capability = GoreEditionModVariables.getPlayerVariables(entity);
         capability.center_y_player = _setval;
         capability.syncPlayerVariables(entity);
      }
   }
}
