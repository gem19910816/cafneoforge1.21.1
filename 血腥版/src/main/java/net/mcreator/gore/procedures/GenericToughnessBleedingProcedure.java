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

public class GenericToughnessBleedingProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         if (entity.getPersistentData().getBoolean("pouring_out_blood") && world instanceof ServerLevel _level) {
            _level.sendParticles(
               (SimpleParticleType)GoreEditionModParticleTypes.GENERIC_BLOOD_DROPS.get(),
               x,
               y + 1.2,
               z,
               (int)Math.pow((Double)GoreEditionConfigurationFileConfiguration.PARTICLE_MULTIPLICATOR.get() * 2.0, 2.0),
               0.0,
               0.2,
               0.0,
               (Double)GoreEditionConfigurationFileConfiguration.PARTICLE_SPEED_MULTIPLICATOR.get()
            );
         }

         if (entity.getPersistentData().getDouble("pouring_timer") == 0.0) {
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

            entity.getPersistentData().putBoolean("pouring_out_blood", true);
         }

         entity.getPersistentData().putDouble("pouring_timer", entity.getPersistentData().getDouble("pouring_timer") + 1.0);
         if (entity.getPersistentData().getDouble("pouring_timer") == 40.0) {
            entity.getPersistentData().putBoolean("pouring_out_blood", false);
         }

         if (entity.getPersistentData().getDouble("pouring_timer") == 200.0) {
            entity.getPersistentData().putDouble("pouring_timer", 0.0);
         }
      }
   }
}
