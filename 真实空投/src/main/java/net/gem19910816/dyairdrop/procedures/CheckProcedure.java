package net.gem19910816.dyairdrop.procedures;

import net.gem19910816.dyairdrop.core.Vars;

import net.gem19910816.dyairdrop.core.Commands;

import net.gem19910816.dyairdrop.core.Blocks;

import net.gem19910816.dyairdrop.core.Nbt;

import net.gem19910816.dyairdrop.DyairdropMod;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.BuiltInRegistries;
import net.gem19910816.dyairdrop.init.DyairdropModSounds;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class CheckProcedure {
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
               String _setval = "Z";
               Vars.of(entity).ifPresentData(capability -> {
                  capability.passwordre = _setval;
                  capability.syncPlayerVariables(entity);
               });
               if (!world.isClientSide()) {
                  BlockPos _bp = BlockPos.containing(x, y, z);
                  BlockEntity _blockEntity = world.getBlockEntity(_bp);
                  BlockState _bs = world.getBlockState(_bp);
                  if (_blockEntity != null) {
                     _blockEntity.getPersistentData().putString("valid", "shutdown");
                  }

                  if (world instanceof Level _level) {
                     _level.sendBlockUpdated(_bp, _bs, _bs, 3);
                  }
               }

               DyairdropMod.queueServerWork(
                  7,
                  () -> {
                     if (Nbt.getString(world, BlockPos.containing(x, y, z), "pw").length() >= 2) {
                        String _setvalx = Vars.of(entity)
                              .passwordre
                           + Nbt.getString(world, BlockPos.containing(x, y, z), "pw").substring(0, 1);
                        Vars.of(entity).ifPresentData(capability -> {
                           capability.passwordre = _setvalx;
                           capability.syncPlayerVariables(entity);
                        });
                        if (world instanceof Level _levelx) {
                           if (!_levelx.isClientSide()) {
                              _levelx.playSound(
                                 null,
                                 BlockPos.containing(x, y, z),
                                 DyairdropModSounds.CHECK.get(),
                                 SoundSource.BLOCKS,
                                 1.0F,
                                 1.0F
                              );
                           } else {
                              _levelx.playLocalSound(
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
                  }
               );
               DyairdropMod.queueServerWork(
                  14,
                  () -> {
                     if (Nbt.getString(world, BlockPos.containing(x, y, z), "pw").length() >= 3) {
                        String _setvalx = Vars.of(entity)
                              .passwordre
                           + Nbt.getString(world, BlockPos.containing(x, y, z), "pw").substring(1, 2);
                        Vars.of(entity).ifPresentData(capability -> {
                           capability.passwordre = _setvalx;
                           capability.syncPlayerVariables(entity);
                        });
                        if (world instanceof Level _levelx) {
                           if (!_levelx.isClientSide()) {
                              _levelx.playSound(
                                 null,
                                 BlockPos.containing(x, y, z),
                                 DyairdropModSounds.CHECK.get(),
                                 SoundSource.BLOCKS,
                                 1.0F,
                                 1.0F
                              );
                           } else {
                              _levelx.playLocalSound(
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
                  }
               );
               DyairdropMod.queueServerWork(
                  21,
                  () -> {
                     if (Nbt.getString(world, BlockPos.containing(x, y, z), "pw").length() >= 4) {
                        String _setvalx = Vars.of(entity)
                              .passwordre
                           + Nbt.getString(world, BlockPos.containing(x, y, z), "pw").substring(2, 3);
                        Vars.of(entity).ifPresentData(capability -> {
                           capability.passwordre = _setvalx;
                           capability.syncPlayerVariables(entity);
                        });
                        if (world instanceof Level _levelx) {
                           if (!_levelx.isClientSide()) {
                              _levelx.playSound(
                                 null,
                                 BlockPos.containing(x, y, z),
                                 DyairdropModSounds.CHECK.get(),
                                 SoundSource.BLOCKS,
                                 1.0F,
                                 1.0F
                              );
                           } else {
                              _levelx.playLocalSound(
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
                  }
               );
               DyairdropMod.queueServerWork(
                  28,
                  () -> {
                     if (Nbt.getString(world, BlockPos.containing(x, y, z), "pw").length() >= 5) {
                        String _setvalx = Vars.of(entity)
                              .passwordre
                           + Nbt.getString(world, BlockPos.containing(x, y, z), "pw").substring(3, 4);
                        Vars.of(entity).ifPresentData(capability -> {
                           capability.passwordre = _setvalx;
                           capability.syncPlayerVariables(entity);
                        });
                        if (world instanceof Level _levelx) {
                           if (!_levelx.isClientSide()) {
                              _levelx.playSound(
                                 null,
                                 BlockPos.containing(x, y, z),
                                 DyairdropModSounds.CHECK.get(),
                                 SoundSource.BLOCKS,
                                 1.0F,
                                 1.0F
                              );
                           } else {
                              _levelx.playLocalSound(
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
                  }
               );
               DyairdropMod.queueServerWork(
                  35,
                  () -> {
                     if (Nbt.getString(world, BlockPos.containing(x, y, z), "pw").length() >= 6) {
                        String _setvalx = Vars.of(entity)
                              .passwordre
                           + Nbt.getString(world, BlockPos.containing(x, y, z), "pw").substring(4, 5);
                        Vars.of(entity).ifPresentData(capability -> {
                           capability.passwordre = _setvalx;
                           capability.syncPlayerVariables(entity);
                        });
                        if (world instanceof Level _levelx) {
                           if (!_levelx.isClientSide()) {
                              _levelx.playSound(
                                 null,
                                 BlockPos.containing(x, y, z),
                                 DyairdropModSounds.CHECK.get(),
                                 SoundSource.BLOCKS,
                                 1.0F,
                                 1.0F
                              );
                           } else {
                              _levelx.playLocalSound(
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
                  }
               );
               DyairdropMod.queueServerWork(
                  42,
                  () -> {
                     if (Nbt.getString(world, BlockPos.containing(x, y, z), "pw").length() >= 6) {
                        String _setvalx = Vars.of(entity)
                              .passwordre
                           + Nbt.getString(world, BlockPos.containing(x, y, z), "pw").substring(5, 6);
                        Vars.of(entity).ifPresentData(capability -> {
                           capability.passwordre = _setvalx;
                           capability.syncPlayerVariables(entity);
                        });
                        if (world instanceof Level _levelx) {
                           if (!_levelx.isClientSide()) {
                              _levelx.playSound(
                                 null,
                                 BlockPos.containing(x, y, z),
                                 DyairdropModSounds.CHECK.get(),
                                 SoundSource.BLOCKS,
                                 1.0F,
                                 1.0F
                              );
                           } else {
                              _levelx.playLocalSound(
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

                        DyairdropMod.queueServerWork(
                           7,
                           () -> {
                              if (world instanceof Level _levelxx) {
                                 if (!_levelxx.isClientSide()) {
                                    _levelxx.playSound(
                                       null,
                                       BlockPos.containing(x, y, z),
                                       DyairdropModSounds.PWCORRECT.get(),
                                       SoundSource.BLOCKS,
                                       1.0F,
                                       1.0F
                                    );
                                 } else {
                                    _levelxx.playLocalSound(
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

                              String _setvalxx = Vars.of(entity)
                                    .passwordre
                                 + "Y";
                              Vars.of(entity).ifPresentData(capability -> {
                                 capability.passwordre = _setvalxx;
                                 capability.syncPlayerVariables(entity);
                              });
                              if (entity instanceof Player _player && !_player.level().isClientSide()) {
                                 _player.displayClientMessage(Component.literal("解锁成功！"), false);
                              }

                              int _value = 1;
                              BlockPos _pos = BlockPos.containing(x, y, z);
                              BlockState _bsx = world.getBlockState(_pos);
                              if (_bsx.getBlock().getStateDefinition().getProperty("animation") instanceof IntegerProperty _integerProp
                                 && _integerProp.getPossibleValues().contains(_value)) {
                                 world.setBlock(_pos, (BlockState)_bsx.setValue(_integerProp, _value), 3);
                              }

                              DyairdropMod.queueServerWork(
                                 20,
                                 () -> {
                                    if (world instanceof ServerLevel _levelxxx) {
                                       Commands.run(_levelxxx, x, y, z, "setblock ~ ~ ~ "
                                                + BuiltInRegistries.BLOCK.getKey(world.getBlockState(BlockPos.containing(x, y, z)).getBlock()).toString()
                                                + "open[facing="
                                                + Blocks.facingOf(world.getBlockState(BlockPos.containing(x, y, z)))
                                                + "]{LootTable:\""
                                                + Nbt.getString(world, BlockPos.containing(x, y, z), "loot")
                                                + "\"} replace"
                                          );
                                    }

                                    int _valuex = 2;
                                    BlockPos _posx = BlockPos.containing(x, y, z);
                                    BlockState _bsxx = world.getBlockState(_posx);
                                    if (_bsxx.getBlock().getStateDefinition().getProperty("animation") instanceof IntegerProperty _integerPropx
                                       && _integerPropx.getPossibleValues().contains(_valuex)) {
                                       world.setBlock(_posx, (BlockState)_bsxx.setValue(_integerPropx, _valuex), 3);
                                    }
                                 }
                              );
                           }
                        );
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
