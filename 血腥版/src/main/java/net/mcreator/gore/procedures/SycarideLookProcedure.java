package net.mcreator.gore.procedures;

import java.util.Comparator;
import net.mcreator.gore.entity.LuxSycaridaeEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class SycarideLookProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         entity.getPersistentData().putDouble("look", entity.getPersistentData().getDouble("look") + 1.0);
         if (entity.getPersistentData().getDouble("look") == 200.0) {
            if (entity instanceof LuxSycaridaeEntity) {
               ((LuxSycaridaeEntity)entity).setAnimation("look");
            }

            entity.getPersistentData().putBoolean("searching", true);
         }

         if (entity.getPersistentData().getDouble("look") == 221.0) {
            entity.getPersistentData().putBoolean("searching", false);
            entity.getPersistentData().putDouble("look", 0.0);
         }

         if (entity.getPersistentData().getBoolean("searching")) {
            Vec3 _center = new Vec3(x, y, z);

            for (Entity entityiterator : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(8.0), e -> true)
               .stream()
               .sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center)))
               .toList()) {
               if (entityiterator instanceof LivingEntity
                  && !entityiterator.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("gore_edition:ashes_natives")))
                  && !entityiterator.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_corpses")))
                  && !(new Object() {
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
                     .checkGamemode(entityiterator)
                  && !(new Object() {
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
                     .checkGamemode(entityiterator)) {
                  if (entity instanceof LivingEntity) {
                     LivingEntity _entity = (LivingEntity)entity;
                     if (!_entity.level().isClientSide()) {
                        _entity.addEffect(new MobEffectInstance(MobEffects.GLOWING, 40, 0));
                     }
                  }

                  if (entityiterator instanceof LivingEntity) {
                     LivingEntity _entity = (LivingEntity)entityiterator;
                     if (!_entity.level().isClientSide()) {
                        _entity.addEffect(new MobEffectInstance(MobEffects.GLOWING, 200, 0));
                     }
                  }

                  if (entity.getPersistentData().getDouble("look") == 221.0 && world instanceof Level) {
                     Level _level = (Level)world;
                     if (!_level.isClientSide()) {
                        _level.playSound(
                           null,
                           BlockPos.containing(entityiterator.getX(), entityiterator.getY(), entityiterator.getZ()),
                           (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore.slime_hurt_sound")),
                           SoundSource.NEUTRAL,
                           1.0F,
                           2.0F
                        );
                     } else {
                        _level.playLocalSound(
                           entityiterator.getX(),
                           entityiterator.getY(),
                           entityiterator.getZ(),
                           (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore.slime_hurt_sound")),
                           SoundSource.NEUTRAL,
                           1.0F,
                           2.0F,
                           false
                        );
                     }
                  }
               }
            }
         }
      }
   }
}
