package net.mcreator.dyairdrop.procedures;

import java.text.DecimalFormat;
import java.util.HashMap;
import net.mcreator.dyairdrop.DyairdropMod;
import net.mcreator.dyairdrop.configuration.AirdropconfigConfiguration;
import net.mcreator.dyairdrop.network.DyairdropModVariables;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.core.Direction.AxisDirection;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.mcreator.dyairdrop.init.DyairdropModSounds;
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

public class ButtoncheckProcedure {
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
         if (((DyairdropModVariables.PlayerVariables)entity.getData(DyairdropModVariables.PLAYER_VARIABLES_ATTACHMENT.get()))
               .showlight
            == 0.0) {
            gx = x;
            gy = y;
            gz = z;
            password_panel = ((DyairdropModVariables.PlayerVariables)entity.getData(DyairdropModVariables.PLAYER_VARIABLES_ATTACHMENT.get()))
               .password;
            password_panel = password_panel.replaceAll(" ", "");
            String _setval = password_panel;
            entity.getData(DyairdropModVariables.PLAYER_VARIABLES_ATTACHMENT.get()).ifPresentData(capability -> {
               capability.password = _setval;
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

            if (((DyairdropModVariables.PlayerVariables)entity.getData(DyairdropModVariables.PLAYER_VARIABLES_ATTACHMENT.get()))
                  .showlight
               != 1.0) {
               String _setvalb = "";
               entity.getData(DyairdropModVariables.PLAYER_VARIABLES_ATTACHMENT.get()).ifPresentData(capability -> {
                  capability.pw = _setvalb;
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
               && (guistate.containsKey("text:password_panel") ? ((EditBox)guistate.get("text:password_panel")).getValue() : "").length() > 0) {
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

               double _setvalx = 1.0;
               entity.getData(DyairdropModVariables.PLAYER_VARIABLES_ATTACHMENT.get()).ifPresentData(capability -> {
                  capability.showlight = _setvalx;
                  capability.syncPlayerVariables(entity);
               });
               double _setvalxx = ((Level)world).getDayTime();
               entity.getData(DyairdropModVariables.PLAYER_VARIABLES_ATTACHMENT.get()).ifPresentData(capability -> {
                  capability.keyticking = _setvalxx;
                  capability.syncPlayerVariables(entity);
               });
               String _setvalc = j;
               entity.getData(DyairdropModVariables.PLAYER_VARIABLES_ATTACHMENT.get()).ifPresentData(capability -> {
                  capability.pw = _setvalc;
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
                              DyairdropModSounds.CHECK.get(),
                              SoundSource.BLOCKS,
                              5.0F,
                              1.0F
                           );
                        } else {
                           _levelx.playLocalSound(
                              x,
                              y,
                              z,
                              DyairdropModSounds.CHECK.get(),
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
                                    DyairdropModSounds.CHECK.get(),
                                    SoundSource.BLOCKS,
                                    5.0F,
                                    1.0F
                                 );
                              } else {
                                 _levelxx.playLocalSound(
                                    x,
                                    y,
                                    z,
                                    DyairdropModSounds.CHECK.get(),
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
                                          DyairdropModSounds.CHECK.get(),
                                          SoundSource.BLOCKS,
                                          5.0F,
                                          1.0F
                                       );
                                    } else {
                                       _levelxxx.playLocalSound(
                                          x,
                                          y,
                                          z,
                                          DyairdropModSounds.CHECK.get(),
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
                                                DyairdropModSounds.CHECK.get(),
                                                SoundSource.BLOCKS,
                                                5.0F,
                                                1.0F
                                             );
                                          } else {
                                             _levelxxxx.playLocalSound(
                                                x,
                                                y,
                                                z,
                                                DyairdropModSounds.CHECK.get(),
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
                                                      DyairdropModSounds.CHECK.get(),
                                                      SoundSource.BLOCKS,
                                                      5.0F,
                                                      1.0F
                                                   );
                                                } else {
                                                   _levelxxxxx.playLocalSound(
                                                      x,
                                                      y,
                                                      z,
                                                      DyairdropModSounds.CHECK.get(),
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
                                                            DyairdropModSounds.CHECK.get(),
                                                            SoundSource.BLOCKS,
                                                            5.0F,
                                                            1.0F
                                                         );
                                                      } else {
                                                         _levelxxxxxx.playLocalSound(
                                                            x,
                                                            y,
                                                            z,
                                                            DyairdropModSounds.CHECK.get(),
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
                     BlockPos _bp = BlockPos.containing(gx, gy, gz);
                     BlockEntity _blockEntity = world.getBlockEntity(_bp);
                     BlockState _bs = world.getBlockState(_bp);
                     if (_blockEntity != null) {
                        _blockEntity.getPersistentData().putString("open", "1");
                     }

                     if (world instanceof Level _level) {
                        _level.sendBlockUpdated(_bp, _bs, _bs, 3);
                     }
                  }

                  if (!world.isClientSide()) {
                     BlockPos _bp = BlockPos.containing(gx, gy, gz);
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
                     45,
                     () -> {
                        if (world instanceof Level _levelx) {
                           if (!_levelx.isClientSide()) {
                              _levelx.playSound(
                                 null,
                                 BlockPos.containing(x, y, z),
                                 DyairdropModSounds.PWCORRECT.get(),
                                 SoundSource.BLOCKS,
                                 5.0F,
                                 1.0F
                              );
                           } else {
                              _levelx.playLocalSound(
                                 x,
                                 y,
                                 z,
                                 DyairdropModSounds.PWCORRECT.get(),
                                 SoundSource.BLOCKS,
                                 5.0F,
                                 1.0F,
                                 false
                              );
                           }
                        }

                        int _value = 1;
                        BlockPos _pos = BlockPos.containing(x, y, z);
                        BlockState _bsx = world.getBlockState(_pos);
                        if (_bsx.getBlock().getStateDefinition().getProperty("animation") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value)
                           )
                         {
                           world.setBlock(_pos, (BlockState)_bsx.setValue(_integerProp, _value), 3);
                        }
                     }
                  );
                  DyairdropMod.queueServerWork(
                     84,
                     () -> {
                        if (world instanceof ServerLevel _levelx) {
                           _levelx.getServer()
                              .getCommands()
                              .performPrefixedCommand(
                                 new CommandSourceStack(
                                       CommandSource.NULL,
                                       new Vec3(x, y, z),
                                       Vec2.ZERO,
                                       _levelx,
                                       4,
                                       "",
                                       Component.literal(""),
                                       _levelx.getServer(),
                                       null
                                    )
                                    .withSuppressedOutput(),
                                 "setblock ~ ~ ~ "
                                    + BuiltInRegistries.BLOCK.getKey(world.getBlockState(BlockPos.containing(x, y, z)).getBlock()).toString()
                                    + "open[facing="
                                    + (new Object() {
                                          public Direction getDirection(BlockState _bs) {
                                             if (_bs.getBlock().getStateDefinition().getProperty("facing") instanceof DirectionProperty _dp) {
                                                return (Direction)_bs.getValue(_dp);
                                             } else {
                                                return _bs.getBlock().getStateDefinition().getProperty("axis") instanceof EnumProperty _ep
                                                      && _ep.getPossibleValues().toArray()[0] instanceof Axis
                                                   ? Direction.fromAxisAndDirection((Axis)_bs.getValue(_ep), AxisDirection.POSITIVE)
                                                   : Direction.NORTH;
                                             }
                                          }
                                       })
                                       .getDirection(world.getBlockState(BlockPos.containing(x, y, z)))
                                    + "]{LootTable:\""
                                    + (new Object() {
                                       public String getValue(LevelAccessor world, BlockPos pos, String tag) {
                                          BlockEntity blockEntity = world.getBlockEntity(pos);
                                          return blockEntity != null ? blockEntity.getPersistentData().getString(tag) : "";
                                       }
                                    }).getValue(world, BlockPos.containing(x, y, z), "loot")
                                    + "\"} replace"
                              );
                        }
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
                                 DyairdropModSounds.PWWRONG.get(),
                                 SoundSource.BLOCKS,
                                 1.0F,
                                 1.0F
                              );
                           } else {
                              _levelx.playLocalSound(
                                 x,
                                 y,
                                 z,
                                 DyairdropModSounds.PWWRONG.get(),
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
