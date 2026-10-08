package net.mcreator.gore.procedures;

import javax.annotation.Nullable;
import net.mcreator.gore.GoreEditionMod;
import net.mcreator.gore.entity.FleshEaterEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.EntityTickEvent.Pre;

@EventBusSubscriber
public class ExarrackMonsterAngrySoundProcedure {
   @SubscribeEvent
   public static void onEntityTick(Pre event) {
      execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity());
   }

   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      execute(null, world, x, y, z, entity);
   }

   private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null && entity instanceof FleshEaterEntity) {
         if ((entity instanceof Mob _mobEnt ? _mobEnt.getTarget() : null) != null) {
            if (!entity.getPersistentData().getBoolean("initial_scream")) {
               entity.getPersistentData().putBoolean("initial_scream", true);
               GoreEditionMod.queueServerWork(
                  Mth.nextInt(RandomSource.create(), 10, 30),
                  () -> {
                     if (world instanceof Level _level) {
                        if (!_level.isClientSide()) {
                           _level.playSound(
                              null,
                              BlockPos.containing(x, y, z),
                              (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:entity.exarrack_monster.angry")),
                              SoundSource.HOSTILE,
                              3.0F,
                              0.0F
                           );
                        } else {
                           _level.playLocalSound(
                              x,
                              y,
                              z,
                              (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:entity.exarrack_monster.angry")),
                              SoundSource.HOSTILE,
                              3.0F,
                              0.0F,
                              false
                           );
                        }
                     }
                  }
               );
            }
         } else {
            entity.getPersistentData().putBoolean("initial_scream", false);
         }
      }
   }
}
