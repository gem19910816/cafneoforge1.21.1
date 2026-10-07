package net.gem19910816.dyairdrop.command;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.gem19910816.dyairdrop.core.AirdropScheduler;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.arguments.blocks.BlockStateArgument;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;

@EventBusSubscriber
public class ReAirdropCommand {
   @SubscribeEvent
   public static void registerCommand(RegisterCommandsEvent event) {
      event.getDispatcher()
         .register(
            (LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)Commands.literal("airdrop").requires(s -> s.hasPermission(2)))
                  .then(
                     Commands.argument("player", EntityArgument.player())
                        .then(
                           Commands.argument("blockid", BlockStateArgument.block(event.getBuildContext()))
                              .then(Commands.argument("pin", BoolArgumentType.bool()).executes(arguments -> {
                                 Level world = ((CommandSourceStack)arguments.getSource()).getUnsidedLevel();
                                 double x = ((CommandSourceStack)arguments.getSource()).getPosition().x();
                                 double y = ((CommandSourceStack)arguments.getSource()).getPosition().y();
                                 double z = ((CommandSourceStack)arguments.getSource()).getPosition().z();
                                 AirdropScheduler.reAirdrop(world, arguments);
                                 return 0;
                              }))
                        )
                  ))
               .then(Commands.literal("world").executes(arguments -> {
                  Level world = ((CommandSourceStack)arguments.getSource()).getUnsidedLevel();
                  double x = ((CommandSourceStack)arguments.getSource()).getPosition().x();
                  double y = ((CommandSourceStack)arguments.getSource()).getPosition().y();
                  double z = ((CommandSourceStack)arguments.getSource()).getPosition().z();
                  AirdropScheduler.randomWorldAirdrop(world);
                  return 0;
               }))
         );
   }
}
