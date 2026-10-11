package net.mcreator.gore.potion;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

/** A hidden animation marker; AI suspension is handled by the shove procedure. */
public final class ZombieDownedMobEffect extends MobEffect {
   public ZombieDownedMobEffect() {
      super(MobEffectCategory.NEUTRAL, 0x707070);
   }
}
