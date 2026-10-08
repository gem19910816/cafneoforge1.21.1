package net.mcreator.gore.procedures;

import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

@EventBusSubscriber
public class SpiralTwistedHurtSfxProcedure {
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
         && damagesource.is(ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.parse("gore_edition:spiral_twisted")))
         && world instanceof Level _level) {
         if (!_level.isClientSide()) {
            _level.playSound(
               null,
               BlockPos.containing(x, y, z),
               (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:damage.spiral_twisted.hurt")),
               SoundSource.AMBIENT,
               2.0F,
               1.0F
            );
         } else {
            _level.playLocalSound(
               x,
               y,
               z,
               (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:damage.spiral_twisted.hurt")),
               SoundSource.AMBIENT,
               2.0F,
               1.0F,
               false
            );
         }
      }
   }
}
