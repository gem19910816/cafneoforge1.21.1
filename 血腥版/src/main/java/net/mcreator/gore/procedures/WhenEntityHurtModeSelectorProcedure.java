package net.mcreator.gore.procedures;

import javax.annotation.Nullable;
import net.mcreator.gore.configuration.GoreEditionModeSettingsConfiguration;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

@EventBusSubscriber
public class WhenEntityHurtModeSelectorProcedure {
   @SubscribeEvent
   public static void onEntityAttacked(LivingIncomingDamageEvent event) {
      if (event != null && event.getEntity() != null) {
         execute(
            event,
            event.getEntity().level(),
            event.getEntity().getX(),
            event.getEntity().getY(),
            event.getEntity().getZ(),
            event.getSource(),
            event.getEntity(),
            (double)event.getAmount()
         );
      }
   }

   public static void execute(LevelAccessor world, double x, double y, double z, DamageSource damagesource, Entity entity, double amount) {
      execute(null, world, x, y, z, damagesource, entity, amount);
   }

   private static void execute(
      @Nullable Event event, LevelAccessor world, double x, double y, double z, DamageSource damagesource, Entity entity, double amount
   ) {
      if (damagesource != null
         && entity != null
         && !damagesource.is(DamageTypes.IN_FIRE)
         && !damagesource.is(DamageTypes.ON_FIRE)
         && !damagesource.is(DamageTypes.LAVA)
         && !damagesource.is(ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.parse("gore_edition:acid_damage")))
         && !entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("gore_edition:ashes_natives")))) {
         if ((Double)GoreEditionModeSettingsConfiguration.HURT_INTENSITY.get() == 1.0 && amount > 0.0) {
            WhenEntityIsHurtGoreProcedure.execute(world, x, y, z, entity, amount);
         }

         if ((Double)GoreEditionModeSettingsConfiguration.HURT_INTENSITY.get() == 2.0 && amount > 0.0) {
            WhenEntityIsHurtLegacyProcedure.execute(world, x, y, z, entity, amount);
         }
      }
   }
}
