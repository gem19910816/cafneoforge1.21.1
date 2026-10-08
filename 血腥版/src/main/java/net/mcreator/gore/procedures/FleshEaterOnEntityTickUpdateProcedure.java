package net.mcreator.gore.procedures;

import net.mcreator.gore.entity.ExarrackHydraEntity;
import net.mcreator.gore.entity.FakeExarrackMonsterEntity;
import net.mcreator.gore.entity.FleshEaterEntity;
import net.mcreator.gore.entity.TheExarrackMonsterEntity;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;

public class FleshEaterOnEntityTickUpdateProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         double number_of_entities = 0.0;
         AshesNativesSetAttackTargetProcedure.execute(world, x, y, z, entity);
         if (!entity.getPersistentData().getBoolean("hengeyon_grace_ashes")) {
            if (entity instanceof FakeExarrackMonsterEntity) {
               entity.getPersistentData().putDouble("distance", (double)Mth.nextInt(RandomSource.create(), 12, 53));
            }

            if (entity instanceof FleshEaterEntity) {
               entity.getPersistentData().putDouble("distance", (double)Mth.nextInt(RandomSource.create(), 10, 13));
            }

            if (entity instanceof TheExarrackMonsterEntity) {
               entity.getPersistentData().putDouble("distance", (double)Mth.nextInt(RandomSource.create(), 11, 16));
            }

            if (entity instanceof ExarrackHydraEntity) {
               entity.getPersistentData().putDouble("distance", (double)Mth.nextInt(RandomSource.create(), 14, 16));
            }
         } else {
            entity.getPersistentData().putDouble("distance", 128.0);
         }

         if (entity instanceof TheExarrackMonsterEntity) {
            TheexarrackmonsterjumpplayerProcedure.execute(entity);
         }

         FleshEaterScreamProcedure.execute(world, x, y, z, entity);
      }
   }
}
