package net.mcreator.dyairdrop.network;

import java.util.HashMap;
import java.util.Map;

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
   private static final Map<String, String> LAST = new HashMap<>();

   public PanelText() {
   }

   public static void set(String text) {
      LAST.put("text:password_panel", text == null ? "" : text);
   }

   public static String get() {
      return LAST.getOrDefault("text:password_panel", "");
   }

   public static String from(HashMap<?, ?> guistate) {
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
      return get();
   }
}
