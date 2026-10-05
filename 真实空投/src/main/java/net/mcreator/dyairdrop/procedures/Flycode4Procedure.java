package net.mcreator.dyairdrop.procedures;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;

public class Flycode4Procedure {
   public static void execute(LevelAccessor world, CommandContext<CommandSourceStack> arguments) {
      double dx = 0.0;
      double dy = 0.0;
      double dz = 0.0;
      double squtmean = 0.0;
      double Zranvirable = 0.0;
      Entity player = null;
      List<String> list = new ArrayList<>();
      list.add("minecraft:overworld");
      Entity var21 = GetrandomplayerProcedure.getRandomPlayer(world, list);
      if (var21 != null) {
         squtmean = Math.pow(DoubleArgumentType.getDouble(arguments, "drift"), 0.5);
         Zranvirable = (new Random().nextGaussian() + squtmean) * squtmean;
         if (Mth.nextInt(RandomSource.create(), 0, 1) == 0) {
            dx = Zranvirable;
            if (Mth.nextInt(RandomSource.create(), 0, 1) == 0) {
               dz = Zranvirable;
            } else {
               dz = Zranvirable * -1.0;
            }
         } else {
            dx = Zranvirable * -1.0;
            if (Mth.nextInt(RandomSource.create(), 0, 1) == 0) {
               dz = Zranvirable;
            } else {
               dz = Zranvirable * -1.0;
            }
         }

         dx = var21.getX() + dx;
         dz = var21.getZ() + dz;
         if (!world.isClientSide() && world.getServer() != null) {
            world.getServer()
               .getPlayerList()
               .broadcastSystemMessage(
                  Component.literal(
                     Component.translatable("message.wordaridropevents").getString()
                        + "["
                        + Math.round(dx)
                        + ","
                        + Math.round(dz)
                        + "]地图："
                        + var21.level().dimension()
                  ),
                  false
               );
         }

         if (BoolArgumentType.getBool(arguments, "pin")) {
            Entity _ent = var21;
            if (!_ent.level().isClientSide() && _ent.getServer() != null) {
               _ent.getServer()
                  .getCommands()
                  .performPrefixedCommand(
                     new CommandSourceStack(
                        CommandSource.NULL,
                        _ent.position(),
                        _ent.getRotationVector(),
                        _ent.level() instanceof ServerLevel ? (ServerLevel)_ent.level() : null,
                        4,
                        _ent.getName().getString(),
                        _ent.getDisplayName(),
                        _ent.level().getServer(),
                        _ent
                     ),
                     "execute as @s run setairdrop free "
                        + new DecimalFormat("##").format(dx)
                        + " "
                        + new DecimalFormat("##").format(dz)
                        + " "
                        + Math.round(DoubleArgumentType.getDouble(arguments, "height"))
                        + " "
                        + Math.round(DoubleArgumentType.getDouble(arguments, "length"))
                        + " \""
                        + StringArgumentType.getString(arguments, "blockid")
                        + "\" \""
                        + StringArgumentType.getString(arguments, "loot_table")
                        + "\" true"
                  );
            }
         } else {
            Entity _ent = var21;
            if (!_ent.level().isClientSide() && _ent.getServer() != null) {
               _ent.getServer()
                  .getCommands()
                  .performPrefixedCommand(
                     new CommandSourceStack(
                        CommandSource.NULL,
                        _ent.position(),
                        _ent.getRotationVector(),
                        _ent.level() instanceof ServerLevel ? (ServerLevel)_ent.level() : null,
                        4,
                        _ent.getName().getString(),
                        _ent.getDisplayName(),
                        _ent.level().getServer(),
                        _ent
                     ),
                     "execute as @s run setairdrop free "
                        + new DecimalFormat("##").format(dx)
                        + " "
                        + new DecimalFormat("##").format(dz)
                        + " "
                        + Math.round(DoubleArgumentType.getDouble(arguments, "height"))
                        + " "
                        + Math.round(DoubleArgumentType.getDouble(arguments, "length"))
                        + " \""
                        + StringArgumentType.getString(arguments, "blockid")
                        + "\" \""
                        + StringArgumentType.getString(arguments, "loot_table")
                        + "\" false"
                  );
            }
         }
      } else if (!world.isClientSide() && world.getServer() != null) {
         world.getServer().getPlayerList().broadcastSystemMessage(Component.literal("一个随机空投试图投放，但是由于没有玩家在主世界或指定的玩家不在主世界而失败。"), false);
      }
   }
}
