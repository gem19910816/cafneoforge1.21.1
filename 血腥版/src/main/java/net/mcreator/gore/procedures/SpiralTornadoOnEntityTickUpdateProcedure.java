package net.mcreator.gore.procedures;

import java.util.Comparator;
import net.mcreator.gore.entity.SpiralTornadoEntity;
import net.mcreator.gore.init.GoreEditionModMobEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class SpiralTornadoOnEntityTickUpdateProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         SpiralTornadoParticlesProcedure.execute(world, x, y, z);
         if (entity.isInWaterOrBubble() && !entity.level().isClientSide()) {
            entity.discard();
         }

         entity.getPersistentData().putDouble("despawntimer", entity.getPersistentData().getDouble("despawntimer") + 1.0);
         if (entity.getPersistentData().getDouble("despawntimer") >= 1.0) {
            Vec3 _center = new Vec3(x, y, z);

            for (Entity entityiterator : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(0.75), e -> true)
               .stream()
               .sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center)))
               .toList()) {
               if (entityiterator != entity
                  && entityiterator != (entity instanceof TamableAnimal _tamEnt ? _tamEnt.getOwner() : null)
                  && !(entityiterator instanceof SpiralTornadoEntity)) {
                  if (!entity.level().isClientSide()) {
                     entity.discard();
                  }

                  if (entityiterator instanceof LivingEntity) {
                     LivingEntity _entity = (LivingEntity)entityiterator;
                     if (!_entity.level().isClientSide()) {
                        _entity.addEffect(new MobEffectInstance(MobEffects.GLOWING, 10, 0));
                     }
                  }

                  if (entityiterator instanceof LivingEntity) {
                     LivingEntity _entity = (LivingEntity)entityiterator;
                     if (!_entity.level().isClientSide()) {
                        _entity.addEffect(new MobEffectInstance(GoreEditionModMobEffects.SPIRAL_TWIST, 320, 0));
                     }
                  }

                  if (world instanceof Level) {
                     Level _level = (Level)world;
                     if (!_level.isClientSide()) {
                        _level.playSound(
                           null,
                           BlockPos.containing(x, y, z),
                           (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:damage.spiral_twisted.die")),
                           SoundSource.AMBIENT,
                           2.0F,
                           1.0F
                        );
                     } else {
                        _level.playLocalSound(
                           x,
                           y,
                           z,
                           (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:damage.spiral_twisted.die")),
                           SoundSource.AMBIENT,
                           2.0F,
                           1.0F,
                           false
                        );
                     }
                  }
               }
            }
         }

         if (entity.getPersistentData().getDouble("despawntimer") >= 320.0 && !entity.level().isClientSide()) {
            entity.discard();
         }
      }
   }
}
