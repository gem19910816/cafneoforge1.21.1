package net.mcreator.dyairdrop.procedures;

import net.gem19910816.dyairdrop.core.Commands;

import net.gem19910816.dyairdrop.core.Blocks;

import net.gem19910816.dyairdrop.core.CommandArgs;

import net.minecraft.core.registries.BuiltInRegistries;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import java.text.DecimalFormat;
import net.mcreator.dyairdrop.configuration.AirdropconfigConfiguration;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.arguments.blocks.BlockStateArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

public class FastairdropProcedure {
   public static void execute(LevelAccessor world, final CommandContext<CommandSourceStack> arguments) {
      Entity player = null;
      String worldname = "";
      String blockid = "";
      String loot_table = "";
      String worldlist = "";
      String modid = "";
      String parts1 = "";
      String parts2 = "";
      double dx = 0.0;
      double dy = 0.0;
      double dz = 0.0;
      double squtmean = 0.0;
      double Zranvirable = 0.0;
      double level = 0.0;
      double gap = 0.0;
      double now = 0.0;
      double coefficient = 0.0;
      double position = 0.0;
      double height = 0.0;
      double drift = 0.0;
      double pheight = 0.0;
      double plength = 0.0;
      player = CommandArgs.entity(arguments, "player");
      if (player != null) {
         dx = player.getX();
         dz = player.getZ();
         if (world instanceof Level _lvl) {
            _lvl.dimension().location().toString();
         } else {
            Level.OVERWORLD.toString();
         }

         if ((Double)AirdropconfigConfiguration.GAP.get() < 1.0) {
            coefficient = 1.0;
         } else {
            coefficient = Math.round((Double)AirdropconfigConfiguration.GAP.get());
         }

         gap = coefficient * 24000.0;
         now = Math.abs(((Level)world).getDayTime() - 100L);
         level = now / gap;
         if (level >= 1.0) {
            level = Math.floor(level);
         } else {
            level = 1.0;
         }

         blockid = BuiltInRegistries.BLOCK.getKey(BlockStateArgument.getBlock(arguments, "blockid").getState().getBlock()).toString();
         String[] parts = blockid.split(":");
         if (parts.length != 2) {
            return;
         }

         parts1 = parts[0];
         parts2 = parts[1];
         loot_table = parts1 + ":chests/" + parts2.replace("airdrop", "") + "airdrop" + Math.round(level);
         pheight = Math.round((Double)AirdropconfigConfiguration.HEIGHT.get());
         plength = Math.round((Double)AirdropconfigConfiguration.STARTPOSITION.get());
         plength = Math.max(0.0, Math.min(plength, 512.0));
         pheight = Math.max(100.0, Math.min(pheight, 320.0));
         if (BoolArgumentType.getBool(arguments, "pin")) {
            Entity _ent = player;
            if (!_ent.level().isClientSide() && _ent.getServer() != null) {
               Commands.runAs(_ent,
                     "/setairdrop free "
                        + new DecimalFormat("##").format(dx)
                        + " "
                        + new DecimalFormat("##").format(dz)
                        + " "
                        + Math.round(pheight)
                        + " "
                        + Math.round(plength)
                        + " \""
                        + blockid
                        + "\" \""
                        + loot_table
                        + "\" true"
                  );
            }
         } else {
            Entity _ent = player;
            if (!_ent.level().isClientSide() && _ent.getServer() != null) {
               Commands.runAs(_ent,
                     "/setairdrop free "
                        + new DecimalFormat("##").format(dx)
                        + " "
                        + new DecimalFormat("##").format(dz)
                        + " "
                        + Math.round(pheight)
                        + " "
                        + Math.round(plength)
                        + " \""
                        + blockid
                        + "\" \""
                        + loot_table
                        + "\" false"
                  );
            }
         }
      } else if (!world.isClientSide() && world.getServer() != null) {
         world.getServer().getPlayerList().broadcastSystemMessage(Component.literal("一个随机空投试图投放，但是由于没有玩家在主世界或指定的玩家不在主世界而失败。"), false);
      }
   }
}
