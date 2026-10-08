package net.mcreator.gore.procedures;

import net.mcreator.gore.GoreEditionMod;
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

public class DimensionalGapToAshesProcedureProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z) {
      if (world instanceof Level _level) {
         if (!_level.isClientSide()) {
            _level.playSound(
               null,
               BlockPos.containing(x, y, z),
               (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:ambient.ashes")),
               SoundSource.AMBIENT,
               3.0F,
               1.0F
            );
         } else {
            _level.playLocalSound(
               x,
               y,
               z,
               (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:ambient.ashes")),
               SoundSource.AMBIENT,
               3.0F,
               1.0F,
               false
            );
         }
      }

      GoreEditionMod.queueServerWork(
         100,
         () -> {
            if (world instanceof ServerLevel _levelx) {
               Entity entityToSpawn = ((EntityType)GoreEditionModEntities.ASHES_GATEWAY.get())
                  .spawn(_levelx, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED);
               if (entityToSpawn != null) {
                  entityToSpawn.setYRot(world.getRandom().nextFloat() * 360.0F);
               }
            }
         }
      );
   }
}
