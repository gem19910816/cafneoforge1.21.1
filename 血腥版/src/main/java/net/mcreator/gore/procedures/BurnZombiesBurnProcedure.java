package net.mcreator.gore.procedures;

import javax.annotation.Nullable;
import net.mcreator.gore.configuration.GoreEditionModeSettingsConfiguration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.EntityTickEvent.Pre;

@EventBusSubscriber
public class BurnZombiesBurnProcedure {
   @SubscribeEvent
   public static void onEntityTick(Pre event) {
      execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity());
   }

   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      execute(null, world, x, y, z, entity);
   }

   private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null
         && (Boolean)GoreEditionModeSettingsConfiguration.TOUGHNESS_ZOMBIE_BURN.get()
         && entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("minecraft:sun_burning_entities")))
         && world.canSeeSkyFromBelowWater(BlockPos.containing(x, y, z))
         && !entity.isInWaterRainOrBubble()
         && world instanceof Level _lvl4
         && _lvl4.isDay()
         && (double)(world.getMaxLocalRawBrightness(BlockPos.containing(x, y, z)) / 15) > 0.5
         && Math.random() * 30.0 < ((double)(world.getMaxLocalRawBrightness(BlockPos.containing(x, y, z)) / 15) - 0.4) * 2.0) {
         entity.igniteForSeconds(8.0F);
      }
   }
}
