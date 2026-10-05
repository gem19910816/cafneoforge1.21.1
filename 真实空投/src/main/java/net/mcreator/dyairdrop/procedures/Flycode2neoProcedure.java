package net.mcreator.dyairdrop.procedures;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import java.text.DecimalFormat;
import net.mcreator.dyairdrop.DyairdropMod;
import net.mcreator.dyairdrop.configuration.AirdropconfigConfiguration;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

public class Flycode2neoProcedure {
   public static void execute(LevelAccessor world, CommandContext<CommandSourceStack> arguments) {
      if (world instanceof ServerLevel _level) {
         _level.getServer()
            .getCommands()
            .performPrefixedCommand(
               new CommandSourceStack(
                     CommandSource.NULL,
                     new Vec3(DoubleArgumentType.getDouble(arguments, "x"), 74.0, DoubleArgumentType.getDouble(arguments, "z")),
                     Vec2.ZERO,
                     _level,
                     4,
                     "",
                     Component.literal(""),
                     _level.getServer(),
                     null
                  )
                  .withSuppressedOutput(),
               "/playsound dyairdrop:planesound ambient @a ~ ~ ~ 25 0"
            );
      }

      if (BoolArgumentType.getBool(arguments, "pin") && StringArgumentType.getString(arguments, "blockid").contains("dyairdrop:")) {
         DyairdropMod.queueServerWork(
            60,
            () -> {
               if ((Boolean)AirdropconfigConfiguration.FORCELOAD.get()) {
                  if (world instanceof ServerLevel _levelxx) {
                     _levelxx.getServer()
                        .getCommands()
                        .performPrefixedCommand(
                           new CommandSourceStack(
                                 CommandSource.NULL,
                                 new Vec3(
                                    Math.round(DoubleArgumentType.getDouble(arguments, "x") - DoubleArgumentType.getDouble(arguments, "length")),
                                    Math.round(DoubleArgumentType.getDouble(arguments, "height")),
                                    Math.round(DoubleArgumentType.getDouble(arguments, "z"))
                                 ),
                                 Vec2.ZERO,
                                 _levelxx,
                                 4,
                                 "",
                                 Component.literal(""),
                                 _levelxx.getServer(),
                                 null
                              )
                              .withSuppressedOutput(),
                           "summon dyairdrop:transportplane ~ ~ ~ {CustomName:'{\"text\":\"dyairdrop:locked"
                              + StringArgumentType.getString(arguments, "blockid").replace("dyairdrop:", "")
                              + ","
                              + StringArgumentType.getString(arguments, "loot_table")
                              + ","
                              + new DecimalFormat("##").format(DoubleArgumentType.getDouble(arguments, "length"))
                              + "\"}'}"
                        );
                  }
               } else if (world instanceof ServerLevel _levelx) {
                  _levelx.getServer()
                     .getCommands()
                     .performPrefixedCommand(
                        new CommandSourceStack(
                              CommandSource.NULL,
                              new Vec3(
                                 Math.round(DoubleArgumentType.getDouble(arguments, "x") - DoubleArgumentType.getDouble(arguments, "length")),
                                 Math.round(DoubleArgumentType.getDouble(arguments, "height")),
                                 Math.round(DoubleArgumentType.getDouble(arguments, "z"))
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
                        "summon dyairdrop:plane ~ ~ ~ {CustomName:'{\"text\":\"dyairdrop:locked"
                           + StringArgumentType.getString(arguments, "blockid").replace("dyairdrop:", "")
                           + ","
                           + StringArgumentType.getString(arguments, "loot_table")
                           + ","
                           + new DecimalFormat("##").format(DoubleArgumentType.getDouble(arguments, "length"))
                           + "\"}'}"
                     );
               }
            }
         );
      } else if ((Boolean)AirdropconfigConfiguration.FORCELOAD.get()) {
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
                                 Math.round(DoubleArgumentType.getDouble(arguments, "x") - DoubleArgumentType.getDouble(arguments, "length")),
                                 Math.round(DoubleArgumentType.getDouble(arguments, "height")),
                                 Math.round(DoubleArgumentType.getDouble(arguments, "z"))
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
                        "summon dyairdrop:transportplane ~ ~ ~ {CustomName:'{\"text\":\""
                           + StringArgumentType.getString(arguments, "blockid")
                           + ","
                           + StringArgumentType.getString(arguments, "loot_table")
                           + ","
                           + new DecimalFormat("##").format(DoubleArgumentType.getDouble(arguments, "length"))
                           + "\"}'}"
                     );
               }
            }
         );
      } else {
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
                                 Math.round(DoubleArgumentType.getDouble(arguments, "x") - DoubleArgumentType.getDouble(arguments, "length")),
                                 Math.round(DoubleArgumentType.getDouble(arguments, "height")),
                                 Math.round(DoubleArgumentType.getDouble(arguments, "z"))
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
                           + ","
                           + new DecimalFormat("##").format(DoubleArgumentType.getDouble(arguments, "length"))
                           + "\"}'}"
                     );
               }
            }
         );
      }
   }
}
