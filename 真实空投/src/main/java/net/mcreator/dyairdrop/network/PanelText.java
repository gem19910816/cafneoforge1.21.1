package net.mcreator.dyairdrop.network;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.world.entity.Entity;

/**
 * MCreator keeps GUI text-box state in the screen's static {@code guistate} map, which
 * only exists on the client.  Every panel button press therefore carries the text-box
 * content with it (see the ButtonMessage payloads) and it is stored here so that the
 * server-side procedure sees exactly what the player typed.
 *
 * The local {@code guistate} is still preferred when it holds a live widget, which keeps
 * single-player behaviour byte-for-byte identical to the original mod.
 */
public class PanelText {
   private static final Map<UUID, String> LAST = new ConcurrentHashMap<>();

   public PanelText() {
   }

   public static void set(String text) {
      set((UUID)null, text);
   }

   public static void set(Entity entity, String text) {
      set(entity == null ? null : entity.getUUID(), text);
   }

   public static void set(UUID playerId, String text) {
      if (playerId != null) {
         LAST.put(playerId, text == null ? "" : text);
      }
   }

   public static String get() {
      return get((UUID)null);
   }

   public static String get(Entity entity) {
      return get(entity == null ? null : entity.getUUID());
   }

   public static String get(UUID playerId) {
      return playerId == null ? "" : LAST.getOrDefault(playerId, "");
   }

   public static String from(HashMap<?, ?> guistate) {
      return from(guistate, null);
   }

   public static String from(HashMap<?, ?> guistate, Entity entity) {
      // A packet carries the authoritative value from the player's screen. On
      // an integrated server the static MCreator guistate can also contain a
      // client EditBox, so prefer the per-player packet cache whenever it has
      // been populated instead of reading a potentially stale widget.
      if (entity != null) {
         UUID playerId = entity.getUUID();
         if (LAST.containsKey(playerId)) {
            return LAST.getOrDefault(playerId, "");
         }
      }
      if (guistate != null) {
         Object widget = guistate.get("text:password_panel");
         if (widget != null) {
            try {
               // reflective so that no client-only widget class is referenced from
               // common code
               return String.valueOf(widget.getClass().getMethod("getValue").invoke(widget));
            } catch (Throwable ignored) {
               // fall through to the value sent by the client
            }
         }
      }
      return get(entity);
   }
}
