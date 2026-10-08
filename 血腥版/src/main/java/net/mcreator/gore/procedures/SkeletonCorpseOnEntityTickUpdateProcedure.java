package net.mcreator.gore.procedures;

import net.mcreator.gore.entity.SkeletonCorpseEntity;
import net.mcreator.gore.entity.SkeletonCorpseIIEntity;
import net.mcreator.gore.entity.SkeletonCorpseWithoutRightArmEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;

public class SkeletonCorpseOnEntityTickUpdateProcedure {
   public static void execute(LevelAccessor world, Entity entity) {
      if (entity != null) {
         entity.getPersistentData().putDouble("TickWait", entity.getPersistentData().getDouble("TickWait") + 1.0);
         if (entity.getPersistentData().getDouble("TickWait") >= 10.0 && entity.getPersistentData().getBoolean("toughness")) {
            if (entity.getPersistentData().getDouble("TickUpdate") >= 0.0) {
               entity.getPersistentData().putBoolean("yessss", true);
            }

            entity.getPersistentData().putDouble("TickUpdate", entity.getPersistentData().getDouble("TickUpdate") + 1.0);
            if (entity.getPersistentData().getDouble("TickUpdate") == 200.0) {
               if (entity instanceof SkeletonCorpseEntity) {
                  ((SkeletonCorpseEntity)entity).setAnimation("moving");
               }

               if (entity instanceof SkeletonCorpseIIEntity) {
                  ((SkeletonCorpseIIEntity)entity).setAnimation("moving");
               }

               if (entity instanceof SkeletonCorpseWithoutRightArmEntity) {
                  ((SkeletonCorpseWithoutRightArmEntity)entity).setAnimation("moving");
               }
            }

            if (entity.getPersistentData().getDouble("TickUpdate") >= 220.0 && entity.getPersistentData().getBoolean("yessss")) {
               if (!entity.level().isClientSide()) {
                  entity.discard();
               }

               if (entity instanceof SkeletonCorpseEntity) {
                  SkeletonPiecesSpawnProceduresProcedure.execute(world, entity);
               }

               if (entity instanceof SkeletonCorpseIIEntity) {
                  SkeletonPiecesSpawningIIProceduresProcedure.execute(world, entity);
               }

               if (entity instanceof SkeletonCorpseWithoutRightArmEntity) {
                  SkeletonPiecesSpawningRightArmProceduresProcedure.execute(world, entity);
               }
            }
         }
      }
   }
}
