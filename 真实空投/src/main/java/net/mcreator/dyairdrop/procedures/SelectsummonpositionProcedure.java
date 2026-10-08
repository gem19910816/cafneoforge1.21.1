package net.mcreator.dyairdrop.procedures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.mcreator.dyairdrop.configuration.AirdropconfigConfiguration;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

public class SelectsummonpositionProcedure {
   public SelectsummonpositionProcedure() {
   }

   public static void execute(LevelAccessor world, double x, double y, double z) {
      String orlist = "";
      String mobname = "";
      boolean t = false;
      double validArea = 0.0;
      double cx = 0.0;
      double rx = 0.0;
      double cy = 0.0;
      double cz = 0.0;
      double rz = 0.0;
      double lenlist = 0.0;
      double n = 0.0;
      double bx = 0.0;
      double by = 0.0;
      double length = 0.0;
      double bz = 0.0;
      t = world.isEmptyBlock(BlockPos.containing(x, y, z));
      orlist = (String)AirdropconfigConfiguration.ENEMYLIST.get();
      String[] enemylist = orlist.split(",");
      double xRange = 10.0;
      double yRange = 5.0;
      double zRange = 10.0;
      List<double[]> coordinates = new ArrayList<>();

      for (double i = x - xRange; i <= x + xRange; i++) {
         for (double j = y - yRange; j <= y + yRange; j++) {
            for (double k = z - zRange; k <= z + zRange; k++) {
               coordinates.add(new double[]{i, j, k});
            }
         }
      }

      Collections.shuffle(coordinates);
      n = 0.0;
      length = (double)enemylist.length;

      for (int index0 = 0; index0 < 4000 && !(n > length - 1.0) && !coordinates.isEmpty(); index0++) {
         double[] point = coordinates.remove(0);
         bx = point[0];
         by = point[1];
         bz = point[2];
         bx = (double)Math.round(bx);
         by = (double)Math.round(by);
         bz = (double)Math.round(bz);
         if (world.canSeeSkyFromBelowWater(BlockPos.containing(bx, by + 1.0, bz)) && world.getBlockState(BlockPos.containing(bx, by, bz)).canOcclude()) {
            mobname = enemylist[(int)n];
            if (world instanceof ServerLevel _level) {
               _level.getServer()
                  .getCommands()
                  .performPrefixedCommand(
                     new CommandSourceStack(
                           CommandSource.NULL, new Vec3(bx, by + 1.0, bz), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null
                        )
                        .withSuppressedOutput(),
                     "summon " + mobname + " ~ ~ ~"
                  );
            }

            n++;
         } else if (world.canSeeSkyFromBelowWater(BlockPos.containing(bx, by + 1.0, bz)) && world.getBlockState(BlockPos.containing(bx, by, bz)).getFluidState().isSource()) {
            mobname = enemylist[(int)n];
            if (world instanceof ServerLevel _level) {
               _level.getServer()
                  .getCommands()
                  .performPrefixedCommand(
                     new CommandSourceStack(
                           CommandSource.NULL, new Vec3(bx, by + 1.0, bz), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null
                        )
                        .withSuppressedOutput(),
                     "summon " + mobname + " ~ ~ ~"
                  );
            }

            n++;
         }
      }
   }
}
