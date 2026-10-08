package net.mcreator.gore.procedures;

import net.mcreator.gore.network.GoreEditionModVariables;
import net.minecraft.world.entity.Entity;

public class EMFC3Procedure {
   public static boolean execute(Entity entity) {
      return entity == null
         ? false
         : GoreEditionModVariables.getPlayerVariables(entity).exarrack_monster_screen_frames >= 12.0
            && GoreEditionModVariables.getPlayerVariables(entity).exarrack_monster_screen_frames < 18.0;
   }
}
