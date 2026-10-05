package net.mcreator.dyairdrop.procedures;

import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.mcreator.dyairdrop.DyairdropMod;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.coordinates.BlockPosArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

public class Flycode2Procedure {
   public static void execute(LevelAccessor world, double y, final CommandContext<CommandSourceStack> arguments) {
      if ((world instanceof Level _lvl ? _lvl.dimension() : Level.OVERWORLD) == Level.OVERWORLD) {
         if (world instanceof ServerLevel _level) {
            _level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3((new Object() {
               public double getX() {
                  try {
                     return BlockPosArgument.getLoadedBlockPos(arguments, "posa").getX();
                  } catch (CommandSyntaxException e) {
                     e.printStackTrace();
                     return 0.0;
                  }
               }
            }).getX(), (new Object() {
               public double getY() {
                  try {
                     return BlockPosArgument.getLoadedBlockPos(arguments, "posa").getY();
                  } catch (CommandSyntaxException e) {
                     e.printStackTrace();
                     return 0.0;
                  }
               }
            }).getY(), (new Object() {
               public double getZ() {
                  try {
                     return BlockPosArgument.getLoadedBlockPos(arguments, "posa").getZ();
                  } catch (CommandSyntaxException e) {
                     e.printStackTrace();
                     return 0.0;
                  }
               }
            }).getZ()), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(), "forceload add ~280 ~ ~-280 ~");
         }

         if (world instanceof ServerLevel _level) {
            _level.getServer()
               .getCommands()
               .performPrefixedCommand(
                  new CommandSourceStack(CommandSource.NULL, new Vec3((new Object() {
                     public double getX() {
                        try {
                           return BlockPosArgument.getLoadedBlockPos(arguments, "posa").getX();
                        } catch (CommandSyntaxException e) {
                           e.printStackTrace();
                           return 0.0;
                        }
                     }
                  }).getX(), (new Object() {
                     public double getY() {
                        try {
                           return BlockPosArgument.getLoadedBlockPos(arguments, "posa").getY();
                        } catch (CommandSyntaxException e) {
                           e.printStackTrace();
                           return 0.0;
                        }
                     }
                  }).getY(), (new Object() {
                     public double getZ() {
                        try {
                           return BlockPosArgument.getLoadedBlockPos(arguments, "posa").getZ();
                        } catch (CommandSyntaxException e) {
                           e.printStackTrace();
                           return 0.0;
                        }
                     }
                  }).getZ()), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
                  "/playsound dyairdrop:planesound ambient @a[distance=..500] ~ ~ ~ 25 0"
               );
         }

         DyairdropMod.queueServerWork(
            60,
            () -> {
               if (world instanceof ServerLevel _levelx) {
                  _levelx.getServer()
                     .getCommands()
                     .performPrefixedCommand(
                        new CommandSourceStack(
                              CommandSource.NULL,
                              new Vec3(
                                 Math.round((new Object() {
                                    public double getX() {
                                       try {
                                          return BlockPosArgument.getLoadedBlockPos(arguments, "posa").getX();
                                       } catch (CommandSyntaxException e) {
                                          e.printStackTrace();
                                          return 0.0;
                                       }
                                    }
                                 }).getX() - DoubleArgumentType.getDouble(arguments, "length")),
                                 Math.round(DoubleArgumentType.getDouble(arguments, "height")),
                                 Math.round((new Object() {
                                    public double getZ() {
                                       try {
                                          return BlockPosArgument.getLoadedBlockPos(arguments, "posa").getZ();
                                       } catch (CommandSyntaxException e) {
                                          e.printStackTrace();
                                          return 0.0;
                                       }
                                    }
                                 }).getZ())
                              ),
                              Vec2.ZERO,
                              _levelx,
                              4,
                              "",
                              Component.literal(""),
                              _levelx.getServer(),
                              null
                           )
                           .withSuppressedOutput(),
                        "summon dyairdrop:plane ~ ~ ~ {CustomName:'{\"text\":\""
                           + StringArgumentType.getString(arguments, "blockid")
                           + ","
                           + StringArgumentType.getString(arguments, "loot_table")
                           + "\"}'}"
                     );
               }
            }
         );
         DyairdropMod.queueServerWork(
            240,
            () -> {
               if (world instanceof ServerLevel _levelxx) {
                  _levelxx.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3((new Object() {
                     public double getX() {
                        try {
                           return BlockPosArgument.getLoadedBlockPos(arguments, "posa").getX();
                        } catch (CommandSyntaxException e) {
                           e.printStackTrace();
                           return 0.0;
                        }
                     }
                  }).getX(), 75.0, (new Object() {
                     public double getZ() {
                        try {
                           return BlockPosArgument.getLoadedBlockPos(arguments, "posa").getZ();
                        } catch (CommandSyntaxException e) {
                           e.printStackTrace();
                           return 0.0;
                        }
                     }
                  }).getZ()), Vec2.ZERO, _levelxx, 4, "", Component.literal(""), _levelxx.getServer(), null).withSuppressedOutput(), "forceload remove all");
               }

               if (world instanceof ServerLevel _levelx) {
                  _levelx.getServer()
                     .getCommands()
                     .performPrefixedCommand(
                        new CommandSourceStack(CommandSource.NULL, new Vec3((new Object() {
                           public double getX() {
                              try {
                                 return BlockPosArgument.getLoadedBlockPos(arguments, "posa").getX();
                              } catch (CommandSyntaxException e) {
                                 e.printStackTrace();
                                 return 0.0;
                              }
                           }
                        }).getX() + DoubleArgumentType.getDouble(arguments, "length"), y, (new Object() {
                           public double getZ() {
                              try {
                                 return BlockPosArgument.getLoadedBlockPos(arguments, "posa").getZ();
                              } catch (CommandSyntaxException e) {
                                 e.printStackTrace();
                                 return 0.0;
                              }
                           }
                        }).getZ()), Vec2.ZERO, _levelx, 4, "", Component.literal(""), _levelx.getServer(), null).withSuppressedOutput(),
                        "kill @e[type=dyinglight:projectile_transport_plane]"
                     );
               }
            }
         );
      }
   }
}
