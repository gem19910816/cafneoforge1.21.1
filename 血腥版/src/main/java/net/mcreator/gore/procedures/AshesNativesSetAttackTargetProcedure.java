package net.mcreator.gore.procedures;

import java.util.Comparator;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class AshesNativesSetAttackTargetProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         double raytrace_distance = 0.0;
         String found_entity_name = "";
         boolean entity_found = false;
         if ((entity instanceof Mob _mobEntxx ? _mobEntxx.getTarget() : null) != null) {
            if ((entity instanceof Mob _mobEntx ? _mobEntx.getTarget() : null) == null) {
               return;
            }

            LivingEntity _center = entity instanceof Mob _mobEnt ? _mobEnt.getTarget() : null;
            if (!((_center instanceof LivingEntity ? _center.getHealth() : -1.0F) <= 0.0F)) {
               return;
            }
         }

         Vec3 _center = new Vec3(x, y, z);

         for (Entity entityiterator : world.getEntitiesOfClass(
               Entity.class, new AABB(_center, _center).inflate(entity.getPersistentData().getDouble("distance") / 2.0), e -> true
            )
            .stream()
            .sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center)))
            .toList()) {
            if (entityiterator instanceof LivingEntity
               && !entityiterator.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("gore_edition:ashes_natives")))
               && entityiterator != entity
               && (
                  !(entityiterator instanceof Player) && !(entityiterator instanceof ServerPlayer)
                     || (new Object() {
                           public boolean checkGamemode(Entity _ent) {
                              if (_ent instanceof ServerPlayer _serverPlayer) {
                                 return _serverPlayer.gameMode.getGameModeForPlayer() == GameType.SURVIVAL;
                              } else {
                                 return _ent.level().isClientSide() && _ent instanceof Player _player
                                    ? Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()) != null
                                       && Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()).getGameMode()
                                          == GameType.SURVIVAL
                                    : false;
                              }
                           }
                        })
                        .checkGamemode(entityiterator)
                     || (new Object() {
                           public boolean checkGamemode(Entity _ent) {
                              if (_ent instanceof ServerPlayer _serverPlayer) {
                                 return _serverPlayer.gameMode.getGameModeForPlayer() == GameType.ADVENTURE;
                              } else {
                                 return _ent.level().isClientSide() && _ent instanceof Player _player
                                    ? Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()) != null
                                       && Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()).getGameMode()
                                          == GameType.ADVENTURE
                                    : false;
                              }
                           }
                        })
                        .checkGamemode(entityiterator)
               )
               && !entityiterator.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_corpses")))) {
               if (entityiterator instanceof LivingEntity) {
                  LivingEntity _livEnt15 = (LivingEntity)entityiterator;
                  if (_livEnt15.hasEffect(MobEffects.INVISIBILITY)) {
                     continue;
                  }
               }

               if (entity instanceof Mob) {
                  Mob _entity = (Mob)entity;
                  if (entityiterator instanceof LivingEntity _ent) {
                     _entity.setTarget(_ent);
                  }
               }
            }
         }

         Vec3 _centerB = new Vec3(x, y, z);

         for (Entity entityiteratorx : world.getEntitiesOfClass(
               Entity.class, new AABB(_centerB, _centerB).inflate((double)Mth.nextInt(RandomSource.create(), 2, 5) / 2.0), e -> true
            )
            .stream()
            .sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_centerB)))
            .toList()) {
            if (entityiteratorx instanceof LivingEntity
               && !entityiteratorx.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("gore_edition:ashes_natives")))
               && entityiteratorx != entity
               && (
                  !(entityiteratorx instanceof Player) && !(entityiteratorx instanceof ServerPlayer)
                     || (new Object() {
                           public boolean checkGamemode(Entity _ent) {
                              if (_ent instanceof ServerPlayer _serverPlayer) {
                                 return _serverPlayer.gameMode.getGameModeForPlayer() == GameType.SURVIVAL;
                              } else {
                                 return _ent.level().isClientSide() && _ent instanceof Player _player
                                    ? Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()) != null
                                       && Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()).getGameMode()
                                          == GameType.SURVIVAL
                                    : false;
                              }
                           }
                        })
                        .checkGamemode(entityiteratorx)
                     || (new Object() {
                           public boolean checkGamemode(Entity _ent) {
                              if (_ent instanceof ServerPlayer _serverPlayer) {
                                 return _serverPlayer.gameMode.getGameModeForPlayer() == GameType.ADVENTURE;
                              } else {
                                 return _ent.level().isClientSide() && _ent instanceof Player _player
                                    ? Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()) != null
                                       && Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()).getGameMode()
                                          == GameType.ADVENTURE
                                    : false;
                              }
                           }
                        })
                        .checkGamemode(entityiteratorx)
               )
               && !entityiteratorx.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_corpses")))
               && entity instanceof Mob) {
               Mob _entity = (Mob)entity;
               if (entityiteratorx instanceof LivingEntity _ent) {
                  _entity.setTarget(_ent);
               }
            }
         }
      }
   }
}
