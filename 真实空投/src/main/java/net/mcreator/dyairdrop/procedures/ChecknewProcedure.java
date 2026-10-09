package net.mcreator.dyairdrop.procedures;

import net.mcreator.dyairdrop.DyairdropMod;
import net.mcreator.dyairdrop.network.DyairdropModVariables;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.registries.BuiltInRegistries;
import net.mcreator.dyairdrop.compat.SideCompat;

public class ChecknewProcedure {
   public ChecknewProcedure() {
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
               DyairdropMod.queueServerWork(
                  7,
                  () -> {
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
                     if (world instanceof Level _level) {
                        if (!_level.isClientSide()) {
                           _level.playSound(
                              null,
                              BlockPos.containing(x, y, z),
                              (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("dyairdrop:check")),
                              SoundSource.BLOCKS,
                              1.0F,
                              1.0F
                           );
                        } else if (SideCompat.isClientThread()) {
                           _level.playLocalSound(
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
               );
               DyairdropMod.queueServerWork(
                  14,
                  () -> {
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
                     if (world instanceof Level _level) {
                        if (!_level.isClientSide()) {
                           _level.playSound(
                              null,
                              BlockPos.containing(x, y, z),
                              (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("dyairdrop:check")),
                              SoundSource.BLOCKS,
                              1.0F,
                              1.0F
                           );
                        } else if (SideCompat.isClientThread()) {
                           _level.playLocalSound(
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
               );
               DyairdropMod.queueServerWork(
                  21,
                  () -> {
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
                     if (world instanceof Level _level) {
                        if (!_level.isClientSide()) {
                           _level.playSound(
                              null,
                              BlockPos.containing(x, y, z),
                              (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("dyairdrop:check")),
                              SoundSource.BLOCKS,
                              1.0F,
                              1.0F
                           );
                        } else if (SideCompat.isClientThread()) {
                           _level.playLocalSound(
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
               );
               DyairdropMod.queueServerWork(
                  28,
                  () -> {
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
                     if (world instanceof Level _level) {
                        if (!_level.isClientSide()) {
                           _level.playSound(
                              null,
                              BlockPos.containing(x, y, z),
                              (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("dyairdrop:check")),
                              SoundSource.BLOCKS,
                              1.0F,
                              1.0F
                           );
                        } else if (SideCompat.isClientThread()) {
                           _level.playLocalSound(
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
               );
               DyairdropMod.queueServerWork(
                  35,
                  () -> {
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
                     if (world instanceof Level _level) {
                        if (!_level.isClientSide()) {
                           _level.playSound(
                              null,
                              BlockPos.containing(x, y, z),
                              (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("dyairdrop:check")),
                              SoundSource.BLOCKS,
                              1.0F,
                              1.0F
                           );
                        } else if (SideCompat.isClientThread()) {
                           _level.playLocalSound(
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
               );
               DyairdropMod.queueServerWork(
                  42,
                  () -> {
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
                     if (world instanceof Level _level) {
                        if (!_level.isClientSide()) {
                           _level.playSound(
                              null,
                              BlockPos.containing(x, y, z),
                              (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("dyairdrop:check")),
                              SoundSource.BLOCKS,
                              1.0F,
                              1.0F
                           );
                        } else if (SideCompat.isClientThread()) {
                           _level.playLocalSound(
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
                           if (world instanceof Level _levelx) {
                              if (!_levelx.isClientSide()) {
                                 _levelx.playSound(
                                    null,
                                    BlockPos.containing(x, y, z),
                                    (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("dyairdrop:pwcorrect")),
                                    SoundSource.BLOCKS,
                                    1.0F,
                                    1.0F
                                 );
                              } else if (SideCompat.isClientThread()) {
                                 _levelx.playLocalSound(
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

                           if (!world.isClientSide()) {
                              BlockPos _bp = BlockPos.containing(x, y, z);
                              BlockEntity _blockEntity = world.getBlockEntity(_bp);
                              BlockState _bs = world.getBlockState(_bp);
                              if (_blockEntity != null) {
                                 _blockEntity.getPersistentData().putBoolean("isopen", true);
                              }

                              if (world instanceof Level _levelx) {
                                 _levelx.sendBlockUpdated(_bp, _bs, _bs, 3);
                              }
                           }
                        }
                     );
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
