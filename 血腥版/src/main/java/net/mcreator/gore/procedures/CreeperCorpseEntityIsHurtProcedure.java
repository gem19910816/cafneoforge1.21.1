package net.mcreator.gore.procedures;

import net.minecraft.world.entity.Entity;

public class CreeperCorpseEntityIsHurtProcedure {
   public static void execute(Entity entity) {
      if (entity != null && !entity.getPersistentData().getBoolean("looted")) {
         entity.getPersistentData().putBoolean("hurted", true);
      }
   }
}
