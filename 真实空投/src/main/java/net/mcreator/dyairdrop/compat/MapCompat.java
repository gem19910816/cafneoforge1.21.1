package net.mcreator.dyairdrop.compat;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

/**
 * Map-mod compatibility.
 *
 * <p>The 1.20.1 mod asked an external helper for <code>addwaypointxaero ...</code>. No
 * released mod provides that command, so on a normal installation every airdrop landing
 * produced an "Unknown command" error and no waypoint ever appeared - which is what made
 * the mod look incompatible with Xaero's Minimap.
 *
 * <p>Two independent paths are offered here:
 * <ol>
 *   <li>the original helper command, but only when it actually exists in the command
 *       dispatcher (so nothing is logged when it does not);</li>
 *   <li>Xaero's own waypoint sharing format, <code>xaero-waypoint:name:symbol:x:y:z:color:rotation:yaw</code>,
 *       sent as a system chat message. Xaero's Minimap turns that message into a
 *       clickable "add waypoint" entry, so the airdrop shows up on the minimap with
 *       nothing but Xaero's Minimap installed.</li>
 * </ol>
 */
public class MapCompat {
   public static final String XAERO_WAYPOINT_PREFIX = "xaero-waypoint:";

   public MapCompat() {
   }

   /** True when the server's command dispatcher really has this root command. */
   public static boolean hasCommand(ServerLevel level, String root) {
      MinecraftServer server = level.getServer();
      if (server == null) {
         return false;
      }
      try {
         return server.getCommands().getDispatcher().getRoot().getChild(root) != null;
      } catch (Throwable ignored) {
         return false;
      }
   }

   /** Runs a helper command (used only for commands proven to exist). */
   public static void runHelperCommand(CommandSourceStack source, String command) {
      if (source.getServer() == null) {
         return;
      }
      source.getServer().getCommands().performPrefixedCommand(source.withSuppressedOutput(), command);
   }

   /**
    * Sends every player a Xaero waypoint-share message for the given position.
    *
    * @param colorIndex Xaero {@code WaypointColor} ordinal (6 = gold)
    */
   public static void sendXaeroWaypoint(ServerLevel level, int x, int y, int z, String name, int colorIndex) {
      MinecraftServer server = level.getServer();
      if (server == null) {
         return;
      }
      String safeName = sanitiseName(name);
      String symbol = symbolFor(safeName);
      String message = XAERO_WAYPOINT_PREFIX
         + safeName + ":"
         + symbol + ":"
         + x + ":"
         + y + ":"
         + z + ":"
         + Math.max(0, colorIndex) + ":"
         + "false:0";
      Component component = Component.literal(message);
      for (ServerPlayer player : server.getPlayerList().getPlayers()) {
         player.sendSystemMessage(component);
      }
   }

   /** Xaero requires 1..32 characters and splits the message on ':'. */
   public static String sanitiseName(String name) {
      String safe = name == null ? "" : name.replace(":", "").replace("\n", " ").strip();
      if (safe.length() > 32) {
         safe = safe.substring(0, 32);
      }
      if (safe.isEmpty()) {
         safe = "Airdrop";
      }
      return safe;
   }

   /** Xaero requires a symbol of 1..3 characters. */
   public static String symbolFor(String safeName) {
      String symbol = safeName.replace(":", "").strip();
      if (symbol.isEmpty()) {
         return "A";
      }
      return symbol.substring(0, Math.min(3, symbol.length()));
   }
}
