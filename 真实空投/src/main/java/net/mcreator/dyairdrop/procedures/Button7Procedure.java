package net.mcreator.dyairdrop.procedures;

import java.util.HashMap;
import net.mcreator.dyairdrop.network.DyairdropModVariables;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.world.entity.Entity;

public class Button7Procedure {
   public Button7Procedure() {
   }

   public static void execute(Entity entity, HashMap guistate) {
      if (entity != null && guistate != null) {
         String _setval = DyairdropModVariables.get(entity)
               .password
            + " 7 ";
         DyairdropModVariables.with(entity, capability -> {
            capability.password = _setval;
            capability.syncPlayerVariables(entity);
         });
         if (guistate.get("text:password_panel") instanceof EditBox _tf) {
            _tf.setValue(
               DyairdropModVariables.get(entity)
                  .password
            );
         }
      }
   }
}
