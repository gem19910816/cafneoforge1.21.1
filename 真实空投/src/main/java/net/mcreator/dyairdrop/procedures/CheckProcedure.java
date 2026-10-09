package net.mcreator.dyairdrop.procedures;

import net.mcreator.dyairdrop.DyairdropMod;
import net.mcreator.dyairdrop.compat.CrateCompat;
import net.mcreator.dyairdrop.network.DyairdropModVariables;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.core.Direction.AxisDirection;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.minecraft.core.registries.BuiltInRegistries;

public class CheckProcedure {
   public CheckProcedure() {
   }

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
         input = DyairdropModVariables.get(entity)
            .passwordre;
         if (!input.chars().anyMatch(Character::isUpperCase)) {
            if (DyairdropModVariables.get(entity)
                  .passwordre
                  .length()
               == 6) {
               String _setval = "Z";
               DyairdropModVariables.with(entity, capability -> {
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
                     if ((new Object() {
                        public String getValue(LevelAccessor world, BlockPos pos, String tag) {
                           BlockEntity blockEntity = world.getBlockEntity(pos);
                           return blockEntity != null ? blockEntity.getPersistentData().getString(tag) : "";
                        }
                     }).getValue(world, BlockPos.containing(x, y, z), "pw").length() >= 2) {
                        String _setvalx = DyairdropModVariables.get(entity)
                              .passwordre
                           + (new Object() {
                              public String getValue(LevelAccessor world, BlockPos pos, String tag) {
                                 BlockEntity blockEntity = world.getBlockEntity(pos);
                                 return blockEntity != null ? blockEntity.getPersistentData().getString(tag) : "";
                              }
                           }).getValue(world, BlockPos.containing(x, y, z), "pw").substring(0, 1);
                        DyairdropModVariables.with(entity, capability -> {
                           capability.passwordre = _setvalx;
                           capability.syncPlayerVariables(entity);
                        });
                        if (world instanceof Level _levelx) {
                           if (!_levelx.isClientSide()) {
                              _levelx.playSound(
                                 null,
                                 BlockPos.containing(x, y, z),
                                 (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("dyairdrop:check")),
                                 SoundSource.BLOCKS,
                                 1.0F,
                                 1.0F
                              );
                           } else {
                              _levelx.playLocalSound(
                                 x,
                                 y,
                                 z,
                                 (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("dyairdrop:check")),
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
                     if ((new Object() {
                        public String getValue(LevelAccessor world, BlockPos pos, String tag) {
                           BlockEntity blockEntity = world.getBlockEntity(pos);
                           return blockEntity != null ? blockEntity.getPersistentData().getString(tag) : "";
                        }
                     }).getValue(world, BlockPos.containing(x, y, z), "pw").length() >= 3) {
                        String _setvalx = DyairdropModVariables.get(entity)
                              .passwordre
                           + (new Object() {
                              public String getValue(LevelAccessor world, BlockPos pos, String tag) {
                                 BlockEntity blockEntity = world.getBlockEntity(pos);
                                 return blockEntity != null ? blockEntity.getPersistentData().getString(tag) : "";
                              }
                           }).getValue(world, BlockPos.containing(x, y, z), "pw").substring(1, 2);
                        DyairdropModVariables.with(entity, capability -> {
                           capability.passwordre = _setvalx;
                           capability.syncPlayerVariables(entity);
                        });
                        if (world instanceof Level _levelx) {
                           if (!_levelx.isClientSide()) {
                              _levelx.playSound(
                                 null,
                                 BlockPos.containing(x, y, z),
                                 (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("dyairdrop:check")),
                                 SoundSource.BLOCKS,
                                 1.0F,
                                 1.0F
                              );
                           } else {
                              _levelx.playLocalSound(
                                 x,
                                 y,
                                 z,
                                 (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("dyairdrop:check")),
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
                     if ((new Object() {
                        public String getValue(LevelAccessor world, BlockPos pos, String tag) {
                           BlockEntity blockEntity = world.getBlockEntity(pos);
                           return blockEntity != null ? blockEntity.getPersistentData().getString(tag) : "";
                        }
                     }).getValue(world, BlockPos.containing(x, y, z), "pw").length() >= 4) {
                        String _setvalx = DyairdropModVariables.get(entity)
                              .passwordre
                           + (new Object() {
                              public String getValue(LevelAccessor world, BlockPos pos, String tag) {
                                 BlockEntity blockEntity = world.getBlockEntity(pos);
                                 return blockEntity != null ? blockEntity.getPersistentData().getString(tag) : "";
                              }
                           }).getValue(world, BlockPos.containing(x, y, z), "pw").substring(2, 3);
                        DyairdropModVariables.with(entity, capability -> {
                           capability.passwordre = _setvalx;
                           capability.syncPlayerVariables(entity);
                        });
                        if (world instanceof Level _levelx) {
                           if (!_levelx.isClientSide()) {
                              _levelx.playSound(
                                 null,
                                 BlockPos.containing(x, y, z),
                                 (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("dyairdrop:check")),
                                 SoundSource.BLOCKS,
                                 1.0F,
                                 1.0F
                              );
                           } else {
                              _levelx.playLocalSound(
                                 x,
                                 y,
                                 z,
                                 (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("dyairdrop:check")),
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
                     if ((new Object() {
                        public String getValue(LevelAccessor world, BlockPos pos, String tag) {
                           BlockEntity blockEntity = world.getBlockEntity(pos);
                           return blockEntity != null ? blockEntity.getPersistentData().getString(tag) : "";
                        }
                     }).getValue(world, BlockPos.containing(x, y, z), "pw").length() >= 5) {
                        String _setvalx = DyairdropModVariables.get(entity)
                              .passwordre
                           + (new Object() {
                              public String getValue(LevelAccessor world, BlockPos pos, String tag) {
                                 BlockEntity blockEntity = world.getBlockEntity(pos);
                                 return blockEntity != null ? blockEntity.getPersistentData().getString(tag) : "";
                              }
                           }).getValue(world, BlockPos.containing(x, y, z), "pw").substring(3, 4);
                        DyairdropModVariables.with(entity, capability -> {
                           capability.passwordre = _setvalx;
                           capability.syncPlayerVariables(entity);
                        });
                        if (world instanceof Level _levelx) {
                           if (!_levelx.isClientSide()) {
                              _levelx.playSound(
                                 null,
                                 BlockPos.containing(x, y, z),
                                 (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("dyairdrop:check")),
                                 SoundSource.BLOCKS,
                                 1.0F,
                                 1.0F
                              );
                           } else {
                              _levelx.playLocalSound(
                                 x,
                                 y,
                                 z,
                                 (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("dyairdrop:check")),
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
                     if ((new Object() {
                        public String getValue(LevelAccessor world, BlockPos pos, String tag) {
                           BlockEntity blockEntity = world.getBlockEntity(pos);
                           return blockEntity != null ? blockEntity.getPersistentData().getString(tag) : "";
                        }
                     }).getValue(world, BlockPos.containing(x, y, z), "pw").length() >= 6) {
                        String _setvalx = DyairdropModVariables.get(entity)
                              .passwordre
                           + (new Object() {
                              public String getValue(LevelAccessor world, BlockPos pos, String tag) {
                                 BlockEntity blockEntity = world.getBlockEntity(pos);
                                 return blockEntity != null ? blockEntity.getPersistentData().getString(tag) : "";
                              }
                           }).getValue(world, BlockPos.containing(x, y, z), "pw").substring(4, 5);
                        DyairdropModVariables.with(entity, capability -> {
                           capability.passwordre = _setvalx;
                           capability.syncPlayerVariables(entity);
                        });
                        if (world instanceof Level _levelx) {
                           if (!_levelx.isClientSide()) {
                              _levelx.playSound(
                                 null,
                                 BlockPos.containing(x, y, z),
                                 (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("dyairdrop:check")),
                                 SoundSource.BLOCKS,
                                 1.0F,
                                 1.0F
                              );
                           } else {
                              _levelx.playLocalSound(
                                 x,
                                 y,
                                 z,
                                 (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("dyairdrop:check")),
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
                     if ((new Object() {
                        public String getValue(LevelAccessor world, BlockPos pos, String tag) {
                           BlockEntity blockEntity = world.getBlockEntity(pos);
                           return blockEntity != null ? blockEntity.getPersistentData().getString(tag) : "";
                        }
                     }).getValue(world, BlockPos.containing(x, y, z), "pw").length() >= 6) {
                        String _setvalx = DyairdropModVariables.get(entity)
                              .passwordre
                           + (new Object() {
                              public String getValue(LevelAccessor world, BlockPos pos, String tag) {
                                 BlockEntity blockEntity = world.getBlockEntity(pos);
                                 return blockEntity != null ? blockEntity.getPersistentData().getString(tag) : "";
                              }
                           }).getValue(world, BlockPos.containing(x, y, z), "pw").substring(5, 6);
                        DyairdropModVariables.with(entity, capability -> {
                           capability.passwordre = _setvalx;
                           capability.syncPlayerVariables(entity);
                        });
                        if (world instanceof Level _levelx) {
                           if (!_levelx.isClientSide()) {
                              _levelx.playSound(
                                 null,
                                 BlockPos.containing(x, y, z),
                                 (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("dyairdrop:check")),
                                 SoundSource.BLOCKS,
                                 1.0F,
                                 1.0F
                              );
                           } else {
                              _levelx.playLocalSound(
                                 x,
                                 y,
                                 z,
                                 (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("dyairdrop:check")),
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
                                       (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("dyairdrop:pwcorrect")),
                                       SoundSource.BLOCKS,
                                       1.0F,
                                       1.0F
                                    );
                                 } else {
                                    _levelxx.playLocalSound(
                                       x,
                                       y,
                                       z,
                                       (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("dyairdrop:pwcorrect")),
                                       SoundSource.BLOCKS,
                                       1.0F,
                                       1.0F,
                                       false
                                    );
                                 }
                              }

                              String _setvalxx = DyairdropModVariables.get(entity)
                                    .passwordre
                                 + "Y";
                              DyairdropModVariables.with(entity, capability -> {
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
                                 CrateCompat.setBlockAnimation(world, _pos, _value);
                              }

                              DyairdropMod.queueServerWork(
                                 20,
                                 () -> {
                                    CrateCompat.openCrate(world, BlockPos.containing(x, y, z));

                                    int _valuex = 2;
                                    BlockPos _posx = BlockPos.containing(x, y, z);
                                    BlockState _bsxx = world.getBlockState(_posx);
                                    if (_bsxx.getBlock().getStateDefinition().getProperty("animation") instanceof IntegerProperty _integerPropx
                                       && _integerPropx.getPossibleValues().contains(_valuex)) {
                                       CrateCompat.setBlockAnimation(world, _posx, _valuex);
                                    }
                                 }
                              );
                           }
                        );
                     }
                  }
               );
            } else {
               final String _setval1 = "";
               DyairdropModVariables.with(entity, capability -> {
                  capability.passwordre = _setval1;
                  capability.syncPlayerVariables(entity);
               });
            }
         } else if (!DyairdropModVariables.get(entity)
            .passwordre
            .contains("Z")) {
            final String _setval2 = "";
            DyairdropModVariables.with(entity, capability -> {
               capability.passwordre = _setval2;
               capability.syncPlayerVariables(entity);
            });
         }
      }
   }
}
