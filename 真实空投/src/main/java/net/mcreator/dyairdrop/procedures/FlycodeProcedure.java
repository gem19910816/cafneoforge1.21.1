package net.mcreator.dyairdrop.procedures;

import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.mcreator.dyairdrop.DyairdropMod;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.MessageArgument;
import net.minecraft.commands.arguments.coordinates.BlockPosArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

public class FlycodeProcedure {
   public static void execute(LevelAccessor world, double y, final CommandContext<CommandSourceStack> arguments) {
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
         }).getX(), 75.0, (new Object() {
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
               }).getX(), 75.0, (new Object() {
                  public double getZ() {
                     try {
                        return BlockPosArgument.getLoadedBlockPos(arguments, "posa").getZ();
                     } catch (CommandSyntaxException e) {
                        e.printStackTrace();
                        return 0.0;
                     }
                  }
               }).getZ()), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
               "/playsound dyairdrop:planesound ambient @a[distance=..200] ~ ~ ~ 5 0.5"
            );
      }

      DyairdropMod.queueServerWork(
         60,
         () -> {
            if (!world.isClientSide() && world.getServer() != null) {
               world.getServer().getPlayerList().broadcastSystemMessage(Component.literal("空投投放成功"), false);
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
                     }).getX() - DoubleArgumentType.getDouble(arguments, "length"), DoubleArgumentType.getDouble(arguments, "height"), (new Object() {
                        public double getZ() {
                           try {
                              return BlockPosArgument.getLoadedBlockPos(arguments, "posa").getZ();
                           } catch (CommandSyntaxException e) {
                              e.printStackTrace();
                              return 0.0;
                           }
                        }
                     }).getZ()), Vec2.ZERO, _levelx, 4, "", Component.literal(""), _levelx.getServer(), null).withSuppressedOutput(),
                     "summon dyairdrop:plane ~ ~ ~ {CustomName:'{\"text\":\"dyairdrop:airdroplarge," + (new Object() {
                        public String getMessage() {
                           try {
                              return MessageArgument.getMessage(arguments, "loot_table").getString();
                           } catch (CommandSyntaxException ignored) {
                              return "";
                           }
                        }
                     }).getMessage() + "\"}'}"
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
