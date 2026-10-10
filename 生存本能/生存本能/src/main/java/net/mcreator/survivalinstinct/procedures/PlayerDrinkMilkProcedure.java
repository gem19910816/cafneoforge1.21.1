package net.mcreator.survivalinstinct.procedures;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class PlayerDrinkMilkProcedure {
   public static void execute(Entity entity) {
      if (entity != null) {
         if (entity instanceof LivingEntity _entity) {
            _entity.removeAllEffects();
         }
      }
   }
}
