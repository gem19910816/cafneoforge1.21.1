package net.mcreator.gore.procedures;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.phys.Vec3;

public class TheexarrackmonsterjumpplayerProcedure {
   public static void execute(Entity entity) {
      if (entity != null) {
         Entity old_entity_targeting = null;
         if ((entity instanceof Mob _mobEnt ? _mobEnt.getTarget() : null) != null) {
            if ((entity instanceof Mob _mobEntxx ? _mobEntxx.getTarget() : null) instanceof LivingEntity
               && (entity instanceof Mob _mobEntx ? _mobEntx.getTarget() : null).getY() > entity.getY() + 3.0) {
               if (entity.getPersistentData().getDouble("jump_timer") == 0.0) {
                  entity.setDeltaMovement(
                     new Vec3(
                        entity.getLookAngle().x * 2.0,
                        ((entity instanceof Mob _mobEntxxx ? _mobEntxxx.getTarget() : null).getY() - entity.getY()) / 5.0,
                        entity.getLookAngle().z * 2.0
                     )
                  );
               }

               entity.getPersistentData().putDouble("jump_timer", entity.getPersistentData().getDouble("jump_timer") + 1.0);
               if (entity.getPersistentData().getDouble("jump_timer") == 40.0) {
                  entity.getPersistentData().putDouble("jump_timer", 0.0);
               }
            }

            if (!(entity instanceof Mob _mobEntx ? _mobEntx.getTarget() : null).getPersistentData().getBoolean("exarrack_demon_targeting_screamer")
               && !entity.getPersistentData().getBoolean("DontToggleAgain")) {
               entity.getPersistentData().putBoolean("DontToggleAgain", true);
               (entity instanceof Mob _mobEntxx ? _mobEntxx.getTarget() : null).getPersistentData().putBoolean("exarrack_demon_targeting_screamer", true);
            }
         } else {
            entity.getPersistentData().putBoolean("DontToggleAgain", false);
         }
      }
   }
}
