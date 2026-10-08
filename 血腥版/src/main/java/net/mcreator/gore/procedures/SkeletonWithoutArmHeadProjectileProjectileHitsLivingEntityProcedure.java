package net.mcreator.gore.procedures;

import net.mcreator.gore.init.GoreEditionModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.LevelAccessor;

public class SkeletonWithoutArmHeadProjectileProjectileHitsLivingEntityProcedure {
   public static void execute(LevelAccessor world, Entity immediatesourceentity) {
      if (immediatesourceentity != null) {
         if (world instanceof ServerLevel _level) {
            Entity entityToSpawn = ((EntityType)GoreEditionModEntities.SKELETON_HEAD.get())
               .spawn(
                  _level,
                  BlockPos.containing(immediatesourceentity.getX(), immediatesourceentity.getY(), immediatesourceentity.getZ()),
                  MobSpawnType.MOB_SUMMONED
               );
            if (entityToSpawn != null) {
               entityToSpawn.setYRot(immediatesourceentity.getYRot());
               entityToSpawn.setYBodyRot(immediatesourceentity.getYRot());
               entityToSpawn.setYHeadRot(immediatesourceentity.getYRot());
               entityToSpawn.setXRot(immediatesourceentity.getXRot());
            }
         }

         if (!immediatesourceentity.level().isClientSide()) {
            immediatesourceentity.discard();
         }
      }
   }
}
