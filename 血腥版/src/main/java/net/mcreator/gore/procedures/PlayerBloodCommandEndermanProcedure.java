package net.mcreator.gore.procedures;

import net.mcreator.gore.network.GoreEditionModVariables;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

public class PlayerBloodCommandEndermanProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         String _setval = "ender";
         GoreEditionModVariables.PlayerVariables capability = GoreEditionModVariables.getPlayerVariables(entity);
         capability.player_blood_particles = _setval;
         capability.syncPlayerVariables(entity);
         if (world instanceof ServerLevel _level) {
            _level.getServer()
               .getCommands()
               .performPrefixedCommand(
                  new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null)
                     .withSuppressedOutput(),
                  "playsound goreedition:gore_50_blood_sound ambient @s ~ ~ ~ 0.4 2 0"
               );
         }
      }
   }
}
