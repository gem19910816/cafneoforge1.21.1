package net.mcreator.dyairdrop.procedures;

import net.gem19910816.dyairdrop.core.Numbers;

import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.EntityAnchorArgument.Anchor;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level.ExplosionInteraction;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

public class FancyplaneticksProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         double mdx = 0.0;
         double mtheta = 0.0;
         double mdz = 0.0;
         double dx = 0.0;
         double dz = 0.0;
         double model = 0.0;
         double theta = 0.0;
         String all = "";
         String blockid = "";
         String mobname = "";
         String distination = "";
         String alll = "";
         String loot = "";
         String[] parts = new String[0];
         String[] parts2 = new String[0];
         if (entity.getPersistentData().getDouble("timer") <= 0.0) {
            entity.getPersistentData().putDouble("starter", entity.getX());
            entity.setNoGravity(true);
            entity.getPersistentData().putDouble("timer", 0.0);
            entity.getPersistentData().putString("name", entity.getDisplayName().getString());
            all = entity.getPersistentData().getString("name");
            parts = all.split(",", 3);
            if (parts.length > 2) {
               distination = parts[2];
            } else {
               distination = "108";
            }

            entity.getPersistentData().putDouble("d", Numbers.parseDouble(distination));
            entity.setCustomName(Component.literal("运输机"));
            entity.lookAt(Anchor.EYES, new Vec3(x + 1.0, y, z));
            entity.setDeltaMovement(new Vec3(3.0, 0.0, 0.0));
         }

         if (entity.getPersistentData().getDouble("timer") >= 2.0) {
            entity.getPersistentData().putDouble("dpassed", Math.abs(entity.getPersistentData().getDouble("starter") - entity.getX()));
         }

         if (entity.getPersistentData().getDouble("flytime") > 0.0) {
            if (entity.getPersistentData().getDouble("timer") >= entity.getPersistentData().getDouble("flytime") * 2.0 && !entity.level().isClientSide()) {
               entity.discard();
            }
         } else if (entity.getPersistentData().getDouble("timer") >= 205.0 && !entity.level().isClientSide()) {
            entity.discard();
         }

         if (entity.getPersistentData().getDouble("timer") % 5.0 == 0.0) {
            entity.setDeltaMovement(new Vec3(3.0, 0.0, 0.0));
         }

         if (Math.round(Math.abs(entity.getPersistentData().getDouble("dpassed") - entity.getPersistentData().getDouble("d"))) <= 1L
            && !entity.getPersistentData().getBoolean("hasdone")) {
            entity.getPersistentData().putBoolean("hasdone", true);
            entity.getPersistentData().putDouble("flytime", entity.getPersistentData().getDouble("timer"));
            alll = entity.getPersistentData().getString("name");
            parts2 = alll.split(",", 3);
            if (parts2.length > 2) {
               blockid = alll.split(",", 3)[0];
               loot = alll.split(",", 3)[1];
            } else {
               blockid = "dyairdrop:airdroplarge";
               loot = "dyairdrop:largeairdrop1";
            }

            blockid = blockid.replace("locked", "");
            if (blockid.equals("dyairdrop:airdropsmall")) {
               mobname = "dyairdrop:smallairdrop";
            } else if (blockid.equals("dyairdrop:airdropweapon")) {
               mobname = "dyairdrop:weaponairdrop";
            } else if (blockid.equals("dyairdrop:airdropmedical")) {
               mobname = "dyairdrop:medicalairdrop";
            } else {
               mobname = "dyairdrop:airdrop";
            }

            if (entity.getPersistentData().getBoolean("dymap")) {
               if (world instanceof ServerLevel _level) {
                  _level.getServer()
                     .getCommands()
                     .performPrefixedCommand(
                        new CommandSourceStack(
                              CommandSource.NULL,
                              new Vec3(Math.round(x), Math.round(y), Math.round(z)),
                              Vec2.ZERO,
                              _level,
                              4,
                              "",
                              Component.literal(""),
                              _level.getServer(),
                              null
                           )
                           .withSuppressedOutput(),
                        "summon " + mobname + " ~ ~ ~ {CustomName:'{\"text\":\"" + entity.getPersistentData().getString("name") + "\"}',ForgeData:{dymap:1b}}"
                     );
               }
            } else if (world instanceof ServerLevel _level) {
               _level.getServer()
                  .getCommands()
                  .performPrefixedCommand(
                     new CommandSourceStack(
                           CommandSource.NULL,
                           new Vec3(Math.round(x), Math.round(y), Math.round(z)),
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
                           new Vec3(Math.round(x), Math.round(y), Math.round(z)),
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

         theta = Math.atan(0.33333333) + 1.570796327;
         mtheta = (Math.atan(0.33333333) + 1.570796327) * -1.0;
         dx = entity.getLookAngle().x * Math.cos(theta) - entity.getLookAngle().z * Math.sin(theta);
         dz = entity.getLookAngle().x * Math.sin(theta) + entity.getLookAngle().z * Math.cos(theta);
         mdx = entity.getLookAngle().x * Math.cos(mtheta) - entity.getLookAngle().z * Math.sin(mtheta);
         mdz = entity.getLookAngle().x * Math.sin(mtheta) + entity.getLookAngle().z * Math.cos(mtheta);
         model = (Math.pow(dx, 2.0) + Math.pow(dz, 2.0)) * 0.5;
         dx = dx / model * 1.4;
         dz = dz / model * 1.4;
         mdx = mdx / model * 1.4;
         mdz = mdz / model * 1.4;
         if (world instanceof ServerLevel _level) {
            _level.getServer()
               .getCommands()
               .performPrefixedCommand(
                  new CommandSourceStack(
                        CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null
                     )
                     .withSuppressedOutput(),
                  "particle cloud " + (x + dx) + " " + (y + 1.0) + " " + (z + dz) + " 0.2 0.2 0.2 0 5 force"
               );
         }

         if (world instanceof ServerLevel _level) {
            _level.getServer()
               .getCommands()
               .performPrefixedCommand(
                  new CommandSourceStack(
                        CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null
                     )
                     .withSuppressedOutput(),
                  "particle cloud " + (x + mdx) + " " + (y + 1.0) + " " + (z + mdz) + " 0.2 0.2 0.2 0 5 force"
               );
         }
      }
   }
}
