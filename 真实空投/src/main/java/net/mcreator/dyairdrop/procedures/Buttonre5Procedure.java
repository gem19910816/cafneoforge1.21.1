package net.mcreator.dyairdrop.procedures;

import net.mcreator.dyairdrop.network.DyairdropModVariables;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;

public class Buttonre5Procedure {
   public Buttonre5Procedure() {
   }

   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         String input = "";
         input = DyairdropModVariables.get(entity)
            .passwordre;
         if (!input.chars().anyMatch(Character::isUpperCase)) {
            if ((new Object() {
                  public String getValue(LevelAccessor world, BlockPos pos, String tag) {
                     BlockEntity blockEntity = world.getBlockEntity(pos);
                     return blockEntity != null ? blockEntity.getPersistentData().getString(tag) : "";
                  }
               })
               .getValue(world, BlockPos.containing(x, y, z), "pw")
               .contains(
                  DyairdropModVariables.get(entity)
                        .passwordre
                     + "e"
               )) {
               String _setval = DyairdropModVariables.get(entity)
                     .passwordre
                  + "e";
               DyairdropModVariables.with(entity, capability -> {
                  capability.passwordre = _setval;
                  capability.syncPlayerVariables(entity);
               });
            } else {
               final String _setval1 = DyairdropModVariables.get(entity)
                     .passwordre
                  + "E";
               DyairdropModVariables.with(entity, capability -> {
                  capability.passwordre = _setval1;
                  capability.syncPlayerVariables(entity);
               });
            }
         }
      }
   }
}
