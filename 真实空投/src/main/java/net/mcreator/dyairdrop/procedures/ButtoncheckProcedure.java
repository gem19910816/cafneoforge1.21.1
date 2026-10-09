package net.mcreator.dyairdrop.procedures;

import java.text.DecimalFormat;
import java.util.HashMap;
import net.mcreator.dyairdrop.DyairdropMod;
import net.mcreator.dyairdrop.compat.CrateCompat;
import net.mcreator.dyairdrop.configuration.AirdropconfigConfiguration;
import net.mcreator.dyairdrop.network.DyairdropModVariables;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.core.Direction.AxisDirection;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
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
import net.mcreator.dyairdrop.network.PanelText;
import net.mcreator.dyairdrop.compat.SideCompat;

public class ButtoncheckProcedure {
   public ButtoncheckProcedure() {
   }

   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, HashMap guistate) {
      if (entity != null && guistate != null) {
         Entity entity1 = null;
         String pw = "";
         String j = "";
         String password_panel = "";
         double gx = 0.0;
         double gy = 0.0;
         double gz = 0.0;
         double i = 0.0;
         double n = 0.0;
         if (DyairdropModVariables.get(entity)
               .showlight
            == 0.0) {
            password_panel = DyairdropModVariables.get(entity)
               .password;
            password_panel = password_panel.replaceAll(" ", "");
            final String _passwordPanel = password_panel;
            DyairdropModVariables.with(entity, capability -> {
               capability.password = _passwordPanel;
               capability.syncPlayerVariables(entity);
            });
            if ((new Object() {
               public String getValue(LevelAccessor world, BlockPos pos, String tag) {
                  BlockEntity blockEntity = world.getBlockEntity(pos);
                  return blockEntity != null ? blockEntity.getPersistentData().getString(tag) : "";
               }
            }).getValue(world, BlockPos.containing(x, y, z), "key").length() <= 1) {
               for (int index0 = 0; index0 < 6; index0++) {
                  pw = pw + new DecimalFormat("##").format(Mth.nextDouble(RandomSource.create(), 0.0, 9.0));
                  if (!world.isClientSide()) {
                     BlockPos _bp = BlockPos.containing(x, y, z);
                     BlockEntity _blockEntity = world.getBlockEntity(_bp);
                     BlockState _bs = world.getBlockState(_bp);
                     if (_blockEntity != null) {
                        _blockEntity.getPersistentData().putString("key", pw);
                     }

                     if (world instanceof Level _level) {
                        _level.sendBlockUpdated(_bp, _bs, _bs, 3);
                     }
                  }
               }
            }

            if (DyairdropModVariables.get(entity)
                  .showlight
               != 1.0) {
               String _setval = "";
               DyairdropModVariables.with(entity, capability -> {
                  capability.pw = _setval;
                  capability.syncPlayerVariables(entity);
               });
               i = 0.0;
            }

            if (password_panel.length() == (new Object() {
                  public String getValue(LevelAccessor world, BlockPos pos, String tag) {
                     BlockEntity blockEntity = world.getBlockEntity(pos);
                     return blockEntity != null ? blockEntity.getPersistentData().getString(tag) : "";
                  }
               }).getValue(world, BlockPos.containing(x, y, z), "key").length()
               && !PanelText.from(guistate).isEmpty()) {
               for (int index1 = 0; index1 < password_panel.length(); index1++) {
                  if (password_panel.substring((int)i, (int)(i + 1.0)).equals((new Object() {
                     public String getValue(LevelAccessor world, BlockPos pos, String tag) {
                        BlockEntity blockEntity = world.getBlockEntity(pos);
                        return blockEntity != null ? blockEntity.getPersistentData().getString(tag) : "";
                     }
                  }).getValue(world, BlockPos.containing(x, y, z), "key").substring((int)i, (int)(i + 1.0)))) {
                     j = j + "1";
                  } else {
                     j = j + "0";
                  }

                  i++;
               }

               final double _setval1 = 1.0;
               DyairdropModVariables.with(entity, capability -> {
                  capability.showlight = _setval1;
                  capability.syncPlayerVariables(entity);
               });
               final double _setval2 = (double)world.dayTime();
               DyairdropModVariables.with(entity, capability -> {
                  capability.keyticking = _setval2;
                  capability.syncPlayerVariables(entity);
               });
               final String _setvalPw = j;
               DyairdropModVariables.with(entity, capability -> {
                  capability.pw = _setvalPw;
                  capability.syncPlayerVariables(entity);
               });
               DyairdropMod.queueServerWork(
                  1,
                  () -> {
                     if (world instanceof Level _levelx) {
                        if (!_levelx.isClientSide()) {
                           _levelx.playSound(
                              null,
                              BlockPos.containing(x, y, z),
                              (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("dyairdrop:check")),
                              SoundSource.BLOCKS,
                              5.0F,
                              1.0F
                           );
                        } else if (SideCompat.isClientThread()) {
                           _levelx.playLocalSound(
                              x,
                              y,
                              z,
                              (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("dyairdrop:check")),
                              SoundSource.BLOCKS,
                              5.0F,
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
                                    (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("dyairdrop:check")),
                                    SoundSource.BLOCKS,
                                    5.0F,
                                    1.0F
                                 );
                              } else if (SideCompat.isClientThread()) {
                                 _levelxx.playLocalSound(
                                    x,
                                    y,
                                    z,
                                    (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("dyairdrop:check")),
                                    SoundSource.BLOCKS,
                                    5.0F,
                                    1.0F,
                                    false
                                 );
                              }
                           }

                           DyairdropMod.queueServerWork(
                              7,
                              () -> {
                                 if (world instanceof Level _levelxxx) {
                                    if (!_levelxxx.isClientSide()) {
                                       _levelxxx.playSound(
                                          null,
                                          BlockPos.containing(x, y, z),
                                          (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("dyairdrop:check")),
                                          SoundSource.BLOCKS,
                                          5.0F,
                                          1.0F
                                       );
                                    } else if (SideCompat.isClientThread()) {
                                       _levelxxx.playLocalSound(
                                          x,
                                          y,
                                          z,
                                          (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("dyairdrop:check")),
                                          SoundSource.BLOCKS,
                                          5.0F,
                                          1.0F,
                                          false
                                       );
                                    }
                                 }

                                 DyairdropMod.queueServerWork(
                                    7,
                                    () -> {
                                       if (world instanceof Level _levelxxxx) {
                                          if (!_levelxxxx.isClientSide()) {
                                             _levelxxxx.playSound(
                                                null,
                                                BlockPos.containing(x, y, z),
                                                (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("dyairdrop:check")),
                                                SoundSource.BLOCKS,
                                                5.0F,
                                                1.0F
                                             );
                                          } else if (SideCompat.isClientThread()) {
                                             _levelxxxx.playLocalSound(
                                                x,
                                                y,
                                                z,
                                                (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("dyairdrop:check")),
                                                SoundSource.BLOCKS,
                                                5.0F,
                                                1.0F,
                                                false
                                             );
                                          }
                                       }

                                       DyairdropMod.queueServerWork(
                                          7,
                                          () -> {
                                             if (world instanceof Level _levelxxxxx) {
                                                if (!_levelxxxxx.isClientSide()) {
                                                   _levelxxxxx.playSound(
                                                      null,
                                                      BlockPos.containing(x, y, z),
                                                      (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("dyairdrop:check")),
                                                      SoundSource.BLOCKS,
                                                      5.0F,
                                                      1.0F
                                                   );
                                                } else if (SideCompat.isClientThread()) {
                                                   _levelxxxxx.playLocalSound(
                                                      x,
                                                      y,
                                                      z,
                                                      (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("dyairdrop:check")),
                                                      SoundSource.BLOCKS,
                                                      5.0F,
                                                      1.0F,
                                                      false
                                                   );
                                                }
                                             }

                                             DyairdropMod.queueServerWork(
                                                7,
                                                () -> {
                                                   if (world instanceof Level _levelxxxxxx) {
                                                      if (!_levelxxxxxx.isClientSide()) {
                                                         _levelxxxxxx.playSound(
                                                            null,
                                                            BlockPos.containing(x, y, z),
                                                            (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("dyairdrop:check")),
                                                            SoundSource.BLOCKS,
                                                            5.0F,
                                                            1.0F
                                                         );
                                                      } else if (SideCompat.isClientThread()) {
                                                         _levelxxxxxx.playLocalSound(
                                                            x,
                                                            y,
                                                            z,
                                                            (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("dyairdrop:check")),
                                                            SoundSource.BLOCKS,
                                                            5.0F,
                                                            1.0F,
                                                            false
                                                         );
                                                      }
                                                   }
                                                }
                                             );
                                          }
                                       );
                                    }
                                 );
                              }
                           );
                        }
                     );
                  }
               );
               if (password_panel.equals((new Object() {
                  public String getValue(LevelAccessor world, BlockPos pos, String tag) {
                     BlockEntity blockEntity = world.getBlockEntity(pos);
                     return blockEntity != null ? blockEntity.getPersistentData().getString(tag) : "";
                  }
               }).getValue(world, BlockPos.containing(x, y, z), "key"))) {
                  if (!world.isClientSide()) {
                     BlockPos _bpx = BlockPos.containing(x, y, z);
                     BlockEntity _blockEntityx = world.getBlockEntity(_bpx);
                     BlockState _bsx = world.getBlockState(_bpx);
                     if (_blockEntityx != null) {
                        _blockEntityx.getPersistentData().putString("open", "1");
                     }

                     if (world instanceof Level _level) {
                        _level.sendBlockUpdated(_bpx, _bsx, _bsx, 3);
                     }
                  }

                  if (!world.isClientSide()) {
                     BlockPos _bpxx = BlockPos.containing(x, y, z);
                     BlockEntity _blockEntityxx = world.getBlockEntity(_bpxx);
                     BlockState _bsxx = world.getBlockState(_bpxx);
                     if (_blockEntityxx != null) {
                        _blockEntityxx.getPersistentData().putString("valid", "shutdown");
                     }

                     if (world instanceof Level _level) {
                        _level.sendBlockUpdated(_bpxx, _bsxx, _bsxx, 3);
                     }
                  }

                  DyairdropMod.queueServerWork(
                     45,
                     () -> {
                        if (world instanceof Level _levelx) {
                           if (!_levelx.isClientSide()) {
                              _levelx.playSound(
                                 null,
                                 BlockPos.containing(x, y, z),
                                 (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("dyairdrop:pwcorrect")),
                                 SoundSource.BLOCKS,
                                 5.0F,
                                 1.0F
                              );
                           } else if (SideCompat.isClientThread()) {
                              _levelx.playLocalSound(
                                 x,
                                 y,
                                 z,
                                 (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("dyairdrop:pwcorrect")),
                                 SoundSource.BLOCKS,
                                 5.0F,
                                 1.0F,
                                 false
                              );
                           }
                        }

                        int _value = 1;
                        BlockPos _pos = BlockPos.containing(x, y, z);
                        BlockState _bsxxx = world.getBlockState(_pos);
                        if (_bsxxx.getBlock().getStateDefinition().getProperty("animation") instanceof IntegerProperty _integerProp
                           && _integerProp.getPossibleValues().contains(_value)) {
                           CrateCompat.setBlockAnimation(world, _pos, _value);
                        }
                     }
                  );
                  DyairdropMod.queueServerWork(
                     84,
                     () -> {
                        CrateCompat.openCrate(world, BlockPos.containing(x, y, z));
                     }
                  );
               } else {
                  DyairdropMod.queueServerWork(
                     45,
                     () -> {
                        if (entity instanceof Player _playerx && !_playerx.level().isClientSide()) {
                           _playerx.displayClientMessage(Component.literal("密码错误"), false);
                        }

                        if (world instanceof Level _levelx) {
                           if (!_levelx.isClientSide()) {
                              _levelx.playSound(
                                 null,
                                 BlockPos.containing(x, y, z),
                                 (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("dyairdrop:pwwrong")),
                                 SoundSource.BLOCKS,
                                 1.0F,
                                 1.0F
                              );
                           } else if (SideCompat.isClientThread()) {
                              _levelx.playLocalSound(
                                 x,
                                 y,
                                 z,
                                 (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("dyairdrop:pwwrong")),
                                 SoundSource.BLOCKS,
                                 1.0F,
                                 1.0F,
                                 false
                              );
                           }
                        }

                        entity.hurt(
                           new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.MAGIC)),
                           (float)((Double)AirdropconfigConfiguration.ATTEMPTPUNISHMENT.get()).doubleValue()
                        );
                     }
                  );
               }
            } else {
               if (entity instanceof Player _player && !_player.level().isClientSide()) {
                  _player.displayClientMessage(Component.literal("长度错误"), false);
               }

               if (entity instanceof Player _player && !_player.level().isClientSide()) {
                  _player.displayClientMessage(Component.literal(password_panel), false);
               }
            }
         }
      }
   }
}
