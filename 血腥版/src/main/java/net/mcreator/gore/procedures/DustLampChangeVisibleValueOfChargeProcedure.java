package net.mcreator.gore.procedures;

import net.mcreator.gore.configuration.GeSpiralsConfiguration;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;

public class DustLampChangeVisibleValueOfChargeProcedure {
   public static String execute(LevelAccessor world, double x, double y, double z) {
      return (new Object() {
         public double getValue(LevelAccessor world, BlockPos pos, String tag) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            return blockEntity != null ? blockEntity.getPersistentData().getDouble(tag) : -1.0;
         }
      }).getValue(world, BlockPos.containing(x, y, z), "charge") + "/" + (Double)GeSpiralsConfiguration.REQUIRED_CHARGE.get();
   }
}
