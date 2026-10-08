package net.mcreator.gore.procedures;

import javax.annotation.Nullable;
import net.mcreator.gore.configuration.GoreEditionModeSettingsConfiguration;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;

@EventBusSubscriber
public class ToughnessSpawningProcedure {
   @SubscribeEvent
   public static void onEntitySpawned(EntityJoinLevelEvent event) {
      execute(event, event.getEntity());
   }

   public static void execute(Entity entity) {
      execute(null, entity);
   }

   private static void execute(@Nullable Event event, Entity entity) {
      if (entity != null) {
         double probability = 0.0;
         double probability_two = 0.0;
         if ((
               entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("minecraft:true_zombie")))
                  || entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("minecraft:true_husk")))
                  || entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("minecraft:true_spider")))
                  || entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("minecraft:true_skeleton")))
                  || entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_corpses")))
            )
            && (!(entity instanceof LivingEntity _livEnt5) || !_livEnt5.isBaby())
            && Math.random() * 100.0 < (Double)GoreEditionModeSettingsConfiguration.TOUGHNESS_ZOMBIE_SPAWN_PROBABILITIES.get()) {
            entity.getPersistentData().putBoolean("toughness", true);
         }

         if (entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("minecraft:toughness")))
            && Math.random() * 100.0 < (Double)GoreEditionModeSettingsConfiguration.TOUGHNESS_ZOMBIE_RESURRECTION.get()) {
            entity.getPersistentData().putBoolean("resurrection", true);
         }
      }
   }
}
