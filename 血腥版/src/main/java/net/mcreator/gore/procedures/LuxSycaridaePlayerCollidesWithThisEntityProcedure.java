package net.mcreator.gore.procedures;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class LuxSycaridaePlayerCollidesWithThisEntityProcedure {
   public static void execute(Entity sourceentity) {
      if (sourceentity != null && sourceentity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
         _entity.addEffect(new MobEffectInstance(MobEffects.GLOWING, 200, 0));
      }
   }
}
