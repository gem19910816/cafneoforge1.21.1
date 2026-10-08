package net.mcreator.gore.procedures;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

public class HengeyonOnInitialEntitySpawnProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         entity.setNoGravity(true);
         entity.getPersistentData().putDouble("spawn_x", x);
         entity.getPersistentData().putDouble("spawn_y", y);
         entity.getPersistentData().putDouble("spawn_z", z);
         entity.teleportTo(x, y + -20.0, z);
         if (entity instanceof ServerPlayer _serverPlayer) {
            _serverPlayer.connection.teleport(x, y + -20.0, z, entity.getYRot(), entity.getXRot());
         }

         if (world instanceof Level _level) {
            if (!_level.isClientSide()) {
               _level.playSound(
                  null,
                  BlockPos.containing(x, y, z),
                  (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:entity.hengeyon.scream")),
                  SoundSource.HOSTILE,
                  10.0F,
                  1.0F
               );
            } else {
               _level.playLocalSound(
                  x,
                  y,
                  z,
                  (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:entity.hengeyon.scream")),
                  SoundSource.HOSTILE,
                  10.0F,
                  1.0F,
                  false
               );
            }
         }
      }
   }
}
