package net.mcreator.gore.procedures;

import net.mcreator.gore.entity.DisarmedHuskEntity;
import net.mcreator.gore.entity.DisarmedZombieEntity;
import net.mcreator.gore.entity.DismemberedSpiderIEntity;
import net.mcreator.gore.entity.DismemberedSpiderIIEntity;
import net.mcreator.gore.entity.DismemberedSpiderIIIEntity;
import net.mcreator.gore.entity.HeadlessHuskEntity;
import net.mcreator.gore.entity.HeadlessZombieEntity;
import net.mcreator.gore.entity.SeveredLegsAndArmHuskEntity;
import net.mcreator.gore.entity.SeveredLegsAndArmZombieEntity;
import net.mcreator.gore.entity.SeveredlegsHuskEntity;
import net.mcreator.gore.entity.SeveredlegsZombieEntity;
import net.mcreator.gore.entity.SkeletonWithoutArmEntity;
import net.mcreator.gore.entity.SkeletonWithoutLeftArmEntity;
import net.mcreator.gore.init.GoreEditionModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Husk;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.level.LevelAccessor;

public class WhenEntityIsDeathSpawnCorpseProcedure {
   public static void execute(LevelAccessor world, DamageSource damagesource, Entity entity) {
      if (damagesource != null
         && entity != null
         && (
            entity.getPersistentData().getDouble("type") == 999.0
               ? entity.getPersistentData().getBoolean("toughness")
               : !entity.getPersistentData().getBoolean("toughness")
         )
         && !damagesource.is(DamageTypes.IN_FIRE)
         && !damagesource.is(DamageTypes.ON_FIRE)
         && !damagesource.is(ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.parse("gore_edition:acid_damage")))
         && !damagesource.is(DamageTypes.LAVA)
         && !entity.isOnFire()
         && (!(entity instanceof LivingEntity _livEnt8) || !_livEnt8.isBaby())
         && !damagesource.is(DamageTypes.PLAYER_EXPLOSION)
         && !damagesource.is(DamageTypes.EXPLOSION)) {
         if (entity instanceof Creeper && world instanceof ServerLevel _serverLevel) {
            Entity entityinstance = ((EntityType)GoreEditionModEntities.CREEPER_CORPSE.get())
               .create(_serverLevel, null, BlockPos.containing(entity.getX(), entity.getY(), entity.getZ()), MobSpawnType.MOB_SUMMONED, false, false);
            if (entityinstance != null) {
               entityinstance.setYRot(world.getRandom().nextFloat() * 360.0F);
               entityinstance.setYRot(entity.getYRot());
               entityinstance.setXRot(entity.getXRot());
               entityinstance.setYBodyRot(entityinstance.getYRot());
               entityinstance.setYHeadRot(entityinstance.getYRot());
               entityinstance.yRotO = entityinstance.getYRot();
               entityinstance.xRotO = entityinstance.getXRot();
               if (entityinstance instanceof LivingEntity _entity) {
                  _entity.yBodyRotO = _entity.getYRot();
                  _entity.yHeadRotO = _entity.getYRot();
               }

               _serverLevel.addFreshEntity(entityinstance);
            }
         }

         if (entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("minecraft:true_zombie")))
            && world instanceof ServerLevel _serverLevelx) {
            Entity entityinstance = ((EntityType)GoreEditionModEntities.ZOMBIE_CORPSE.get())
               .create(_serverLevelx, null, BlockPos.containing(entity.getX(), entity.getY(), entity.getZ()), MobSpawnType.MOB_SUMMONED, false, false);
            if (entityinstance != null) {
               entityinstance.setYRot(world.getRandom().nextFloat() * 360.0F);
               entityinstance.setYRot(entity.getYRot());
               entityinstance.setXRot(entity.getXRot());
               entityinstance.setYBodyRot(entityinstance.getYRot());
               entityinstance.setYHeadRot(entityinstance.getYRot());
               entityinstance.yRotO = entityinstance.getYRot();
               entityinstance.xRotO = entityinstance.getXRot();
               if (entityinstance instanceof LivingEntity _entity) {
                  _entity.yBodyRotO = _entity.getYRot();
                  _entity.yHeadRotO = _entity.getYRot();
               }

               _serverLevelx.addFreshEntity(entityinstance);
            }
         }

         if (entity instanceof SeveredLegsAndArmZombieEntity && world instanceof ServerLevel _serverLevelxx) {
            Entity entityinstance = ((EntityType)GoreEditionModEntities.ZOMBIE_WITHOUT_LEGS_AND_ARM_CORPSE.get())
               .create(_serverLevelxx, null, BlockPos.containing(entity.getX(), entity.getY(), entity.getZ()), MobSpawnType.MOB_SUMMONED, false, false);
            if (entityinstance != null) {
               entityinstance.setYRot(world.getRandom().nextFloat() * 360.0F);
               entityinstance.setYRot(entity.getYRot());
               entityinstance.setXRot(entity.getXRot());
               entityinstance.setYBodyRot(entityinstance.getYRot());
               entityinstance.setYHeadRot(entityinstance.getYRot());
               entityinstance.yRotO = entityinstance.getYRot();
               entityinstance.xRotO = entityinstance.getXRot();
               if (entityinstance instanceof LivingEntity _entity) {
                  _entity.yBodyRotO = _entity.getYRot();
                  _entity.yHeadRotO = _entity.getYRot();
               }

               _serverLevelxx.addFreshEntity(entityinstance);
            }
         }

         if (entity instanceof SeveredlegsZombieEntity && world instanceof ServerLevel _serverLevelxxx) {
            Entity entityinstance = ((EntityType)GoreEditionModEntities.SEVERED_LEGS_ZOMBIE_CORPSE.get())
               .create(_serverLevelxxx, null, BlockPos.containing(entity.getX(), entity.getY(), entity.getZ()), MobSpawnType.MOB_SUMMONED, false, false);
            if (entityinstance != null) {
               entityinstance.setYRot(world.getRandom().nextFloat() * 360.0F);
               entityinstance.setYRot(entity.getYRot());
               entityinstance.setXRot(entity.getXRot());
               entityinstance.setYBodyRot(entityinstance.getYRot());
               entityinstance.setYHeadRot(entityinstance.getYRot());
               entityinstance.yRotO = entityinstance.getYRot();
               entityinstance.xRotO = entityinstance.getXRot();
               if (entityinstance instanceof LivingEntity _entity) {
                  _entity.yBodyRotO = _entity.getYRot();
                  _entity.yHeadRotO = _entity.getYRot();
               }

               _serverLevelxxx.addFreshEntity(entityinstance);
            }
         }

         if (entity instanceof DisarmedZombieEntity && world instanceof ServerLevel _serverLevelxxxx) {
            Entity entityinstance = ((EntityType)GoreEditionModEntities.DISARMED_ZOMBIE_CORPSE.get())
               .create(_serverLevelxxxx, null, BlockPos.containing(entity.getX(), entity.getY(), entity.getZ()), MobSpawnType.MOB_SUMMONED, false, false);
            if (entityinstance != null) {
               entityinstance.setYRot(world.getRandom().nextFloat() * 360.0F);
               entityinstance.setYRot(entity.getYRot());
               entityinstance.setXRot(entity.getXRot());
               entityinstance.setYBodyRot(entityinstance.getYRot());
               entityinstance.setYHeadRot(entityinstance.getYRot());
               entityinstance.yRotO = entityinstance.getYRot();
               entityinstance.xRotO = entityinstance.getXRot();
               if (entityinstance instanceof LivingEntity _entity) {
                  _entity.yBodyRotO = _entity.getYRot();
                  _entity.yHeadRotO = _entity.getYRot();
               }

               _serverLevelxxxx.addFreshEntity(entityinstance);
            }
         }

         if (entity instanceof HeadlessZombieEntity && world instanceof ServerLevel _serverLevelxxxxx) {
            Entity entityinstance = ((EntityType)GoreEditionModEntities.HEADLESS_ZOMBIE_CORPSE.get())
               .create(_serverLevelxxxxx, null, BlockPos.containing(entity.getX(), entity.getY(), entity.getZ()), MobSpawnType.MOB_SUMMONED, false, false);
            if (entityinstance != null) {
               entityinstance.setYRot(world.getRandom().nextFloat() * 360.0F);
               entityinstance.setYRot(entity.getYRot());
               entityinstance.setXRot(entity.getXRot());
               entityinstance.setYBodyRot(entityinstance.getYRot());
               entityinstance.setYHeadRot(entityinstance.getYRot());
               entityinstance.yRotO = entityinstance.getYRot();
               entityinstance.xRotO = entityinstance.getXRot();
               if (entityinstance instanceof LivingEntity _entity) {
                  _entity.yBodyRotO = _entity.getYRot();
                  _entity.yHeadRotO = _entity.getYRot();
               }

               _serverLevelxxxxx.addFreshEntity(entityinstance);
            }
         }

         if (entity instanceof Husk && world instanceof ServerLevel _serverLevelxxxxxx) {
            Entity entityinstance = ((EntityType)GoreEditionModEntities.HUSK_CORPSE.get())
               .create(_serverLevelxxxxxx, null, BlockPos.containing(entity.getX(), entity.getY(), entity.getZ()), MobSpawnType.MOB_SUMMONED, false, false);
            if (entityinstance != null) {
               entityinstance.setYRot(world.getRandom().nextFloat() * 360.0F);
               entityinstance.setYRot(entity.getYRot());
               entityinstance.setXRot(entity.getXRot());
               entityinstance.setYBodyRot(entityinstance.getYRot());
               entityinstance.setYHeadRot(entityinstance.getYRot());
               entityinstance.yRotO = entityinstance.getYRot();
               entityinstance.xRotO = entityinstance.getXRot();
               if (entityinstance instanceof LivingEntity _entity) {
                  _entity.yBodyRotO = _entity.getYRot();
                  _entity.yHeadRotO = _entity.getYRot();
               }

               _serverLevelxxxxxx.addFreshEntity(entityinstance);
            }
         }

         if (entity instanceof SeveredLegsAndArmHuskEntity && world instanceof ServerLevel _serverLevelxxxxxxx) {
            Entity entityinstance = ((EntityType)GoreEditionModEntities.HUSK_WITHOUT_LEGS_AND_ARM_CORPSE.get())
               .create(_serverLevelxxxxxxx, null, BlockPos.containing(entity.getX(), entity.getY(), entity.getZ()), MobSpawnType.MOB_SUMMONED, false, false);
            if (entityinstance != null) {
               entityinstance.setYRot(world.getRandom().nextFloat() * 360.0F);
               entityinstance.setYRot(entity.getYRot());
               entityinstance.setXRot(entity.getXRot());
               entityinstance.setYBodyRot(entityinstance.getYRot());
               entityinstance.setYHeadRot(entityinstance.getYRot());
               entityinstance.yRotO = entityinstance.getYRot();
               entityinstance.xRotO = entityinstance.getXRot();
               if (entityinstance instanceof LivingEntity _entity) {
                  _entity.yBodyRotO = _entity.getYRot();
                  _entity.yHeadRotO = _entity.getYRot();
               }

               _serverLevelxxxxxxx.addFreshEntity(entityinstance);
            }
         }

         if (entity instanceof SeveredlegsHuskEntity && world instanceof ServerLevel _serverLevelxxxxxxxx) {
            Entity entityinstance = ((EntityType)GoreEditionModEntities.SEVERED_LEGS_HUSK_CORPSE.get())
               .create(_serverLevelxxxxxxxx, null, BlockPos.containing(entity.getX(), entity.getY(), entity.getZ()), MobSpawnType.MOB_SUMMONED, false, false);
            if (entityinstance != null) {
               entityinstance.setYRot(world.getRandom().nextFloat() * 360.0F);
               entityinstance.setYRot(entity.getYRot());
               entityinstance.setXRot(entity.getXRot());
               entityinstance.setYBodyRot(entityinstance.getYRot());
               entityinstance.setYHeadRot(entityinstance.getYRot());
               entityinstance.yRotO = entityinstance.getYRot();
               entityinstance.xRotO = entityinstance.getXRot();
               if (entityinstance instanceof LivingEntity _entity) {
                  _entity.yBodyRotO = _entity.getYRot();
                  _entity.yHeadRotO = _entity.getYRot();
               }

               _serverLevelxxxxxxxx.addFreshEntity(entityinstance);
            }
         }

         if (entity instanceof DisarmedHuskEntity && world instanceof ServerLevel _serverLevelxxxxxxxxx) {
            Entity entityinstance = ((EntityType)GoreEditionModEntities.DISARMED_HUSK_CORPSE.get())
               .create(_serverLevelxxxxxxxxx, null, BlockPos.containing(entity.getX(), entity.getY(), entity.getZ()), MobSpawnType.MOB_SUMMONED, false, false);
            if (entityinstance != null) {
               entityinstance.setYRot(world.getRandom().nextFloat() * 360.0F);
               entityinstance.setYRot(entity.getYRot());
               entityinstance.setXRot(entity.getXRot());
               entityinstance.setYBodyRot(entityinstance.getYRot());
               entityinstance.setYHeadRot(entityinstance.getYRot());
               entityinstance.yRotO = entityinstance.getYRot();
               entityinstance.xRotO = entityinstance.getXRot();
               if (entityinstance instanceof LivingEntity _entity) {
                  _entity.yBodyRotO = _entity.getYRot();
                  _entity.yHeadRotO = _entity.getYRot();
               }

               _serverLevelxxxxxxxxx.addFreshEntity(entityinstance);
            }
         }

         if (entity instanceof HeadlessHuskEntity && world instanceof ServerLevel _serverLevelxxxxxxxxxx) {
            Entity entityinstance = ((EntityType)GoreEditionModEntities.HEADLESS_HUSK_CORPSE.get())
               .create(_serverLevelxxxxxxxxxx, null, BlockPos.containing(entity.getX(), entity.getY(), entity.getZ()), MobSpawnType.MOB_SUMMONED, false, false);
            if (entityinstance != null) {
               entityinstance.setYRot(world.getRandom().nextFloat() * 360.0F);
               entityinstance.setYRot(entity.getYRot());
               entityinstance.setXRot(entity.getXRot());
               entityinstance.setYBodyRot(entityinstance.getYRot());
               entityinstance.setYHeadRot(entityinstance.getYRot());
               entityinstance.yRotO = entityinstance.getYRot();
               entityinstance.xRotO = entityinstance.getXRot();
               if (entityinstance instanceof LivingEntity _entity) {
                  _entity.yBodyRotO = _entity.getYRot();
                  _entity.yHeadRotO = _entity.getYRot();
               }

               _serverLevelxxxxxxxxxx.addFreshEntity(entityinstance);
            }
         }

         if (entity instanceof Skeleton && world instanceof ServerLevel _serverLevelxxxxxxxxxxx) {
            Entity entityinstance = ((EntityType)GoreEditionModEntities.SKELETON_CORPSE.get())
               .create(_serverLevelxxxxxxxxxxx, null, BlockPos.containing(entity.getX(), entity.getY(), entity.getZ()), MobSpawnType.MOB_SUMMONED, false, false);
            if (entityinstance != null) {
               entityinstance.setYRot(world.getRandom().nextFloat() * 360.0F);
               entityinstance.setYRot(entity.getYRot());
               entityinstance.setXRot(entity.getXRot());
               entityinstance.setYBodyRot(entityinstance.getYRot());
               entityinstance.setYHeadRot(entityinstance.getYRot());
               entityinstance.yRotO = entityinstance.getYRot();
               entityinstance.xRotO = entityinstance.getXRot();
               if (entityinstance instanceof LivingEntity _entity) {
                  _entity.yBodyRotO = _entity.getYRot();
                  _entity.yHeadRotO = _entity.getYRot();
               }

               _serverLevelxxxxxxxxxxx.addFreshEntity(entityinstance);
            }
         }

         if (entity instanceof SkeletonWithoutLeftArmEntity || entity instanceof SkeletonWithoutArmEntity) {
            if (entity.getPersistentData().getDouble("RemoveTurn") == 0.0 && world instanceof ServerLevel _serverLevelxxxxxxxxxxxx) {
               Entity entityinstance = ((EntityType)GoreEditionModEntities.SKELETON_CORPSE_II.get())
                  .create(
                     _serverLevelxxxxxxxxxxxx, null, BlockPos.containing(entity.getX(), entity.getY(), entity.getZ()), MobSpawnType.MOB_SUMMONED, false, false
                  );
               if (entityinstance != null) {
                  entityinstance.setYRot(world.getRandom().nextFloat() * 360.0F);
                  entityinstance.setYRot(entity.getYRot());
                  entityinstance.setXRot(entity.getXRot());
                  entityinstance.setYBodyRot(entityinstance.getYRot());
                  entityinstance.setYHeadRot(entityinstance.getYRot());
                  entityinstance.yRotO = entityinstance.getYRot();
                  entityinstance.xRotO = entityinstance.getXRot();
                  if (entityinstance instanceof LivingEntity _entity) {
                     _entity.yBodyRotO = _entity.getYRot();
                     _entity.yHeadRotO = _entity.getYRot();
                  }

                  _serverLevelxxxxxxxxxxxx.addFreshEntity(entityinstance);
               }
            }

            if (entity.getPersistentData().getDouble("RemoveTurn") == 1.0 && world instanceof ServerLevel _serverLevelxxxxxxxxxxxxx) {
               Entity entityinstance = ((EntityType)GoreEditionModEntities.SKELETON_CORPSE_III.get())
                  .create(
                     _serverLevelxxxxxxxxxxxxx, null, BlockPos.containing(entity.getX(), entity.getY(), entity.getZ()), MobSpawnType.MOB_SUMMONED, false, false
                  );
               if (entityinstance != null) {
                  entityinstance.setYRot(world.getRandom().nextFloat() * 360.0F);
                  entityinstance.setYRot(entity.getYRot());
                  entityinstance.setXRot(entity.getXRot());
                  entityinstance.setYBodyRot(entityinstance.getYRot());
                  entityinstance.setYHeadRot(entityinstance.getYRot());
                  entityinstance.yRotO = entityinstance.getYRot();
                  entityinstance.xRotO = entityinstance.getXRot();
                  if (entityinstance instanceof LivingEntity _entity) {
                     _entity.yBodyRotO = _entity.getYRot();
                     _entity.yHeadRotO = _entity.getYRot();
                  }

                  _serverLevelxxxxxxxxxxxxx.addFreshEntity(entityinstance);
               }
            }
         }

         if (entity instanceof DismemberedSpiderIEntity && world instanceof ServerLevel _serverLevelxxxxxxxxxxxxxx) {
            Entity entityinstance = ((EntityType)GoreEditionModEntities.DISMEMBERED_SPIDER_I_CORPSE.get())
               .create(
                  _serverLevelxxxxxxxxxxxxxx, null, BlockPos.containing(entity.getX(), entity.getY(), entity.getZ()), MobSpawnType.MOB_SUMMONED, false, false
               );
            if (entityinstance != null) {
               entityinstance.setYRot(world.getRandom().nextFloat() * 360.0F);
               entityinstance.setYRot(entity.getYRot());
               entityinstance.setXRot(entity.getXRot());
               entityinstance.setYBodyRot(entityinstance.getYRot());
               entityinstance.setYHeadRot(entityinstance.getYRot());
               entityinstance.yRotO = entityinstance.getYRot();
               entityinstance.xRotO = entityinstance.getXRot();
               if (entityinstance instanceof LivingEntity _entity) {
                  _entity.yBodyRotO = _entity.getYRot();
                  _entity.yHeadRotO = _entity.getYRot();
               }

               _serverLevelxxxxxxxxxxxxxx.addFreshEntity(entityinstance);
            }
         }

         if (entity instanceof DismemberedSpiderIIEntity && world instanceof ServerLevel _serverLevelxxxxxxxxxxxxxxx) {
            Entity entityinstance = ((EntityType)GoreEditionModEntities.DISMEMBERED_SPIDER_II_CORPSE.get())
               .create(
                  _serverLevelxxxxxxxxxxxxxxx, null, BlockPos.containing(entity.getX(), entity.getY(), entity.getZ()), MobSpawnType.MOB_SUMMONED, false, false
               );
            if (entityinstance != null) {
               entityinstance.setYRot(world.getRandom().nextFloat() * 360.0F);
               entityinstance.setYRot(entity.getYRot());
               entityinstance.setXRot(entity.getXRot());
               entityinstance.setYBodyRot(entityinstance.getYRot());
               entityinstance.setYHeadRot(entityinstance.getYRot());
               entityinstance.yRotO = entityinstance.getYRot();
               entityinstance.xRotO = entityinstance.getXRot();
               if (entityinstance instanceof LivingEntity _entity) {
                  _entity.yBodyRotO = _entity.getYRot();
                  _entity.yHeadRotO = _entity.getYRot();
               }

               _serverLevelxxxxxxxxxxxxxxx.addFreshEntity(entityinstance);
            }
         }

         if (entity instanceof DismemberedSpiderIIIEntity && world instanceof ServerLevel _serverLevelxxxxxxxxxxxxxxxx) {
            Entity entityinstance = ((EntityType)GoreEditionModEntities.DISMEMBERED_SPIDER_III_CORPSE.get())
               .create(
                  _serverLevelxxxxxxxxxxxxxxxx, null, BlockPos.containing(entity.getX(), entity.getY(), entity.getZ()), MobSpawnType.MOB_SUMMONED, false, false
               );
            if (entityinstance != null) {
               entityinstance.setYRot(world.getRandom().nextFloat() * 360.0F);
               entityinstance.setYRot(entity.getYRot());
               entityinstance.setXRot(entity.getXRot());
               entityinstance.setYBodyRot(entityinstance.getYRot());
               entityinstance.setYHeadRot(entityinstance.getYRot());
               entityinstance.yRotO = entityinstance.getYRot();
               entityinstance.xRotO = entityinstance.getXRot();
               if (entityinstance instanceof LivingEntity _entity) {
                  _entity.yBodyRotO = _entity.getYRot();
                  _entity.yHeadRotO = _entity.getYRot();
               }

               _serverLevelxxxxxxxxxxxxxxxx.addFreshEntity(entityinstance);
            }
         }

         if (entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("minecraft:true_spider")))
            && world instanceof ServerLevel _serverLevelxxxxxxxxxxxxxxxxx) {
            Entity entityinstance = ((EntityType)GoreEditionModEntities.SPIDER_CORPSE.get())
               .create(
                  _serverLevelxxxxxxxxxxxxxxxxx,
                  null,
                  BlockPos.containing(entity.getX(), entity.getY(), entity.getZ()),
                  MobSpawnType.MOB_SUMMONED,
                  false,
                  false
               );
            if (entityinstance != null) {
               entityinstance.setYRot(world.getRandom().nextFloat() * 360.0F);
               entityinstance.setYRot(entity.getYRot());
               entityinstance.setXRot(entity.getXRot());
               entityinstance.setYBodyRot(entityinstance.getYRot());
               entityinstance.setYHeadRot(entityinstance.getYRot());
               entityinstance.yRotO = entityinstance.getYRot();
               entityinstance.xRotO = entityinstance.getXRot();
               if (entityinstance instanceof LivingEntity _entity) {
                  _entity.yBodyRotO = _entity.getYRot();
                  _entity.yHeadRotO = _entity.getYRot();
               }

               _serverLevelxxxxxxxxxxxxxxxxx.addFreshEntity(entityinstance);
            }
         }
      }
   }
}
