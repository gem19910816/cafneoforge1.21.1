package net.gem19910816.dyairdrop.command;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.gem19910816.dyairdrop.core.Chat;
import net.gem19910816.dyairdrop.core.CommandArgs;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.coordinates.BlockPosArgument;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;

@EventBusSubscriber
public class TestcodeCommand {
   @SubscribeEvent
   public static void registerCommand(RegisterCommandsEvent event) {
      event.getDispatcher()
         .register((LiteralArgumentBuilder)Commands.literal("testcode").then(Commands.argument("b", BlockPosArgument.blockPos()).executes(arguments -> {
            Level world = ((CommandSourceStack)arguments.getSource()).getUnsidedLevel();
            double x = ((CommandSourceStack)arguments.getSource()).getPosition().x();
            double y = ((CommandSourceStack)arguments.getSource()).getPosition().y();
            double z = ((CommandSourceStack)arguments.getSource()).getPosition().z();
            broadcastBlockX(world, arguments);
            return 0;
         })));
   }
   /** 调试用：把参数 b 指定方块坐标的 X 值广播出来（原 TestsProcedure）。 */
   private static void broadcastBlockX(Level world, com.mojang.brigadier.context.CommandContext<CommandSourceStack> arguments) {
      Chat.broadcast(world, net.minecraft.network.chat.Component.literal(
            new java.text.DecimalFormat("##.##").format(CommandArgs.loadedPos(arguments, "b").getX())));
   }
}