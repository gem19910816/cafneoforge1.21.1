package net.gem19910816.dyairdrop.procedures;

import net.gem19910816.dyairdrop.core.Commands;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.gem19910816.dyairdrop.configuration.AirdropconfigConfiguration;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.neoforged.fml.ModList;

public class RandomworldairdropProcedure {
   public static void execute(LevelAccessor world) {
      Entity player = null;
      double driftmin = 0.0;
      double level = 0.0;
      double driftmax = 0.0;
      double gap = 0.0;
      double now = 0.0;
      double coefficient = 0.0;
      double position = 0.0;
      double height = 0.0;
      String loottable = "";
      String worldlist = "";
      String modid = "";
      String drifts = "";
      String parametermap = "";
      String parametermapfalse = "";
      if ((Double)AirdropconfigConfiguration.STARTPOSITION.get() <= 0.0) {
         position = 0.0;
      } else if ((Double)AirdropconfigConfiguration.STARTPOSITION.get() >= 512.0) {
         position = 512.0;
      } else {
         position = (Double)AirdropconfigConfiguration.STARTPOSITION.get();
      }

      if ((Double)AirdropconfigConfiguration.HEIGHT.get() <= 100.0) {
         height = 100.0;
      } else if ((Double)AirdropconfigConfiguration.HEIGHT.get() >= 320.0) {
         height = 320.0;
      } else {
         height = Math.round((Double)AirdropconfigConfiguration.HEIGHT.get());
      }

      drifts = (String)AirdropconfigConfiguration.DRIFT.get();
      String[] parts = drifts.split(",");
      if (parts.length == 2) {
         driftmin = Double.parseDouble(parts[0]);
         driftmax = Double.parseDouble(parts[1]);
      } else {
         driftmin = 50.0;
         driftmax = 100.0;
      }

      if (driftmin <= 0.0) {
         driftmin = 0.0;
      } else if (driftmax >= 1024.0) {
         driftmax = 1024.0;
      }

      if (driftmin > driftmax) {
         double temp = driftmin;
         driftmin = driftmax;
         driftmax = temp;
      }

      if ((Boolean)AirdropconfigConfiguration.ENABLEAIRDROPEVENTS.get() && (world instanceof Level _lvl ? _lvl.dimension() : Level.OVERWORLD) == Level.OVERWORLD) {
         if ((Double)AirdropconfigConfiguration.GAP.get() < 1.0) {
            coefficient = 1.0;
         } else {
            coefficient = Math.round((Double)AirdropconfigConfiguration.GAP.get());
         }

         gap = coefficient * 24000.0;
         now = Math.abs(((Level)world).getDayTime() - 100L);
         level = Math.round(now / gap);
         if (level < 1.0) {
            level = 1.0;
         }

         if (level >= Math.floor((Double)AirdropconfigConfiguration.MAXLEVEL.get())) {
            level = Math.floor((Double)AirdropconfigConfiguration.MAXLEVEL.get());
            if ((Boolean)AirdropconfigConfiguration.DEBUGMODE.get() && !world.isClientSide() && world.getServer() != null) {
               world.getServer().getPlayerList().broadcastSystemMessage(Component.literal("目前天数大于最大值，已削减到" + level), false);
            }
         }

         if ((Boolean)AirdropconfigConfiguration.DEBUGMODE.get() && !world.isClientSide() && world.getServer() != null) {
            world.getServer().getPlayerList().broadcastSystemMessage(Component.literal("预计目标战利品表等级：" + level), false);
         }

         worldlist = (String)AirdropconfigConfiguration.AVAILABLEWORLD.get();
         new ArrayList();
         List<String> list = Arrays.asList(worldlist.split(","));
         Entity var28 = GetrandomplayerProcedure.getRandomPlayer(world, list);
         if (ModList.get().isLoaded("zombiekit")) {
            modid = "zombiekit";
         } else {
            modid = "dyairdrop";
         }

         loottable = "\"" + modid + ":chests/largeairdrop" + new DecimalFormat("##").format(level) + "\"";
         if (null != var28) {
            if ((Boolean)AirdropconfigConfiguration.ENABLEGLOBALCOORDINATES.get()) {
               parametermap = " true true";
               parametermapfalse = " false true";
            } else {
               parametermap = " true";
               parametermapfalse = " false";
            }

            if ((Boolean)AirdropconfigConfiguration.ENABLELOCK.get()) {
               Entity _ent = var28;
               if (!_ent.level().isClientSide() && _ent.getServer() != null) {
                  Commands.runAs(_ent,
                        "setairdrop random @s "
                           + new DecimalFormat("##").format(height)
                           + " "
                           + new DecimalFormat("##").format(position)
                           + " "
                           + new DecimalFormat("##").format(driftmin)
                           + " "
                           + new DecimalFormat("##").format(driftmax)
                           + " \"dyairdrop:airdroplarge\" "
                           + loottable
                           + parametermap
                     );
               }
            } else {
               Entity _ent = var28;
               if (!_ent.level().isClientSide() && _ent.getServer() != null) {
                  Commands.runAs(_ent,
                        "setairdrop random @s "
                           + new DecimalFormat("##").format(height)
                           + " "
                           + new DecimalFormat("##").format(position)
                           + " "
                           + new DecimalFormat("##").format(driftmin)
                           + " "
                           + new DecimalFormat("##").format(driftmax)
                           + " \"dyairdrop:airdroplarge\" "
                           + loottable
                           + parametermapfalse
                     );
               }
            }
         } else if (!world.isClientSide() && world.getServer() != null) {
            world.getServer().getPlayerList().broadcastSystemMessage(Component.literal("一个空投尝试投放，但是因为指定的世界没有玩家而失败"), false);
         }
      }
   }
}
