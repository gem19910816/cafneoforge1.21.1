package net.mcreator.gore.procedures;

import javax.annotation.Nullable;
import net.mcreator.gore.init.GoreEditionModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.LevelAccessor;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;

@EventBusSubscriber
public class ToughnessDeathProcedure {
   @SubscribeEvent
   public static void onEntityDeath(LivingDeathEvent event) {
      if (event != null && event.getEntity() != null) {
         execute(
            event,
            event.getEntity().level(),
            event.getEntity().getX(),
            event.getEntity().getY(),
            event.getEntity().getZ(),
            event.getSource(),
            event.getEntity()
         );
      }
   }

   public static void execute(LevelAccessor world, double x, double y, double z, DamageSource damagesource, Entity entity) {
      execute(null, world, x, y, z, damagesource, entity);
   }

   private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, DamageSource damagesource, Entity entity) {
      if (damagesource != null && entity != null) {
         double type = 0.0;
         if (!damagesource.is(DamageTypes.IN_FIRE)
            && !damagesource.is(DamageTypes.ON_FIRE)
            && !damagesource.is(DamageTypes.LAVA)
            && !damagesource.is(ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.parse("gore_edition:acid_damage")))
            && !entity.isOnFire()) {
            if (entity.getPersistentData().getBoolean("toughness") && entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
               _entity.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 9999, 1, false, false));
            }

            if (entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("minecraft:true_zombie")))) {
               if (entity.getPersistentData().getBoolean("toughness")) {
                  if (entity.getPersistentData().getDouble("type") == 1.0 && world instanceof ServerLevel _level) {
                     Entity entityToSpawn = ((EntityType)GoreEditionModEntities.DISARMED_ZOMBIE.get())
                        .spawn(_level, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED);
                     if (entityToSpawn != null) {
                        entityToSpawn.setYRot(entity.getYRot());
                        entityToSpawn.setYBodyRot(entity.getYRot());
                        entityToSpawn.setYHeadRot(entity.getYRot());
                        entityToSpawn.setXRot(entity.getXRot());
                     }
                  }

                  if (entity.getPersistentData().getDouble("type") == 2.0 && world instanceof ServerLevel _levelx) {
                     Entity entityToSpawn = ((EntityType)GoreEditionModEntities.HEADLESS_ZOMBIE.get())
                        .spawn(_levelx, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED);
                     if (entityToSpawn != null) {
                        entityToSpawn.setYRot(entity.getYRot());
                        entityToSpawn.setYBodyRot(entity.getYRot());
                        entityToSpawn.setYHeadRot(entity.getYRot());
                        entityToSpawn.setXRot(entity.getXRot());
                     }
                  }

                  if (entity.getPersistentData().getDouble("type") == 3.0 && world instanceof ServerLevel _levelxx) {
                     Entity entityToSpawn = ((EntityType)GoreEditionModEntities.SEVEREDLEGS_ZOMBIE.get())
                        .spawn(_levelxx, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED);
                     if (entityToSpawn != null) {
                        entityToSpawn.setYRot(entity.getYRot());
                        entityToSpawn.setYBodyRot(entity.getYRot());
                        entityToSpawn.setYHeadRot(entity.getYRot());
                        entityToSpawn.setXRot(entity.getXRot());
                     }
                  }

                  if (entity.getPersistentData().getDouble("type") == 4.0 && world instanceof ServerLevel _levelxxx) {
                     Entity entityToSpawn = ((EntityType)GoreEditionModEntities.SEVERED_LEGS_AND_ARM_ZOMBIE.get())
                        .spawn(_levelxxx, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED);
                     if (entityToSpawn != null) {
                        entityToSpawn.setYRot(entity.getYRot());
                        entityToSpawn.setYBodyRot(entity.getYRot());
                        entityToSpawn.setYHeadRot(entity.getYRot());
                        entityToSpawn.setXRot(entity.getXRot());
                     }
                  }

                  if (entity.getPersistentData().getDouble("type") == 5.0 && world instanceof ServerLevel _levelxxxx) {
                     Entity entityToSpawn = ((EntityType)GoreEditionModEntities.SEVEREDLEGS_ZOMBIE.get())
                        .spawn(_levelxxxx, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED);
                     if (entityToSpawn != null) {
                        entityToSpawn.setYRot(entity.getYRot());
                        entityToSpawn.setYBodyRot(entity.getYRot());
                        entityToSpawn.setYHeadRot(entity.getYRot());
                        entityToSpawn.setXRot(entity.getXRot());
                     }
                  }
               }

               if (entity.getPersistentData().getDouble("death_animation_type") == 1.0 && world instanceof ServerLevel _levelxxxxx) {
                  Entity entityToSpawn = ((EntityType)GoreEditionModEntities.HORIZONTALLY_CUTTED_ZOMBIE.get())
                     .spawn(_levelxxxxx, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED);
                  if (entityToSpawn != null) {
                     entityToSpawn.setYRot(entity.getYRot());
                     entityToSpawn.setYBodyRot(entity.getYRot());
                     entityToSpawn.setYHeadRot(entity.getYRot());
                     entityToSpawn.setXRot(entity.getXRot());
                  }
               }

               if (entity.getPersistentData().getDouble("death_animation_type") == 2.0 && world instanceof ServerLevel _levelxxxxxx) {
                  Entity entityToSpawn = ((EntityType)GoreEditionModEntities.CRUSHED_ZOMBIE.get())
                     .spawn(_levelxxxxxx, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED);
                  if (entityToSpawn != null) {
                     entityToSpawn.setYRot(entity.getYRot());
                     entityToSpawn.setYBodyRot(entity.getYRot());
                     entityToSpawn.setYHeadRot(entity.getYRot());
                     entityToSpawn.setXRot(entity.getXRot());
                  }
               }

               if (entity.getPersistentData().getDouble("death_animation_type") == 3.0 && world instanceof ServerLevel _levelxxxxxxx) {
                  Entity entityToSpawn = ((EntityType)GoreEditionModEntities.VERTICAL_CUTTED_ZOMBIE.get())
                     .spawn(_levelxxxxxxx, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED);
                  if (entityToSpawn != null) {
                     entityToSpawn.setYRot(entity.getYRot());
                     entityToSpawn.setYBodyRot(entity.getYRot());
                     entityToSpawn.setYHeadRot(entity.getYRot());
                     entityToSpawn.setXRot(entity.getXRot());
                  }
               }
            }

            if (entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("minecraft:true_husk")))) {
               if (entity.getPersistentData().getBoolean("toughness")) {
                  if (entity.getPersistentData().getDouble("type") == 1.0 && world instanceof ServerLevel _levelxxxxxxxx) {
                     Entity entityToSpawn = ((EntityType)GoreEditionModEntities.DISARMED_HUSK.get())
                        .spawn(_levelxxxxxxxx, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED);
                     if (entityToSpawn != null) {
                        entityToSpawn.setYRot(world.getRandom().nextFloat() * 360.0F);
                     }
                  }

                  if (entity.getPersistentData().getDouble("type") == 2.0 && world instanceof ServerLevel _levelxxxxxxxxx) {
                     Entity entityToSpawn = ((EntityType)GoreEditionModEntities.HEADLESS_HUSK.get())
                        .spawn(_levelxxxxxxxxx, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED);
                     if (entityToSpawn != null) {
                        entityToSpawn.setYRot(world.getRandom().nextFloat() * 360.0F);
                     }
                  }

                  if (entity.getPersistentData().getDouble("type") == 3.0 && world instanceof ServerLevel _levelxxxxxxxxxx) {
                     Entity entityToSpawn = ((EntityType)GoreEditionModEntities.SEVEREDLEGS_HUSK.get())
                        .spawn(_levelxxxxxxxxxx, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED);
                     if (entityToSpawn != null) {
                        entityToSpawn.setYRot(world.getRandom().nextFloat() * 360.0F);
                     }
                  }

                  if (entity.getPersistentData().getDouble("type") == 4.0 && world instanceof ServerLevel _levelxxxxxxxxxxx) {
                     Entity entityToSpawn = ((EntityType)GoreEditionModEntities.SEVERED_LEGS_AND_ARM_HUSK.get())
                        .spawn(_levelxxxxxxxxxxx, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED);
                     if (entityToSpawn != null) {
                        entityToSpawn.setYRot(world.getRandom().nextFloat() * 360.0F);
                     }
                  }

                  if (entity.getPersistentData().getDouble("type") == 5.0 && world instanceof ServerLevel _levelxxxxxxxxxxxx) {
                     Entity entityToSpawn = ((EntityType)GoreEditionModEntities.SEVEREDLEGS_HUSK.get())
                        .spawn(_levelxxxxxxxxxxxx, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED);
                     if (entityToSpawn != null) {
                        entityToSpawn.setYRot(world.getRandom().nextFloat() * 360.0F);
                     }
                  }
               }

               if (entity.getPersistentData().getDouble("death_animation_type") == 1.0 && world instanceof ServerLevel _levelxxxxxxxxxxxxx) {
                  Entity entityToSpawn = ((EntityType)GoreEditionModEntities.HORIZONTALLY_CUTTED_HUSK.get())
                     .spawn(_levelxxxxxxxxxxxxx, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED);
                  if (entityToSpawn != null) {
                     entityToSpawn.setYRot(world.getRandom().nextFloat() * 360.0F);
                  }
               }

               if (entity.getPersistentData().getDouble("death_animation_type") == 2.0 && world instanceof ServerLevel _levelxxxxxxxxxxxxxx) {
                  Entity entityToSpawn = ((EntityType)GoreEditionModEntities.CRUSHED_HUSK.get())
                     .spawn(_levelxxxxxxxxxxxxxx, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED);
                  if (entityToSpawn != null) {
                     entityToSpawn.setYRot(world.getRandom().nextFloat() * 360.0F);
                  }
               }

               if (entity.getPersistentData().getDouble("death_animation_type") == 3.0 && world instanceof ServerLevel _levelxxxxxxxxxxxxxxx) {
                  Entity entityToSpawn = ((EntityType)GoreEditionModEntities.VERTICALCUTTED_HUSK.get())
                     .spawn(_levelxxxxxxxxxxxxxxx, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED);
                  if (entityToSpawn != null) {
                     entityToSpawn.setYRot(world.getRandom().nextFloat() * 360.0F);
                  }
               }
            }

            if (entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("minecraft:true_spider")))) {
               if (entity.getPersistentData().getBoolean("toughness")) {
                  if (entity.getPersistentData().getDouble("type") == 3.0 && world instanceof ServerLevel _levelxxxxxxxxxxxxxxxx) {
                     Entity entityToSpawn = ((EntityType)GoreEditionModEntities.DISMEMBERED_SPIDER_I.get())
                        .spawn(_levelxxxxxxxxxxxxxxxx, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED);
                     if (entityToSpawn != null) {
                        entityToSpawn.setYRot(world.getRandom().nextFloat() * 360.0F);
                     }
                  }

                  if (entity.getPersistentData().getDouble("type") == 4.0 && world instanceof ServerLevel _levelxxxxxxxxxxxxxxxxx) {
                     Entity entityToSpawn = ((EntityType)GoreEditionModEntities.DISMEMBERED_SPIDER_II.get())
                        .spawn(_levelxxxxxxxxxxxxxxxxx, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED);
                     if (entityToSpawn != null) {
                        entityToSpawn.setYRot(world.getRandom().nextFloat() * 360.0F);
                     }
                  }

                  if (entity.getPersistentData().getDouble("type") == 5.0 && world instanceof ServerLevel _levelxxxxxxxxxxxxxxxxxx) {
                     Entity entityToSpawn = ((EntityType)GoreEditionModEntities.DISMEMBERED_SPIDER_III.get())
                        .spawn(_levelxxxxxxxxxxxxxxxxxx, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED);
                     if (entityToSpawn != null) {
                        entityToSpawn.setYRot(world.getRandom().nextFloat() * 360.0F);
                     }
                  }
               }

               if (entity.getPersistentData().getDouble("death_animation_type") == 1.0 && world instanceof ServerLevel _levelxxxxxxxxxxxxxxxxxxx) {
                  Entity entityToSpawn = ((EntityType)GoreEditionModEntities.HORIZONTALLY_CUTTED_SPIDER.get())
                     .spawn(_levelxxxxxxxxxxxxxxxxxxx, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED);
                  if (entityToSpawn != null) {
                     entityToSpawn.setYRot(world.getRandom().nextFloat() * 360.0F);
                  }
               }

               if (entity.getPersistentData().getDouble("death_animation_type") == 2.0 && world instanceof ServerLevel _levelxxxxxxxxxxxxxxxxxxxx) {
                  Entity entityToSpawn = ((EntityType)GoreEditionModEntities.VERTICALLY_CUTTED_SPIDER.get())
                     .spawn(_levelxxxxxxxxxxxxxxxxxxxx, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED);
                  if (entityToSpawn != null) {
                     entityToSpawn.setYRot(world.getRandom().nextFloat() * 360.0F);
                  }
               }

               if (entity.getPersistentData().getDouble("death_animation_type") == 3.0 && world instanceof ServerLevel _levelxxxxxxxxxxxxxxxxxxxxx) {
                  Entity entityToSpawn = ((EntityType)GoreEditionModEntities.EXPLODED_HEAD_SPIDER.get())
                     .spawn(_levelxxxxxxxxxxxxxxxxxxxxx, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED);
                  if (entityToSpawn != null) {
                     entityToSpawn.setYRot(world.getRandom().nextFloat() * 360.0F);
                  }
               }
            }

            if (entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("minecraft:true_skeleton")))
               && entity.getPersistentData().getBoolean("toughness")) {
               if (entity.getPersistentData().getDouble("type") == 1.0) {
                  if (entity instanceof LivingEntity _entMainHand76
                     && _entMainHand76.getMainArm() == HumanoidArm.RIGHT
                     && world instanceof ServerLevel _levelxxxxxxxxxxxxxxxxxxxxxx) {
                     Entity entityToSpawn = ((EntityType)GoreEditionModEntities.DISARMED_SKELETON_RIGHT_ARM.get())
                        .spawn(_levelxxxxxxxxxxxxxxxxxxxxxx, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED);
                     if (entityToSpawn != null) {
                        entityToSpawn.setYRot(world.getRandom().nextFloat() * 360.0F);
                     }
                  }

                  if (entity instanceof LivingEntity _entMainHand78
                     && _entMainHand78.getMainArm() == HumanoidArm.LEFT
                     && world instanceof ServerLevel _levelxxxxxxxxxxxxxxxxxxxxxxx) {
                     Entity entityToSpawn = ((EntityType)GoreEditionModEntities.DISARMED_SKELETON_LEFT_ARM.get())
                        .spawn(_levelxxxxxxxxxxxxxxxxxxxxxxx, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED);
                     if (entityToSpawn != null) {
                        entityToSpawn.setYRot(world.getRandom().nextFloat() * 360.0F);
                     }
                  }
               }

               if (entity.getPersistentData().getDouble("type") == 2.0 && world instanceof ServerLevel _levelxxxxxxxxxxxxxxxxxxxxxxxx) {
                  Entity entityToSpawn = ((EntityType)GoreEditionModEntities.COLLAPSING_SKELETON.get())
                     .spawn(_levelxxxxxxxxxxxxxxxxxxxxxxxx, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED);
                  if (entityToSpawn != null) {
                     entityToSpawn.setYRot(world.getRandom().nextFloat() * 360.0F);
                  }
               }

               if (entity.getPersistentData().getDouble("type") == 3.0 && world instanceof ServerLevel _levelxxxxxxxxxxxxxxxxxxxxxxxxx) {
                  Entity entityToSpawn = ((EntityType)GoreEditionModEntities.CRUSHING_SKELETON.get())
                     .spawn(_levelxxxxxxxxxxxxxxxxxxxxxxxxx, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED);
                  if (entityToSpawn != null) {
                     entityToSpawn.setYRot(world.getRandom().nextFloat() * 360.0F);
                  }
               }

               if (entity.getPersistentData().getDouble("type") == 4.0 && world instanceof ServerLevel _levelxxxxxxxxxxxxxxxxxxxxxxxxxx) {
                  Entity entityToSpawn = ((EntityType)GoreEditionModEntities.SEVERED_LEGS_SKELETON.get())
                     .spawn(_levelxxxxxxxxxxxxxxxxxxxxxxxxxx, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED);
                  if (entityToSpawn != null) {
                     entityToSpawn.setYRot(world.getRandom().nextFloat() * 360.0F);
                  }
               }
            }
         }
      }
   }
}
