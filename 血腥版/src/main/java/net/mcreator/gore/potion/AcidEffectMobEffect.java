package net.mcreator.gore.potion;

import net.mcreator.gore.procedures.AcidEffectEffectStartedappliedProcedure;
import net.mcreator.gore.procedures.AcidEffectOnEffectActiveTickProcedure;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;

public class AcidEffectMobEffect extends MobEffect {
   public AcidEffectMobEffect() {
      super(MobEffectCategory.HARMFUL, -11993088);
   }

   public void addAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
      AcidEffectEffectStartedappliedProcedure.execute(entity.level(), entity);
   }

   public boolean applyEffectTick(LivingEntity entity, int amplifier) {
      AcidEffectOnEffectActiveTickProcedure.execute(entity.level(), entity, (double)amplifier);
      return true;
   }

   public boolean isDurationEffectTick(int duration, int amplifier) {
      return true;
   }
}
