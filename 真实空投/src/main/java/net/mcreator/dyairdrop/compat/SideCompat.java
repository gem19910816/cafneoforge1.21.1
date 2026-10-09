package net.mcreator.dyairdrop.compat;

import net.minecraft.server.MinecraftServer;
import net.neoforged.neoforge.server.ServerLifecycleHooks;

/**
 * Thread safety helper for the client-only branches the 1.20.1 sources contain.
 *
 * <p>The original code decides "am I on the client?" from the <em>level</em>
 * ({@code world.isClientSide()}). In single-player that is not the same question as "am I on the
 * client thread": the integrated server's tick thread can be holding the {@code ClientLevel},
 * because MCreator's screens also run the server-side procedure locally and its delayed steps are
 * queued into the server tick loop. Calling {@code ClientLevel.playLocalSound} from that thread
 * touches {@code ThreadLocalRandom} owned by the render thread; with C2ME installed that is a hard
 * crash:
 *
 * <pre>
 * com.ishland.c2me...CheckedThreadLocalRandom: ThreadLocalRandom accessed from a different thread
 *   at net.minecraft.client.multiplayer.ClientLevel.playLocalSound
 *   at net.mcreator.dyairdrop.procedures.CheckProcedure.lambda$execute$2
 *   at net.mcreator.dyairdrop.DyairdropMod.onServerTick
 * </pre>
 *
 * <p>{@link #isClientThread()} answers the question the code actually needs: it is only safe to
 * touch client-only APIs when no server tick thread is involved.
 */
public class SideCompat {
   public SideCompat() {
   }

   /** True when the current thread may safely use client-side APIs (no server tick thread involved). */
   public static boolean isClientThread() {
      MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
      return server == null || !server.isSameThread();
   }
}
