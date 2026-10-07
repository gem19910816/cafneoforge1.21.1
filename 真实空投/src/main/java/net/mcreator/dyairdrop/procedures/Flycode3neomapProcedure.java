package net.mcreator.dyairdrop.procedures;

import net.gem19910816.dyairdrop.core.Commands;

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

public class Flycode3neomapProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, CommandContext<CommandSourceStack> arguments) {
      Entity player;
      try {
         player = EntityArgument.getEntity(arguments, "player");
      } catch (CommandSyntaxException e) {
         e.printStackTrace();
         broadcastFail(world);
         return;
      }

      if (player == null) {
         broadcastFail(world);
      } else {
         double driftMaxArg = DoubleArgumentType.getDouble(arguments, "driftmax");
         double driftMinArg = DoubleArgumentType.getDouble(arguments, "driftmin");
         int drMin = (int)Math.min(driftMinArg, driftMaxArg);
         int drMax = (int)Math.max(driftMinArg, driftMaxArg);
         int radius = Mth.nextInt(RandomSource.create(), drMin, drMax);
         Random rand = new Random();
         double angle = rand.nextDouble() * 2.0 * Math.PI;
         double dx = player.getX() + radius * Math.cos(angle);
         double dz = player.getZ() + radius * Math.sin(angle);
         String worldName = world instanceof Level lvl ? lvl.dimension().location().toString() : Level.OVERWORLD.toString();
         if (!world.isClientSide() && world.getServer() != null) {
            world.getServer()
               .getPlayerList()
               .broadcastSystemMessage(
                  Component.literal(
                     Component.translatable("message.wordaridropevents").getString() + worldName + " [" + Math.round(dx) + "," + Math.round(dz) + "]"
                  ),
                  false
               );
         }

         boolean pinFlag = BoolArgumentType.getBool(arguments, "pin");
         long height = Math.round(DoubleArgumentType.getDouble(arguments, "height"));
         long length = Math.round(DoubleArgumentType.getDouble(arguments, "length"));
         String blockId = StringArgumentType.getString(arguments, "blockid");
         String lootTable = StringArgumentType.getString(arguments, "loot_table");
         DecimalFormat fmt = new DecimalFormat("##");
         StringBuilder cmd = new StringBuilder(128);
         cmd.append("/setairdrop free ")
            .append(fmt.format(dx))
            .append(" ")
            .append(fmt.format(dz))
            .append(" ")
            .append(height)
            .append(" ")
            .append(length)
            .append(" ")
            .append("\"")
            .append(blockId)
            .append("\"")
            .append(" ")
            .append("\"")
            .append(lootTable)
            .append("\"")
            .append(" ")
            .append(pinFlag ? "true" : "false");

         try {
            boolean mapFlag = BoolArgumentType.getBool(arguments, "map");
            cmd.append(" ").append(mapFlag ? "true" : "false");
         } catch (Exception var34) {
         }

         if (world instanceof ServerLevel level) {
            Commands.run(level, x, y, z, cmd.toString()
               );
         }
      }
   }

   private static void broadcastFail(LevelAccessor world) {
      if (!world.isClientSide() && world.getServer() != null) {
         world.getServer().getPlayerList().broadcastSystemMessage(Component.literal("一个随机空投试图投放，但由于没有玩家在主世界或指定的玩家不在主世界而失败。"), false);
      }
   }
}
