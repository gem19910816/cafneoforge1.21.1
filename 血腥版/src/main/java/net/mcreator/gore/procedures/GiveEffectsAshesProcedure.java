package net.mcreator.gore.procedures;

import java.util.Comparator;
import javax.annotation.Nullable;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.EntityTickEvent.Pre;

@EventBusSubscriber
public class GiveEffectsAshesProcedure {
   @SubscribeEvent
   public static void onEntityTick(Pre event) {
      execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity());
   }

   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      execute(null, world, x, y, z, entity);
   }

   private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         double tickremanfc = 0.0;
         if (entity.level().dimension() == ResourceKey.create(Registries.DIMENSION, ResourceLocation.parse("gore_edition:the_xash"))
            && !entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("gore_edition:ashes_natives")))
            && entity instanceof LivingEntity _livEnt4
            && _livEnt4.hasEffect(MobEffects.GLOWING)
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
               .checkGamemode(entity)
            && !(new Object() {
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
               .checkGamemode(entity)) {
            int var10000;
            label64: {
               if (entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(MobEffects.GLOWING)) {
                  var10000 = _livEnt.getEffect(MobEffects.GLOWING).getAmplifier();
                  break label64;
               }

               var10000 = 0;
            }

            if (var10000 == 0) {
               Vec3 _center = new Vec3(x, y, z);

               for (Entity entityiterator : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(48.0), e -> true)
                  .stream()
                  .sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center)))
                  .toList()) {
                  if (entityiterator.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("gore_edition:ashes_natives")))
                     && entityiterator instanceof Mob) {
                     Mob _entity = (Mob)entityiterator;
                     if (entity instanceof LivingEntity _ent) {
                        _entity.setTarget(_ent);
                     }
                  }
               }
            }

            label51: {
               if (entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(MobEffects.GLOWING)) {
                  var10000 = _livEnt.getEffect(MobEffects.GLOWING).getAmplifier();
                  break label51;
               }

               var10000 = 0;
            }

            if (var10000 == 1) {
               Vec3 _center = new Vec3(x, y, z);

               for (Entity entityiteratorx : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(80.0), e -> true)
                  .stream()
                  .sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center)))
                  .toList()) {
                  if (entityiteratorx.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("gore_edition:ashes_natives")))
                     && entityiteratorx instanceof Mob) {
                     Mob _entity = (Mob)entityiteratorx;
                     if (entity instanceof LivingEntity _ent) {
                        _entity.setTarget(_ent);
                     }
                  }
               }
            }
         }
      }
   }
}
