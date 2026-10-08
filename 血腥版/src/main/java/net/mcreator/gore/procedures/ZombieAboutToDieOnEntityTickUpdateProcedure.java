package net.mcreator.gore.procedures;

import net.mcreator.gore.init.GoreEditionModMobEffects;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.LevelAccessor;

public class ZombieAboutToDieOnEntityTickUpdateProcedure {
   public static void execute(LevelAccessor world, Entity entity) {
      if (entity != null) {
         if ((!(entity instanceof LivingEntity _livEnt0) || !_livEnt0.hasEffect(GoreEditionModMobEffects.VULNERABILITY))
            && entity instanceof LivingEntity _entity
            && !_entity.level().isClientSide()) {
            _entity.addEffect(new MobEffectInstance(GoreEditionModMobEffects.VULNERABILITY, Integer.MAX_VALUE, 2));
         }

         entity.getPersistentData().putDouble("tick1", entity.getPersistentData().getDouble("tick1") + 1.0);
         if (entity.getPersistentData().getDouble("tick1") == 200.0) {
            entity.hurt(
               new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.GENERIC)),
               (entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1.0F) * 2.0F
            );
         }
      }
   }
}
