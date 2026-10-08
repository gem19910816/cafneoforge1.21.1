package net.mcreator.gore.procedures;

import java.util.Comparator;
import net.mcreator.gore.entity.NowindEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level.ExplosionInteraction;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class NowindOnEntityTickUpdateProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         entity.getPersistentData().putDouble("distance", 13.0);
         AshesNativesSetAttackTargetProcedure.execute(world, x, y, z, entity);
         if ((entity instanceof Mob _mobEntxxx ? _mobEntxxx.getTarget() : null) != null
            && (entity instanceof Mob _mobEntxx ? _mobEntxx.getTarget() : null) instanceof LivingEntity
            && (
               (new Object() {
                        public boolean checkGamemode(Entity _ent) {
                           if (_ent instanceof ServerPlayer _serverPlayer) {
                              return _serverPlayer.gameMode.getGameModeForPlayer() == GameType.CREATIVE;
                           } else {
                              return _ent.level().isClientSide() && _ent instanceof Player _player
                                 ? Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()) != null
                                    && Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()).getGameMode()
                                       == GameType.CREATIVE
                                 : false;
                           }
                        }
                     })
                     .checkGamemode(entity instanceof Mob _mobEntx ? _mobEntx.getTarget() : null)
                  || (new Object() {
                        public boolean checkGamemode(Entity _ent) {
                           if (_ent instanceof ServerPlayer _serverPlayer) {
                              return _serverPlayer.gameMode.getGameModeForPlayer() == GameType.SPECTATOR;
                           } else {
                              return _ent.level().isClientSide() && _ent instanceof Player _player
                                 ? Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()) != null
                                    && Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()).getGameMode()
                                       == GameType.SPECTATOR
                                 : false;
                           }
                        }
                     })
                     .checkGamemode(entity instanceof Mob _mobEnt ? _mobEnt.getTarget() : null)
            )) {
            Vec3 _center = new Vec3(x, y, z);

            for (Entity entityiterator : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(64.0), e -> true)
               .stream()
               .sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center)))
               .toList()) {
               if (entityiterator instanceof NowindEntity && entityiterator instanceof LivingEntity && entityiterator instanceof Mob) {
                  Mob _entity = (Mob)entityiterator;
                  LivingEntity var18 = entity instanceof Mob _mobEntxxxx ? _mobEntxxxx.getTarget() : null;
                  if (var18 instanceof LivingEntity) {
                     _entity.setTarget(var18);
                  }
               }
            }
         }

         if ((entity instanceof Mob _mobEntx ? _mobEntx.getTarget() : null) != null
            && (entity instanceof Mob _mobEnt ? _mobEnt.getTarget() : null) instanceof LivingEntity) {
            Vec3 _centerA = new Vec3(x, y, z);

            for (Entity entityiteratorx : world.getEntitiesOfClass(Entity.class, new AABB(_centerA, _centerA).inflate(2.0), e -> true)
               .stream()
               .sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_centerA)))
               .toList()) {
               if (entityiteratorx == (entity instanceof Mob _mobEntxx ? _mobEntxx.getTarget() : null)
                  && !entityiteratorx.getPersistentData().getBoolean("wait")) {
                  entity.getPersistentData().putBoolean("explode", true);
               }
            }

            Vec3 _centerB = new Vec3(x, y, z);

            for (Entity entityiteratorxx : world.getEntitiesOfClass(Entity.class, new AABB(_centerB, _centerB).inflate(2.0), e -> true)
               .stream()
               .sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_centerB)))
               .toList()) {
               if (entityiteratorxx instanceof NowindEntity && entityiteratorxx != entity && entityiteratorxx instanceof LivingEntity) {
                  if (entityiteratorxx.getPersistentData().getBoolean("explode")) {
                     entity.getPersistentData().putBoolean("wait", true);
                  } else {
                     entity.getPersistentData().putBoolean("wait", false);
                  }
               }
            }
         }

         if (entity.getPersistentData().getBoolean("explode")) {
            if (entity.getPersistentData().getDouble("tick") == 0.0) {
               if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
                  _entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 110, 254, false, false));
               }

               if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
                  _entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 110, 254, false, false));
               }

               if (world instanceof Level _level) {
                  if (!_level.isClientSide()) {
                     _level.playSound(
                        null,
                        BlockPos.containing(x, y, z),
                        (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:entity.nowind_preparing")),
                        SoundSource.HOSTILE,
                        3.0F,
                        2.0F
                     );
                  } else {
                     _level.playLocalSound(
                        x,
                        y,
                        z,
                        (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:entity.nowind_preparing")),
                        SoundSource.HOSTILE,
                        3.0F,
                        2.0F,
                        false
                     );
                  }
               }
            }

            if (entity instanceof LivingEntity) {
               entity.getPersistentData().putDouble("tick", entity.getPersistentData().getDouble("tick") + 1.0);
            }

            if (entity.getPersistentData().getDouble("tick") == 40.0 && world instanceof Level _levelx) {
               if (!_levelx.isClientSide()) {
                  _levelx.playSound(
                     null,
                     BlockPos.containing(x, y, z),
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:entity.nowind_explode")),
                     SoundSource.HOSTILE,
                     3.0F,
                     2.0F
                  );
               } else {
                  _levelx.playLocalSound(
                     x,
                     y,
                     z,
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:entity.nowind_explode")),
                     SoundSource.HOSTILE,
                     3.0F,
                     2.0F,
                     false
                  );
               }
            }

            if (entity.getPersistentData().getDouble("tick") == 50.0) {
               if (entity.isAlive()) {
                  if (!entity.level().isClientSide()) {
                     entity.discard();
                  }

                  if (world instanceof Level _levelxx && !_levelxx.isClientSide()) {
                     _levelxx.explode(null, x, y, z, 4.0F, ExplosionInteraction.MOB);
                  }
               }

               entity.getPersistentData().putBoolean("explode", false);
               entity.getPersistentData().putDouble("tick", 0.0);
            }
         }
      }
   }
}
