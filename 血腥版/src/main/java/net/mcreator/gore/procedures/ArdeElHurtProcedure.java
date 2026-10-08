package net.mcreator.gore.procedures;

import javax.annotation.Nullable;
import net.mcreator.gore.configuration.GoreEditionConfigurationFileConfiguration;
import net.mcreator.gore.configuration.GoreEditionModeSettingsConfiguration;
import net.mcreator.gore.configuration.GoreEditionSoundsConfigurationConfiguration;
import net.mcreator.gore.init.GoreEditionModParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

@EventBusSubscriber
public class ArdeElHurtProcedure {
   @SubscribeEvent
   public static void onEntityAttacked(LivingIncomingDamageEvent event) {
      if (event != null && event.getEntity() != null) {
         execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getSource());
      }
   }

   public static void execute(LevelAccessor world, double x, double y, double z, DamageSource damagesource) {
      execute(null, world, x, y, z, damagesource);
   }

   private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, DamageSource damagesource) {
      if (damagesource != null
         && (Boolean)GoreEditionModeSettingsConfiguration.FHURT.get()
         && (damagesource.is(DamageTypes.IN_FIRE) || damagesource.is(DamageTypes.ON_FIRE) || damagesource.is(DamageTypes.LAVA))) {
         if (world instanceof ServerLevel _level) {
            _level.sendParticles(
               (SimpleParticleType)GoreEditionModParticleTypes.FIRE_PARTICLE.get(),
               x,
               y + (Double)GoreEditionConfigurationFileConfiguration.CENTER_Y_MOB.get(),
               z,
               (int)(
                  Math.pow((Double)GoreEditionConfigurationFileConfiguration.PARTICLE_MULTIPLICATOR.get() * 3.0, 2.0)
                     * (Double)GoreEditionConfigurationFileConfiguration.FIRE_PARTICLES_REDUCTOR.get()
               ),
               0.1,
               0.4,
               0.1,
               (Double)GoreEditionConfigurationFileConfiguration.PARTICLE_SPEED_MULTIPLICATOR.get() / 2.6
            );
         }

         if (world instanceof Level _level) {
            if (!_level.isClientSide()) {
               _level.playSound(
                  null,
                  BlockPos.containing(x, y, z),
                  (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.generic.extinguish_fire")),
                  SoundSource.NEUTRAL,
                  (float)((Double)GoreEditionSoundsConfigurationConfiguration.GORE_INCINERATED_SOUND.get()).doubleValue(),
                  2.0F
               );
            } else {
               _level.playLocalSound(
                  x,
                  y,
                  z,
                  (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.generic.extinguish_fire")),
                  SoundSource.NEUTRAL,
                  (float)((Double)GoreEditionSoundsConfigurationConfiguration.GORE_INCINERATED_SOUND.get()).doubleValue(),
                  2.0F,
                  false
               );
            }
         }
      }
   }
}
