package net.mcreator.dyairdrop.procedures;

import net.mcreator.dyairdrop.network.DyairdropModVariables;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;

public class Buttonre5Procedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         String input = "";
         input = ((DyairdropModVariables.PlayerVariables)entity.getData(DyairdropModVariables.PLAYER_VARIABLES_ATTACHMENT.get()))
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
                  ((DyairdropModVariables.PlayerVariables)entity.getData(DyairdropModVariables.PLAYER_VARIABLES_ATTACHMENT.get()))
                        .passwordre
                     + "e"
               )) {
               String _setval = ((DyairdropModVariables.PlayerVariables)entity.getData(DyairdropModVariables.PLAYER_VARIABLES_ATTACHMENT.get()))
                     .passwordre
                  + "e";
               entity.getData(DyairdropModVariables.PLAYER_VARIABLES_ATTACHMENT.get()).ifPresentData(capability -> {
                  capability.passwordre = _setval;
                  capability.syncPlayerVariables(entity);
               });
            } else {
               String _setval = ((DyairdropModVariables.PlayerVariables)entity.getData(DyairdropModVariables.PLAYER_VARIABLES_ATTACHMENT.get()))
                     .passwordre
                  + "E";
               entity.getData(DyairdropModVariables.PLAYER_VARIABLES_ATTACHMENT.get()).ifPresentData(capability -> {
                  capability.passwordre = _setval;
                  capability.syncPlayerVariables(entity);
               });
            }
         }
      }
   }
}
