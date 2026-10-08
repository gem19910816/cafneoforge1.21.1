package net.mcreator.gore.procedures;

import net.mcreator.gore.configuration.GoreEditionModeSettingsConfiguration;
import net.mcreator.gore.init.GoreEditionModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level.ExplosionInteraction;
import net.minecraft.world.level.block.Blocks;

public class CreeperCorpseOnEntityTickUpdateProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         double sx = 0.0;
         double sy = 0.0;
         double sz = 0.0;
         boolean found = false;
         boolean adsf = false;
         if ((Boolean)GoreEditionModeSettingsConfiguration.INSANE_THINGS.get()) {
            if ((Boolean)GoreEditionModeSettingsConfiguration.CREEPER_CORPSE_MULTIPLICATION.get()) {
               entity.getPersistentData().putDouble("TICK", entity.getPersistentData().getDouble("TICK") + 1.0);
               if (entity.getPersistentData().getDouble("TICK") == 201.0) {
                  sx = -3.0;
                  found = false;

                  for (int index0 = 0; index0 < 6; index0++) {
                     sy = -3.0;

                     for (int index1 = 0; index1 < 6; index1++) {
                        sz = -3.0;

                        for (int index2 = 0; index2 < 6; index2++) {
                           if (world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz)).getBlock() == Blocks.GRASS_BLOCK) {
                              adsf = true;
                           }

                           sz++;
                        }

                        sy++;
                     }

                     sx++;
                  }

                  if (adsf) {
                     if (world instanceof ServerLevel _level) {
                        Entity entityToSpawn = ((EntityType)GoreEditionModEntities.CREEPER_GRASS_GENERATOR.get())
                           .spawn(_level, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED);
                        if (entityToSpawn != null) {
                           entityToSpawn.setYRot(world.getRandom().nextFloat() * 360.0F);
                        }
                     }

                     if (world instanceof ServerLevel _levelx) {
                        Entity entityToSpawn = ((EntityType)GoreEditionModEntities.CREEPER_GRASS_GENERATOR.get())
                           .spawn(_levelx, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED);
                        if (entityToSpawn != null) {
                           entityToSpawn.setYRot(world.getRandom().nextFloat() * 360.0F);
                        }
                     }

                     if (world instanceof ServerLevel _levelxx) {
                        Entity entityToSpawn = ((EntityType)GoreEditionModEntities.CREEPER_GRASS_GENERATOR.get())
                           .spawn(_levelxx, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED);
                        if (entityToSpawn != null) {
                           entityToSpawn.setYRot(world.getRandom().nextFloat() * 360.0F);
                        }
                     }

                     adsf = false;
                  }
               }
            }

            if ((Boolean)GoreEditionModeSettingsConfiguration.CREEPER_CORPSE_EXPLODE_ON_HIT.get() && entity.getPersistentData().getBoolean("hurted")) {
               entity.getPersistentData().putDouble("explode_tick", entity.getPersistentData().getDouble("explode_tick") + 1.0);
               if (entity.getPersistentData().getDouble("explode_tick") == 1.0 && world instanceof Level _levelxxx) {
                  if (!_levelxxx.isClientSide()) {
                     _levelxxx.playSound(
                        null,
                        BlockPos.containing(x, y, z),
                        (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.creeper.primed")),
                        SoundSource.HOSTILE,
                        2.0F,
                        2.0F
                     );
                  } else {
                     _levelxxx.playLocalSound(
                        x,
                        y,
                        z,
                        (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.creeper.primed")),
                        SoundSource.HOSTILE,
                        2.0F,
                        2.0F,
                        false
                     );
                  }
               }

               if (entity.getPersistentData().getDouble("explode_tick") == 10.0) {
                  if (!entity.level().isClientSide()) {
                     entity.discard();
                  }

                  if (world instanceof Level _levelxxxx && !_levelxxxx.isClientSide()) {
                     _levelxxxx.explode(
                        entity,
                        new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.EXPLOSION)),
                        null,
                        x,
                        y,
                        z,
                        3.0F,
                        false,
                        ExplosionInteraction.MOB
                     );
                  }
               }
            }
         }
      }
   }
}
