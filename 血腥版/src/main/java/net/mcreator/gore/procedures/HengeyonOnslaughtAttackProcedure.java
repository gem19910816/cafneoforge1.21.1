package net.mcreator.gore.procedures;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

public class HengeyonOnslaughtAttackProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         if (entity.getPersistentData().getDouble("targetTickUpdate") == 0.0) {
            entity.getPersistentData().putDouble("target_x", (entity instanceof Mob _mobEnt ? _mobEnt.getTarget() : null).getX());
            entity.getPersistentData().putDouble("target_y", (entity instanceof Mob _mobEntx ? _mobEntx.getTarget() : null).getY());
            entity.getPersistentData().putDouble("target_z", (entity instanceof Mob _mobEntxx ? _mobEntxx.getTarget() : null).getZ());
            if (world instanceof Level _level) {
               if (!_level.isClientSide()) {
                  _level.playSound(
                     null,
                     BlockPos.containing(x, y, z),
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:entity.hengeyon_shot")),
                     SoundSource.HOSTILE,
                     6.0F,
                     0.0F
                  );
               } else {
                  _level.playLocalSound(
                     x,
                     y,
                     z,
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:entity.hengeyon_shot")),
                     SoundSource.HOSTILE,
                     6.0F,
                     0.0F,
                     false
                  );
               }
            }
         }

         entity.getPersistentData().putDouble("targetTickUpdate", entity.getPersistentData().getDouble("targetTickUpdate") + 1.0);
         if (entity.getPersistentData().getDouble("targetTickUpdate") == 40.0) {
            entity.getPersistentData().putDouble("freezing", entity.getPersistentData().getDouble("freezing") + 220.0);
            entity.getPersistentData().putDouble("elevate_velocity", 0.2);
            entity.teleportTo(
               entity.getPersistentData().getDouble("target_x"),
               entity.getPersistentData().getDouble("target_y") - 10.0,
               entity.getPersistentData().getDouble("target_z")
            );
            if (entity instanceof ServerPlayer _serverPlayer) {
               _serverPlayer.connection
                  .teleport(
                     entity.getPersistentData().getDouble("target_x"),
                     entity.getPersistentData().getDouble("target_y") - 10.0,
                     entity.getPersistentData().getDouble("target_z"),
                     entity.getYRot(),
                     entity.getXRot()
                  );
            }
         }

         if (entity.getPersistentData().getDouble("targetTickUpdate") > 40.0) {
            entity.getPersistentData().putDouble("targetTickUpdate", 0.0);
         }
      }
   }
}
