package net.mcreator.gore.procedures;

import net.mcreator.gore.entity.CrushingSkeletonEntity;
import net.mcreator.gore.init.GoreEditionModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

public class CrushingSkeletonOnEntityTickUpdateProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         CrushingSkeletonSpawnOnEntityTickUpdateProcedure.execute(world, entity);
         if (entity.getPersistentData().getBoolean("canattackskeleton")) {
            if (entity.getPersistentData().getDouble("crushing_skeleton_dying") == 1.0 && entity instanceof CrushingSkeletonEntity animatable) {
               animatable.setTexture("cracked_skeleton_frame_2");
            }

            if (entity.getPersistentData().getDouble("crushing_skeleton_dying") == 2.0 && entity instanceof CrushingSkeletonEntity animatable) {
               animatable.setTexture("cracked_skeleton_frame_3");
            }

            if (entity.getPersistentData().getDouble("crushing_skeleton_dying") >= 3.0) {
               if (entity instanceof CrushingSkeletonEntity animatable) {
                  animatable.setTexture("cracked_skeleton_frame_4");
               }

               entity.getPersistentData()
                  .putDouble("wait_to_despawn_crushing_skeleton", entity.getPersistentData().getDouble("wait_to_despawn_crushing_skeleton") + 1.0);
            }

            if (entity.getPersistentData().getDouble("crushing_skeleton_dying") == 4.0 && !entity.level().isClientSide()) {
               entity.discard();
            }

            if (entity.getPersistentData().getDouble("wait_to_despawn_crushing_skeleton") == 40.0) {
               if (!entity.level().isClientSide()) {
                  entity.discard();
               }

               if (world instanceof Level _level) {
                  if (!_level.isClientSide()) {
                     _level.playSound(
                        null,
                        BlockPos.containing(x, y, z),
                        (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.generic.explode")),
                        SoundSource.HOSTILE,
                        4.0F,
                        2.0F
                     );
                  } else {
                     _level.playLocalSound(
                        x,
                        y,
                        z,
                        (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.generic.explode")),
                        SoundSource.HOSTILE,
                        4.0F,
                        2.0F,
                        false
                     );
                  }
               }

               if (world instanceof ServerLevel _levelx) {
                  Entity entityToSpawn = ((EntityType)GoreEditionModEntities.SKELETON_LEFT_LEG.get())
                     .spawn(_levelx, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED);
                  if (entityToSpawn != null) {
                     entityToSpawn.setYRot(world.getRandom().nextFloat() * 360.0F);
                  }
               }

               if (world instanceof ServerLevel _levelxx) {
                  Entity entityToSpawn = ((EntityType)GoreEditionModEntities.SKELETON_RIGHT_LEG.get())
                     .spawn(_levelxx, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED);
                  if (entityToSpawn != null) {
                     entityToSpawn.setYRot(world.getRandom().nextFloat() * 360.0F);
                  }
               }
            }
         }
      }
   }
}
