package net.mcreator.dyairdrop.command;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.mcreator.dyairdrop.procedures.Flycode2neoProcedure;
import net.mcreator.dyairdrop.procedures.Flycode2neomapProcedure;
import net.mcreator.dyairdrop.procedures.Flycode3neoProcedure;
import net.mcreator.dyairdrop.procedures.Flycode3neomapProcedure;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.util.FakePlayerFactory;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;

@EventBusSubscriber
public class SetairdropCommand {
   public SetairdropCommand() {
   }

   @SubscribeEvent
   public static void registerCommand(RegisterCommandsEvent event) {
      event.getDispatcher()
         .register(
            (LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)Commands.literal(
                              "setairdrop"
                           )
                           .requires(s -> s.hasPermission(2)))
                        .then(
                           Commands.literal("free")
                              .then(
                                 Commands.argument("x", DoubleArgumentType.doubleArg())
                                    .then(
                                       Commands.argument("z", DoubleArgumentType.doubleArg())
                                          .then(
                                             Commands.argument("height", DoubleArgumentType.doubleArg(0.0, 320.0))
                                                .then(
                                                   Commands.argument("length", DoubleArgumentType.doubleArg(0.0, 512.0))
                                                      .then(
                                                         Commands.argument("blockid", StringArgumentType.string())
                                                            .then(
                                                               Commands.argument("loot_table", StringArgumentType.string())
                                                                  .then(Commands.argument("pin", BoolArgumentType.bool()).executes(arguments -> {
                                                                     Level world = ((CommandSourceStack)arguments.getSource()).getUnsidedLevel();
                                                                     double x = ((CommandSourceStack)arguments.getSource()).getPosition().x();
                                                                     double y = ((CommandSourceStack)arguments.getSource()).getPosition().y();
                                                                     double z = ((CommandSourceStack)arguments.getSource()).getPosition().z();
                                                                     Entity entity = ((CommandSourceStack)arguments.getSource()).getEntity();
                                                                     if (entity == null && world instanceof ServerLevel _servLevel) {
                                                                        entity = FakePlayerFactory.getMinecraft(_servLevel);
                                                                     }

                                                                     Direction direction = Direction.DOWN;
                                                                     if (entity != null) {
                                                                        direction = entity.getDirection();
                                                                     }

                                                                     Flycode2neoProcedure.execute(world, arguments);
                                                                     return 0;
                                                                  }))
                                                            )
                                                      )
                                                )
                                          )
                                    )
                              )
                        ))
                     .then(
                        Commands.literal("random")
                           .then(
                              Commands.argument("player", EntityArgument.player())
                                 .then(
                                    Commands.argument("height", DoubleArgumentType.doubleArg(0.0, 320.0))
                                       .then(
                                          Commands.argument("length", DoubleArgumentType.doubleArg(0.0, 512.0))
                                             .then(
                                                Commands.argument("driftmin", DoubleArgumentType.doubleArg(0.0))
                                                   .then(
                                                      Commands.argument("driftmax", DoubleArgumentType.doubleArg(0.0))
                                                         .then(
                                                            Commands.argument("blockid", StringArgumentType.string())
                                                               .then(
                                                                  Commands.argument("loot_table", StringArgumentType.string())
                                                                     .then(Commands.argument("pin", BoolArgumentType.bool()).executes(arguments -> {
                                                                        Level world = ((CommandSourceStack)arguments.getSource()).getUnsidedLevel();
                                                                        double x = ((CommandSourceStack)arguments.getSource()).getPosition().x();
                                                                        double y = ((CommandSourceStack)arguments.getSource()).getPosition().y();
                                                                        double z = ((CommandSourceStack)arguments.getSource()).getPosition().z();
                                                                        Entity entity = ((CommandSourceStack)arguments.getSource()).getEntity();
                                                                        if (entity == null && world instanceof ServerLevel _servLevel) {
                                                                           entity = FakePlayerFactory.getMinecraft(_servLevel);
                                                                        }

                                                                        Direction direction = Direction.DOWN;
                                                                        if (entity != null) {
                                                                           direction = entity.getDirection();
                                                                        }

                                                                        Flycode3neoProcedure.execute(world, x, y, z, arguments);
                                                                        return 0;
                                                                     }))
                                                               )
                                                         )
                                                   )
                                             )
                                       )
                                 )
                           )
                     ))
                  .then(
                     Commands.literal("free")
                        .then(
                           Commands.argument("x", DoubleArgumentType.doubleArg())
                              .then(
                                 Commands.argument("z", DoubleArgumentType.doubleArg())
                                    .then(
                                       Commands.argument("height", DoubleArgumentType.doubleArg(0.0, 320.0))
                                          .then(
                                             Commands.argument("length", DoubleArgumentType.doubleArg(0.0, 512.0))
                                                .then(
                                                   Commands.argument("blockid", StringArgumentType.string())
                                                      .then(
                                                         Commands.argument("loot_table", StringArgumentType.string())
                                                            .then(
                                                               Commands.argument("pin", BoolArgumentType.bool())
                                                                  .then(Commands.argument("map", BoolArgumentType.bool()).executes(arguments -> {
                                                                     Level world = ((CommandSourceStack)arguments.getSource()).getUnsidedLevel();
                                                                     double x = ((CommandSourceStack)arguments.getSource()).getPosition().x();
                                                                     double y = ((CommandSourceStack)arguments.getSource()).getPosition().y();
                                                                     double z = ((CommandSourceStack)arguments.getSource()).getPosition().z();
                                                                     Entity entity = ((CommandSourceStack)arguments.getSource()).getEntity();
                                                                     if (entity == null && world instanceof ServerLevel _servLevel) {
                                                                        entity = FakePlayerFactory.getMinecraft(_servLevel);
                                                                     }

                                                                     Direction direction = Direction.DOWN;
                                                                     if (entity != null) {
                                                                        direction = entity.getDirection();
                                                                     }

                                                                     Flycode2neomapProcedure.execute(world, arguments);
                                                                     return 0;
                                                                  }))
                                                            )
                                                      )
                                                )
                                          )
                                    )
                              )
                        )
                  ))
               .then(
                  Commands.literal("random")
                     .then(
                        Commands.argument("player", EntityArgument.player())
                           .then(
                              Commands.argument("height", DoubleArgumentType.doubleArg(0.0, 320.0))
                                 .then(
                                    Commands.argument("length", DoubleArgumentType.doubleArg(0.0, 512.0))
                                       .then(
                                          Commands.argument("driftmin", DoubleArgumentType.doubleArg(0.0))
                                             .then(
                                                Commands.argument("driftmax", DoubleArgumentType.doubleArg(0.0))
                                                   .then(
                                                      Commands.argument("blockid", StringArgumentType.string())
                                                         .then(
                                                            Commands.argument("loot_table", StringArgumentType.string())
                                                               .then(
                                                                  Commands.argument("pin", BoolArgumentType.bool())
                                                                     .then(Commands.argument("map", BoolArgumentType.bool()).executes(arguments -> {
                                                                        Level world = ((CommandSourceStack)arguments.getSource()).getUnsidedLevel();
                                                                        double x = ((CommandSourceStack)arguments.getSource()).getPosition().x();
                                                                        double y = ((CommandSourceStack)arguments.getSource()).getPosition().y();
                                                                        double z = ((CommandSourceStack)arguments.getSource()).getPosition().z();
                                                                        Entity entity = ((CommandSourceStack)arguments.getSource()).getEntity();
                                                                        if (entity == null && world instanceof ServerLevel _servLevel) {
                                                                           entity = FakePlayerFactory.getMinecraft(_servLevel);
                                                                        }

                                                                        Direction direction = Direction.DOWN;
                                                                        if (entity != null) {
                                                                           direction = entity.getDirection();
                                                                        }

                                                                        Flycode3neomapProcedure.execute(world, x, y, z, arguments);
                                                                        return 0;
                                                                     }))
                                                               )
                                                         )
                                                   )
                                             )
                                       )
                                 )
                           )
                     )
               )
         );
   }
}
