package net.mcreator.gore.procedures;

import javax.annotation.Nullable;
import net.mcreator.gore.GoreEditionMod;
import net.mcreator.gore.configuration.GoreEditionModeSettingsConfiguration;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.LevelAccessor;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;

@EventBusSubscriber
public class ToughessHasStrengthProcedure {
   @SubscribeEvent
   public static void onEntitySpawned(EntityJoinLevelEvent event) {
      execute(event, event.getLevel(), event.getEntity());
   }

   public static void execute(LevelAccessor world, Entity entity) {
      execute(null, world, entity);
   }

   private static void execute(@Nullable Event event, LevelAccessor world, Entity entity) {
      if (entity != null
         && (Boolean)GoreEditionModeSettingsConfiguration.INSANE_THINGS.get()
         && (Boolean)GoreEditionModeSettingsConfiguration.TOUGHNESS_RAGE.get()) {
         GoreEditionMod.queueServerWork(2, () -> {
            if (entity.getPersistentData().getBoolean("toughness")) {
               if (Math.random() < 0.4) {
                  if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
                     _entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, Integer.MAX_VALUE, 1));
                  }
               } else if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
                  _entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, Integer.MAX_VALUE, 0));
               }

               if (Math.random() < 0.5) {
                  if (Math.random() < 0.3) {
                     if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
                        _entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 0));
                     }
                  } else if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
                     _entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 1));
                  }
               }

               if (Math.random() < 0.5) {
                  if (Math.random() < 0.4) {
                     if (Math.random() < 0.4) {
                        if (Math.random() < 0.4) {
                           if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
                              _entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, Integer.MAX_VALUE, 3));
                           }
                        } else if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
                           _entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, Integer.MAX_VALUE, 2));
                        }
                     } else if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
                        _entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, Integer.MAX_VALUE, 1));
                     }
                  } else if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
                     _entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, Integer.MAX_VALUE, 0));
                  }
               }
            }
         });
      }
   }
}
