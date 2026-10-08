package net.mcreator.gore.procedures;

import net.mcreator.gore.entity.DismemberedSpiderICorpseEntity;
import net.minecraft.world.entity.Entity;

public class DismemberedSpiderICorpseEntityIsHurtProcedure {
   public static void execute(Entity entity) {
      if (entity != null && entity instanceof DismemberedSpiderICorpseEntity) {
         ((DismemberedSpiderICorpseEntity)entity).setAnimation("hurt");
      }
   }
}
