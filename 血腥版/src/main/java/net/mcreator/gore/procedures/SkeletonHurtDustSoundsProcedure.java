package net.mcreator.gore.procedures;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

public class SkeletonHurtDustSoundsProcedure {
   public static void execute(LevelAccessor world, Entity entity, double amount) {
      if (entity != null) {
         if (world instanceof Level _level) {
            if (!_level.isClientSide()) {
               _level.playSound(
                  null,
                  BlockPos.containing(entity.getX(), entity.getY(), entity.getZ()),
                  (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore_50_sound")),
                  SoundSource.AMBIENT,
                  1.0F,
                  1.0F
               );
            } else {
               _level.playLocalSound(
                  entity.getX(),
                  entity.getY(),
                  entity.getZ(),
                  (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore_50_sound")),
                  SoundSource.AMBIENT,
                  1.0F,
                  1.0F,
                  false
               );
            }
         }

         if (amount >= 2.0 && world instanceof Level _levelx) {
            if (!_levelx.isClientSide()) {
               _levelx.playSound(
                  null,
                  BlockPos.containing(entity.getX(), entity.getY(), entity.getZ()),
                  (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore.skeleton_hurt_sound")),
                  SoundSource.AMBIENT,
                  1.0F,
                  1.0F
               );
            } else {
               _levelx.playLocalSound(
                  entity.getX(),
                  entity.getY(),
                  entity.getZ(),
                  (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore.skeleton_hurt_sound")),
                  SoundSource.AMBIENT,
                  1.0F,
                  1.0F,
                  false
               );
            }
         }

         if (amount >= 7.0 && world instanceof Level _levelxx) {
            if (!_levelxx.isClientSide()) {
               _levelxx.playSound(
                  null,
                  BlockPos.containing(entity.getX(), entity.getY(), entity.getZ()),
                  (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore.skeleton_50_sound")),
                  SoundSource.AMBIENT,
                  1.0F,
                  1.0F
               );
            } else {
               _levelxx.playLocalSound(
                  entity.getX(),
                  entity.getY(),
                  entity.getZ(),
                  (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore.skeleton_50_sound")),
                  SoundSource.AMBIENT,
                  1.0F,
                  1.0F,
                  false
               );
            }
         }

         if (amount >= 9.0 && world instanceof Level _levelxxx) {
            if (!_levelxxx.isClientSide()) {
               _levelxxx.playSound(
                  null,
                  BlockPos.containing(entity.getX(), entity.getY(), entity.getZ()),
                  (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore.skeleton_concentred_damage_in_one_piece_sound")),
                  SoundSource.AMBIENT,
                  1.0F,
                  1.0F
               );
            } else {
               _levelxxx.playLocalSound(
                  entity.getX(),
                  entity.getY(),
                  entity.getZ(),
                  (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore.skeleton_concentred_damage_in_one_piece_sound")),
                  SoundSource.AMBIENT,
                  1.0F,
                  1.0F,
                  false
               );
            }
         }
      }
   }
}
