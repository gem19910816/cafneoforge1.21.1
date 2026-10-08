package net.mcreator.gore.procedures;

import net.mcreator.gore.configuration.GoreEditionConfigurationFileConfiguration;
import net.mcreator.gore.configuration.GoreEditionSoundsConfigurationConfiguration;
import net.mcreator.gore.init.GoreEditionModParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

public class GenericToughnessBleedingLowProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         if (entity.getPersistentData().getDouble("bleeding_timer") == 0.0) {
            if (world instanceof Level _level) {
               if (!_level.isClientSide()) {
                  _level.playSound(
                     null,
                     BlockPos.containing(x, y, z),
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore.short_pouring_out_blood")),
                     SoundSource.AMBIENT,
                     (float)((Double)GoreEditionSoundsConfigurationConfiguration.GORE_SHORT_POURING_OUT_BLOOD.get()).doubleValue(),
                     1.0F
                  );
               } else {
                  _level.playLocalSound(
                     x,
                     y,
                     z,
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore.short_pouring_out_blood")),
                     SoundSource.AMBIENT,
                     (float)((Double)GoreEditionSoundsConfigurationConfiguration.GORE_SHORT_POURING_OUT_BLOOD.get()).doubleValue(),
                     1.0F,
                     false
                  );
               }
            }

            entity.getPersistentData().putBoolean("bleeding", true);
         }

         entity.getPersistentData().putDouble("bleeding_timer", entity.getPersistentData().getDouble("bleeding_timer") + 1.0);
         if (entity.getPersistentData().getBoolean("bleeding") && world instanceof ServerLevel _levelx) {
            _levelx.sendParticles(
               (SimpleParticleType)GoreEditionModParticleTypes.GENERIC_BLOOD_DROPS.get(),
               x,
               y + 0.4,
               z,
               (int)Math.pow((Double)GoreEditionConfigurationFileConfiguration.PARTICLE_MULTIPLICATOR.get() * 2.0, 2.0),
               0.3,
               0.0,
               0.3,
               (Double)GoreEditionConfigurationFileConfiguration.PARTICLE_SPEED_MULTIPLICATOR.get()
            );
         }

         if (entity.getPersistentData().getDouble("bleeding_timer") == 40.0) {
            entity.getPersistentData().putBoolean("bleeding", false);
         }

         if (entity.getPersistentData().getDouble("bleeding_timer") == 200.0) {
            entity.getPersistentData().putDouble("bleeding_timer", 0.0);
         }
      }
   }
}
