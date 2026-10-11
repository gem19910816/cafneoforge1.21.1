package net.mcreator.dyairdrop.command;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.mcreator.dyairdrop.procedures.Flycode2neoProcedure;
import net.mcreator.dyairdrop.procedures.Flycode2neomapProcedure;
import net.mcreator.dyairdrop.procedures.Flycode3neoProcedure;
import net.mcreator.dyairdrop.procedures.Flycode3neomapProcedure;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

@EventBusSubscriber
public class SetairdropCommand {
   private SetairdropCommand() {
   }

   @SubscribeEvent
   public static void registerCommand(RegisterCommandsEvent event) {
      LiteralArgumentBuilder<CommandSourceStack> root = Commands.literal("setairdrop")
         .requires(source -> source.hasPermission(2));

      ArgumentBuilder<CommandSourceStack, ?> freePin = Commands.argument("pin", BoolArgumentType.bool())
         .executes(context -> executeFree(context, false));
      freePin.then(Commands.argument("map", BoolArgumentType.bool()).executes(context -> executeFree(context, true)));
      ArgumentBuilder<CommandSourceStack, ?> freeLoot = Commands.argument("loot_table", StringArgumentType.string()).then(freePin);
      ArgumentBuilder<CommandSourceStack, ?> freeBlock = Commands.argument("blockid", StringArgumentType.string()).then(freeLoot);
      ArgumentBuilder<CommandSourceStack, ?> freeLength = Commands.argument("length", DoubleArgumentType.doubleArg(0.0, 512.0)).then(freeBlock);
      ArgumentBuilder<CommandSourceStack, ?> freeHeight = Commands.argument("height", DoubleArgumentType.doubleArg(0.0, 320.0)).then(freeLength);
      ArgumentBuilder<CommandSourceStack, ?> freeZ = Commands.argument("z", DoubleArgumentType.doubleArg()).then(freeHeight);
      root.then(Commands.literal("free").then(Commands.argument("x", DoubleArgumentType.doubleArg()).then(freeZ)));

      ArgumentBuilder<CommandSourceStack, ?> randomPin = Commands.argument("pin", BoolArgumentType.bool())
         .executes(context -> executeRandom(context, false));
      randomPin.then(Commands.argument("map", BoolArgumentType.bool()).executes(context -> executeRandom(context, true)));
      ArgumentBuilder<CommandSourceStack, ?> randomLoot = Commands.argument("loot_table", StringArgumentType.string()).then(randomPin);
      ArgumentBuilder<CommandSourceStack, ?> randomBlock = Commands.argument("blockid", StringArgumentType.string()).then(randomLoot);
      ArgumentBuilder<CommandSourceStack, ?> randomDriftMax = Commands.argument("driftmax", DoubleArgumentType.doubleArg(0.0)).then(randomBlock);
      ArgumentBuilder<CommandSourceStack, ?> randomDriftMin = Commands.argument("driftmin", DoubleArgumentType.doubleArg(0.0)).then(randomDriftMax);
      ArgumentBuilder<CommandSourceStack, ?> randomLength = Commands.argument("length", DoubleArgumentType.doubleArg(0.0, 512.0)).then(randomDriftMin);
      ArgumentBuilder<CommandSourceStack, ?> randomHeight = Commands.argument("height", DoubleArgumentType.doubleArg(0.0, 320.0)).then(randomLength);
      ArgumentBuilder<CommandSourceStack, ?> randomPlayer = Commands.argument("player", EntityArgument.player()).then(randomHeight);
      root.then(Commands.literal("random").then(randomPlayer));

      event.getDispatcher().register(root);
   }

   private static int executeFree(CommandContext<CommandSourceStack> context, boolean map) {
      if (map) {
         Flycode2neomapProcedure.execute(context.getSource().getUnsidedLevel(), context);
      } else {
         Flycode2neoProcedure.execute(context.getSource().getUnsidedLevel(), context);
      }
      return 1;
   }

   private static int executeRandom(CommandContext<CommandSourceStack> context, boolean map) {
      double x = context.getSource().getPosition().x();
      double y = context.getSource().getPosition().y();
      double z = context.getSource().getPosition().z();
      if (map) {
         Flycode3neomapProcedure.execute(context.getSource().getUnsidedLevel(), x, y, z, context);
      } else {
         Flycode3neoProcedure.execute(context.getSource().getUnsidedLevel(), x, y, z, context);
      }
      return 1;
   }
}
