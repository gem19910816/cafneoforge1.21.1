package net.mcreator.gore.procedures;

import javax.annotation.Nullable;
import net.mcreator.gore.configuration.GoreEditionModeSettingsConfiguration;
import net.mcreator.gore.entity.CreeperCorpseEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.LevelAccessor;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.EntityTickEvent.Pre;

@EventBusSubscriber
public class CorpseNaturallyExplodeProcedure {
   @SubscribeEvent
   public static void onEntityTick(Pre event) {
      execute(event, event.getEntity().level(), event.getEntity());
   }

   public static void execute(LevelAccessor world, Entity entity) {
      execute(null, world, entity);
   }

   private static void execute(@Nullable Event event, LevelAccessor world, Entity entity) {
      if (entity != null
         && (Boolean)GoreEditionModeSettingsConfiguration.CORPSES_CAN_EXPLODE.get()
         && entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_corpses")))) {
         if (!(entity instanceof CreeperCorpseEntity) && !entity.getPersistentData().getBoolean("time_to_explode")) {
            entity.getPersistentData().putBoolean("time_to_explode", true);
         }

         if (entity.getPersistentData().getBoolean("time_to_explode")) {
            if (entity.getPersistentData().getDouble("exploit_timer") == (Double)GoreEditionModeSettingsConfiguration.MIN_TIME_TO_EXPLODE_CORPSE.get()
               && !entity.getPersistentData().getBoolean("toughness")) {
               entity.getPersistentData().putBoolean("can_exploit", true);
            }

            entity.getPersistentData().putDouble("exploit_timer", entity.getPersistentData().getDouble("exploit_timer") + 1.0);
            if (entity.getPersistentData().getDouble("exploit_timer") >= (Double)GoreEditionModeSettingsConfiguration.MAX_TIME_TO_EXPLODE_CORPSE.get()) {
               entity.getPersistentData().putBoolean("can_exploit", true);
            }

            if (entity.getPersistentData().getBoolean("can_exploit")
               && !entity.getPersistentData().getBoolean("cordyceps_propagating_in_a_corpse")
               && Math.random() < 0.04) {
               entity.hurt(
                  new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.GENERIC)),
                  (entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1.0F) * 2.0F
               );
            }
         }
      }
   }
}
