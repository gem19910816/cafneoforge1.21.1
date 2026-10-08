package net.mcreator.gore.procedures;

import net.mcreator.gore.init.GoreEditionModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.LevelAccessor;

public class SkeletonPiecesSpawningRightArmProceduresProcedure {
   public static void execute(LevelAccessor world, Entity entity) {
      if (entity != null) {
         if (world instanceof ServerLevel _serverLevel) {
            Entity entityinstance = ((EntityType)GoreEditionModEntities.SKELETON_HEAD.get())
               .create(_serverLevel, null, BlockPos.containing(entity.getX(), entity.getY() + 1.65, entity.getZ()), MobSpawnType.MOB_SUMMONED, false, false);
            if (entityinstance != null) {
               entityinstance.setYRot(world.getRandom().nextFloat() * 360.0F);
               entityinstance.setYRot(entity.getYRot());
               entityinstance.setXRot(entity.getXRot());
               entityinstance.setYBodyRot(entityinstance.getYRot());
               entityinstance.setYHeadRot(entityinstance.getYRot());
               entityinstance.yRotO = entityinstance.getYRot();
               entityinstance.xRotO = entityinstance.getXRot();
               if (entityinstance instanceof LivingEntity _entity) {
                  _entity.yBodyRotO = _entity.getYRot();
                  _entity.yHeadRotO = _entity.getYRot();
               }

               _serverLevel.addFreshEntity(entityinstance);
            }
         }

         if (world instanceof ServerLevel _serverLevelx) {
            Entity entityinstance = ((EntityType)GoreEditionModEntities.SKELETON_RIBS.get())
               .create(_serverLevelx, null, BlockPos.containing(entity.getX(), entity.getY() + 1.4, entity.getZ()), MobSpawnType.MOB_SUMMONED, false, false);
            if (entityinstance != null) {
               entityinstance.setYRot(world.getRandom().nextFloat() * 360.0F);
               entityinstance.setYRot(entity.getYRot());
               entityinstance.setXRot(entity.getXRot());
               entityinstance.setYBodyRot(entityinstance.getYRot());
               entityinstance.setYHeadRot(entityinstance.getYRot());
               entityinstance.yRotO = entityinstance.getYRot();
               entityinstance.xRotO = entityinstance.getXRot();
               if (entityinstance instanceof LivingEntity _entity) {
                  _entity.yBodyRotO = _entity.getYRot();
                  _entity.yHeadRotO = _entity.getYRot();
               }

               _serverLevelx.addFreshEntity(entityinstance);
            }
         }

         if (world instanceof ServerLevel _serverLevelxx) {
            Entity entityinstance = ((EntityType)GoreEditionModEntities.SKELETON_LEFT_ARM.get())
               .create(_serverLevelxx, null, BlockPos.containing(entity.getX(), entity.getY() + 1.4, entity.getZ()), MobSpawnType.MOB_SUMMONED, false, false);
            if (entityinstance != null) {
               entityinstance.setYRot(world.getRandom().nextFloat() * 360.0F);
               entityinstance.setYRot(entity.getYRot());
               entityinstance.setXRot(entity.getXRot());
               entityinstance.setYBodyRot(entityinstance.getYRot());
               entityinstance.setYHeadRot(entityinstance.getYRot());
               entityinstance.yRotO = entityinstance.getYRot();
               entityinstance.xRotO = entityinstance.getXRot();
               if (entityinstance instanceof LivingEntity _entity) {
                  _entity.yBodyRotO = _entity.getYRot();
                  _entity.yHeadRotO = _entity.getYRot();
               }

               _serverLevelxx.addFreshEntity(entityinstance);
            }
         }

         if (world instanceof ServerLevel _serverLevelxxx) {
            Entity entityinstance = ((EntityType)GoreEditionModEntities.SKELETON_SPINAL_COLUMN.get())
               .create(_serverLevelxxx, null, BlockPos.containing(entity.getX(), entity.getY() + 1.4, entity.getZ()), MobSpawnType.MOB_SUMMONED, false, false);
            if (entityinstance != null) {
               entityinstance.setYRot(world.getRandom().nextFloat() * 360.0F);
               entityinstance.setYRot(entity.getYRot());
               entityinstance.setXRot(entity.getXRot());
               entityinstance.setYBodyRot(entityinstance.getYRot());
               entityinstance.setYHeadRot(entityinstance.getYRot());
               entityinstance.yRotO = entityinstance.getYRot();
               entityinstance.xRotO = entityinstance.getXRot();
               if (entityinstance instanceof LivingEntity _entity) {
                  _entity.yBodyRotO = _entity.getYRot();
                  _entity.yHeadRotO = _entity.getYRot();
               }

               _serverLevelxxx.addFreshEntity(entityinstance);
            }
         }

         if (world instanceof ServerLevel _serverLevelxxxx) {
            Entity entityinstance = ((EntityType)GoreEditionModEntities.SKELETON_PELVIS.get())
               .create(_serverLevelxxxx, null, BlockPos.containing(entity.getX(), entity.getY() + 1.0, entity.getZ()), MobSpawnType.MOB_SUMMONED, false, false);
            if (entityinstance != null) {
               entityinstance.setYRot(world.getRandom().nextFloat() * 360.0F);
               entityinstance.setYRot(entity.getYRot());
               entityinstance.setXRot(entity.getXRot());
               entityinstance.setYBodyRot(entityinstance.getYRot());
               entityinstance.setYHeadRot(entityinstance.getYRot());
               entityinstance.yRotO = entityinstance.getYRot();
               entityinstance.xRotO = entityinstance.getXRot();
               if (entityinstance instanceof LivingEntity _entity) {
                  _entity.yBodyRotO = _entity.getYRot();
                  _entity.yHeadRotO = _entity.getYRot();
               }

               _serverLevelxxxx.addFreshEntity(entityinstance);
            }
         }

         if (world instanceof ServerLevel _serverLevelxxxxx) {
            Entity entityinstance = ((EntityType)GoreEditionModEntities.SKELETON_LEFT_LEG.get())
               .create(_serverLevelxxxxx, null, BlockPos.containing(entity.getX(), entity.getY(), entity.getZ()), MobSpawnType.MOB_SUMMONED, false, false);
            if (entityinstance != null) {
               entityinstance.setYRot(world.getRandom().nextFloat() * 360.0F);
               entityinstance.setYRot(entity.getYRot());
               entityinstance.setXRot(entity.getXRot());
               entityinstance.setYBodyRot(entityinstance.getYRot());
               entityinstance.setYHeadRot(entityinstance.getYRot());
               entityinstance.yRotO = entityinstance.getYRot();
               entityinstance.xRotO = entityinstance.getXRot();
               if (entityinstance instanceof LivingEntity _entity) {
                  _entity.yBodyRotO = _entity.getYRot();
                  _entity.yHeadRotO = _entity.getYRot();
               }

               _serverLevelxxxxx.addFreshEntity(entityinstance);
            }
         }

         if (world instanceof ServerLevel _serverLevelxxxxxx) {
            Entity entityinstance = ((EntityType)GoreEditionModEntities.SKELETON_RIGHT_LEG.get())
               .create(_serverLevelxxxxxx, null, BlockPos.containing(entity.getX(), entity.getY(), entity.getZ()), MobSpawnType.MOB_SUMMONED, false, false);
            if (entityinstance != null) {
               entityinstance.setYRot(world.getRandom().nextFloat() * 360.0F);
               entityinstance.setYRot(entity.getYRot());
               entityinstance.setXRot(entity.getXRot());
               entityinstance.setYBodyRot(entityinstance.getYRot());
               entityinstance.setYHeadRot(entityinstance.getYRot());
               entityinstance.yRotO = entityinstance.getYRot();
               entityinstance.xRotO = entityinstance.getXRot();
               if (entityinstance instanceof LivingEntity _entity) {
                  _entity.yBodyRotO = _entity.getYRot();
                  _entity.yHeadRotO = _entity.getYRot();
               }

               _serverLevelxxxxxx.addFreshEntity(entityinstance);
            }
         }
      }
   }
}
