package net.mcreator.gore.procedures;

import java.util.Comparator;
import net.mcreator.gore.entity.CollapsingSkeletonEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class CollapsingSkeletonOnEntityTickUpdateProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         if (!entity.getPersistentData().getBoolean("TickIUpdate")) {
            entity.getPersistentData().putDouble("TickI", entity.getPersistentData().getDouble("TickI") + 1.0);
         }

         if (entity.getPersistentData().getDouble("TickI") == 1.0) {
            if (entity instanceof CollapsingSkeletonEntity) {
               ((CollapsingSkeletonEntity)entity).setAnimation("spawn");
            }

            if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
               _entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 40, 254, false, false));
            }

            if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
               _entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, 254, false, false));
            }
         }

         if (entity.getPersistentData().getDouble("TickI") == 31.0) {
            if (!entity.getPersistentData().getBoolean("resurrection")) {
               entity.hurt(
                  new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.MOB_ATTACK)),
                  (entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1.0F) * 2.0F
               );
            }

            entity.getPersistentData().putBoolean("TickIUpdate", true);
         }

         if ((entity instanceof Mob _mobEnt ? _mobEnt.getTarget() : null) != null) {
            Vec3 _center = new Vec3(x, y, z);

            for (Entity entityiterator : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(1.5), e -> true)
               .stream()
               .sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center)))
               .toList()) {
               if (entityiterator == (entity instanceof Mob _mobEntx ? _mobEntx.getTarget() : null)) {
                  entity.getPersistentData().putBoolean("TickCollapseUpdate", true);
               }
            }
         }

         if (entity.getPersistentData().getBoolean("TickCollapseUpdate") && entity.getPersistentData().getBoolean("TickIUpdate")) {
            entity.getPersistentData().putDouble("TickCollapse", entity.getPersistentData().getDouble("TickCollapse") + 1.0);
            if (entity.getPersistentData().getDouble("TickCollapse") == 1.0) {
               if (entity instanceof CollapsingSkeletonEntity) {
                  ((CollapsingSkeletonEntity)entity).setAnimation("collapse");
               }

               if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
                  _entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 60, 254, false, false));
               }

               if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
                  _entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60, 254, false, false));
               }
            }

            if (entity.getPersistentData().getDouble("TickCollapse") == 51.0) {
               if (!entity.level().isClientSide()) {
                  entity.discard();
               }

               SkeletonPiecesSpawnProceduresProcedure.execute(world, entity);
               if (world instanceof Level _level) {
                  if (!_level.isClientSide()) {
                     _level.playSound(
                        null,
                        BlockPos.containing(x, y, z),
                        (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.generic.explode")),
                        SoundSource.HOSTILE,
                        4.0F,
                        1.6F
                     );
                  } else {
                     _level.playLocalSound(
                        x,
                        y,
                        z,
                        (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.generic.explode")),
                        SoundSource.HOSTILE,
                        4.0F,
                        1.6F,
                        false
                     );
                  }
               }

               if (world instanceof Level _levelx) {
                  if (!_levelx.isClientSide()) {
                     _levelx.playSound(
                        null,
                        BlockPos.containing(x, y, z),
                        (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.skeleton.death")),
                        SoundSource.HOSTILE,
                        3.0F,
                        0.0F
                     );
                  } else {
                     _levelx.playLocalSound(
                        x,
                        y,
                        z,
                        (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.skeleton.death")),
                        SoundSource.HOSTILE,
                        3.0F,
                        0.0F,
                        false
                     );
                  }
               }
            }
         }
      }
   }
}
