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

public class Flycode2forceloadProcedure {
   public Flycode2forceloadProcedure() {
   }

   public static void execute(LevelAccessor world, CommandContext<CommandSourceStack> arguments) {
      if ((Boolean)AirdropconfigConfiguration.FORCELOAD.get() && world instanceof ServerLevel _level) {
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
               "forceload add ~280 ~ ~-280 ~"
            );
      }

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

      if (BoolArgumentType.getBool(arguments, "pin")) {
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
                                 (double)Math.round(DoubleArgumentType.getDouble(arguments, "x") - DoubleArgumentType.getDouble(arguments, "length")),
                                 (double)Math.round(DoubleArgumentType.getDouble(arguments, "height")),
                                 (double)Math.round(DoubleArgumentType.getDouble(arguments, "z"))
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
                        "summon dyairdrop:transportplane ~ ~ ~ {CustomName:'{\"text\":\"dyairdrop:locked"
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
         DyairdropMod.queueServerWork(
            240,
            () -> {
               if (world instanceof ServerLevel _levelx) {
                  _levelx.getServer()
                     .getCommands()
                     .performPrefixedCommand(
                        new CommandSourceStack(
                              CommandSource.NULL,
                              new Vec3(DoubleArgumentType.getDouble(arguments, "x"), 75.0, DoubleArgumentType.getDouble(arguments, "z")),
                              Vec2.ZERO,
                              _levelx,
                              4,
                              "",
                              Component.literal(""),
                              _levelx.getServer(),
                              null
                           )
                           .withSuppressedOutput(),
                        "forceload remove all"
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
                                 (double)Math.round(DoubleArgumentType.getDouble(arguments, "x") - DoubleArgumentType.getDouble(arguments, "length")),
                                 (double)Math.round(DoubleArgumentType.getDouble(arguments, "height")),
                                 (double)Math.round(DoubleArgumentType.getDouble(arguments, "z"))
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
         DyairdropMod.queueServerWork(
            240,
            () -> {
               if ((Boolean)AirdropconfigConfiguration.FORCELOAD.get() && world instanceof ServerLevel _levelx) {
                  _levelx.getServer()
                     .getCommands()
                     .performPrefixedCommand(
                        new CommandSourceStack(
                              CommandSource.NULL,
                              new Vec3(DoubleArgumentType.getDouble(arguments, "x"), 75.0, DoubleArgumentType.getDouble(arguments, "z")),
                              Vec2.ZERO,
                              _levelx,
                              4,
                              "",
                              Component.literal(""),
                              _levelx.getServer(),
                              null
                           )
                           .withSuppressedOutput(),
                        "forceload remove all"
                     );
               }
            }
         );
      }
   }
}
