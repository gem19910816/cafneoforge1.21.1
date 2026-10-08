package net.mcreator.gore.procedures;

import javax.annotation.Nullable;
import net.mcreator.gore.configuration.GoreEditionModeSettingsConfiguration;
import net.mcreator.gore.entity.HengeyonEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.LevelAccessor;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;

@EventBusSubscriber
public class WhenEntityDeathModeSelectorProcedure {
   @SubscribeEvent
   public static void onEntityDeath(LivingDeathEvent event) {
      if (event != null && event.getEntity() != null) {
         execute(
            event,
            event.getEntity().level(),
            event.getEntity().getX(),
            event.getEntity().getY(),
            event.getEntity().getZ(),
            event.getSource(),
            event.getEntity()
         );
      }
   }

   public static void execute(LevelAccessor world, double x, double y, double z, DamageSource damagesource, Entity entity) {
      execute(null, world, x, y, z, damagesource, entity);
   }

   private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, DamageSource damagesource, Entity entity) {
      if (damagesource != null && entity != null) {
         if ((Boolean)GoreEditionModeSettingsConfiguration.CORPSES.get()) {
            WhenEntityIsDeathSpawnCorpseProcedure.execute(world, damagesource, entity);
         }

         if (!entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("gore_edition:ashes_natives")))
            && (
               !entity.getPersistentData().getBoolean("have_a_corpse")
                     && (
                        entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_corpses")))
                           || !entity.getPersistentData().getBoolean("toughness")
                     )
                  || entity instanceof LivingEntity _livEnt5 && _livEnt5.isBaby()
            )
            && (Boolean)GoreEditionModeSettingsConfiguration.CANCELPARTICLESWHENENTTYCHANGESSTATE.get()) {
            if ((Double)GoreEditionModeSettingsConfiguration.DEATH_INTENSITY.get() == 1.0) {
               WhenEntityDeathGoreProcedure.execute(world, x, y, z, entity);
            }

            if ((Double)GoreEditionModeSettingsConfiguration.DEATH_INTENSITY.get() == 2.0) {
               WhenEntityIsDeathLegacyExplodeProcedure.execute(world, x, y, z, damagesource, entity);
            }
         }

         if (!entity.getPersistentData().getBoolean("toughness")
            && (Boolean)GoreEditionModeSettingsConfiguration.INVISIBILITYWHENENTITYDIES.get()
            && !(entity instanceof HengeyonEntity)
            && !entity.isAlive()
            && entity instanceof LivingEntity _entity
            && !_entity.level().isClientSide()) {
            _entity.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 200, 0, false, false));
         }

         if (entity.getPersistentData().getBoolean("toughness")
            && (Boolean)GoreEditionModeSettingsConfiguration.INVISIBLITYBECAUSEENTITYCHANGESTATE.get()
            && !entity.isAlive()
            && entity instanceof LivingEntity _entity
            && !_entity.level().isClientSide()) {
            _entity.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 200, 0, false, false));
         }
      }
   }
}
