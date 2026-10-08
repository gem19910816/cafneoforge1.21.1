package net.mcreator.gore.procedures;

import java.util.Comparator;
import net.mcreator.gore.entity.AshesGatewayEntity;
import net.mcreator.gore.network.GoreEditionModVariables;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownEnderpearl;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.portal.DimensionTransition;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

public class AshesGatewayOnEntityTickUpdateProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         double safe_x = 0.0;
         double safe_y = 0.0;
         double safe_z = 0.0;
         if (entity.getPersistentData().getDouble("animated_on_tick") == 0.0 && entity instanceof AshesGatewayEntity animatable) {
            animatable.setTexture("ashes_portal_0001");
         }

         entity.getPersistentData().putDouble("animated_on_tick", entity.getPersistentData().getDouble("animated_on_tick") + 1.0);
         if (entity.getPersistentData().getDouble("animated_on_tick") == 4.0 && entity instanceof AshesGatewayEntity animatable) {
            animatable.setTexture("ashes_portal_0002");
         }

         if (entity.getPersistentData().getDouble("animated_on_tick") == 8.0 && entity instanceof AshesGatewayEntity animatable) {
            animatable.setTexture("ashes_portal_0003");
         }

         if (entity.getPersistentData().getDouble("animated_on_tick") == 12.0 && entity instanceof AshesGatewayEntity animatable) {
            animatable.setTexture("ashes_portal_0004");
         }

         if (entity.getPersistentData().getDouble("animated_on_tick") == 16.0) {
            entity.getPersistentData().putDouble("animated_on_tick", 0.0);
         }

         entity.getPersistentData().putDouble("despawn_tick", entity.getPersistentData().getDouble("despawn_tick") + 1.0);
         if (entity.getPersistentData().getDouble("despawn_tick") >= 1200.0) {
            entity.getPersistentData().putBoolean("despawn", true);
         }

         if (entity.getPersistentData().getBoolean("despawn")) {
            if (entity.getPersistentData().getDouble("despawning_tick") == 0.0 && entity instanceof AshesGatewayEntity) {
               ((AshesGatewayEntity)entity).setAnimation("despawn");
            }

            entity.getPersistentData().putDouble("despawning_tick", entity.getPersistentData().getDouble("despawning_tick") + 1.0);
            if (entity.getPersistentData().getDouble("despawning_tick") >= 200.0) {
               if (world instanceof ServerLevel _level) {
                  _level.getServer()
                     .getCommands()
                     .performPrefixedCommand(
                        new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null)
                           .withSuppressedOutput(),
                        "stopsound @a neutral gore_edition:portal.ashes_gateway.ambient"
                     );
               }

               if (!entity.level().isClientSide()) {
                  entity.discard();
               }
            }
         }

         Vec3 _center = new Vec3(x, y, z);

         for (Entity entityiterator : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(32.0), e -> true)
            .stream()
            .sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center)))
            .toList()) {
            if (!(new Object() {
                     public boolean checkGamemode(Entity _ent) {
                        if (_ent instanceof ServerPlayer _serverPlayer) {
                           return _serverPlayer.gameMode.getGameModeForPlayer() == GameType.SPECTATOR;
                        } else {
                           return _ent.level().isClientSide() && _ent instanceof Player _player
                              ? Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()) != null
                                 && Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()).getGameMode() == GameType.SPECTATOR
                              : false;
                        }
                     }
                  })
                  .checkGamemode(entityiterator)
               && !(new Object() {
                     public boolean checkGamemode(Entity _ent) {
                        if (_ent instanceof ServerPlayer _serverPlayer) {
                           return _serverPlayer.gameMode.getGameModeForPlayer() == GameType.CREATIVE;
                        } else {
                           return _ent.level().isClientSide() && _ent instanceof Player _player
                              ? Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()) != null
                                 && Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()).getGameMode() == GameType.CREATIVE
                              : false;
                        }
                     }
                  })
                  .checkGamemode(entityiterator)
               && !(entityiterator instanceof ThrownEnderpearl)
               && !(entityiterator instanceof AshesGatewayEntity)) {
               entityiterator.setDeltaMovement(
                  new Vec3(
                     (entity.getX() - entityiterator.getX()) * 0.005,
                     (entity.getY() - entityiterator.getY()) * 0.005,
                     (entity.getZ() - entityiterator.getZ()) * 0.005
                  )
               );
            }
         }

         Vec3 _center1 = new Vec3(x, y, z);

         for (Entity entityiteratorx : world.getEntitiesOfClass(Entity.class, new AABB(_center1, _center1).inflate(12.0), e -> true)
            .stream()
            .sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center1)))
            .toList()) {
            if (!(new Object() {
                     public boolean checkGamemode(Entity _ent) {
                        if (_ent instanceof ServerPlayer _serverPlayer) {
                           return _serverPlayer.gameMode.getGameModeForPlayer() == GameType.SPECTATOR;
                        } else {
                           return _ent.level().isClientSide() && _ent instanceof Player _player
                              ? Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()) != null
                                 && Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()).getGameMode() == GameType.SPECTATOR
                              : false;
                        }
                     }
                  })
                  .checkGamemode(entityiteratorx)
               && !(new Object() {
                     public boolean checkGamemode(Entity _ent) {
                        if (_ent instanceof ServerPlayer _serverPlayer) {
                           return _serverPlayer.gameMode.getGameModeForPlayer() == GameType.CREATIVE;
                        } else {
                           return _ent.level().isClientSide() && _ent instanceof Player _player
                              ? Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()) != null
                                 && Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()).getGameMode() == GameType.CREATIVE
                              : false;
                        }
                     }
                  })
                  .checkGamemode(entityiteratorx)
               && !(entityiteratorx instanceof ThrownEnderpearl)
               && !(entityiteratorx instanceof AshesGatewayEntity)) {
               if (entityiteratorx instanceof LivingEntity) {
                  LivingEntity _entity = (LivingEntity)entityiteratorx;
                  if (!_entity.level().isClientSide()) {
                     _entity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 120, 0));
                  }
               }

               entityiteratorx.setDeltaMovement(
                  new Vec3(
                     (entity.getX() - entityiteratorx.getX()) * 0.01,
                     (entity.getY() - entityiteratorx.getY()) * 0.01,
                     (entity.getZ() - entityiteratorx.getZ()) * 0.01
                  )
               );
            }
         }

         Vec3 _center2 = new Vec3(x, y, z);

         for (Entity entityiteratorxx : world.getEntitiesOfClass(Entity.class, new AABB(_center2, _center2).inflate(4.5), e -> true)
            .stream()
            .sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center2)))
            .toList()) {
            if (!(new Object() {
                     public boolean checkGamemode(Entity _ent) {
                        if (_ent instanceof ServerPlayer _serverPlayer) {
                           return _serverPlayer.gameMode.getGameModeForPlayer() == GameType.SPECTATOR;
                        } else {
                           return _ent.level().isClientSide() && _ent instanceof Player _player
                              ? Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()) != null
                                 && Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()).getGameMode() == GameType.SPECTATOR
                              : false;
                        }
                     }
                  })
                  .checkGamemode(entityiteratorxx)
               && !(new Object() {
                     public boolean checkGamemode(Entity _ent) {
                        if (_ent instanceof ServerPlayer _serverPlayer) {
                           return _serverPlayer.gameMode.getGameModeForPlayer() == GameType.CREATIVE;
                        } else {
                           return _ent.level().isClientSide() && _ent instanceof Player _player
                              ? Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()) != null
                                 && Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()).getGameMode() == GameType.CREATIVE
                              : false;
                        }
                     }
                  })
                  .checkGamemode(entityiteratorxx)
               && !(entityiteratorxx instanceof ThrownEnderpearl)
               && !(entityiteratorxx instanceof AshesGatewayEntity)) {
               if (entityiteratorxx instanceof LivingEntity) {
                  LivingEntity _entity = (LivingEntity)entityiteratorxx;
                  if (!_entity.level().isClientSide()) {
                     _entity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 120, 0));
                  }
               }

               entityiteratorxx.setDeltaMovement(
                  new Vec3(
                     (entity.getX() - entityiteratorxx.getX()) * 0.1,
                     (entity.getY() - entityiteratorxx.getY()) * 0.1,
                     (entity.getZ() - entityiteratorxx.getZ()) * 0.1
                  )
               );
            }
         }

         if (entity.getPersistentData().getDouble("ambient_sound_tick") == 0.0 && world instanceof Level _level) {
            if (!_level.isClientSide()) {
               _level.playSound(
                  null,
                  BlockPos.containing(x, y, z),
                  (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:portal.ashes_gateway.ambient")),
                  SoundSource.NEUTRAL,
                  4.0F,
                  1.0F
               );
            } else {
               _level.playLocalSound(
                  x,
                  y,
                  z,
                  (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:portal.ashes_gateway.ambient")),
                  SoundSource.NEUTRAL,
                  4.0F,
                  1.0F,
                  false
               );
            }
         }

         entity.getPersistentData().putDouble("ambient_sound_tick", entity.getPersistentData().getDouble("ambient_sound_tick") + 1.0);
         if (entity.getPersistentData().getDouble("ambient_sound_tick") == 160.0) {
            entity.getPersistentData().putDouble("ambient_sound_tick", 0.0);
         }

         if (entity.getPersistentData().getDouble("initial_sound") == 0.0) {
            if (world instanceof Level _levelx) {
               if (!_levelx.isClientSide()) {
                  _levelx.playSound(
                     null,
                     BlockPos.containing(x, y, z),
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:portal.ashes_gateway.open")),
                     SoundSource.NEUTRAL,
                     9.0F,
                     1.0F
                  );
               } else {
                  _levelx.playLocalSound(
                     x,
                     y,
                     z,
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:portal.ashes_gateway.open")),
                     SoundSource.NEUTRAL,
                     9.0F,
                     1.0F,
                     false
                  );
               }
            }

            entity.getPersistentData().putDouble("initial_sound", 1.0);
         }

         if (entity.getPersistentData().getDouble("particle_tick") == 0.0) {
            if (world instanceof ServerLevel _levelxx) {
               _levelxx.sendParticles(ParticleTypes.ASH, x, y, z, Mth.nextInt(RandomSource.create(), 200, 300), 16.0, 16.0, 16.0, 0.0);
            }

            if (world instanceof ServerLevel _levelxx) {
               _levelxx.sendParticles(ParticleTypes.LARGE_SMOKE, x, y + 4.0, z, Mth.nextInt(RandomSource.create(), 32, 85), 16.0, 16.0, 16.0, 0.15);
            }
         }

         entity.getPersistentData().putDouble("particle_tick", entity.getPersistentData().getDouble("particle_tick") + 1.0);
         if (entity.getPersistentData().getDouble("particle_tick") == 4.0) {
            entity.getPersistentData().putDouble("particle_tick", 0.0);
         }

         Vec3 _center3 = new Vec3(x, y, z);

         for (Entity entityiteratorxxx : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(2.0), e -> true)
            .stream()
            .sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center3)))
            .toList()) {
            if (!(entityiteratorxxx instanceof AshesGatewayEntity)) {
               if ((world instanceof Level _lvl ? _lvl.dimension() : (world instanceof WorldGenLevel _wgl ? _wgl.getLevel().dimension() : Level.OVERWORLD))
                  != ResourceKey.create(Registries.DIMENSION, ResourceLocation.parse("gore_edition:the_xash"))) {
                  entityiteratorxxx.getPersistentData().putDouble("prev_xash_x", entityiteratorxxx.getX());
                  entityiteratorxxx.getPersistentData().putDouble("prev_xash_y", entityiteratorxxx.getY());
                  entityiteratorxxx.getPersistentData().putDouble("prev_xash_z", entityiteratorxxx.getZ());
                  double _setval = entityiteratorxxx.getX();
                  GoreEditionModVariables.PlayerVariables capability = GoreEditionModVariables.getPlayerVariables(entity);
                  capability.prev_xash_x = _setval;
                  capability.syncPlayerVariables(entity);
                  _setval = entityiteratorxxx.getY();
                  capability = GoreEditionModVariables.getPlayerVariables(entity);
                  capability.prev_xash_y = _setval;
                  capability.syncPlayerVariables(entity);
                  _setval = entityiteratorxxx.getZ();
                  capability = GoreEditionModVariables.getPlayerVariables(entity);
                  capability.prev_xash_z = _setval;
                  capability.syncPlayerVariables(entity);
                  if (world instanceof ServerLevel) {
                     ServerLevel _currentLevel = (ServerLevel)world;
                     ServerLevel _nextLevel = _currentLevel.getServer()
                        .getLevel(ResourceKey.create(Registries.DIMENSION, ResourceLocation.parse("gore_edition:the_xash")));
                     if (entityiteratorxxx.canChangeDimensions(_currentLevel, _nextLevel)
                        && _nextLevel != null
                        && !_nextLevel.dimension().equals(_currentLevel.dimension())) {
                        DimensionTransition _dt = new DimensionTransition(
                           _nextLevel,
                           new Vec3(entityiteratorxxx.getX(), entityiteratorxxx.getY(), entityiteratorxxx.getZ()),
                           Vec3.ZERO,
                           entityiteratorxxx.getYRot(),
                           entityiteratorxxx.getXRot(),
                           DimensionTransition.DO_NOTHING.then(_te -> {
                              _te.teleportTo(0.0, 330.0, 0.0);
                              if (_te instanceof LivingEntity _le) {
                                 _le.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 720, 0));
                              }
                           })
                        );
                        entityiteratorxxx.changeDimension(_dt);
                     }
                  }
               } else if (world instanceof ServerLevel) {
                  ServerLevel _currentLevelx = (ServerLevel)world;
                  ServerLevel _nextLevel = _currentLevelx.getServer().getLevel(Level.OVERWORLD);
                  if (entityiteratorxxx.canChangeDimensions(_currentLevelx, _nextLevel)
                     && _nextLevel != null
                     && !_nextLevel.dimension().equals(_currentLevelx.dimension())) {
                     DimensionTransition _dt = new DimensionTransition(
                        _nextLevel,
                        new Vec3(entityiteratorxxx.getX(), entityiteratorxxx.getY(), entityiteratorxxx.getZ()),
                        Vec3.ZERO,
                        entityiteratorxxx.getYRot(),
                        entityiteratorxxx.getXRot(),
                        DimensionTransition.DO_NOTHING.then(_te -> {
                           _te.teleportTo(0.0, 330.0, 0.0);
                           if (_te instanceof LivingEntity _le) {
                              _le.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 720, 0));
                           }
                        })
                     );
                     entityiteratorxxx.changeDimension(_dt);
                  }
               }
            }
         }

         if (!(entity instanceof LivingEntity) && world instanceof ServerLevel _levelxx) {
            _levelxx.getServer()
               .getCommands()
               .performPrefixedCommand(
                  new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _levelxx, 4, "", Component.literal(""), _levelxx.getServer(), null)
                     .withSuppressedOutput(),
                  "stopsound @a neutral gore_edition:portal.ashes_gateway.ambient"
               );
         }
      }
   }
}
