package net.mcreator.gore.procedures;

import javax.annotation.Nullable;
import net.mcreator.gore.entity.CrushingSkeletonEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

@EventBusSubscriber
public class CrushingSkeletonDealsDamageToAnotherEntityProcedure {
   @SubscribeEvent
   public static void onEntityAttacked(LivingIncomingDamageEvent event) {
      if (event != null && event.getEntity() != null) {
         execute(event, event.getEntity().level(), event.getEntity(), event.getSource().getEntity());
      }
   }

   public static void execute(LevelAccessor world, Entity entity, Entity sourceentity) {
      execute(null, world, entity, sourceentity);
   }

   private static void execute(@Nullable Event event, LevelAccessor world, Entity entity, Entity sourceentity) {
      if (entity != null && sourceentity != null && sourceentity instanceof CrushingSkeletonEntity) {
         sourceentity.getPersistentData().putDouble("crushing_skeleton_dying", sourceentity.getPersistentData().getDouble("crushing_skeleton_dying") + 1.0);
         if (world instanceof Level _level) {
            if (!_level.isClientSide()) {
               _level.playSound(
                  null,
                  BlockPos.containing(entity.getX(), entity.getY(), entity.getZ()),
                  (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.skeleton.death")),
                  SoundSource.HOSTILE,
                  1.0F,
                  1.0F
               );
            } else {
               _level.playLocalSound(
                  entity.getX(),
                  entity.getY(),
                  entity.getZ(),
                  (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.skeleton.death")),
                  SoundSource.HOSTILE,
                  1.0F,
                  1.0F,
                  false
               );
            }
         }
      }
   }
}
