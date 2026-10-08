package net.mcreator.gore.potion;

import net.mcreator.gore.procedures.ExplosiveEffectExpiresProcedure;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;

public class ExplosiveMobEffect extends MobEffect {
   public ExplosiveMobEffect() {
      super(MobEffectCategory.NEUTRAL, -1907998);
   }

   public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
      super.removeAttributeModifiers(attributeMap);
      ExplosiveEffectExpiresProcedure.execute(entity.level(), entity.getX(), entity.getY(), entity.getZ(), entity);
   }

   public boolean isDurationEffectTick(int duration, int amplifier) {
      return true;
   }
}
