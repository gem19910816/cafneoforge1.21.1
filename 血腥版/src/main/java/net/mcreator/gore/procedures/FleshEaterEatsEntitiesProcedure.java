package net.mcreator.gore.procedures;

import javax.annotation.Nullable;
import net.mcreator.gore.entity.ExarrackHydraEntity;
import net.mcreator.gore.entity.FakeExarrackMonsterEntity;
import net.mcreator.gore.entity.FleshEaterEntity;
import net.mcreator.gore.entity.TheExarrackMonsterEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;

@EventBusSubscriber
public class FleshEaterEatsEntitiesProcedure {
   @SubscribeEvent
   public static void onEntityDeath(LivingDeathEvent event) {
      if (event != null && event.getEntity() != null) {
         execute(
            event,
            event.getEntity().level(),
            event.getEntity().getX(),
            event.getEntity().getY(),
            event.getEntity().getZ(),
            event.getEntity(),
            event.getSource().getEntity()
         );
      }
   }

   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, Entity sourceentity) {
      execute(null, world, x, y, z, entity, sourceentity);
   }

   private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity, Entity sourceentity) {
      if (entity != null
         && sourceentity != null
         && (
            sourceentity instanceof FleshEaterEntity
               || sourceentity instanceof FakeExarrackMonsterEntity
               || sourceentity instanceof TheExarrackMonsterEntity
               || sourceentity instanceof ExarrackHydraEntity
         )
         && !(entity instanceof FleshEaterEntity)
         && !(entity instanceof FakeExarrackMonsterEntity)
         && !(entity instanceof TheExarrackMonsterEntity)
         && !(entity instanceof ExarrackHydraEntity)) {
         if (world instanceof Level _level) {
            if (!_level.isClientSide()) {
               _level.playSound(
                  null,
                  BlockPos.containing(x, y, z),
                  (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore.devoration")),
                  SoundSource.AMBIENT,
                  1.0F,
                  1.0F
               );
            } else {
               _level.playLocalSound(
                  x,
                  y,
                  z,
                  (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore.devoration")),
                  SoundSource.AMBIENT,
                  1.0F,
                  1.0F,
                  false
               );
            }
         }

         if (sourceentity instanceof LivingEntity _entity) {
            _entity.setHealth(
               (sourceentity instanceof LivingEntity _livEntx ? _livEntx.getHealth() : -1.0F)
                  + (entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1.0F)
            );
         }

         entity.getPersistentData().putBoolean("ge_cancel_die_particles", true);
         entity.getPersistentData().putBoolean("ge_cancel_chances", true);
         if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
            _entity.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 255, 1, false, false));
         }
      }
   }
}
