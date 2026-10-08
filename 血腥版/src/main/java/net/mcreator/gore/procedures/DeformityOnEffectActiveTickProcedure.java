package net.mcreator.gore.procedures;

import net.mcreator.gore.init.GoreEditionModMobEffects;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.LevelAccessor;

public class DeformityOnEffectActiveTickProcedure {
   public static void execute(LevelAccessor world, Entity entity) {
      if (entity != null) {
         if (Math.random() < 0.01) {
            DamageSource var10001;
            int var10004;
            label33: {
               var10001 = new DamageSource(
                  world.registryAccess()
                     .registryOrThrow(Registries.DAMAGE_TYPE)
                     .getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.parse("gore_edition:spiral_twisted")))
               );
               if (entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(GoreEditionModMobEffects.SPIRAL_TWIST)) {
                  var10004 = _livEnt.getEffect(GoreEditionModMobEffects.SPIRAL_TWIST).getAmplifier();
                  break label33;
               }

               var10004 = 0;
            }

            entity.hurt(var10001, (float)(1 + 1 * var10004));
         }

         int var10000;
         label26: {
            if (entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(GoreEditionModMobEffects.SPIRAL_TWIST)) {
               var10000 = _livEnt.getEffect(GoreEditionModMobEffects.SPIRAL_TWIST).getAmplifier();
               break label26;
            }

            var10000 = 0;
         }

         if (var10000 >= 1 && entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
            _entity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 80, 0));
         }
      }
   }
}
