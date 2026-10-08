package net.mcreator.dyairdrop.procedures;

import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level.ExplosionInteraction;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

public class PlaneticksneoProcedure {
   public PlaneticksneoProcedure() {
   }

   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         String all = "";
         String blockid = "";
         String loot = "";
         String mobname = "";
         if (entity.getPersistentData().getDouble("timer") <= 0.0) {
            entity.setNoGravity(true);
            entity.getPersistentData().putDouble("timer", 0.0);
            entity.getPersistentData().putString("name", entity.getDisplayName().getString());
            entity.setCustomName(Component.literal("运输机"));
            entity.setDeltaMovement(new Vec3(3.0, 0.0, 0.0));
         }

         if (entity.getPersistentData().getDouble("timer") >= 220.0 && !entity.level().isClientSide()) {
            entity.discard();
         }

         if (entity.getPersistentData().getDouble("timer") % 5.0 == 0.0) {
            entity.setDeltaMovement(new Vec3(3.0, 0.0, 0.0));
         }

         if (entity.getPersistentData().getDouble("timer") == 105.0) {
            all = entity.getPersistentData().getString("name");
            blockid = all.split(",", 2)[0];
            if (blockid.equals("dyairdrop:airdropsmall")) {
               mobname = "dyairdrop:smallairdrop";
            } else {
               mobname = "dyairdrop:airdrop";
            }

            if (world instanceof ServerLevel _level) {
               _level.getServer()
                  .getCommands()
                  .performPrefixedCommand(
                     new CommandSourceStack(
                           CommandSource.NULL,
                           new Vec3((double)Math.round(x), (double)Math.round(y), (double)Math.round(z)),
                           Vec2.ZERO,
                           _level,
                           4,
                           "",
                           Component.literal(""),
                           _level.getServer(),
                           null
                        )
                        .withSuppressedOutput(),
                     "summon " + mobname + " ~ ~ ~ {CustomName:'{\"text\":\"" + entity.getPersistentData().getString("name") + "\"}'}"
                  );
            }
         }

         entity.getPersistentData().putDouble("timer", entity.getPersistentData().getDouble("timer") + 1.0);
         if (entity.getDeltaMovement().x() == 0.0 || entity.isInWall()) {
            if (!entity.level().isClientSide()) {
               entity.discard();
            }

            if (world instanceof Level _level && !_level.isClientSide()) {
               _level.explode(null, x, y, z, 4.0F, ExplosionInteraction.MOB);
            }

            if (world instanceof ServerLevel _level) {
               _level.getServer()
                  .getCommands()
                  .performPrefixedCommand(
                     new CommandSourceStack(
                           CommandSource.NULL,
                           new Vec3((double)Math.round(x), (double)Math.round(y), (double)Math.round(z)),
                           Vec2.ZERO,
                           _level,
                           4,
                           "",
                           Component.literal(""),
                           _level.getServer(),
                           null
                        )
                        .withSuppressedOutput(),
                     "/stopsound @a[distance=..200] ambient dyairdrop:planesound"
                  );
            }
         }
      }
   }
}
