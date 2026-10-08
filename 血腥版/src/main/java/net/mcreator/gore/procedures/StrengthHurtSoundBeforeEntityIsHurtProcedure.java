package net.mcreator.gore.procedures;

import javax.annotation.Nullable;
import net.mcreator.gore.configuration.GoreEditionModeSettingsConfiguration;
import net.mcreator.gore.configuration.GoreEditionSoundsConfigurationConfiguration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

@EventBusSubscriber
public class StrengthHurtSoundBeforeEntityIsHurtProcedure {
   @SubscribeEvent
   public static void onEntityAttacked(LivingIncomingDamageEvent event) {
      if (event != null && event.getEntity() != null) {
         execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getSource().getEntity());
      }
   }

   public static void execute(LevelAccessor world, double x, double y, double z, Entity sourceentity) {
      execute(null, world, x, y, z, sourceentity);
   }

   private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity sourceentity) {
      if (sourceentity != null
         && (Double)GoreEditionModeSettingsConfiguration.HURT_INTENSITY.get() == 1.0
         && sourceentity instanceof LivingEntity _livEnt1
         && _livEnt1.hasEffect(MobEffects.DAMAGE_BOOST)
         && world instanceof Level _level) {
         if (!_level.isClientSide()) {
            _level.playSound(
               null,
               BlockPos.containing(x, y, z),
               (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore_hurt_severe_sound")),
               SoundSource.AMBIENT,
               (float)((Double)GoreEditionSoundsConfigurationConfiguration.GORE_HURT_SEVERE_SOUND.get()).doubleValue(),
               1.0F
            );
         } else {
            _level.playLocalSound(
               x,
               y,
               z,
               (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore_hurt_severe_sound")),
               SoundSource.AMBIENT,
               (float)((Double)GoreEditionSoundsConfigurationConfiguration.GORE_HURT_SEVERE_SOUND.get()).doubleValue(),
               1.0F,
               false
            );
         }
      }
   }
}
