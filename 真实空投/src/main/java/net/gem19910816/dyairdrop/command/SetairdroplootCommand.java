package net.gem19910816.dyairdrop.command;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.gem19910816.dyairdrop.procedures.SetairdropcodeProcedure;
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
public class SetairdroplootCommand {
   @SubscribeEvent
   public static void registerCommand(RegisterCommandsEvent event) {
      event.getDispatcher()
         .register(
            (LiteralArgumentBuilder)((LiteralArgumentBuilder)Commands.literal("setairdroploot").requires(s -> s.hasPermission(4)))
               .then(
                  Commands.argument("player", EntityArgument.player())
                     .then(
                        Commands.argument("blockid", StringArgumentType.string())
                           .then(Commands.argument("loot", StringArgumentType.string()).executes(arguments -> {
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

                              SetairdropcodeProcedure.execute(arguments);
                              return 0;
                           }))
                     )
               )
         );
   }
}
