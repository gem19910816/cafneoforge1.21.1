package net.mcreator.gore.procedures;

import java.util.Comparator;
import net.mcreator.gore.entity.SkeletonWithoutArmArmProjectileEntity;
import net.mcreator.gore.entity.SkeletonWithoutArmEntity;
import net.mcreator.gore.entity.SkeletonWithoutArmHeadProjectileEntity;
import net.mcreator.gore.entity.SkeletonWithoutLeftArmEntity;
import net.mcreator.gore.init.GoreEditionModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
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

public class SkeletonWithoutArmOnEntityTickUpdateProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         if (entity.getPersistentData().getDouble("TickI") == 1.0) {
            if (entity instanceof SkeletonWithoutArmEntity) {
               ((SkeletonWithoutArmEntity)entity).setAnimation("spawn");
            }

            if (entity instanceof SkeletonWithoutLeftArmEntity) {
               ((SkeletonWithoutLeftArmEntity)entity).setAnimation("spawn");
            }

            if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
               _entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 25, 255, false, false));
            }

            if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
               _entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 25, 255, false, false));
            }
         }

         if (entity.getPersistentData().getDouble("TickI") != 27.0) {
            if (world instanceof Level _level) {
               if (!_level.isClientSide()) {
                  _level.playSound(
                     null,
                     BlockPos.containing(x, y, z),
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore_disarmed_zombie_ambient")),
                     SoundSource.AMBIENT,
                     0.7F,
                     2.0F
                  );
               } else {
                  _level.playLocalSound(
                     x,
                     y,
                     z,
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore_disarmed_zombie_ambient")),
                     SoundSource.AMBIENT,
                     0.7F,
                     2.0F,
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
                     SoundSource.AMBIENT,
                     1.0F,
                     1.0F
                  );
               } else {
                  _levelx.playLocalSound(
                     x,
                     y,
                     z,
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.skeleton.death")),
                     SoundSource.AMBIENT,
                     1.0F,
                     1.0F,
                     false
                  );
               }
            }

            entity.getPersistentData().putDouble("TickI", entity.getPersistentData().getDouble("TickI") + 1.0);
         } else {
            if (!entity.getPersistentData().getBoolean("resurrection")) {
               entity.hurt(
                  new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.GENERIC)),
                  (entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1.0F) * 2.0F
               );
            }

            entity.getPersistentData().putBoolean("Time_To_Attack", true);
         }

         if (!entity.getPersistentData().getBoolean("Wait")
            && (entity instanceof Mob _mobEntx ? _mobEntx.getTarget() : null) != null
            && entity.getPersistentData().getBoolean("Time_To_Attack")
            && (entity instanceof Mob _mobEnt ? _mobEnt.getTarget() : null) instanceof LivingEntity) {
            if (!entity.getPersistentData().getBoolean("Stop_BucleTick") && entity.getPersistentData().getDouble("RemoveTurn") != 2.0) {
               entity.getPersistentData().putDouble("BucleTick", entity.getPersistentData().getDouble("BucleTick") + 1.0);
            }

            if (entity.getPersistentData().getDouble("BucleTick") == 20.0) {
               entity.getPersistentData().putDouble("BucleTick", 0.0);
            }

            Vec3 _center = new Vec3(x, y, z);

            for (Entity entityiterator : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(8.0), e -> true)
               .stream()
               .sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center)))
               .toList()) {
               if (entityiterator == (entity instanceof Mob _mobEntxx ? _mobEntxx.getTarget() : null)
                  && entity.getPersistentData().getDouble("BucleTick") == 0.0) {
                  entity.getPersistentData().putBoolean("Stop_BucleTick", true);
               }
            }

            if (entity.getPersistentData().getBoolean("Stop_BucleTick")) {
               if (entity.getPersistentData().getDouble("RemoveTurn") == 0.0) {
                  entity.getPersistentData().putDouble("TickII", entity.getPersistentData().getDouble("TickII") + 1.0);
                  if (entity.getPersistentData().getDouble("TickII") == 1.0) {
                     if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
                        _entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 42, 255, false, false));
                     }

                     if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
                        _entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 42, 255, false, false));
                     }

                     for (int index0 = 0; index0 < 22; index0++) {
                        if (entity instanceof SkeletonWithoutArmEntity) {
                           ((SkeletonWithoutArmEntity)entity).setAnimation("head");
                        }

                        if (entity instanceof SkeletonWithoutLeftArmEntity) {
                           ((SkeletonWithoutLeftArmEntity)entity).setAnimation("head");
                        }
                     }
                  }

                  if (entity.getPersistentData().getDouble("TickII") == 30.0) {
                     if (entity instanceof SkeletonWithoutArmEntity animatable) {
                        animatable.setTexture("skeleton_whitout_arm_1");
                     }

                     if (entity instanceof SkeletonWithoutLeftArmEntity animatable) {
                        animatable.setTexture("skeleton_whitout_arm_1");
                     }

                     if (world instanceof ServerLevel projectileLevel) {
                        Projectile _entityToSpawn = (new Object() {
                              public Projectile getArrow(Level level, float damage, int knockback) {
                                 AbstractArrow entityToSpawn = new SkeletonWithoutArmHeadProjectileEntity(
                                    (EntityType<? extends SkeletonWithoutArmHeadProjectileEntity>)GoreEditionModEntities.SKELETON_WITHOUT_ARM_HEAD_PROJECTILE
                                       .get(),
                                    level
                                 );
                                 entityToSpawn.setBaseDamage((double)damage);
                                 entityToSpawn.setSilent(true);
                                 return entityToSpawn;
                              }
                           })
                           .getArrow(projectileLevel, 4.0F, 3);
                        _entityToSpawn.setPos(entity.getX(), entity.getY() + 1.6, entity.getZ());
                        _entityToSpawn.shoot(
                           (entity instanceof Mob _mobEntxxxx ? _mobEntxxxx.getTarget() : null).getX() - entity.getX(),
                           (entity instanceof Mob _mobEntxxx ? _mobEntxxx.getTarget() : null).getY() - entity.getY(),
                           (entity instanceof Mob _mobEntxx ? _mobEntxx.getTarget() : null).getZ() - entity.getZ(),
                           3.0F,
                           5.0F
                        );
                        projectileLevel.addFreshEntity(_entityToSpawn);
                     }

                     entity.getPersistentData().putDouble("RemoveTurn", entity.getPersistentData().getDouble("RemoveTurn") + 1.0);
                  }

                  if (entity.getPersistentData().getDouble("TickII") == 60.0) {
                     entity.getPersistentData().putDouble("BucleTick", 1.0);
                     entity.getPersistentData().putBoolean("Stop_BucleTick", false);
                     entity.getPersistentData().putBoolean("Wait", true);
                  }
               }

               if (entity.getPersistentData().getDouble("RemoveTurn") == 1.0) {
                  entity.getPersistentData().putDouble("TickIII", entity.getPersistentData().getDouble("TickIII") + 1.0);
                  if (entity.getPersistentData().getDouble("TickIII") == 2.0) {
                     if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
                        _entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 42, 255, false, false));
                     }

                     if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
                        _entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 42, 255, false, false));
                     }

                     for (int index1 = 0; index1 < 22; index1++) {
                        if (entity instanceof SkeletonWithoutArmEntity) {
                           ((SkeletonWithoutArmEntity)entity).setAnimation("arm");
                        }

                        if (entity instanceof SkeletonWithoutLeftArmEntity) {
                           ((SkeletonWithoutLeftArmEntity)entity).setAnimation("arm");
                        }
                     }
                  }

                  if (entity.getPersistentData().getDouble("TickIII") == 31.0) {
                     if (entity instanceof SkeletonWithoutArmEntity animatable) {
                        animatable.setTexture("skeleton_whitout_arm_2");
                     }

                     if (entity instanceof SkeletonWithoutLeftArmEntity animatable) {
                        animatable.setTexture("skeleton_whitout_arm_2");
                     }

                     if (world instanceof ServerLevel projectileLevel) {
                        Projectile _entityToSpawn = (new Object() {
                              public Projectile getArrow(Level level, float damage, int knockback) {
                                 AbstractArrow entityToSpawn = new SkeletonWithoutArmArmProjectileEntity(
                                    (EntityType<? extends SkeletonWithoutArmArmProjectileEntity>)GoreEditionModEntities.SKELETON_WITHOUT_ARM_ARM_PROJECTILE
                                       .get(),
                                    level
                                 );
                                 entityToSpawn.setBaseDamage((double)damage);
                                 entityToSpawn.setSilent(true);
                                 return entityToSpawn;
                              }
                           })
                           .getArrow(projectileLevel, 3.0F, 2);
                        _entityToSpawn.setPos(entity.getX(), entity.getY() + 1.6, entity.getZ());
                        _entityToSpawn.shoot(
                           (entity instanceof Mob _mobEntxxxx ? _mobEntxxxx.getTarget() : null).getX() - entity.getX(),
                           (entity instanceof Mob _mobEntxxx ? _mobEntxxx.getTarget() : null).getY() - entity.getY(),
                           (entity instanceof Mob _mobEntxx ? _mobEntxx.getTarget() : null).getZ() - entity.getZ(),
                           3.0F,
                           5.0F
                        );
                        projectileLevel.addFreshEntity(_entityToSpawn);
                     }

                     entity.getPersistentData().putDouble("RemoveTurn", entity.getPersistentData().getDouble("RemoveTurn") + 1.0);
                  }

                  if (entity.getPersistentData().getDouble("TickIII") == 45.0) {
                     entity.getPersistentData().putDouble("BucleTick", 1.0);
                     entity.getPersistentData().putBoolean("Stop_BucleTick", false);
                     entity.getPersistentData().putBoolean("Wait", true);
                  }
               }
            }
         }

         if (entity.getPersistentData().getBoolean("Wait")) {
            entity.getPersistentData().putDouble("TickWait", entity.getPersistentData().getDouble("TickWait") + 1.0);
         }

         if (entity.getPersistentData().getDouble("TickWait") == 20.0) {
            entity.getPersistentData().putDouble("TickWait", 0.0);
            entity.getPersistentData().putBoolean("Wait", false);
         }
      }
   }
}
