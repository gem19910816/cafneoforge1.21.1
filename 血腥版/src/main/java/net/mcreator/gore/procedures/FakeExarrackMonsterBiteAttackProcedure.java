package net.mcreator.gore.procedures;

import net.mcreator.gore.entity.FakeExarrackMonsterEntity;
import net.mcreator.gore.network.GoreEditionModVariables;
import net.minecraft.world.entity.Entity;

public class FakeExarrackMonsterBiteAttackProcedure {
   public static void execute(Entity entity, Entity sourceentity) {
      if (entity != null && sourceentity != null && sourceentity instanceof FakeExarrackMonsterEntity) {
         if (!sourceentity.level().isClientSide()) {
            sourceentity.discard();
         }

         boolean _setval = true;
         GoreEditionModVariables.PlayerVariables capability = GoreEditionModVariables.getPlayerVariables(entity);
         capability.exarrack_monster_screen = _setval;
         capability.syncPlayerVariables(entity);
      }
   }
}
