package net.mcreator.gore.procedures;

import javax.annotation.Nullable;
import net.mcreator.gore.network.GoreEditionModVariables;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.portal.DimensionTransition;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.EntityTickEvent.Pre;

@EventBusSubscriber
public class HellCursedProcedure {
   @SubscribeEvent
   public static void onEntityTick(Pre event) {
      execute(event, event.getEntity().level(), event.getEntity());
   }

   public static void execute(LevelAccessor world, Entity entity) {
      execute(null, world, entity);
   }

   private static void execute(@Nullable Event event, LevelAccessor world, Entity entity) {
      if (entity != null && entity.level().dimension() != ResourceKey.create(Registries.DIMENSION, ResourceLocation.parse("gore_edition:the_xash"))) {
         if (!(entity instanceof ServerPlayer) && !(entity instanceof Player)) {
            if (entity.getPersistentData().getDouble("hell_cursed") == 10.0) {
               entity.getPersistentData().putDouble("stop_x", entity.getX());
               entity.getPersistentData().putDouble("stop_y", entity.getY());
               entity.getPersistentData().putDouble("stop_z", entity.getZ());
               entity.getPersistentData().putDouble("hell_cursed", 0.0);
               entity.getPersistentData().putBoolean("stop_hell_cursed", true);
               if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
                  _entity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 60, 0));
               }

               if (world instanceof Level _level) {
                  if (!_level.isClientSide()) {
                     _level.playSound(
                        null,
                        BlockPos.containing(entity.getX(), entity.getY(), entity.getZ()),
                        (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.donkey.death")),
                        SoundSource.AMBIENT,
                        1.0F,
                        0.0F
                     );
                  } else {
                     _level.playLocalSound(
                        entity.getX(),
                        entity.getY(),
                        entity.getZ(),
                        (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.donkey.death")),
                        SoundSource.AMBIENT,
                        1.0F,
                        0.0F,
                        false
                     );
                  }
               }
            }

            if (entity.getPersistentData().getBoolean("stop_hell_cursed")) {
               entity.teleportTo(
                  entity.getPersistentData().getDouble("stop_x"),
                  entity.getPersistentData().getDouble("stop_y"),
                  entity.getPersistentData().getDouble("stop_z")
               );
               if (entity instanceof ServerPlayer _serverPlayer) {
                  _serverPlayer.connection
                     .teleport(
                        entity.getPersistentData().getDouble("stop_x"),
                        entity.getPersistentData().getDouble("stop_y"),
                        entity.getPersistentData().getDouble("stop_z"),
                        entity.getYRot(),
                        entity.getXRot()
                     );
               }

               entity.getPersistentData().putDouble("hell_ashes_teleport_timer", entity.getPersistentData().getDouble("hell_ashes_teleport_timer") + 1.0);
            }

            if (entity.getPersistentData().getDouble("hell_ashes_teleport_timer") == 60.0) {
               entity.getPersistentData().putDouble("prev_xash_x", entity.getX());
               entity.getPersistentData().putDouble("prev_xash_y", entity.getY());
               entity.getPersistentData().putDouble("prev_xash_z", entity.getZ());
               if (world instanceof ServerLevel _currentLevel) {
                  ServerLevel _nextLevel = _currentLevel.getServer()
                     .getLevel(ResourceKey.create(Registries.DIMENSION, ResourceLocation.parse("gore_edition:the_xash")));
                  if (entity.canChangeDimensions(_currentLevel, _nextLevel) && _nextLevel != null && !_nextLevel.dimension().equals(_currentLevel.dimension())) {
                     DimensionTransition _dt = new DimensionTransition(
                        _nextLevel,
                        new Vec3(entity.getX(), entity.getY(), entity.getZ()),
                        Vec3.ZERO,
                        entity.getYRot(),
                        entity.getXRot(),
                        DimensionTransition.DO_NOTHING.then(_te -> {
                           if (_te.level() instanceof ServerLevel _sl) {
                              BlockPos _sp = _sl.getSharedSpawnPos();
                              _te.teleportTo((double)_sp.getX(), 144.0, (double)_sp.getZ());
                           }
                        })
                     );
                     entity.changeDimension(_dt);
                  }
               }

               if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
                  _entity.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 400, 0));
               }

               entity.getPersistentData().putDouble("hell_cursed", 0.0);
               entity.getPersistentData().putDouble("hell_ashes_teleport_timer", 0.0);
               entity.getPersistentData().putBoolean("stop_hell_cursed", false);
            }
         } else {
            if (GoreEditionModVariables.getPlayerVariables(entity).hell_cursed_for_player == 10.0) {
               double _setval = entity.getX();
               GoreEditionModVariables.PlayerVariables capability = GoreEditionModVariables.getPlayerVariables(entity);
               capability.stop_x = _setval;
               capability.syncPlayerVariables(entity);
               _setval = entity.getY();
               capability = GoreEditionModVariables.getPlayerVariables(entity);
               capability.stop_y = _setval;
               capability.syncPlayerVariables(entity);
               _setval = entity.getZ();
               capability = GoreEditionModVariables.getPlayerVariables(entity);
               capability.stop_z = _setval;
               capability.syncPlayerVariables(entity);
               _setval = 0.0;
               capability = GoreEditionModVariables.getPlayerVariables(entity);
               capability.hell_cursed_for_player = _setval;
               capability.syncPlayerVariables(entity);
               boolean _setvalx = true;
               GoreEditionModVariables.PlayerVariables capabilityx = GoreEditionModVariables.getPlayerVariables(entity);
               capabilityx.stop_hell_cursed_for_player = _setvalx;
               capabilityx.syncPlayerVariables(entity);
               if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
                  _entity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 60, 0));
               }

               if (world instanceof Level _levelx) {
                  if (!_levelx.isClientSide()) {
                     _levelx.playSound(
                        null,
                        BlockPos.containing(entity.getX(), entity.getY(), entity.getZ()),
                        (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.donkey.death")),
                        SoundSource.AMBIENT,
                        1.0F,
                        0.0F
                     );
                  } else {
                     _levelx.playLocalSound(
                        entity.getX(),
                        entity.getY(),
                        entity.getZ(),
                        (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.donkey.death")),
                        SoundSource.AMBIENT,
                        1.0F,
                        0.0F,
                        false
                     );
                  }
               }
            }

            if (GoreEditionModVariables.getPlayerVariables(entity).stop_hell_cursed_for_player) {
               double _setvalxx = GoreEditionModVariables.getPlayerVariables(entity).hell_ashes_teleport_timer_for_player + 1.0;
               GoreEditionModVariables.PlayerVariables capabilityxx = GoreEditionModVariables.getPlayerVariables(entity);
               capabilityxx.hell_ashes_teleport_timer_for_player = _setvalxx;
               capabilityxx.syncPlayerVariables(entity);
               entity.teleportTo(
                  GoreEditionModVariables.getPlayerVariables(entity).stop_x,
                  GoreEditionModVariables.getPlayerVariables(entity).stop_y,
                  GoreEditionModVariables.getPlayerVariables(entity).stop_z
               );
               if (entity instanceof ServerPlayer _serverPlayer) {
                  _serverPlayer.connection
                     .teleport(
                        GoreEditionModVariables.getPlayerVariables(entity).stop_x,
                        GoreEditionModVariables.getPlayerVariables(entity).stop_y,
                        GoreEditionModVariables.getPlayerVariables(entity).stop_z,
                        entity.getYRot(),
                        entity.getXRot()
                     );
               }
            }

            if (GoreEditionModVariables.getPlayerVariables(entity).hell_ashes_teleport_timer_for_player == 60.0) {
               double _setvalxx = entity.getX();
               GoreEditionModVariables.PlayerVariables capabilityxx = GoreEditionModVariables.getPlayerVariables(entity);
               capabilityxx.prev_xash_x = _setvalxx;
               capabilityxx.syncPlayerVariables(entity);
               _setvalxx = entity.getY();
               capabilityxx = GoreEditionModVariables.getPlayerVariables(entity);
               capabilityxx.prev_xash_y = _setvalxx;
               capabilityxx.syncPlayerVariables(entity);
               _setvalxx = entity.getZ();
               capabilityxx = GoreEditionModVariables.getPlayerVariables(entity);
               capabilityxx.prev_xash_z = _setvalxx;
               capabilityxx.syncPlayerVariables(entity);
               if (world instanceof ServerLevel _currentLevelx) {
                  ServerLevel _nextLevel = _currentLevelx.getServer()
                     .getLevel(ResourceKey.create(Registries.DIMENSION, ResourceLocation.parse("gore_edition:the_xash")));
                  if (entity.canChangeDimensions(_currentLevelx, _nextLevel)
                     && _nextLevel != null
                     && !_nextLevel.dimension().equals(_currentLevelx.dimension())) {
                     DimensionTransition _dt = new DimensionTransition(
                        _nextLevel,
                        new Vec3(entity.getX(), entity.getY(), entity.getZ()),
                        Vec3.ZERO,
                        entity.getYRot(),
                        entity.getXRot(),
                        DimensionTransition.DO_NOTHING.then(_te -> {
                           if (_te.level() instanceof ServerLevel _sl) {
                              BlockPos _sp = _sl.getSharedSpawnPos();
                              _te.teleportTo((double)_sp.getX(), 144.0, (double)_sp.getZ());
                           }
                        })
                     );
                     entity.changeDimension(_dt);
                  }
               }

               if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
                  _entity.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 400, 0));
               }

               _setvalxx = 0.0;
               capabilityxx = GoreEditionModVariables.getPlayerVariables(entity);
               capabilityxx.hell_cursed_for_player = _setvalxx;
               capabilityxx.syncPlayerVariables(entity);
               _setvalxx = 0.0;
               capabilityxx = GoreEditionModVariables.getPlayerVariables(entity);
               capabilityxx.hell_ashes_teleport_timer_for_player = _setvalxx;
               capabilityxx.syncPlayerVariables(entity);
               boolean _setvalxxx = false;
               GoreEditionModVariables.PlayerVariables capabilityxxx = GoreEditionModVariables.getPlayerVariables(entity);
               capabilityxxx.stop_hell_cursed_for_player = _setvalxxx;
               capabilityxxx.syncPlayerVariables(entity);
            }
         }
      }
   }
}
