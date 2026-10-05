package net.mcreator.dyairdrop.procedures;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import java.text.DecimalFormat;
import java.util.Random;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

public class Flycode3Procedure {
   public static void execute(LevelAccessor world, double x, double y, double z, final CommandContext<CommandSourceStack> arguments) {
      double dx = 0.0;
      double dy = 0.0;
      double dz = 0.0;
      double squtmean = 0.0;
      double Zranvirable = 0.0;
      Entity player = null;
      String worldname = "";
      player = (new Object() {
         public Entity getEntity() {
            try {
               return EntityArgument.getEntity(arguments, "player");
            } catch (CommandSyntaxException e) {
               e.printStackTrace();
               return null;
            }
         }
      }).getEntity();
      if (player != null) {
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

         dx = player.getX() + dx;
         dz = player.getZ() + dz;
         worldname = world instanceof Level _lvl ? _lvl.dimension().location().toString() : Level.OVERWORLD.toString();
         if (!world.isClientSide() && world.getServer() != null) {
            world.getServer()
               .getPlayerList()
               .broadcastSystemMessage(
                  Component.literal(
                     Component.translatable("message.wordaridropevents").getString() + worldname + " [" + Math.round(dx) + "," + Math.round(dz) + "]"
                  ),
                  false
               );
         }

         if (BoolArgumentType.getBool(arguments, "pin")) {
            if (world instanceof ServerLevel _level) {
               _level.getServer()
                  .getCommands()
                  .performPrefixedCommand(
                     new CommandSourceStack(
                           CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null
                        )
                        .withSuppressedOutput(),
                     "/setairdrop free "
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
         } else if (world instanceof ServerLevel _level) {
            _level.getServer()
               .getCommands()
               .performPrefixedCommand(
                  new CommandSourceStack(
                        CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null
                     )
                     .withSuppressedOutput(),
                  "/setairdrop free "
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
      } else if (!world.isClientSide() && world.getServer() != null) {
         world.getServer().getPlayerList().broadcastSystemMessage(Component.literal("一个随机空投试图投放，但是由于没有玩家在主世界或指定的玩家不在主世界而失败。"), false);
      }
   }
}
