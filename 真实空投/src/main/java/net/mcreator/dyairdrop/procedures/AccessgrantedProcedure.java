package net.mcreator.dyairdrop.procedures;

import net.mcreator.dyairdrop.network.DyairdropModVariables;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

public class AccessgrantedProcedure {
   public static boolean execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      return entity == null
         ? false
         : ((DyairdropModVariables.PlayerVariables)entity.getData(DyairdropModVariables.PLAYER_VARIABLES_ATTACHMENT.get()))
                  .pw
                  .length()
               == 6
            && ((DyairdropModVariables.PlayerVariables)entity.getData(DyairdropModVariables.PLAYER_VARIABLES_ATTACHMENT.get()))
                  .showlight
               == 1.0
            && ((DyairdropModVariables.PlayerVariables)entity.getData(DyairdropModVariables.PLAYER_VARIABLES_ATTACHMENT.get()))
               .password
               .equals((new Object() {
                  public String getValue(LevelAccessor world, BlockPos pos, String tag) {
                     BlockEntity blockEntity = world.getBlockEntity(pos);
                     return blockEntity != null ? blockEntity.getPersistentData().getString(tag) : "";
                  }
               }).getValue(world, BlockPos.containing(x, y, z), "key"))
            && ((Level)world).getDayTime()
                  - ((DyairdropModVariables.PlayerVariables)entity.getData(DyairdropModVariables.PLAYER_VARIABLES_ATTACHMENT.get()))
                     .keyticking
               >= 42.0;
   }
}
