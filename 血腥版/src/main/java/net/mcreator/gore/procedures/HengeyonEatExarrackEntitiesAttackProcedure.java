package net.mcreator.gore.procedures;

import java.util.Comparator;
import net.mcreator.gore.entity.ExarrackHydraEntity;
import net.mcreator.gore.entity.FleshEaterEntity;
import net.mcreator.gore.entity.TheExarrackMonsterEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class HengeyonEatExarrackEntitiesAttackProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         if (entity.getPersistentData().getBoolean("eat_exarrack")) {
            Vec3 _center = new Vec3(x, y, z);

            for (Entity entityiterator : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(50.0), e -> true)
               .stream()
               .sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center)))
               .toList()) {
               if (entityiterator instanceof FleshEaterEntity) {
                  entityiterator.teleportTo(
                     entityiterator.getX() + (entity.getX() - entityiterator.getX()) * 0.02,
                     entityiterator.getY() + (entity.getY() - entityiterator.getY()) * 0.068,
                     entityiterator.getZ() + (entity.getZ() - entityiterator.getZ()) * 0.02
                  );
                  if (entityiterator instanceof ServerPlayer _serverPlayer) {
                     _serverPlayer.connection
                        .teleport(
                           entityiterator.getX() + (entity.getX() - entityiterator.getX()) * 0.02,
                           entityiterator.getY() + (entity.getY() - entityiterator.getY()) * 0.068,
                           entityiterator.getZ() + (entity.getZ() - entityiterator.getZ()) * 0.02,
                           entityiterator.getYRot(),
                           entityiterator.getXRot()
                        );
                  }
               }

               if (entityiterator instanceof TheExarrackMonsterEntity) {
                  entityiterator.teleportTo(
                     entityiterator.getX() + (entity.getX() - entityiterator.getX()) * 0.02,
                     entityiterator.getY() + (entity.getY() - entityiterator.getY()) * 0.068,
                     entityiterator.getZ() + (entity.getZ() - entityiterator.getZ()) * 0.02
                  );
                  if (entityiterator instanceof ServerPlayer _serverPlayer) {
                     _serverPlayer.connection
                        .teleport(
                           entityiterator.getX() + (entity.getX() - entityiterator.getX()) * 0.02,
                           entityiterator.getY() + (entity.getY() - entityiterator.getY()) * 0.068,
                           entityiterator.getZ() + (entity.getZ() - entityiterator.getZ()) * 0.02,
                           entityiterator.getYRot(),
                           entityiterator.getXRot()
                        );
                  }
               }

               if (entityiterator instanceof ExarrackHydraEntity) {
                  entityiterator.teleportTo(
                     entityiterator.getX() + (entity.getX() - entityiterator.getX()) * 0.02,
                     entityiterator.getY() + (entity.getY() - entityiterator.getY()) * 0.068,
                     entityiterator.getZ() + (entity.getZ() - entityiterator.getZ()) * 0.02
                  );
                  if (entityiterator instanceof ServerPlayer _serverPlayer) {
                     _serverPlayer.connection
                        .teleport(
                           entityiterator.getX() + (entity.getX() - entityiterator.getX()) * 0.02,
                           entityiterator.getY() + (entity.getY() - entityiterator.getY()) * 0.068,
                           entityiterator.getZ() + (entity.getZ() - entityiterator.getZ()) * 0.02,
                           entityiterator.getYRot(),
                           entityiterator.getXRot()
                        );
                  }
               }
            }
         }

         if (entity.getPersistentData().getBoolean("regen_health")) {
            if (entity.getPersistentData().getDouble("special_timer") == 0.0) {
               entity.getPersistentData().putBoolean("eat_exarrack", true);
               if (world instanceof Level _level) {
                  if (!_level.isClientSide()) {
                     _level.playSound(
                        null,
                        BlockPos.containing(x, y, z),
                        (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.experience_orb.pickup")),
                        SoundSource.NEUTRAL,
                        15.0F,
                        0.0F
                     );
                  } else {
                     _level.playLocalSound(
                        x,
                        y,
                        z,
                        (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.experience_orb.pickup")),
                        SoundSource.NEUTRAL,
                        15.0F,
                        0.0F,
                        false
                     );
                  }
               }
            }

            entity.getPersistentData().putDouble("special_timer", entity.getPersistentData().getDouble("special_timer") + 1.0);
            if (entity.getPersistentData().getDouble("special_timer") == 300.0) {
               if (world instanceof Level _levelx) {
                  if (!_levelx.isClientSide()) {
                     _levelx.playSound(
                        null,
                        BlockPos.containing(x, y, z),
                        (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.experience_bottle.throw")),
                        SoundSource.NEUTRAL,
                        15.0F,
                        0.0F
                     );
                  } else {
                     _levelx.playLocalSound(
                        x,
                        y,
                        z,
                        (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.experience_bottle.throw")),
                        SoundSource.NEUTRAL,
                        15.0F,
                        0.0F,
                        false
                     );
                  }
               }

               entity.getPersistentData().putDouble("special_timer", 0.0);
               entity.getPersistentData().putBoolean("eat_exarrack", false);
               entity.getPersistentData().putBoolean("regen_health", false);
            }
         }
      }
   }
}
