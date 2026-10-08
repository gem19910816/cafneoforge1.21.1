package net.mcreator.gore.procedures;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

public class HengeyonLivingSoundProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         if (entity.getPersistentData().getDouble("ambient_tick") == 0.0 && world instanceof Level _level) {
            if (!_level.isClientSide()) {
               _level.playSound(
                  null,
                  BlockPos.containing(x, y, z),
                  (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:entity.hengeyon_ambient")),
                  SoundSource.HOSTILE,
                  4.0F,
                  1.0F
               );
            } else {
               _level.playLocalSound(
                  x,
                  y,
                  z,
                  (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:entity.hengeyon_ambient")),
                  SoundSource.HOSTILE,
                  4.0F,
                  1.0F,
                  false
               );
            }
         }

         entity.getPersistentData().putDouble("ambient_tick", entity.getPersistentData().getDouble("ambient_tick") + 1.0);
         if (entity.getPersistentData().getDouble("ambient_tick") == 3.0) {
            entity.getPersistentData().putDouble("sadasd", (double)Mth.nextInt(RandomSource.create(), 0, 2));
            if (entity.getPersistentData().getDouble("sadasd") == 0.0) {
               entity.getPersistentData().putDouble("playssad", 450.0);
            }

            if (entity.getPersistentData().getDouble("sadasd") == 1.0) {
               entity.getPersistentData().putDouble("playssad", 500.0);
            }

            if (entity.getPersistentData().getDouble("sadasd") == 2.0) {
               entity.getPersistentData().putDouble("playssad", 800.0);
            }
         }

         if (entity.getPersistentData().getDouble("ambient_tick") == entity.getPersistentData().getDouble("playssad")) {
            entity.getPersistentData().putDouble("ambient_tick", 0.0);
         }
      }
   }
}
