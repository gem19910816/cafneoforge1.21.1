package net.mcreator.gore.procedures;

import net.mcreator.gore.init.GoreEditionModMobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level.ExplosionInteraction;

public class ExplosiveEffectExpiresProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null && world instanceof Level _level && !_level.isClientSide()) {
         double var10003;
         int var10006;
         label16: {
            var10003 = y + (double)(entity.getBbHeight() / 2.0F);
            if (entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(GoreEditionModMobEffects.EXPLODING)) {
               var10006 = _livEnt.getEffect(GoreEditionModMobEffects.EXPLODING).getAmplifier();
               break label16;
            }

            var10006 = 0;
         }

         _level.explode(null, x, var10003, z, (float)(1 + var10006), ExplosionInteraction.NONE);
      }
   }
}
