package net.mcreator.dyairdrop.procedures;

import net.gem19910816.dyairdrop.core.Vars;

import net.gem19910816.dyairdrop.core.Nbt;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;

public class Buttonre5Procedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         String input = "";
         input = Vars.of(entity)
            .passwordre;
         if (!input.chars().anyMatch(Character::isUpperCase)) {
            if (Nbt.getString(world, BlockPos.containing(x, y, z), "pw")
               .contains(
                  Vars.of(entity)
                        .passwordre
                     + "e"
               )) {
               String _setval = Vars.of(entity)
                     .passwordre
                  + "e";
               Vars.of(entity).ifPresentData(capability -> {
                  capability.passwordre = _setval;
                  capability.syncPlayerVariables(entity);
               });
            } else {
               String _setval = Vars.of(entity)
                     .passwordre
                  + "E";
               Vars.of(entity).ifPresentData(capability -> {
                  capability.passwordre = _setval;
                  capability.syncPlayerVariables(entity);
               });
            }
         }
      }
   }
}
