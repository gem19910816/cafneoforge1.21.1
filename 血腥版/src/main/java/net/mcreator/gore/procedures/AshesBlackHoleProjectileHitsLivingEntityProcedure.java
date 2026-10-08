package net.mcreator.gore.procedures;

import java.util.Comparator;
import net.mcreator.gore.GoreEditionMod;
import net.mcreator.gore.init.GoreEditionModMobEffects;
import net.minecraft.client.Minecraft;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class AshesBlackHoleProjectileHitsLivingEntityProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z) {
      GoreEditionMod.queueServerWork(
         5,
         () -> {
            Vec3 _center = new Vec3(x, y, z);

            for (Entity entityiterator : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(12.0), e -> true)
               .stream()
               .sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center)))
               .toList()) {
               if (!(new Object() {
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
                  if (entityiterator instanceof LivingEntity) {
                     LivingEntity _entity = (LivingEntity)entityiterator;
                     if (!_entity.level().isClientSide()) {
                        _entity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 360, 0));
                     }
                  }

                  if (entityiterator instanceof LivingEntity) {
                     LivingEntity _entity = (LivingEntity)entityiterator;
                     if (!_entity.level().isClientSide()) {
                        _entity.addEffect(new MobEffectInstance(GoreEditionModMobEffects.DEAFNESS, 600, 0));
                     }
                  }

                  if (entityiterator instanceof LivingEntity _entity) {
                     _entity.setHealth((entityiterator instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1.0F) / 2.0F);
                  }
               }
            }
         }
      );
   }
}
