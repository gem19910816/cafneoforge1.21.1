package net.mcreator.dyairdrop.procedures;

import java.util.HashMap;
import net.mcreator.dyairdrop.network.DyairdropModVariables;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.world.entity.Entity;

public class Button2Procedure {
   public static void execute(Entity entity, HashMap guistate) {
      if (entity != null && guistate != null) {
         String _setval = ((DyairdropModVariables.PlayerVariables)entity.getData(DyairdropModVariables.PLAYER_VARIABLES_ATTACHMENT.get()))
               .password
            + " 2 ";
         entity.getData(DyairdropModVariables.PLAYER_VARIABLES_ATTACHMENT.get()).ifPresentData(capability -> {
            capability.password = _setval;
            capability.syncPlayerVariables(entity);
         });
         if (guistate.get("text:password_panel") instanceof EditBox _tf) {
            _tf.setValue(
               ((DyairdropModVariables.PlayerVariables)entity.getData(DyairdropModVariables.PLAYER_VARIABLES_ATTACHMENT.get()))
                  .password
            );
         }
      }
   }
}
