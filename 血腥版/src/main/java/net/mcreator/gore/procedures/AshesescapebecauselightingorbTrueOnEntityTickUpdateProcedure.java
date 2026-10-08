package net.mcreator.gore.procedures;

import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.protocol.game.ClientboundGameEventPacket;
import net.minecraft.network.protocol.game.ClientboundLevelEventPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerAbilitiesPacket;
import net.minecraft.network.protocol.game.ClientboundUpdateMobEffectPacket;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.EntityTickEvent.Pre;

@EventBusSubscriber
public class AshesescapebecauselightingorbTrueOnEntityTickUpdateProcedure {
   @SubscribeEvent
   public static void onEntityTick(Pre event) {
      execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity());
   }

   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      execute(null, world, x, y, z, entity);
   }

   private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         if (entity.getPersistentData().getBoolean("ashes_escape_because_lighting_orb")) {
            if (entity.getPersistentData().getDouble("LightingOrbEscapeTick") == 0.0) {
               entity.getPersistentData().putBoolean("LightingOrbEscapeCloudsParticlesLoop", true);
               if (world instanceof Level _level) {
                  if (!_level.isClientSide()) {
                     _level.playSound(
                        null,
                        BlockPos.containing(x, y, z),
                        (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:item.lightning_orb_activate")),
                        SoundSource.PLAYERS,
                        1.0F,
                        0.0F
                     );
                  } else {
                     _level.playLocalSound(
                        x,
                        y,
                        z,
                        (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:item.lightning_orb_activate")),
                        SoundSource.PLAYERS,
                        1.0F,
                        0.0F,
                        false
                     );
                  }
               }
            }

            entity.getPersistentData().putDouble("LightingOrbEscapeTick", entity.getPersistentData().getDouble("LightingOrbEscapeTick") + 1.0);
            if (entity.getPersistentData().getDouble("LightingOrbEscapeTick") == 200.0) {
               entity.getPersistentData().putBoolean("LightingOrbEscapeCloudsParticlesLoop", false);
               entity.getPersistentData().putBoolean("ashes_escape_because_lighting_orb", false);
               entity.getPersistentData().putDouble("LightingOrbEscapeTick", 0.0);
               if (entity instanceof ServerPlayer _player && !_player.level().isClientSide()) {
                  ResourceKey<Level> destinationType = Level.OVERWORLD;
                  if (_player.level().dimension() == destinationType) {
                     return;
                  }

                  ServerLevel nextLevel = _player.server.getLevel(destinationType);
                  if (nextLevel != null) {
                     _player.connection.send(new ClientboundGameEventPacket(ClientboundGameEventPacket.WIN_GAME, 0.0F));
                     _player.teleportTo(nextLevel, _player.getX(), _player.getY(), _player.getZ(), _player.getYRot(), _player.getXRot());
                     _player.connection.send(new ClientboundPlayerAbilitiesPacket(_player.getAbilities()));

                     for (MobEffectInstance _effectinstance : _player.getActiveEffects()) {
                        _player.connection.send(new ClientboundUpdateMobEffectPacket(_player.getId(), _effectinstance, false));
                     }

                     _player.connection.send(new ClientboundLevelEventPacket(1032, BlockPos.ZERO, 0, false));
                  }
               }

               entity.getPersistentData().putBoolean("lighting_epic_ashes_escape", true);
            }
         }

         if (entity.getPersistentData().getBoolean("LightingOrbEscapeCloudsParticlesLoop")) {
            if (entity.getPersistentData().getDouble("LightingOrbEscapeCloudsParticlesLoopTick") == 0.0 && world instanceof ServerLevel _levelx) {
               _levelx.sendParticles(ParticleTypes.CLOUD, x, y + 0.4, z, 12, 2.0, 0.7, 2.0, 0.3);
            }

            entity.getPersistentData()
               .putDouble("LightingOrbEscapeCloudsParticlesLoopTick", entity.getPersistentData().getDouble("LightingOrbEscapeCloudsParticlesLoopTick") + 1.0);
            if (entity.getPersistentData().getDouble("LightingOrbEscapeCloudsParticlesLoopTick") == 2.0) {
               entity.getPersistentData().putDouble("LightingOrbEscapeCloudsParticlesLoopTick", 0.0);
            }
         }
      }
   }
}
