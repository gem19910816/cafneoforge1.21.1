package net.mcreator.gore.procedures;

import net.mcreator.gore.entity.ExarrackHydraNaturalSpawnEntity;
import net.mcreator.gore.entity.FleshEaterNaturalSpawnEntity;
import net.mcreator.gore.entity.TheExarrackMonsterNaturalSpawnEntity;
import net.mcreator.gore.init.GoreEditionModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.LevelAccessor;

public class FleshEaterNaturalSpawnOnInitialEntitySpawnProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         if (!entity.level().isClientSide()) {
            entity.discard();
         }

         if (Math.random() < 0.4) {
            if (entity instanceof FleshEaterNaturalSpawnEntity && world instanceof ServerLevel _level) {
               Entity entityToSpawn = ((EntityType)GoreEditionModEntities.EXARRACK_MONSTER.get())
                  .spawn(_level, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED);
               if (entityToSpawn != null) {
                  entityToSpawn.setYRot(world.getRandom().nextFloat() * 360.0F);
               }
            }

            if (entity instanceof TheExarrackMonsterNaturalSpawnEntity && world instanceof ServerLevel _levelx) {
               Entity entityToSpawn = ((EntityType)GoreEditionModEntities.EXARRACK_DEMON.get())
                  .spawn(_levelx, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED);
               if (entityToSpawn != null) {
                  entityToSpawn.setYRot(world.getRandom().nextFloat() * 360.0F);
               }
            }

            if (entity instanceof ExarrackHydraNaturalSpawnEntity && world instanceof ServerLevel _levelxx) {
               Entity entityToSpawn = ((EntityType)GoreEditionModEntities.EXARRACK_HYDRA.get())
                  .spawn(_levelxx, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED);
               if (entityToSpawn != null) {
                  entityToSpawn.setYRot(world.getRandom().nextFloat() * 360.0F);
               }
            }
         }
      }
   }
}
