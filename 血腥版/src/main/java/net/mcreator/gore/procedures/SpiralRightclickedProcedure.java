package net.mcreator.gore.procedures;

import java.util.Comparator;
import net.mcreator.gore.init.GoreEditionModEntities;
import net.mcreator.gore.init.GoreEditionModMobEffects;
import net.mcreator.gore.utils.ItemTagHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class SpiralRightclickedProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, ItemStack itemstack) {
      if (entity != null
         && (new Object() {
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
         && entity instanceof LivingEntity _livEnt1
         && _livEnt1.hasEffect(GoreEditionModMobEffects.SPIRAL_TWIST)) {
         int var10000;
         label91: {
            if (entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(GoreEditionModMobEffects.SPIRAL_TWIST)) {
               var10000 = _livEnt.getEffect(GoreEditionModMobEffects.SPIRAL_TWIST).getAmplifier();
               break label91;
            }

            var10000 = 0;
         }

         if (var10000 >= 1) {
            if (!entity.isShiftKeyDown()) {
               if (Math.random() < 0.4) {
                  if (world instanceof Level _level) {
                     if (!_level.isClientSide()) {
                        _level.playSound(
                           null,
                           BlockPos.containing(x, y, z),
                           (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:item.spiral.tornado_summoned")),
                           SoundSource.PLAYERS,
                           3.0F,
                           0.0F
                        );
                     } else {
                        _level.playLocalSound(
                           x,
                           y,
                           z,
                           (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:item.spiral.tornado_summoned")),
                           SoundSource.PLAYERS,
                           3.0F,
                           0.0F,
                           false
                        );
                     }
                  }

                  if (world instanceof ServerLevel _serverLevel) {
                     Entity entityinstance = ((EntityType)GoreEditionModEntities.SPIRAL_TORNADO.get())
                        .create(_serverLevel, null, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED, false, false);
                     if (entityinstance != null) {
                        entityinstance.setYRot(world.getRandom().nextFloat() * 360.0F);
                        if (entityinstance instanceof TamableAnimal _toTame && entity instanceof Player _owner) {
                           _toTame.tame(_owner);
                        }

                        _serverLevel.addFreshEntity(entityinstance);
                     }
                  }

                  if (ItemTagHelper.damageItem(itemstack, entity)) {
                     itemstack.shrink(1);
                     itemstack.setDamageValue(0);
                  }
               }

               if (Math.random() < 0.7 && world instanceof Level _levelx) {
                  if (!_levelx.isClientSide()) {
                     _levelx.playSound(
                        null,
                        BlockPos.containing(x, y, z),
                        (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:item.spiral.release")),
                        SoundSource.PLAYERS,
                        2.0F,
                        1.0F
                     );
                  } else {
                     _levelx.playLocalSound(
                        x,
                        y,
                        z,
                        (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:item.spiral.release")),
                        SoundSource.PLAYERS,
                        2.0F,
                        1.0F,
                        false
                     );
                  }
               }
            } else {
               SpiralTornadoOnTickUpdateProcedure.execute(world, x, y, z);
               if (Math.random() < 0.7 && world instanceof Level _levelxx) {
                  if (!_levelxx.isClientSide()) {
                     _levelxx.playSound(
                        null,
                        BlockPos.containing(x, y, z),
                        (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:item.spiral.attract")),
                        SoundSource.PLAYERS,
                        2.0F,
                        1.0F
                     );
                  } else {
                     _levelxx.playLocalSound(
                        x,
                        y,
                        z,
                        (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:item.spiral.attract")),
                        SoundSource.PLAYERS,
                        2.0F,
                        1.0F,
                        false
                     );
                  }
               }

               if (Math.random() < 0.4) {
                  entity.hurt(
                     new DamageSource(
                        world.registryAccess()
                           .registryOrThrow(Registries.DAMAGE_TYPE)
                           .getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.parse("gore_edition:spiral_twisted")))
                     ),
                     9.0F
                  );
                  Vec3 _center = new Vec3(x, y, z);

                  for (Entity entityiterator : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(1.5), e -> true)
                     .stream()
                     .sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center)))
                     .toList()) {
                     if (entityiterator != entity && entityiterator instanceof LivingEntity) {
                        LivingEntity _livEnt15 = (LivingEntity)entityiterator;
                        if (_livEnt15.hasEffect(GoreEditionModMobEffects.SPIRAL_TWIST)) {
                           entityiterator.hurt(
                              new DamageSource(
                                 world.registryAccess()
                                    .registryOrThrow(Registries.DAMAGE_TYPE)
                                    .getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.parse("gore_edition:spiral_twisted"))),
                                 entity
                              ),
                              30.0F
                           );
                        }
                     }
                  }

                  if (ItemTagHelper.damageItem(itemstack, entity)) {
                     itemstack.shrink(1);
                     itemstack.setDamageValue(0);
                  }
               }
            }

            if (ItemTagHelper.damageItem(itemstack, entity)) {
               itemstack.shrink(1);
               itemstack.setDamageValue(0);
            }
         }
      }
   }
}
