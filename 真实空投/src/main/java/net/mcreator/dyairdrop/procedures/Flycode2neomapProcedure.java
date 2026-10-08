package net.mcreator.dyairdrop.procedures;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import java.text.DecimalFormat;
import net.mcreator.dyairdrop.DyairdropMod;
import net.mcreator.dyairdrop.configuration.AirdropconfigConfiguration;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

public class Flycode2neomapProcedure {
   public Flycode2neomapProcedure() {
   }

   public static void execute(LevelAccessor world, CommandContext<CommandSourceStack> arguments) {
      if (world instanceof ServerLevel level) {
         double x = DoubleArgumentType.getDouble(arguments, "x");
         double z = DoubleArgumentType.getDouble(arguments, "z");
         double length = DoubleArgumentType.getDouble(arguments, "length");
         double height = DoubleArgumentType.getDouble(arguments, "height");
         boolean pin = BoolArgumentType.getBool(arguments, "pin");
         boolean map = BoolArgumentType.getBool(arguments, "map");
         String blockId = StringArgumentType.getString(arguments, "blockid");
         String lootTable = StringArgumentType.getString(arguments, "loot_table");
         runCmd(level, new Vec3(x, 74.0, z), "playsound dyairdrop:planesound ambient @a[distance=..128] ~ ~ ~ 8 1 0");
         String entityId = AirdropconfigConfiguration.FORCELOAD.get() ? "dyairdrop:transportplane" : "dyairdrop:plane";
         boolean shouldLockName = pin && blockId.contains("dyairdrop:");
         String nameHead = shouldLockName ? "dyairdrop:locked" + blockId.replace("dyairdrop:", "") : blockId;
         String lenStr = new DecimalFormat("##").format(length);
         String customNameText = nameHead + "," + lootTable + "," + lenStr;
         Vec3 summonPos = new Vec3((double)Math.round(x - length), (double)Math.round(height), (double)Math.round(z));
         String extraNbt = map ? ",NeoForgeData:{dymap:1b}" : "";
         DyairdropMod.queueServerWork(60, () -> {
            if (world instanceof ServerLevel sl) {
               String summonCmd = "summon " + entityId + " ~ ~ ~ {CustomName:'{\"text\":\"" + customNameText + "\"}'" + extraNbt + "}";
               String escapedForTellraw = summonCmd.replace("\\", "\\\\").replace("\"", "\\\"");
               runCmd(sl, summonPos, summonCmd);
            }
         });
      }
   }

   private static void runCmd(ServerLevel level, Vec3 pos, String cmd) {
      level.getServer()
         .getCommands()
         .performPrefixedCommand(
            new CommandSourceStack(CommandSource.NULL, pos, Vec2.ZERO, level, 4, "", Component.literal(""), level.getServer(), null).withSuppressedOutput(), cmd
         );
   }
}
