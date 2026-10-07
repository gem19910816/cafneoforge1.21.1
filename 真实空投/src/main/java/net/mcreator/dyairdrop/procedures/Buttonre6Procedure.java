package net.mcreator.dyairdrop.procedures;

import net.gem19910816.dyairdrop.core.Vars;

import net.gem19910816.dyairdrop.core.Nbt;

import net.mcreator.dyairdrop.network.DyairdropModVariables;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;

public class Buttonre6Procedure {
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
                     + "f"
               )) {
               String _setval = Vars.of(entity)
                     .passwordre
                  + "f";
               Vars.of(entity).ifPresentData(capability -> {
                  capability.passwordre = _setval;
                  capability.syncPlayerVariables(entity);
               });
            } else {
               String _setval = Vars.of(entity)
                     .passwordre
                  + "F";
               Vars.of(entity).ifPresentData(capability -> {
                  capability.passwordre = _setval;
                  capability.syncPlayerVariables(entity);
               });
            }
         }
      }
   }
}
