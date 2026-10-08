package net.mcreator.gore.procedures;

import java.util.Comparator;
import net.mcreator.gore.entity.FakeExarrackMonsterEntity;
import net.mcreator.gore.entity.TripofobicAcidEntity;
import net.mcreator.gore.init.GoreEditionModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class ExarrackMonsterAcidBreakProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         if (entity.getPersistentData().getBoolean("frustrated")) {
            Vec3 _center = new Vec3(x, y, z);

            for (Entity entityiterator : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(8.0), e -> true)
               .stream()
               .sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center)))
               .toList()) {
               if (entityiterator == (entity instanceof Mob _mobEnt ? _mobEnt.getTarget() : null)) {
                  entity.getPersistentData().putBoolean("do1", true);
               }
            }
         }

         if (entity.getPersistentData().getBoolean("do1")) {
            if (entity.getPersistentData().getDouble("acidbreaktick") == 0.0) {
               if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
                  _entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60, 254, false, false));
               }

               if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
                  _entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 60, 254, false, false));
               }

               if (entity instanceof FakeExarrackMonsterEntity) {
                  ((FakeExarrackMonsterEntity)entity).setAnimation("acid_break");
               }
            }

            entity.getPersistentData().putDouble("acidbreaktick", entity.getPersistentData().getDouble("acidbreaktick") + 1.0);
            if (entity.getPersistentData().getDouble("acidbreaktick") >= 4.0 && entity.getPersistentData().getDouble("acidbreaktick") < 50.0) {
               entity.getPersistentData().putBoolean("acid_spam", true);
            }

            if (entity.getPersistentData().getDouble("acidbreaktick") == 50.0) {
               entity.getPersistentData().putBoolean("acid_spam", false);
            }

            if (entity.getPersistentData().getDouble("acidbreaktick") == 60.0) {
               entity.getPersistentData().putDouble("acidbreaktick", 0.0);
               entity.getPersistentData().putBoolean("frustrated", false);
               entity.getPersistentData().putBoolean("do1", false);
            }
         }

         if (entity.getPersistentData().getBoolean("acid_spam")) {
            if (entity.getPersistentData().getDouble("acidspamtickupdate") == 0.0) {
               for (int index0 = 0; index0 < Mth.nextInt(RandomSource.create(), 2, 4); index0++) {
                  if (world instanceof ServerLevel projectileLevel) {
                     Projectile _entityToSpawn = (new Object() {
                           public Projectile getArrow(Level level, float damage, int knockback) {
                              AbstractArrow entityToSpawn = new TripofobicAcidEntity(
                                 (EntityType<? extends TripofobicAcidEntity>)GoreEditionModEntities.ACID_ASHES.get(), level
                              );
                              entityToSpawn.setBaseDamage((double)damage);
                              entityToSpawn.setSilent(true);
                              return entityToSpawn;
                           }
                        })
                        .getArrow(projectileLevel, 2.0F, 1);
                     _entityToSpawn.setPos(
                        entity.getX() + Mth.nextDouble(RandomSource.create(), -0.6, 0.6) + entity.getLookAngle().x * 0.4,
                        y + Mth.nextDouble(RandomSource.create(), -0.6, 0.6) + 2.0,
                        entity.getZ() + Mth.nextDouble(RandomSource.create(), -0.6, 0.6) + entity.getLookAngle().z * 0.4
                     );
                     _entityToSpawn.shoot(
                        (entity instanceof Mob _mobEntxx ? _mobEntxx.getTarget() : null).getX() - entity.getX(),
                        (entity instanceof Mob _mobEntx ? _mobEntx.getTarget() : null).getY() - entity.getY(),
                        (entity instanceof Mob _mobEnt ? _mobEnt.getTarget() : null).getZ() - entity.getZ(),
                        0.3F,
                        1.0F
                     );
                     projectileLevel.addFreshEntity(_entityToSpawn);
                  }
               }

               if (world instanceof Level _level) {
                  if (!_level.isClientSide()) {
                     _level.playSound(
                        null,
                        BlockPos.containing(x, y, z),
                        (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.llama.spit")),
                        SoundSource.HOSTILE,
                        1.0F,
                        1.0F
                     );
                  } else {
                     _level.playLocalSound(
                        x,
                        y,
                        z,
                        (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.llama.spit")),
                        SoundSource.HOSTILE,
                        1.0F,
                        1.0F,
                        false
                     );
                  }
               }
            }

            entity.getPersistentData().putDouble("acidspamtickupdate", entity.getPersistentData().getDouble("acidspamtickupdate") + 1.0);
            if (entity.getPersistentData().getDouble("acidspamtickupdate") == 1.0) {
               entity.getPersistentData().putDouble("acidspamtickupdate", 0.0);
            }
         } else {
            entity.getPersistentData().putDouble("acidspamtickupdate", 0.0);
         }
      }
   }
}
