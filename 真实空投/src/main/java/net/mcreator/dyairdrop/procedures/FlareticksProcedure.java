package net.mcreator.dyairdrop.procedures;

import net.gem19910816.dyairdrop.core.GameModes;

import java.text.DecimalFormat;
import net.mcreator.dyairdrop.configuration.AirdropconfigConfiguration;
import net.mcreator.dyairdrop.network.DyairdropModVariables;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

public class FlareticksProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, Entity immediatesourceentity) {
      if (entity != null && immediatesourceentity != null) {
         String fwn = "";
         String loottable = "";
         double position = 0.0;
         double height = 0.0;
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

         if (world instanceof ServerLevel _level) {
            _level.sendParticles(ParticleTypes.FIREWORK, x, y, z, 4, 0.0, 0.0, 0.0, 0.1);
         }

         immediatesourceentity.getPersistentData().putDouble("counter1", immediatesourceentity.getPersistentData().getDouble("counter1") + 1.0);
         if (immediatesourceentity.getPersistentData().getDouble("counter1") == 35.0) {
            if (((DyairdropModVariables.PlayerVariables)entity.getData(DyairdropModVariables.PLAYER_VARIABLES_ATTACHMENT.get()))
               .airdroploot
               .endsWith("1")) {
               if (world instanceof ServerLevel _level) {
                  _level.getServer()
                     .getCommands()
                     .performPrefixedCommand(
                        new CommandSourceStack(
                              CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null
                           )
                           .withSuppressedOutput(),
                        "/summon minecraft:firework_rocket ~ ~1 ~ {FireworksItem:{tag:{Fireworks:{Flight:2,Explosions:[{Trail:1b,Flicker:1b,Type:0,Colors:[I;64375],FadeColors:[I;3798784]},{Trail:1b,Flicker:1b,Type:1,Colors:[I;3668224],FadeColors:[I;64667]}]}},id:\"minecraft:firework_rocket\",Count:1},LifeTime:1}"
                     );
               }
            } else if (((DyairdropModVariables.PlayerVariables)entity.getData(DyairdropModVariables.PLAYER_VARIABLES_ATTACHMENT.get()))
               .airdroploot
               .endsWith("2")) {
               if (world instanceof ServerLevel _level) {
                  _level.getServer()
                     .getCommands()
                     .performPrefixedCommand(
                        new CommandSourceStack(
                              CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null
                           )
                           .withSuppressedOutput(),
                        "/summon minecraft:firework_rocket ~ ~1 ~ {FireworksItem:{tag:{Fireworks:{Flight:2,Explosions:[{Trail:1b,Flicker:1b,Type:0,Colors:[I;58619],FadeColors:[I;13303]},{Trail:1b,Flicker:1b,Type:1,Colors:[I;12793],FadeColors:[I;55548]}]}},id:\"minecraft:firework_rocket\",Count:1},LifeTime:1}"
                     );
               }
            } else if (((DyairdropModVariables.PlayerVariables)entity.getData(DyairdropModVariables.PLAYER_VARIABLES_ATTACHMENT.get()))
               .airdroploot
               .endsWith("3")) {
               if (world instanceof ServerLevel _level) {
                  _level.getServer()
                     .getCommands()
                     .performPrefixedCommand(
                        new CommandSourceStack(
                              CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null
                           )
                           .withSuppressedOutput(),
                        "/summon minecraft:firework_rocket ~ ~1 ~ {FireworksItem:{tag:{Fireworks:{Flight:2,Explosions:[{Trail:1b,Flicker:1b,Type:0,Colors:[I;11796731],FadeColors:[I;16187633]},{Trail:1b,Flicker:1b,Type:1,Colors:[I;16318671],FadeColors:[I;10551548]}]}},id:\"minecraft:firework_rocket\",Count:1},LifeTime:1}"
                     );
               }
            } else if (((DyairdropModVariables.PlayerVariables)entity.getData(DyairdropModVariables.PLAYER_VARIABLES_ATTACHMENT.get()))
               .airdroploot
               .endsWith("4")) {
               if (world instanceof ServerLevel _level) {
                  _level.getServer()
                     .getCommands()
                     .performPrefixedCommand(
                        new CommandSourceStack(
                              CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null
                           )
                           .withSuppressedOutput(),
                        "/summon minecraft:firework_rocket ~ ~1 ~ {FireworksItem:{tag:{Fireworks:{Flight:2,Explosions:[{Trail:1b,Flicker:1b,Type:0,Colors:[I;14786560],FadeColors:[I;16758272]},{Trail:1b,Flicker:1b,Type:1,Colors:[I;16361728],FadeColors:[I;14519814]}]}},id:\"minecraft:firework_rocket\",Count:1},LifeTime:1}"
                     );
               }
            } else if (((DyairdropModVariables.PlayerVariables)entity.getData(DyairdropModVariables.PLAYER_VARIABLES_ATTACHMENT.get()))
               .airdroploot
               .endsWith("5")) {
               if (world instanceof ServerLevel _level) {
                  _level.getServer()
                     .getCommands()
                     .performPrefixedCommand(
                        new CommandSourceStack(
                              CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null
                           )
                           .withSuppressedOutput(),
                        "/summon minecraft:firework_rocket ~ ~1 ~ {FireworksItem:{tag:{Fireworks:{Flight:2,Explosions:[{Trail:1b,Flicker:1b,Type:0,Colors:[I;16333056],FadeColors:[I;13503243]},{Trail:1b,Flicker:1b,Type:1,Colors:[I;13501444],FadeColors:[I;16202496]}]}},id:\"minecraft:firework_rocket\",Count:1},LifeTime:1}"
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
                     "/summon minecraft:firework_rocket ~ ~ ~ {FireworksItem:{tag:{Fireworks:{Flight:2,Explosions:[{Trail:1b,Flicker:1b,Type:1,Colors:[I;15952396],FadeColors:[I;16582625]},{Trail:1b,Flicker:1b,Type:1,Colors:[I;16059086],FadeColors:[I;16221952]}]}},id:\"minecraft:firework_rocket\",Count:1},LifeTime:1}"
                  );
            }

            if (((DyairdropModVariables.PlayerVariables)entity.getData(DyairdropModVariables.PLAYER_VARIABLES_ATTACHMENT.get()))
                        .airdroploot
                        .length()
                     * ((DyairdropModVariables.PlayerVariables)entity.getData(DyairdropModVariables.PLAYER_VARIABLES_ATTACHMENT.get()))
                        .airdropblock
                        .length()
                  <= 0
               && !GameModes.isCreative(entity)) {
               if (entity instanceof Player _player && !_player.level().isClientSide()) {
                  _player.displayClientMessage(Component.literal("message.airdropeventsfailure"), false);
               }
            } else if (world.canSeeSkyFromBelowWater(BlockPos.containing(x, y, z))) {
               if ((Boolean)AirdropconfigConfiguration.ENABLELOCK.get()) {
                  if (world instanceof ServerLevel _level) {
                     _level.getServer()
                        .getCommands()
                        .performPrefixedCommand(
                           new CommandSourceStack(
                                 CommandSource.NULL,
                                 new Vec3(immediatesourceentity.getX(), immediatesourceentity.getY(), immediatesourceentity.getZ()),
                                 Vec2.ZERO,
                                 _level,
                                 4,
                                 "",
                                 Component.literal(""),
                                 _level.getServer(),
                                 null
                              )
                              .withSuppressedOutput(),
                           "/setairdrop free "
                              + new DecimalFormat("##").format(immediatesourceentity.getX())
                              + " "
                              + new DecimalFormat("##").format(immediatesourceentity.getZ())
                              + " "
                              + new DecimalFormat("##").format(height)
                              + " "
                              + new DecimalFormat("##").format(position)
                              + " \""
                              + ((DyairdropModVariables.PlayerVariables)entity.getData(DyairdropModVariables.PLAYER_VARIABLES_ATTACHMENT.get()))
                                 .airdropblock
                              + "\" \""
                              + ((DyairdropModVariables.PlayerVariables)entity.getData(DyairdropModVariables.PLAYER_VARIABLES_ATTACHMENT.get()))
                                 .airdroploot
                              + "\" true true"
                        );
                  }
               } else if (world instanceof ServerLevel _level) {
                  _level.getServer()
                     .getCommands()
                     .performPrefixedCommand(
                        new CommandSourceStack(
                              CommandSource.NULL,
                              new Vec3(immediatesourceentity.getX(), immediatesourceentity.getY(), immediatesourceentity.getZ()),
                              Vec2.ZERO,
                              _level,
                              4,
                              "",
                              Component.literal(""),
                              _level.getServer(),
                              null
                           )
                           .withSuppressedOutput(),
                        "/setairdrop free "
                           + new DecimalFormat("##").format(immediatesourceentity.getX())
                           + " "
                           + new DecimalFormat("##").format(immediatesourceentity.getZ())
                           + " "
                           + new DecimalFormat("##").format(height)
                           + " "
                           + new DecimalFormat("##").format(position)
                           + " \""
                           + ((DyairdropModVariables.PlayerVariables)entity.getData(DyairdropModVariables.PLAYER_VARIABLES_ATTACHMENT.get()))
                              .airdropblock
                           + "\" \""
                           + ((DyairdropModVariables.PlayerVariables)entity.getData(DyairdropModVariables.PLAYER_VARIABLES_ATTACHMENT.get()))
                              .airdroploot
                           + "\" false true"
                     );
               }

               if (entity instanceof Player _player && !_player.level().isClientSide()) {
                  _player.displayClientMessage(Component.literal(Component.translatable("message.callingairdropsuccess").getString()), false);
               }

               String _setval = "";
               entity.getData(DyairdropModVariables.PLAYER_VARIABLES_ATTACHMENT.get()).ifPresentData(capability -> {
                  capability.airdropblock = _setval;
                  capability.syncPlayerVariables(entity);
               });
               String _setvalb = "";
               entity.getData(DyairdropModVariables.PLAYER_VARIABLES_ATTACHMENT.get()).ifPresentData(capability -> {
                  capability.airdroploot = _setvalb;
                  capability.syncPlayerVariables(entity);
               });
            } else if (entity instanceof Player _player && !_player.level().isClientSide()) {
               _player.displayClientMessage(Component.literal("message.airdropeventsfailure"), false);
            }

            if (!immediatesourceentity.level().isClientSide()) {
               immediatesourceentity.discard();
            }
         }
      }
   }
}
