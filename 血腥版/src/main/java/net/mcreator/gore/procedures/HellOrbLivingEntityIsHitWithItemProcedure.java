package net.mcreator.gore.procedures;

import net.mcreator.gore.init.GoreEditionModMobEffects;
import net.mcreator.gore.network.GoreEditionModVariables;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

public class HellOrbLivingEntityIsHitWithItemProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, Entity sourceentity, ItemStack itemstack) {
      if (entity != null && sourceentity != null) {
         if ((
               (new Object() {
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
                     .checkGamemode(entity)
                  || (new Object() {
                        public boolean checkGamemode(Entity _ent) {
                           if (_ent instanceof ServerPlayer _serverPlayer) {
                              return _serverPlayer.gameMode.getGameModeForPlayer() == GameType.ADVENTURE;
                           } else {
                              return _ent.level().isClientSide() && _ent instanceof Player _playerx
                                 ? Minecraft.getInstance().getConnection().getPlayerInfo(_playerx.getGameProfile().getId()) != null
                                    && Minecraft.getInstance().getConnection().getPlayerInfo(_playerx.getGameProfile().getId()).getGameMode()
                                       == GameType.ADVENTURE
                                 : false;
                           }
                        }
                     })
                     .checkGamemode(entity)
            )
            && sourceentity instanceof Player _player) {
            _player.getInventory().clearOrCountMatchingItems(p -> itemstack.getItem() == p.getItem(), 1, _player.inventoryMenu.getCraftSlots());
         }

         if (Math.random() < 0.5) {
            if (world instanceof Level _level) {
               if (!_level.isClientSide()) {
                  _level.playSound(
                     null,
                     BlockPos.containing(x, y, z),
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.lava.extinguish")),
                     SoundSource.AMBIENT,
                     1.0F,
                     2.0F
                  );
               } else {
                  _level.playLocalSound(
                     x,
                     y,
                     z,
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.lava.extinguish")),
                     SoundSource.AMBIENT,
                     1.0F,
                     2.0F,
                     false
                  );
               }
            }

            entity.igniteForSeconds(8.0F);
         }

         if (entity.level().dimension() != ResourceKey.create(Registries.DIMENSION, ResourceLocation.parse("gore_edition:the_xash"))) {
            if (entity.isOnFire()) {
               if (Math.random() < 0.5 && world instanceof Level _levelx) {
                  if (!_levelx.isClientSide()) {
                     _levelx.playSound(
                        null,
                        BlockPos.containing(x, y, z),
                        (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore_external_burning_hurt_sound")),
                        SoundSource.AMBIENT,
                        1.0F,
                        1.0F
                     );
                  } else {
                     _levelx.playLocalSound(
                        x,
                        y,
                        z,
                        (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore_external_burning_hurt_sound")),
                        SoundSource.AMBIENT,
                        1.0F,
                        1.0F,
                        false
                     );
                  }
               }

               if (Math.random() < 0.5 && world instanceof Level _levelxx) {
                  if (!_levelxx.isClientSide()) {
                     _levelxx.playSound(
                        null,
                        BlockPos.containing(x, y, z),
                        (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.lava.extinguish")),
                        SoundSource.AMBIENT,
                        1.0F,
                        2.0F
                     );
                  } else {
                     _levelxx.playLocalSound(
                        x,
                        y,
                        z,
                        (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.lava.extinguish")),
                        SoundSource.AMBIENT,
                        1.0F,
                        2.0F,
                        false
                     );
                  }
               }

               if (!(entity instanceof Player) && !(entity instanceof ServerPlayer)) {
                  entity.getPersistentData().putDouble("hell_cursed", entity.getPersistentData().getDouble("hell_cursed") + 1.0);
               } else {
                  double _setval = GoreEditionModVariables.getPlayerVariables(entity).hell_cursed_for_player + 1.0;
                  GoreEditionModVariables.PlayerVariables capability = GoreEditionModVariables.getPlayerVariables(entity);
                  capability.hell_cursed_for_player = _setval;
                  capability.syncPlayerVariables(entity);
               }

               if (entity.getPersistentData().getDouble("hell_cursed") >= 10.0) {
                  itemstack.set(DataComponents.CUSTOM_NAME, Component.literal("§f§kAshes"));
                  if (sourceentity instanceof ServerPlayer _player) {
                     AdvancementHolder _adv = _player.server.getAdvancements().get(ResourceLocation.parse("gore_edition:use_the_ashes"));
                     AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
                     if (!_ap.isDone()) {
                        for (String criteria : _ap.getRemainingCriteria()) {
                           _player.getAdvancements().award(_adv, criteria);
                        }
                     }
                  }
               }
            }

            if (entity instanceof LivingEntity _livEnt20 && _livEnt20.hasEffect(GoreEditionModMobEffects.VULNERABILITY)) {
               int var10000;
               label83: {
                  if (entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(GoreEditionModMobEffects.VULNERABILITY)) {
                     var10000 = _livEnt.getEffect(GoreEditionModMobEffects.VULNERABILITY).getAmplifier();
                     break label83;
                  }

                  var10000 = 0;
               }

               if (var10000 >= 1) {
                  if (!(entity instanceof Player) && !(entity instanceof ServerPlayer)) {
                     entity.getPersistentData().putDouble("hell_cursed", entity.getPersistentData().getDouble("hell_cursed") + 1.0);
                  } else {
                     double _setval = GoreEditionModVariables.getPlayerVariables(entity).hell_cursed_for_player + 1.0;
                     GoreEditionModVariables.PlayerVariables capability = GoreEditionModVariables.getPlayerVariables(entity);
                     capability.hell_cursed_for_player = _setval;
                     capability.syncPlayerVariables(entity);
                  }

                  if (entity.getPersistentData().getDouble("hell_cursed") >= 6.0) {
                     itemstack.set(DataComponents.CUSTOM_NAME, Component.literal("§f§kAshes"));
                     if (sourceentity instanceof ServerPlayer _playerx) {
                        AdvancementHolder _adv = _playerx.server.getAdvancements().get(ResourceLocation.parse("gore_edition:use_the_ashes"));
                        AdvancementProgress _ap = _playerx.getAdvancements().getOrStartProgress(_adv);
                        if (!_ap.isDone()) {
                           for (String criteria : _ap.getRemainingCriteria()) {
                              _playerx.getAdvancements().award(_adv, criteria);
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }
}
