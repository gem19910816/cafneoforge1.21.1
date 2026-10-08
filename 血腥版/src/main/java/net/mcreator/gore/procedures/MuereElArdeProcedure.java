package net.mcreator.gore.procedures;

import javax.annotation.Nullable;
import net.mcreator.gore.configuration.GoreEditionModeSettingsConfiguration;
import net.mcreator.gore.configuration.GoreEditionOtherConfigurationsConfiguration;
import net.mcreator.gore.init.GoreEditionModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.LevelAccessor;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;

@EventBusSubscriber
public class MuereElArdeProcedure {
   @SubscribeEvent
   public static void onEntityDeath(LivingDeathEvent event) {
      if (event != null && event.getEntity() != null) {
         execute(event, event.getEntity().level(), event.getSource(), event.getEntity());
      }
   }

   public static void execute(LevelAccessor world, DamageSource damagesource, Entity entity) {
      execute(null, world, damagesource, entity);
   }

   private static void execute(@Nullable Event event, LevelAccessor world, DamageSource damagesource, Entity entity) {
      if (damagesource != null && entity != null) {
         if (((String)GoreEditionModeSettingsConfiguration.FDEATH.get()).equals("FAshes")) {
            if (damagesource.is(DamageTypes.IN_FIRE) || damagesource.is(DamageTypes.ON_FIRE) || damagesource.is(DamageTypes.LAVA)) {
               if (world instanceof ServerLevel _serverLevel) {
                  Entity entityinstance = ((EntityType)GoreEditionModEntities.FIRE_DEATH_NECESARY.get())
                     .create(_serverLevel, null, BlockPos.containing(entity.getX(), entity.getY(), entity.getZ()), MobSpawnType.MOB_SUMMONED, false, false);
                  if (entityinstance != null) {
                     entityinstance.setYRot(world.getRandom().nextFloat() * 360.0F);
                     entityinstance.getPersistentData().putDouble("ge_the_true_entity_height", (double)entity.getBbHeight());
                     entityinstance.getPersistentData().putDouble("ge_the_true_entity_width", (double)entity.getBbWidth());
                     _serverLevel.addFreshEntity(entityinstance);
                  }
               }

               if ((Boolean)GoreEditionOtherConfigurationsConfiguration.VANISHED_ITEMS_WHEN_BURNED.get() && !entity.level().isClientSide()) {
                  entity.discard();
               }
            }

            if (!damagesource.is(DamageTypes.IN_FIRE) && !damagesource.is(DamageTypes.ON_FIRE) && !damagesource.is(DamageTypes.LAVA) && entity.isOnFire()) {
               if (world instanceof ServerLevel _serverLevelx) {
                  Entity entityinstance = ((EntityType)GoreEditionModEntities.BURNING_DEATH_NECESARY.get())
                     .create(_serverLevelx, null, BlockPos.containing(entity.getX(), entity.getY(), entity.getZ()), MobSpawnType.MOB_SUMMONED, false, false);
                  if (entityinstance != null) {
                     entityinstance.setYRot(world.getRandom().nextFloat() * 360.0F);
                     entityinstance.getPersistentData().putDouble("ge_the_true_entity_height", (double)entity.getBbHeight());
                     entityinstance.getPersistentData().putDouble("ge_the_true_entity_width", (double)entity.getBbWidth());
                     _serverLevelx.addFreshEntity(entityinstance);
                  }
               }

               if ((Boolean)GoreEditionOtherConfigurationsConfiguration.VANISHED_ITEMS_WHEN_BURNED.get() && !entity.level().isClientSide()) {
                  entity.discard();
               }
            }
         }

         if (((String)GoreEditionModeSettingsConfiguration.FDEATH.get()).equals("Fire")
            && (damagesource.is(DamageTypes.IN_FIRE) || damagesource.is(DamageTypes.ON_FIRE) || damagesource.is(DamageTypes.LAVA) || entity.isOnFire())) {
            if (world instanceof ServerLevel _serverLevelxx) {
               Entity entityinstance = ((EntityType)GoreEditionModEntities.OLD_FIRE_DEATH_NECESARY.get())
                  .create(_serverLevelxx, null, BlockPos.containing(entity.getX(), entity.getY(), entity.getZ()), MobSpawnType.MOB_SUMMONED, false, false);
               if (entityinstance != null) {
                  entityinstance.setYRot(world.getRandom().nextFloat() * 360.0F);
                  entityinstance.getPersistentData().putDouble("ge_the_true_entity_height", (double)entity.getBbHeight());
                  entityinstance.getPersistentData().putDouble("ge_the_true_entity_width", (double)entity.getBbWidth());
                  _serverLevelxx.addFreshEntity(entityinstance);
               }
            }

            if ((Boolean)GoreEditionOtherConfigurationsConfiguration.VANISHED_ITEMS_WHEN_BURNED.get() && !entity.level().isClientSide()) {
               entity.discard();
            }
         }
      }
   }
}
