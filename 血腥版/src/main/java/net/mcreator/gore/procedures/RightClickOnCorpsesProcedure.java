package net.mcreator.gore.procedures;

import net.mcreator.gore.entity.CreeperCorpseEntity;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

public class RightClickOnCorpsesProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null && entity instanceof CreeperCorpseEntity && entity.getPersistentData().getDouble("loot_limit") == 0.0) {
         entity.getPersistentData().putBoolean("looted", true);
         if (world instanceof ServerLevel _level) {
            _level.getServer()
               .getCommands()
               .performPrefixedCommand(
                  new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null)
                     .withSuppressedOutput(),
                  "loot spawn ~ ~ ~ loot minecraft:entities/creeper"
               );
         }

         entity.getPersistentData().putDouble("loot_limit", entity.getPersistentData().getDouble("loot_limit") + 1.0);
      }
   }
}
