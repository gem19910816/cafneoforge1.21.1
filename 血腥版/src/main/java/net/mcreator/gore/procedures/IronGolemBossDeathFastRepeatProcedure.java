package net.mcreator.gore.procedures;

import net.mcreator.gore.GoreEditionMod;
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
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

public class IronGolemBossDeathFastRepeatProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         double D = 0.0;
         entity.getPersistentData()
            .putDouble(
               "D",
               (double)(
                     !((entity instanceof LivingEntity _livEntx ? _livEntx.getMaxHealth() : -1.0F) >= 100.0F)
                        ? (entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1.0F)
                        : 100.0F
                  )
                  * (Double)GoreEditionConfigurationFileConfiguration.PARTICLE_MULTIPLICATOR.get()
            );
         if (world instanceof ServerLevel _level) {
            _level.sendParticles(
               (SimpleParticleType)GoreEditionModParticleTypes.IRON_GOLEM_DEATH_DUST.get(),
               x,
               y + (Double)GoreEditionConfigurationFileConfiguration.CENTER_Y_MOB.get(),
               z,
               (int)(
                  !(entity.getPersistentData().getDouble("D") > GoreEditionConfigurationFileConfiguration.MAX_DEATH_PARTICLES_AMOUNT.get())
                     ? entity.getPersistentData().getDouble("D")
                     : (Double)GoreEditionConfigurationFileConfiguration.MAX_DEATH_PARTICLES_AMOUNT.get()
               ),
               (Double)GoreEditionConfigurationFileConfiguration.XZ_AREA_SIZE.get(),
               (Double)GoreEditionConfigurationFileConfiguration.Y_AREA_SIZE.get(),
               (Double)GoreEditionConfigurationFileConfiguration.XZ_AREA_SIZE.get(),
               (Double)GoreEditionConfigurationFileConfiguration.PARTICLE_SPEED_MULTIPLICATOR.get() * 2.0
            );
         }

         if (world instanceof ServerLevel _level) {
            _level.sendParticles(
               (SimpleParticleType)GoreEditionModParticleTypes.IRON_GOLEM_50_DUST.get(),
               x,
               y + (Double)GoreEditionConfigurationFileConfiguration.CENTER_Y_MOB.get(),
               z,
               (int)(
                  !(entity.getPersistentData().getDouble("D") > GoreEditionConfigurationFileConfiguration.MAX_DEATH_PARTICLES_AMOUNT.get())
                     ? entity.getPersistentData().getDouble("D")
                     : (Double)GoreEditionConfigurationFileConfiguration.MAX_DEATH_PARTICLES_AMOUNT.get()
               ),
               (Double)GoreEditionConfigurationFileConfiguration.XZ_AREA_SIZE.get(),
               (Double)GoreEditionConfigurationFileConfiguration.Y_AREA_SIZE.get(),
               (Double)GoreEditionConfigurationFileConfiguration.XZ_AREA_SIZE.get(),
               (Double)GoreEditionConfigurationFileConfiguration.PARTICLE_SPEED_MULTIPLICATOR.get() * 3.0
            );
         }

         if (world instanceof ServerLevel _level) {
            _level.sendParticles(
               (SimpleParticleType)GoreEditionModParticleTypes.IRON_GOLEM_DUST_DROPS.get(),
               x,
               y + (Double)GoreEditionConfigurationFileConfiguration.CENTER_Y_MOB.get(),
               z,
               (int)(
                  !(entity.getPersistentData().getDouble("D") > GoreEditionConfigurationFileConfiguration.MAX_DEATH_PARTICLES_AMOUNT.get())
                     ? entity.getPersistentData().getDouble("D") * 2.0
                     : (Double)GoreEditionConfigurationFileConfiguration.MAX_DEATH_PARTICLES_AMOUNT.get() * 2.0
               ),
               (Double)GoreEditionConfigurationFileConfiguration.XZ_AREA_SIZE.get(),
               (Double)GoreEditionConfigurationFileConfiguration.Y_AREA_SIZE.get(),
               (Double)GoreEditionConfigurationFileConfiguration.XZ_AREA_SIZE.get(),
               (Double)GoreEditionConfigurationFileConfiguration.PARTICLE_SPEED_MULTIPLICATOR.get()
            );
         }

         if (world instanceof Level _level) {
            if (!_level.isClientSide()) {
               _level.playSound(
                  null,
                  BlockPos.containing(x, y, z),
                  (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore.iron_golem_death_sound")),
                  SoundSource.AMBIENT,
                  (float)((Double)GoreEditionSoundsConfigurationConfiguration.GORE_IRON_GOLEM_DEATH_SOUND.get()).doubleValue(),
                  1.0F
               );
            } else {
               _level.playLocalSound(
                  x,
                  y,
                  z,
                  (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore.iron_golem_death_sound")),
                  SoundSource.AMBIENT,
                  (float)((Double)GoreEditionSoundsConfigurationConfiguration.GORE_IRON_GOLEM_DEATH_SOUND.get()).doubleValue(),
                  1.0F,
                  false
               );
            }
         }

         GoreEditionMod.queueServerWork(
            5,
            () -> {
               if (world instanceof ServerLevel _levelxxxx) {
                  _levelxxxx.sendParticles(
                     (SimpleParticleType)GoreEditionModParticleTypes.IRON_GOLEM_DEATH_DUST.get(),
                     x,
                     y + (Double)GoreEditionConfigurationFileConfiguration.CENTER_Y_MOB.get(),
                     z,
                     (int)(
                        !(entity.getPersistentData().getDouble("D") > GoreEditionConfigurationFileConfiguration.MAX_DEATH_PARTICLES_AMOUNT.get())
                           ? entity.getPersistentData().getDouble("D")
                           : (Double)GoreEditionConfigurationFileConfiguration.MAX_DEATH_PARTICLES_AMOUNT.get()
                     ),
                     (Double)GoreEditionConfigurationFileConfiguration.XZ_AREA_SIZE.get(),
                     (Double)GoreEditionConfigurationFileConfiguration.Y_AREA_SIZE.get(),
                     (Double)GoreEditionConfigurationFileConfiguration.XZ_AREA_SIZE.get(),
                     (Double)GoreEditionConfigurationFileConfiguration.PARTICLE_SPEED_MULTIPLICATOR.get() * 2.0
                  );
               }

               if (world instanceof ServerLevel _levelxxx) {
                  _levelxxx.sendParticles(
                     (SimpleParticleType)GoreEditionModParticleTypes.IRON_GOLEM_50_DUST.get(),
                     x,
                     y + (Double)GoreEditionConfigurationFileConfiguration.CENTER_Y_MOB.get(),
                     z,
                     (int)(
                        !(entity.getPersistentData().getDouble("D") > GoreEditionConfigurationFileConfiguration.MAX_DEATH_PARTICLES_AMOUNT.get())
                           ? entity.getPersistentData().getDouble("D")
                           : (Double)GoreEditionConfigurationFileConfiguration.MAX_DEATH_PARTICLES_AMOUNT.get()
                     ),
                     (Double)GoreEditionConfigurationFileConfiguration.XZ_AREA_SIZE.get(),
                     (Double)GoreEditionConfigurationFileConfiguration.Y_AREA_SIZE.get(),
                     (Double)GoreEditionConfigurationFileConfiguration.XZ_AREA_SIZE.get(),
                     (Double)GoreEditionConfigurationFileConfiguration.PARTICLE_SPEED_MULTIPLICATOR.get() * 3.0
                  );
               }

               if (world instanceof ServerLevel _levelxx) {
                  _levelxx.sendParticles(
                     (SimpleParticleType)GoreEditionModParticleTypes.IRON_GOLEM_DUST_DROPS.get(),
                     x,
                     y + (Double)GoreEditionConfigurationFileConfiguration.CENTER_Y_MOB.get(),
                     z,
                     (int)(
                        !(entity.getPersistentData().getDouble("D") > GoreEditionConfigurationFileConfiguration.MAX_DEATH_PARTICLES_AMOUNT.get())
                           ? entity.getPersistentData().getDouble("D") * 2.0
                           : (Double)GoreEditionConfigurationFileConfiguration.MAX_DEATH_PARTICLES_AMOUNT.get() * 2.0
                     ),
                     (Double)GoreEditionConfigurationFileConfiguration.XZ_AREA_SIZE.get(),
                     (Double)GoreEditionConfigurationFileConfiguration.Y_AREA_SIZE.get(),
                     (Double)GoreEditionConfigurationFileConfiguration.XZ_AREA_SIZE.get(),
                     (Double)GoreEditionConfigurationFileConfiguration.PARTICLE_SPEED_MULTIPLICATOR.get()
                  );
               }

               if (world instanceof Level _levelx) {
                  if (!_levelx.isClientSide()) {
                     _levelx.playSound(
                        null,
                        BlockPos.containing(x, y, z),
                        (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore.iron_golem_death_sound")),
                        SoundSource.AMBIENT,
                        (float)((Double)GoreEditionSoundsConfigurationConfiguration.GORE_IRON_GOLEM_DEATH_SOUND.get()).doubleValue(),
                        1.0F
                     );
                  } else {
                     _levelx.playLocalSound(
                        x,
                        y,
                        z,
                        (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore.iron_golem_death_sound")),
                        SoundSource.AMBIENT,
                        (float)((Double)GoreEditionSoundsConfigurationConfiguration.GORE_IRON_GOLEM_DEATH_SOUND.get()).doubleValue(),
                        1.0F,
                        false
                     );
                  }
               }
            }
         );
         GoreEditionMod.queueServerWork(
            10,
            () -> {
               if (world instanceof ServerLevel _levelxxxx) {
                  _levelxxxx.sendParticles(
                     (SimpleParticleType)GoreEditionModParticleTypes.IRON_GOLEM_DEATH_DUST.get(),
                     x,
                     y + (Double)GoreEditionConfigurationFileConfiguration.CENTER_Y_MOB.get(),
                     z,
                     (int)(
                        !(entity.getPersistentData().getDouble("D") > GoreEditionConfigurationFileConfiguration.MAX_DEATH_PARTICLES_AMOUNT.get())
                           ? entity.getPersistentData().getDouble("D")
                           : (Double)GoreEditionConfigurationFileConfiguration.MAX_DEATH_PARTICLES_AMOUNT.get()
                     ),
                     (Double)GoreEditionConfigurationFileConfiguration.XZ_AREA_SIZE.get(),
                     (Double)GoreEditionConfigurationFileConfiguration.Y_AREA_SIZE.get(),
                     (Double)GoreEditionConfigurationFileConfiguration.XZ_AREA_SIZE.get(),
                     (Double)GoreEditionConfigurationFileConfiguration.PARTICLE_SPEED_MULTIPLICATOR.get() * 2.0
                  );
               }

               if (world instanceof ServerLevel _levelxxx) {
                  _levelxxx.sendParticles(
                     (SimpleParticleType)GoreEditionModParticleTypes.IRON_GOLEM_50_DUST.get(),
                     x,
                     y + (Double)GoreEditionConfigurationFileConfiguration.CENTER_Y_MOB.get(),
                     z,
                     (int)(
                        !(entity.getPersistentData().getDouble("D") > GoreEditionConfigurationFileConfiguration.MAX_DEATH_PARTICLES_AMOUNT.get())
                           ? entity.getPersistentData().getDouble("D")
                           : (Double)GoreEditionConfigurationFileConfiguration.MAX_DEATH_PARTICLES_AMOUNT.get()
                     ),
                     (Double)GoreEditionConfigurationFileConfiguration.XZ_AREA_SIZE.get(),
                     (Double)GoreEditionConfigurationFileConfiguration.Y_AREA_SIZE.get(),
                     (Double)GoreEditionConfigurationFileConfiguration.XZ_AREA_SIZE.get(),
                     (Double)GoreEditionConfigurationFileConfiguration.PARTICLE_SPEED_MULTIPLICATOR.get() * 3.0
                  );
               }

               if (world instanceof ServerLevel _levelxx) {
                  _levelxx.sendParticles(
                     (SimpleParticleType)GoreEditionModParticleTypes.IRON_GOLEM_DUST_DROPS.get(),
                     x,
                     y + (Double)GoreEditionConfigurationFileConfiguration.CENTER_Y_MOB.get(),
                     z,
                     (int)(
                        !(entity.getPersistentData().getDouble("D") > GoreEditionConfigurationFileConfiguration.MAX_DEATH_PARTICLES_AMOUNT.get())
                           ? entity.getPersistentData().getDouble("D") * 2.0
                           : (Double)GoreEditionConfigurationFileConfiguration.MAX_DEATH_PARTICLES_AMOUNT.get() * 2.0
                     ),
                     (Double)GoreEditionConfigurationFileConfiguration.XZ_AREA_SIZE.get(),
                     (Double)GoreEditionConfigurationFileConfiguration.Y_AREA_SIZE.get(),
                     (Double)GoreEditionConfigurationFileConfiguration.XZ_AREA_SIZE.get(),
                     (Double)GoreEditionConfigurationFileConfiguration.PARTICLE_SPEED_MULTIPLICATOR.get()
                  );
               }

               if (world instanceof Level _levelx) {
                  if (!_levelx.isClientSide()) {
                     _levelx.playSound(
                        null,
                        BlockPos.containing(x, y, z),
                        (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore.iron_golem_death_sound")),
                        SoundSource.AMBIENT,
                        (float)((Double)GoreEditionSoundsConfigurationConfiguration.GORE_IRON_GOLEM_DEATH_SOUND.get()).doubleValue(),
                        1.2F
                     );
                  } else {
                     _levelx.playLocalSound(
                        x,
                        y,
                        z,
                        (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore.iron_golem_death_sound")),
                        SoundSource.AMBIENT,
                        (float)((Double)GoreEditionSoundsConfigurationConfiguration.GORE_IRON_GOLEM_DEATH_SOUND.get()).doubleValue(),
                        1.2F,
                        false
                     );
                  }
               }
            }
         );
         GoreEditionMod.queueServerWork(
            15,
            () -> {
               if (world instanceof ServerLevel _levelxxxx) {
                  _levelxxxx.sendParticles(
                     (SimpleParticleType)GoreEditionModParticleTypes.IRON_GOLEM_DEATH_DUST.get(),
                     x,
                     y + (Double)GoreEditionConfigurationFileConfiguration.CENTER_Y_MOB.get(),
                     z,
                     (int)(
                        !(entity.getPersistentData().getDouble("D") > GoreEditionConfigurationFileConfiguration.MAX_DEATH_PARTICLES_AMOUNT.get())
                           ? entity.getPersistentData().getDouble("D")
                           : (Double)GoreEditionConfigurationFileConfiguration.MAX_DEATH_PARTICLES_AMOUNT.get()
                     ),
                     (Double)GoreEditionConfigurationFileConfiguration.XZ_AREA_SIZE.get(),
                     (Double)GoreEditionConfigurationFileConfiguration.Y_AREA_SIZE.get(),
                     (Double)GoreEditionConfigurationFileConfiguration.XZ_AREA_SIZE.get(),
                     (Double)GoreEditionConfigurationFileConfiguration.PARTICLE_SPEED_MULTIPLICATOR.get() * 2.0
                  );
               }

               if (world instanceof ServerLevel _levelxxx) {
                  _levelxxx.sendParticles(
                     (SimpleParticleType)GoreEditionModParticleTypes.IRON_GOLEM_50_DUST.get(),
                     x,
                     y + (Double)GoreEditionConfigurationFileConfiguration.CENTER_Y_MOB.get(),
                     z,
                     (int)(
                        !(entity.getPersistentData().getDouble("D") > GoreEditionConfigurationFileConfiguration.MAX_DEATH_PARTICLES_AMOUNT.get())
                           ? entity.getPersistentData().getDouble("D")
                           : (Double)GoreEditionConfigurationFileConfiguration.MAX_DEATH_PARTICLES_AMOUNT.get()
                     ),
                     (Double)GoreEditionConfigurationFileConfiguration.XZ_AREA_SIZE.get(),
                     (Double)GoreEditionConfigurationFileConfiguration.Y_AREA_SIZE.get(),
                     (Double)GoreEditionConfigurationFileConfiguration.XZ_AREA_SIZE.get(),
                     (Double)GoreEditionConfigurationFileConfiguration.PARTICLE_SPEED_MULTIPLICATOR.get() * 3.0
                  );
               }

               if (world instanceof ServerLevel _levelxx) {
                  _levelxx.sendParticles(
                     (SimpleParticleType)GoreEditionModParticleTypes.IRON_GOLEM_DUST_DROPS.get(),
                     x,
                     y + (Double)GoreEditionConfigurationFileConfiguration.CENTER_Y_MOB.get(),
                     z,
                     (int)(
                        !(entity.getPersistentData().getDouble("D") > GoreEditionConfigurationFileConfiguration.MAX_DEATH_PARTICLES_AMOUNT.get())
                           ? entity.getPersistentData().getDouble("D") * 2.0
                           : (Double)GoreEditionConfigurationFileConfiguration.MAX_DEATH_PARTICLES_AMOUNT.get() * 2.0
                     ),
                     (Double)GoreEditionConfigurationFileConfiguration.XZ_AREA_SIZE.get(),
                     (Double)GoreEditionConfigurationFileConfiguration.Y_AREA_SIZE.get(),
                     (Double)GoreEditionConfigurationFileConfiguration.XZ_AREA_SIZE.get(),
                     (Double)GoreEditionConfigurationFileConfiguration.PARTICLE_SPEED_MULTIPLICATOR.get()
                  );
               }

               if (world instanceof Level _levelx) {
                  if (!_levelx.isClientSide()) {
                     _levelx.playSound(
                        null,
                        BlockPos.containing(x, y, z),
                        (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore.iron_golem_death_sound")),
                        SoundSource.AMBIENT,
                        (float)((Double)GoreEditionSoundsConfigurationConfiguration.GORE_IRON_GOLEM_DEATH_SOUND.get()).doubleValue(),
                        1.4F
                     );
                  } else {
                     _levelx.playLocalSound(
                        x,
                        y,
                        z,
                        (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore.iron_golem_death_sound")),
                        SoundSource.AMBIENT,
                        (float)((Double)GoreEditionSoundsConfigurationConfiguration.GORE_IRON_GOLEM_DEATH_SOUND.get()).doubleValue(),
                        1.4F,
                        false
                     );
                  }
               }
            }
         );
         GoreEditionMod.queueServerWork(
            20,
            () -> {
               if (world instanceof ServerLevel _levelxxxx) {
                  _levelxxxx.sendParticles(
                     (SimpleParticleType)GoreEditionModParticleTypes.IRON_GOLEM_DEATH_DUST.get(),
                     x,
                     y + (Double)GoreEditionConfigurationFileConfiguration.CENTER_Y_MOB.get(),
                     z,
                     (int)(
                        !(entity.getPersistentData().getDouble("D") > GoreEditionConfigurationFileConfiguration.MAX_DEATH_PARTICLES_AMOUNT.get())
                           ? entity.getPersistentData().getDouble("D")
                           : (Double)GoreEditionConfigurationFileConfiguration.MAX_DEATH_PARTICLES_AMOUNT.get()
                     ),
                     (Double)GoreEditionConfigurationFileConfiguration.XZ_AREA_SIZE.get(),
                     (Double)GoreEditionConfigurationFileConfiguration.Y_AREA_SIZE.get(),
                     (Double)GoreEditionConfigurationFileConfiguration.XZ_AREA_SIZE.get(),
                     (Double)GoreEditionConfigurationFileConfiguration.PARTICLE_SPEED_MULTIPLICATOR.get() * 2.0
                  );
               }

               if (world instanceof ServerLevel _levelxxx) {
                  _levelxxx.sendParticles(
                     (SimpleParticleType)GoreEditionModParticleTypes.IRON_GOLEM_50_DUST.get(),
                     x,
                     y + (Double)GoreEditionConfigurationFileConfiguration.CENTER_Y_MOB.get(),
                     z,
                     (int)(
                        !(entity.getPersistentData().getDouble("D") > GoreEditionConfigurationFileConfiguration.MAX_DEATH_PARTICLES_AMOUNT.get())
                           ? entity.getPersistentData().getDouble("D")
                           : (Double)GoreEditionConfigurationFileConfiguration.MAX_DEATH_PARTICLES_AMOUNT.get()
                     ),
                     (Double)GoreEditionConfigurationFileConfiguration.XZ_AREA_SIZE.get(),
                     (Double)GoreEditionConfigurationFileConfiguration.Y_AREA_SIZE.get(),
                     (Double)GoreEditionConfigurationFileConfiguration.XZ_AREA_SIZE.get(),
                     (Double)GoreEditionConfigurationFileConfiguration.PARTICLE_SPEED_MULTIPLICATOR.get() * 3.0
                  );
               }

               if (world instanceof ServerLevel _levelxx) {
                  _levelxx.sendParticles(
                     (SimpleParticleType)GoreEditionModParticleTypes.IRON_GOLEM_DUST_DROPS.get(),
                     x,
                     y + (Double)GoreEditionConfigurationFileConfiguration.CENTER_Y_MOB.get(),
                     z,
                     (int)(
                        !(entity.getPersistentData().getDouble("D") > GoreEditionConfigurationFileConfiguration.MAX_DEATH_PARTICLES_AMOUNT.get())
                           ? entity.getPersistentData().getDouble("D") * 2.0
                           : (Double)GoreEditionConfigurationFileConfiguration.MAX_DEATH_PARTICLES_AMOUNT.get() * 2.0
                     ),
                     (Double)GoreEditionConfigurationFileConfiguration.XZ_AREA_SIZE.get(),
                     (Double)GoreEditionConfigurationFileConfiguration.Y_AREA_SIZE.get(),
                     (Double)GoreEditionConfigurationFileConfiguration.XZ_AREA_SIZE.get(),
                     (Double)GoreEditionConfigurationFileConfiguration.PARTICLE_SPEED_MULTIPLICATOR.get()
                  );
               }

               if (world instanceof Level _levelx) {
                  if (!_levelx.isClientSide()) {
                     _levelx.playSound(
                        null,
                        BlockPos.containing(x, y, z),
                        (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore.iron_golem_death_sound")),
                        SoundSource.AMBIENT,
                        (float)((Double)GoreEditionSoundsConfigurationConfiguration.GORE_IRON_GOLEM_DEATH_SOUND.get()).doubleValue(),
                        1.6F
                     );
                  } else {
                     _levelx.playLocalSound(
                        x,
                        y,
                        z,
                        (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore.iron_golem_death_sound")),
                        SoundSource.AMBIENT,
                        (float)((Double)GoreEditionSoundsConfigurationConfiguration.GORE_IRON_GOLEM_DEATH_SOUND.get()).doubleValue(),
                        1.6F,
                        false
                     );
                  }
               }
            }
         );
         GoreEditionMod.queueServerWork(
            25,
            () -> {
               if (world instanceof ServerLevel _levelxxxx) {
                  _levelxxxx.sendParticles(
                     (SimpleParticleType)GoreEditionModParticleTypes.IRON_GOLEM_DEATH_DUST.get(),
                     x,
                     y + (Double)GoreEditionConfigurationFileConfiguration.CENTER_Y_MOB.get(),
                     z,
                     (int)(
                        !(entity.getPersistentData().getDouble("D") > GoreEditionConfigurationFileConfiguration.MAX_DEATH_PARTICLES_AMOUNT.get())
                           ? entity.getPersistentData().getDouble("D")
                           : (Double)GoreEditionConfigurationFileConfiguration.MAX_DEATH_PARTICLES_AMOUNT.get()
                     ),
                     (Double)GoreEditionConfigurationFileConfiguration.XZ_AREA_SIZE.get(),
                     (Double)GoreEditionConfigurationFileConfiguration.Y_AREA_SIZE.get(),
                     (Double)GoreEditionConfigurationFileConfiguration.XZ_AREA_SIZE.get(),
                     (Double)GoreEditionConfigurationFileConfiguration.PARTICLE_SPEED_MULTIPLICATOR.get() * 2.0
                  );
               }

               if (world instanceof ServerLevel _levelxxx) {
                  _levelxxx.sendParticles(
                     (SimpleParticleType)GoreEditionModParticleTypes.IRON_GOLEM_50_DUST.get(),
                     x,
                     y + (Double)GoreEditionConfigurationFileConfiguration.CENTER_Y_MOB.get(),
                     z,
                     (int)(
                        !(entity.getPersistentData().getDouble("D") > GoreEditionConfigurationFileConfiguration.MAX_DEATH_PARTICLES_AMOUNT.get())
                           ? entity.getPersistentData().getDouble("D")
                           : (Double)GoreEditionConfigurationFileConfiguration.MAX_DEATH_PARTICLES_AMOUNT.get()
                     ),
                     (Double)GoreEditionConfigurationFileConfiguration.XZ_AREA_SIZE.get(),
                     (Double)GoreEditionConfigurationFileConfiguration.Y_AREA_SIZE.get(),
                     (Double)GoreEditionConfigurationFileConfiguration.XZ_AREA_SIZE.get(),
                     (Double)GoreEditionConfigurationFileConfiguration.PARTICLE_SPEED_MULTIPLICATOR.get() * 3.0
                  );
               }

               if (world instanceof ServerLevel _levelxx) {
                  _levelxx.sendParticles(
                     (SimpleParticleType)GoreEditionModParticleTypes.IRON_GOLEM_DUST_DROPS.get(),
                     x,
                     y + (Double)GoreEditionConfigurationFileConfiguration.CENTER_Y_MOB.get(),
                     z,
                     (int)(
                        !(entity.getPersistentData().getDouble("D") > GoreEditionConfigurationFileConfiguration.MAX_DEATH_PARTICLES_AMOUNT.get())
                           ? entity.getPersistentData().getDouble("D") * 2.0
                           : (Double)GoreEditionConfigurationFileConfiguration.MAX_DEATH_PARTICLES_AMOUNT.get() * 2.0
                     ),
                     (Double)GoreEditionConfigurationFileConfiguration.XZ_AREA_SIZE.get(),
                     (Double)GoreEditionConfigurationFileConfiguration.Y_AREA_SIZE.get(),
                     (Double)GoreEditionConfigurationFileConfiguration.XZ_AREA_SIZE.get(),
                     (Double)GoreEditionConfigurationFileConfiguration.PARTICLE_SPEED_MULTIPLICATOR.get()
                  );
               }

               if (world instanceof Level _levelx) {
                  if (!_levelx.isClientSide()) {
                     _levelx.playSound(
                        null,
                        BlockPos.containing(x, y, z),
                        (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore.iron_golem_death_sound")),
                        SoundSource.AMBIENT,
                        (float)((Double)GoreEditionSoundsConfigurationConfiguration.GORE_IRON_GOLEM_DEATH_SOUND.get()).doubleValue(),
                        1.8F
                     );
                  } else {
                     _levelx.playLocalSound(
                        x,
                        y,
                        z,
                        (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore.iron_golem_death_sound")),
                        SoundSource.AMBIENT,
                        (float)((Double)GoreEditionSoundsConfigurationConfiguration.GORE_IRON_GOLEM_DEATH_SOUND.get()).doubleValue(),
                        1.8F,
                        false
                     );
                  }
               }
            }
         );
         GoreEditionMod.queueServerWork(
            30,
            () -> {
               if (world instanceof ServerLevel _levelxxxx) {
                  _levelxxxx.sendParticles(
                     (SimpleParticleType)GoreEditionModParticleTypes.IRON_GOLEM_DEATH_DUST.get(),
                     x,
                     y + (Double)GoreEditionConfigurationFileConfiguration.CENTER_Y_MOB.get(),
                     z,
                     (int)(
                        !(entity.getPersistentData().getDouble("D") > GoreEditionConfigurationFileConfiguration.MAX_DEATH_PARTICLES_AMOUNT.get())
                           ? entity.getPersistentData().getDouble("D")
                           : (Double)GoreEditionConfigurationFileConfiguration.MAX_DEATH_PARTICLES_AMOUNT.get()
                     ),
                     (Double)GoreEditionConfigurationFileConfiguration.XZ_AREA_SIZE.get(),
                     (Double)GoreEditionConfigurationFileConfiguration.Y_AREA_SIZE.get(),
                     (Double)GoreEditionConfigurationFileConfiguration.XZ_AREA_SIZE.get(),
                     (Double)GoreEditionConfigurationFileConfiguration.PARTICLE_SPEED_MULTIPLICATOR.get() * 2.0
                  );
               }

               if (world instanceof ServerLevel _levelxxx) {
                  _levelxxx.sendParticles(
                     (SimpleParticleType)GoreEditionModParticleTypes.IRON_GOLEM_50_DUST.get(),
                     x,
                     y + (Double)GoreEditionConfigurationFileConfiguration.CENTER_Y_MOB.get(),
                     z,
                     (int)(
                        !(entity.getPersistentData().getDouble("D") > GoreEditionConfigurationFileConfiguration.MAX_DEATH_PARTICLES_AMOUNT.get())
                           ? entity.getPersistentData().getDouble("D")
                           : (Double)GoreEditionConfigurationFileConfiguration.MAX_DEATH_PARTICLES_AMOUNT.get()
                     ),
                     (Double)GoreEditionConfigurationFileConfiguration.XZ_AREA_SIZE.get(),
                     (Double)GoreEditionConfigurationFileConfiguration.Y_AREA_SIZE.get(),
                     (Double)GoreEditionConfigurationFileConfiguration.XZ_AREA_SIZE.get(),
                     (Double)GoreEditionConfigurationFileConfiguration.PARTICLE_SPEED_MULTIPLICATOR.get() * 3.0
                  );
               }

               if (world instanceof ServerLevel _levelxx) {
                  _levelxx.sendParticles(
                     (SimpleParticleType)GoreEditionModParticleTypes.IRON_GOLEM_DUST_DROPS.get(),
                     x,
                     y + (Double)GoreEditionConfigurationFileConfiguration.CENTER_Y_MOB.get(),
                     z,
                     (int)(
                        !(entity.getPersistentData().getDouble("D") > GoreEditionConfigurationFileConfiguration.MAX_DEATH_PARTICLES_AMOUNT.get())
                           ? entity.getPersistentData().getDouble("D") * 2.0
                           : (Double)GoreEditionConfigurationFileConfiguration.MAX_DEATH_PARTICLES_AMOUNT.get() * 2.0
                     ),
                     (Double)GoreEditionConfigurationFileConfiguration.XZ_AREA_SIZE.get(),
                     (Double)GoreEditionConfigurationFileConfiguration.Y_AREA_SIZE.get(),
                     (Double)GoreEditionConfigurationFileConfiguration.XZ_AREA_SIZE.get(),
                     (Double)GoreEditionConfigurationFileConfiguration.PARTICLE_SPEED_MULTIPLICATOR.get()
                  );
               }

               if (world instanceof Level _levelx) {
                  if (!_levelx.isClientSide()) {
                     _levelx.playSound(
                        null,
                        BlockPos.containing(x, y, z),
                        (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore.iron_golem_death_sound")),
                        SoundSource.AMBIENT,
                        (float)((Double)GoreEditionSoundsConfigurationConfiguration.GORE_IRON_GOLEM_DEATH_SOUND.get()).doubleValue(),
                        2.0F
                     );
                  } else {
                     _levelx.playLocalSound(
                        x,
                        y,
                        z,
                        (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore.iron_golem_death_sound")),
                        SoundSource.AMBIENT,
                        (float)((Double)GoreEditionSoundsConfigurationConfiguration.GORE_IRON_GOLEM_DEATH_SOUND.get()).doubleValue(),
                        2.0F,
                        false
                     );
                  }
               }
            }
         );
      }
   }
}
