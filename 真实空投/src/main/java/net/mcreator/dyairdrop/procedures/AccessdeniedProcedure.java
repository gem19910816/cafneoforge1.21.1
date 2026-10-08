package net.mcreator.dyairdrop.procedures;

import net.mcreator.dyairdrop.network.DyairdropModVariables;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;

public class AccessdeniedProcedure {
   public AccessdeniedProcedure() {
   }

   public static boolean execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      return entity == null
         ? false
         : DyairdropModVariables.get(entity)
                  .pw
                  .length()
               == 6
            && DyairdropModVariables.get(entity)
                  .showlight
               == 1.0
            && !DyairdropModVariables.get(entity)
               .password
               .equals((new Object() {
                  public String getValue(LevelAccessor world, BlockPos pos, String tag) {
                     BlockEntity blockEntity = world.getBlockEntity(pos);
                     return blockEntity != null ? blockEntity.getPersistentData().getString(tag) : "";
                  }
               }).getValue(world, BlockPos.containing(x, y, z), "key"))
            && (double)world.dayTime()
                  - DyairdropModVariables.get(entity)
                     .keyticking
               >= 42.0;
   }
}
