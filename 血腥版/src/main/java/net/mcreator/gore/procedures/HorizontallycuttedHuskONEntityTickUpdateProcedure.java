package net.mcreator.gore.procedures;

import net.mcreator.gore.configuration.GoreEditionConfigurationFileConfiguration;
import net.mcreator.gore.configuration.GoreEditionSoundsConfigurationConfiguration;
import net.mcreator.gore.entity.HorizontallyCuttedHuskEntity;
import net.mcreator.gore.init.GoreEditionModParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

public class HorizontallycuttedHuskONEntityTickUpdateProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         if (entity.getPersistentData().getDouble("timer") == 0.0) {
            if (entity instanceof HorizontallyCuttedHuskEntity) {
               ((HorizontallyCuttedHuskEntity)entity).setAnimation("cutted_zombie.death");
            }

            if (world instanceof Level _level) {
               if (!_level.isClientSide()) {
                  _level.playSound(
                     null,
                     BlockPos.containing(x, y, z),
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:dukeplus.squished")),
                     SoundSource.AMBIENT,
                     (float)((Double)GoreEditionSoundsConfigurationConfiguration.DUKEPLUS_SQUISHED.get()).doubleValue(),
                     1.0F
                  );
               } else {
                  _level.playLocalSound(
                     x,
                     y,
                     z,
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:dukeplus.squished")),
                     SoundSource.AMBIENT,
                     (float)((Double)GoreEditionSoundsConfigurationConfiguration.DUKEPLUS_SQUISHED.get()).doubleValue(),
                     1.0F,
                     false
                  );
               }
            }
         }

         entity.getPersistentData().putDouble("timer", entity.getPersistentData().getDouble("timer") + 1.0);
         if (entity.getPersistentData().getDouble("timer") == 38.0) {
            entity.hurt(
               new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.GENERIC)),
               (entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1.0F) * 2.0F
            );
         }

         if (!entity.getPersistentData().getBoolean("bleeding") && world instanceof ServerLevel _levelx) {
            _levelx.sendParticles(
               (SimpleParticleType)GoreEditionModParticleTypes.GENERIC_BLOOD_DROPS.get(),
               x,
               y + 1.1,
               z,
               (int)Math.pow((Double)GoreEditionConfigurationFileConfiguration.PARTICLE_MULTIPLICATOR.get() * 3.0, 2.0),
               0.0,
               0.0,
               0.0,
               (Double)GoreEditionConfigurationFileConfiguration.PARTICLE_SPEED_MULTIPLICATOR.get()
            );
         }

         if (entity.getPersistentData().getDouble("bleeding_timer") == 0.0 && world instanceof Level _levelx) {
            if (!_levelx.isClientSide()) {
               _levelx.playSound(
                  null,
                  BlockPos.containing(x, y, z),
                  (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore.short_pouring_out_blood")),
                  SoundSource.NEUTRAL,
                  (float)((Double)GoreEditionSoundsConfigurationConfiguration.GORE_SHORT_POURING_OUT_BLOOD.get()).doubleValue(),
                  1.0F
               );
            } else {
               _levelx.playLocalSound(
                  x,
                  y,
                  z,
                  (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore.short_pouring_out_blood")),
                  SoundSource.NEUTRAL,
                  (float)((Double)GoreEditionSoundsConfigurationConfiguration.GORE_SHORT_POURING_OUT_BLOOD.get()).doubleValue(),
                  1.0F,
                  false
               );
            }
         }

         entity.getPersistentData().putDouble("bleeding_timer", entity.getPersistentData().getDouble("bleeding_timer") + 1.0);
         if (entity.getPersistentData().getDouble("bleeding_timer") == 22.0) {
            entity.getPersistentData().putBoolean("bleeding", true);
         }
      }
   }
}
