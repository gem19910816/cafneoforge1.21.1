package net.mcreator.dyairdrop.procedures;

import net.mcreator.dyairdrop.network.DyairdropModVariables;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

public class FlareburstProcedure {
   public FlareburstProcedure() {
   }

   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, Entity immediatesourceentity) {
      if (entity != null && immediatesourceentity != null) {
         if (DyairdropModVariables.get(entity)
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
         } else if (DyairdropModVariables.get(entity)
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
         } else if (DyairdropModVariables.get(entity)
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
         } else if (DyairdropModVariables.get(entity)
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
         } else if (DyairdropModVariables.get(entity)
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

         if (!immediatesourceentity.level().isClientSide()) {
            immediatesourceentity.discard();
         }
      }
   }
}
