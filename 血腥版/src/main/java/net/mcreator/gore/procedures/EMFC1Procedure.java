package net.mcreator.gore.procedures;

import net.mcreator.gore.network.GoreEditionModVariables;
import net.minecraft.world.entity.Entity;

public class EMFC1Procedure {
   public static boolean execute(Entity entity) {
      return entity == null
         ? false
         : GoreEditionModVariables.getPlayerVariables(entity).exarrack_monster_screen
            && GoreEditionModVariables.getPlayerVariables(entity).exarrack_monster_screen_frames >= 0.0
            && GoreEditionModVariables.getPlayerVariables(entity).exarrack_monster_screen_frames < 6.0;
   }
}
