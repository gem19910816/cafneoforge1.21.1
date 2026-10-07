package net.mcreator.dyairdrop.procedures;

import net.gem19910816.dyairdrop.core.Vars;

import net.mcreator.dyairdrop.DyairdropMod;
import net.mcreator.dyairdrop.network.DyairdropModVariables;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.mcreator.dyairdrop.init.DyairdropModSounds;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class ChecknewliteProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         Entity entity1 = null;
         double gx = 0.0;
         double gy = 0.0;
         double gz = 0.0;
         double i = 0.0;
         String input = "";
         String PW = "";
         String password_panel = "";
         String j = "";
         input = Vars.of(entity)
            .passwordre;
         if (!input.chars().anyMatch(Character::isUpperCase)) {
            if (Vars.of(entity)
                  .passwordre
                  .length()
               == 6) {
               String _setval = Vars.of(entity)
                  .passwordre;
               Vars.of(entity).ifPresentData(capability -> {
                  capability.keyre = _setval;
                  capability.syncPlayerVariables(entity);
               });
               String _setvalb = "Z";
               Vars.of(entity).ifPresentData(capability -> {
                  capability.passwordre = _setvalb;
                  capability.syncPlayerVariables(entity);
               });
               DyairdropMod.queueServerWork(
                  7,
                  () -> {
                     String _setvalx = Vars.of(entity)
                           .passwordre
                        + Vars.of(entity)
                           .keyre
                           .substring(0, 1);
                     Vars.of(entity).ifPresentData(capability -> {
                        capability.passwordre = _setvalx;
                        capability.syncPlayerVariables(entity);
                     });
                     if (world instanceof Level _level) {
                        if (!_level.isClientSide()) {
                           _level.playSound(
                              null,
                              BlockPos.containing(x, y, z),
                              DyairdropModSounds.CHECK.get(),
                              SoundSource.BLOCKS,
                              1.0F,
                              1.0F
                           );
                        } else {
                           _level.playLocalSound(
                              x,
                              y,
                              z,
                              DyairdropModSounds.CHECK.get(),
                              SoundSource.BLOCKS,
                              1.0F,
                              1.0F,
                              false
                           );
                        }
                     }
                  }
               );
               DyairdropMod.queueServerWork(
                  14,
                  () -> {
                     String _setvalx = Vars.of(entity)
                           .passwordre
                        + Vars.of(entity)
                           .keyre
                           .substring(1, 2);
                     Vars.of(entity).ifPresentData(capability -> {
                        capability.passwordre = _setvalx;
                        capability.syncPlayerVariables(entity);
                     });
                     if (world instanceof Level _level) {
                        if (!_level.isClientSide()) {
                           _level.playSound(
                              null,
                              BlockPos.containing(x, y, z),
                              DyairdropModSounds.CHECK.get(),
                              SoundSource.BLOCKS,
                              1.0F,
                              1.0F
                           );
                        } else {
                           _level.playLocalSound(
                              x,
                              y,
                              z,
                              DyairdropModSounds.CHECK.get(),
                              SoundSource.BLOCKS,
                              1.0F,
                              1.0F,
                              false
                           );
                        }
                     }
                  }
               );
               DyairdropMod.queueServerWork(
                  21,
                  () -> {
                     String _setvalx = Vars.of(entity)
                           .passwordre
                        + Vars.of(entity)
                           .keyre
                           .substring(2, 3);
                     Vars.of(entity).ifPresentData(capability -> {
                        capability.passwordre = _setvalx;
                        capability.syncPlayerVariables(entity);
                     });
                     if (world instanceof Level _level) {
                        if (!_level.isClientSide()) {
                           _level.playSound(
                              null,
                              BlockPos.containing(x, y, z),
                              DyairdropModSounds.CHECK.get(),
                              SoundSource.BLOCKS,
                              1.0F,
                              1.0F
                           );
                        } else {
                           _level.playLocalSound(
                              x,
                              y,
                              z,
                              DyairdropModSounds.CHECK.get(),
                              SoundSource.BLOCKS,
                              1.0F,
                              1.0F,
                              false
                           );
                        }
                     }
                  }
               );
               DyairdropMod.queueServerWork(
                  28,
                  () -> {
                     String _setvalx = Vars.of(entity)
                           .passwordre
                        + Vars.of(entity)
                           .keyre
                           .substring(3, 4);
                     Vars.of(entity).ifPresentData(capability -> {
                        capability.passwordre = _setvalx;
                        capability.syncPlayerVariables(entity);
                     });
                     if (world instanceof Level _level) {
                        if (!_level.isClientSide()) {
                           _level.playSound(
                              null,
                              BlockPos.containing(x, y, z),
                              DyairdropModSounds.CHECK.get(),
                              SoundSource.BLOCKS,
                              1.0F,
                              1.0F
                           );
                        } else {
                           _level.playLocalSound(
                              x,
                              y,
                              z,
                              DyairdropModSounds.CHECK.get(),
                              SoundSource.BLOCKS,
                              1.0F,
                              1.0F,
                              false
                           );
                        }
                     }
                  }
               );
               DyairdropMod.queueServerWork(
                  35,
                  () -> {
                     String _setvalx = Vars.of(entity)
                           .passwordre
                        + Vars.of(entity)
                           .keyre
                           .substring(4, 5);
                     Vars.of(entity).ifPresentData(capability -> {
                        capability.passwordre = _setvalx;
                        capability.syncPlayerVariables(entity);
                     });
                     if (world instanceof Level _level) {
                        if (!_level.isClientSide()) {
                           _level.playSound(
                              null,
                              BlockPos.containing(x, y, z),
                              DyairdropModSounds.CHECK.get(),
                              SoundSource.BLOCKS,
                              1.0F,
                              1.0F
                           );
                        } else {
                           _level.playLocalSound(
                              x,
                              y,
                              z,
                              DyairdropModSounds.CHECK.get(),
                              SoundSource.BLOCKS,
                              1.0F,
                              1.0F,
                              false
                           );
                        }
                     }
                  }
               );
               DyairdropMod.queueServerWork(
                  42,
                  () -> {
                     String _setvalx = Vars.of(entity)
                           .passwordre
                        + Vars.of(entity)
                           .keyre
                           .substring(5, 6);
                     Vars.of(entity).ifPresentData(capability -> {
                        capability.passwordre = _setvalx;
                        capability.syncPlayerVariables(entity);
                     });
                     if (world instanceof Level _level) {
                        if (!_level.isClientSide()) {
                           _level.playSound(
                              null,
                              BlockPos.containing(x, y, z),
                              DyairdropModSounds.CHECK.get(),
                              SoundSource.BLOCKS,
                              1.0F,
                              1.0F
                           );
                        } else {
                           _level.playLocalSound(
                              x,
                              y,
                              z,
                              DyairdropModSounds.CHECK.get(),
                              SoundSource.BLOCKS,
                              1.0F,
                              1.0F,
                              false
                           );
                        }
                     }
                  }
               );
               DyairdropMod.queueServerWork(
                  49,
                  () -> {
                     if (world instanceof Level _level) {
                        if (!_level.isClientSide()) {
                           _level.playSound(
                              null,
                              BlockPos.containing(x, y, z),
                              DyairdropModSounds.PWCORRECT.get(),
                              SoundSource.BLOCKS,
                              1.0F,
                              1.0F
                           );
                        } else {
                           _level.playLocalSound(
                              x,
                              y,
                              z,
                              DyairdropModSounds.PWCORRECT.get(),
                              SoundSource.BLOCKS,
                              1.0F,
                              1.0F,
                              false
                           );
                        }
                     }

                     String _setvalx = Vars.of(entity)
                           .passwordre
                        + "Y";
                     Vars.of(entity).ifPresentData(capability -> {
                        capability.passwordre = _setvalx;
                        capability.syncPlayerVariables(entity);
                     });
                     if (entity instanceof Player _player && !_player.level().isClientSide()) {
                        _player.displayClientMessage(Component.literal("解锁成功！"), false);
                     }

                     if (!world.isClientSide()) {
                        BlockPos _bp = BlockPos.containing(x, y, z);
                        BlockEntity _blockEntity = world.getBlockEntity(_bp);
                        BlockState _bs = world.getBlockState(_bp);
                        if (_blockEntity != null) {
                           _blockEntity.getPersistentData().putBoolean("isopen", true);
                        }

                        if (world instanceof Level _level) {
                           _level.sendBlockUpdated(_bp, _bs, _bs, 3);
                        }
                     }
                  }
               );
            } else {
               String _setval = "";
               Vars.of(entity).ifPresentData(capability -> {
                  capability.passwordre = _setval;
                  capability.syncPlayerVariables(entity);
               });
            }
         } else if (!Vars.of(entity)
            .passwordre
            .contains("Z")) {
            String _setval = "";
            Vars.of(entity).ifPresentData(capability -> {
               capability.passwordre = _setval;
               capability.syncPlayerVariables(entity);
            });
         }
      }
   }
}
