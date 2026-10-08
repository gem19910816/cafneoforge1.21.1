package net.mcreator.gore.procedures;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

public class AshesBlackHoleWhileProjectileFlyingTickProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity immediatesourceentity) {
      if (immediatesourceentity != null) {
         if (!immediatesourceentity.getPersistentData().getBoolean("gravity_applied")) {
            immediatesourceentity.setNoGravity(true);
            immediatesourceentity.getPersistentData().putBoolean("gravity_applied", true);

            for (int index0 = 0; index0 < 3; index0++) {
               if (world instanceof Level) {
                  Level _level = (Level)world;
                  if (!_level.isClientSide()) {
                     _level.playSound(
                        null,
                        BlockPos.containing(x, y, z),
                        (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:entity.ashes_black_hole.spawn")),
                        SoundSource.NEUTRAL,
                        8.0F,
                        2.0F
                     );
                  } else {
                     _level.playLocalSound(
                        x,
                        y,
                        z,
                        (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:entity.ashes_black_hole.spawn")),
                        SoundSource.NEUTRAL,
                        8.0F,
                        2.0F,
                        false
                     );
                  }
               }
            }
         }

         immediatesourceentity.getPersistentData().putDouble("tick", immediatesourceentity.getPersistentData().getDouble("tick") + 1.0);
         if (immediatesourceentity.getPersistentData().getDouble("tick") >= 120.0) {
            AshesBlackHoleProjectileHitsLivingEntityProcedure.execute(world, x, y, z);
            if (!immediatesourceentity.level().isClientSide()) {
               immediatesourceentity.discard();
            }
         }
      }
   }
}
