package net.mcreator.gore.potion;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class DeafnessMobEffect extends MobEffect {
   public DeafnessMobEffect() {
      super(MobEffectCategory.HARMFUL, -14737629);
   }

   public boolean isDurationEffectTick(int duration, int amplifier) {
      return true;
   }
}
