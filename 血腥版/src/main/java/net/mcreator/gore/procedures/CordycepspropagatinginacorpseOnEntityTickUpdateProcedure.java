package net.mcreator.gore.procedures;

import javax.annotation.Nullable;
import net.mcreator.gore.entity.DisarmedHuskCorpseEntity;
import net.mcreator.gore.entity.DisarmedZombieCorpseEntity;
import net.mcreator.gore.entity.HeadlessHuskCorpseEntity;
import net.mcreator.gore.entity.HeadlessZombieCorpseEntity;
import net.mcreator.gore.entity.HuskCorpseEntity;
import net.mcreator.gore.entity.HuskWithoutLegsAndArmCorpseEntity;
import net.mcreator.gore.entity.SeveredLegsHuskCorpseEntity;
import net.mcreator.gore.entity.SeveredLegsZombieCorpseEntity;
import net.mcreator.gore.entity.ZombieCorpseEntity;
import net.mcreator.gore.entity.ZombieWithoutLegsAndArmCorpseEntity;
import net.mcreator.gore.init.GoreEditionModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.EntityTickEvent.Pre;

@EventBusSubscriber
public class CordycepspropagatinginacorpseOnEntityTickUpdateProcedure {
   @SubscribeEvent
   public static void onEntityTick(Pre event) {
      execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity());
   }

   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      execute(null, world, x, y, z, entity);
   }

   private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         Entity owner_entity = null;
         if (entity.getPersistentData().getBoolean("cordyceps_propagating_in_a_corpse")) {
            if (entity.getPersistentData().getDouble("cordyceps_propagating_tick") == 0.0 && world instanceof Level _level) {
               if (!_level.isClientSide()) {
                  _level.playSound(
                     null,
                     BlockPos.containing(x, y, z),
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:item.cordyceps_propagating")),
                     SoundSource.PLAYERS,
                     1.0F,
                     1.0F
                  );
               } else {
                  _level.playLocalSound(
                     x,
                     y,
                     z,
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:item.cordyceps_propagating")),
                     SoundSource.PLAYERS,
                     1.0F,
                     1.0F,
                     false
                  );
               }
            }

            entity.getPersistentData().putDouble("cordyceps_propagating_tick", entity.getPersistentData().getDouble("cordyceps_propagating_tick") + 1.0);
            if (entity.getPersistentData().getDouble("cordyceps_propagating_tick") == 40.0) {
               if (entity instanceof ZombieCorpseEntity) {
                  if (!entity.level().isClientSide()) {
                     entity.discard();
                  }

                  if (world instanceof ServerLevel _serverLevel) {
                     Entity entityinstance = ((EntityType)GoreEditionModEntities.CORDYCEPS_ZOMBIE.get())
                        .create(_serverLevel, null, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED, false, false);
                     if (entityinstance != null) {
                        entityinstance.setYRot(world.getRandom().nextFloat() * 360.0F);
                        if ((entity instanceof Mob _mobEntx ? _mobEntx.getTarget() : null) instanceof LivingEntity
                           && entityinstance instanceof TamableAnimal _toTame
                           && (entity instanceof Mob _mobEnt ? _mobEnt.getTarget() : null) instanceof Player _owner) {
                           _toTame.tame(_owner);
                        }

                        _serverLevel.addFreshEntity(entityinstance);
                     }
                  }

                  if (world instanceof Level _levelx) {
                     if (!_levelx.isClientSide()) {
                        _levelx.playSound(
                           null,
                           BlockPos.containing(x, y, z),
                           (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:item.cordyceps_finish")),
                           SoundSource.PLAYERS,
                           1.0F,
                           1.0F
                        );
                     } else {
                        _levelx.playLocalSound(
                           x,
                           y,
                           z,
                           (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:item.cordyceps_finish")),
                           SoundSource.PLAYERS,
                           1.0F,
                           1.0F,
                           false
                        );
                     }
                  }
               }

               if (entity instanceof SeveredLegsZombieCorpseEntity) {
                  if (!entity.level().isClientSide()) {
                     entity.discard();
                  }

                  if (world instanceof ServerLevel _serverLevelx) {
                     Entity entityinstance = ((EntityType)GoreEditionModEntities.SEVERED_LEGS_CORDYCEPS_ZOMBIE.get())
                        .create(_serverLevelx, null, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED, false, false);
                     if (entityinstance != null) {
                        entityinstance.setYRot(world.getRandom().nextFloat() * 360.0F);
                        if ((entity instanceof Mob _mobEntx ? _mobEntx.getTarget() : null) instanceof LivingEntity
                           && entityinstance instanceof TamableAnimal _toTame
                           && (entity instanceof Mob _mobEnt ? _mobEnt.getTarget() : null) instanceof Player _owner) {
                           _toTame.tame(_owner);
                        }

                        _serverLevelx.addFreshEntity(entityinstance);
                     }
                  }

                  if (world instanceof Level _levelxx) {
                     if (!_levelxx.isClientSide()) {
                        _levelxx.playSound(
                           null,
                           BlockPos.containing(x, y, z),
                           (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:item.cordyceps_finish")),
                           SoundSource.PLAYERS,
                           1.0F,
                           1.0F
                        );
                     } else {
                        _levelxx.playLocalSound(
                           x,
                           y,
                           z,
                           (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:item.cordyceps_finish")),
                           SoundSource.PLAYERS,
                           1.0F,
                           1.0F,
                           false
                        );
                     }
                  }
               }

               if (entity instanceof HeadlessZombieCorpseEntity) {
                  if (!entity.level().isClientSide()) {
                     entity.discard();
                  }

                  if (world instanceof ServerLevel _serverLevelxx) {
                     Entity entityinstance = ((EntityType)GoreEditionModEntities.ZOMBIE_CORDYCEPS_HEADLESS.get())
                        .create(_serverLevelxx, null, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED, false, false);
                     if (entityinstance != null) {
                        entityinstance.setYRot(world.getRandom().nextFloat() * 360.0F);
                        if ((entity instanceof Mob _mobEntx ? _mobEntx.getTarget() : null) instanceof LivingEntity
                           && entityinstance instanceof TamableAnimal _toTame
                           && (entity instanceof Mob _mobEnt ? _mobEnt.getTarget() : null) instanceof Player _owner) {
                           _toTame.tame(_owner);
                        }

                        _serverLevelxx.addFreshEntity(entityinstance);
                     }
                  }

                  if (world instanceof Level _levelxxx) {
                     if (!_levelxxx.isClientSide()) {
                        _levelxxx.playSound(
                           null,
                           BlockPos.containing(x, y, z),
                           (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:item.cordyceps_finish")),
                           SoundSource.PLAYERS,
                           1.0F,
                           1.0F
                        );
                     } else {
                        _levelxxx.playLocalSound(
                           x,
                           y,
                           z,
                           (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:item.cordyceps_finish")),
                           SoundSource.PLAYERS,
                           1.0F,
                           1.0F,
                           false
                        );
                     }
                  }
               }

               if (entity instanceof ZombieWithoutLegsAndArmCorpseEntity) {
                  if (!entity.level().isClientSide()) {
                     entity.discard();
                  }

                  if (world instanceof ServerLevel _serverLevelxxx) {
                     Entity entityinstance = ((EntityType)GoreEditionModEntities.SEVERED_LEGS_AND_ONE_ARM_CORDYCEPS_ZOMBIE.get())
                        .create(_serverLevelxxx, null, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED, false, false);
                     if (entityinstance != null) {
                        entityinstance.setYRot(world.getRandom().nextFloat() * 360.0F);
                        if ((entity instanceof Mob _mobEntx ? _mobEntx.getTarget() : null) instanceof LivingEntity
                           && entityinstance instanceof TamableAnimal _toTame
                           && (entity instanceof Mob _mobEnt ? _mobEnt.getTarget() : null) instanceof Player _owner) {
                           _toTame.tame(_owner);
                        }

                        _serverLevelxxx.addFreshEntity(entityinstance);
                     }
                  }

                  if (world instanceof Level _levelxxxx) {
                     if (!_levelxxxx.isClientSide()) {
                        _levelxxxx.playSound(
                           null,
                           BlockPos.containing(x, y, z),
                           (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:item.cordyceps_finish")),
                           SoundSource.PLAYERS,
                           1.0F,
                           1.0F
                        );
                     } else {
                        _levelxxxx.playLocalSound(
                           x,
                           y,
                           z,
                           (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:item.cordyceps_finish")),
                           SoundSource.PLAYERS,
                           1.0F,
                           1.0F,
                           false
                        );
                     }
                  }
               }

               if (entity instanceof DisarmedZombieCorpseEntity) {
                  if (!entity.level().isClientSide()) {
                     entity.discard();
                  }

                  if (world instanceof ServerLevel _serverLevelxxxx) {
                     Entity entityinstance = ((EntityType)GoreEditionModEntities.DISARMED_CORDYCEPS_ZOMBIE.get())
                        .create(_serverLevelxxxx, null, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED, false, false);
                     if (entityinstance != null) {
                        entityinstance.setYRot(world.getRandom().nextFloat() * 360.0F);
                        if ((entity instanceof Mob _mobEntx ? _mobEntx.getTarget() : null) instanceof LivingEntity
                           && entityinstance instanceof TamableAnimal _toTame
                           && (entity instanceof Mob _mobEnt ? _mobEnt.getTarget() : null) instanceof Player _owner) {
                           _toTame.tame(_owner);
                        }

                        _serverLevelxxxx.addFreshEntity(entityinstance);
                     }
                  }

                  if (world instanceof Level _levelxxxxx) {
                     if (!_levelxxxxx.isClientSide()) {
                        _levelxxxxx.playSound(
                           null,
                           BlockPos.containing(x, y, z),
                           (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:item.cordyceps_finish")),
                           SoundSource.PLAYERS,
                           1.0F,
                           1.0F
                        );
                     } else {
                        _levelxxxxx.playLocalSound(
                           x,
                           y,
                           z,
                           (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:item.cordyceps_finish")),
                           SoundSource.PLAYERS,
                           1.0F,
                           1.0F,
                           false
                        );
                     }
                  }
               }

               if (entity instanceof HuskCorpseEntity) {
                  if (!entity.level().isClientSide()) {
                     entity.discard();
                  }

                  if (world instanceof ServerLevel _serverLevelxxxxx) {
                     Entity entityinstance = ((EntityType)GoreEditionModEntities.CORDYCEPS_HUSK.get())
                        .create(_serverLevelxxxxx, null, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED, false, false);
                     if (entityinstance != null) {
                        entityinstance.setYRot(world.getRandom().nextFloat() * 360.0F);
                        if ((entity instanceof Mob _mobEntx ? _mobEntx.getTarget() : null) instanceof LivingEntity
                           && entityinstance instanceof TamableAnimal _toTame
                           && (entity instanceof Mob _mobEnt ? _mobEnt.getTarget() : null) instanceof Player _owner) {
                           _toTame.tame(_owner);
                        }

                        _serverLevelxxxxx.addFreshEntity(entityinstance);
                     }
                  }

                  if (world instanceof Level _levelxxxxxx) {
                     if (!_levelxxxxxx.isClientSide()) {
                        _levelxxxxxx.playSound(
                           null,
                           BlockPos.containing(x, y, z),
                           (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:item.cordyceps_finish")),
                           SoundSource.PLAYERS,
                           1.0F,
                           1.0F
                        );
                     } else {
                        _levelxxxxxx.playLocalSound(
                           x,
                           y,
                           z,
                           (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:item.cordyceps_finish")),
                           SoundSource.PLAYERS,
                           1.0F,
                           1.0F,
                           false
                        );
                     }
                  }
               }

               if (entity instanceof SeveredLegsHuskCorpseEntity) {
                  if (!entity.level().isClientSide()) {
                     entity.discard();
                  }

                  if (world instanceof ServerLevel _serverLevelxxxxxx) {
                     Entity entityinstance = ((EntityType)GoreEditionModEntities.SEVERED_LEGS_CORDYCEPS_HUSK.get())
                        .create(_serverLevelxxxxxx, null, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED, false, false);
                     if (entityinstance != null) {
                        entityinstance.setYRot(world.getRandom().nextFloat() * 360.0F);
                        if ((entity instanceof Mob _mobEntx ? _mobEntx.getTarget() : null) instanceof LivingEntity
                           && entityinstance instanceof TamableAnimal _toTame
                           && (entity instanceof Mob _mobEnt ? _mobEnt.getTarget() : null) instanceof Player _owner) {
                           _toTame.tame(_owner);
                        }

                        _serverLevelxxxxxx.addFreshEntity(entityinstance);
                     }
                  }

                  if (world instanceof Level _levelxxxxxxx) {
                     if (!_levelxxxxxxx.isClientSide()) {
                        _levelxxxxxxx.playSound(
                           null,
                           BlockPos.containing(x, y, z),
                           (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:item.cordyceps_finish")),
                           SoundSource.PLAYERS,
                           1.0F,
                           1.0F
                        );
                     } else {
                        _levelxxxxxxx.playLocalSound(
                           x,
                           y,
                           z,
                           (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:item.cordyceps_finish")),
                           SoundSource.PLAYERS,
                           1.0F,
                           1.0F,
                           false
                        );
                     }
                  }
               }

               if (entity instanceof HeadlessHuskCorpseEntity) {
                  if (!entity.level().isClientSide()) {
                     entity.discard();
                  }

                  if (world instanceof ServerLevel _serverLevelxxxxxxx) {
                     Entity entityinstance = ((EntityType)GoreEditionModEntities.CORDYCEPS_HEADLESS_HUSK.get())
                        .create(_serverLevelxxxxxxx, null, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED, false, false);
                     if (entityinstance != null) {
                        entityinstance.setYRot(world.getRandom().nextFloat() * 360.0F);
                        if ((entity instanceof Mob _mobEntx ? _mobEntx.getTarget() : null) instanceof LivingEntity
                           && entityinstance instanceof TamableAnimal _toTame
                           && (entity instanceof Mob _mobEnt ? _mobEnt.getTarget() : null) instanceof Player _owner) {
                           _toTame.tame(_owner);
                        }

                        _serverLevelxxxxxxx.addFreshEntity(entityinstance);
                     }
                  }

                  if (world instanceof Level _levelxxxxxxxx) {
                     if (!_levelxxxxxxxx.isClientSide()) {
                        _levelxxxxxxxx.playSound(
                           null,
                           BlockPos.containing(x, y, z),
                           (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:item.cordyceps_finish")),
                           SoundSource.PLAYERS,
                           1.0F,
                           1.0F
                        );
                     } else {
                        _levelxxxxxxxx.playLocalSound(
                           x,
                           y,
                           z,
                           (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:item.cordyceps_finish")),
                           SoundSource.PLAYERS,
                           1.0F,
                           1.0F,
                           false
                        );
                     }
                  }
               }

               if (entity instanceof HuskWithoutLegsAndArmCorpseEntity) {
                  if (!entity.level().isClientSide()) {
                     entity.discard();
                  }

                  if (world instanceof ServerLevel _serverLevelxxxxxxxx) {
                     Entity entityinstance = ((EntityType)GoreEditionModEntities.SEVERED_LEGS_AND_ONE_ARM_CORDYCEPS_HUSK.get())
                        .create(_serverLevelxxxxxxxx, null, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED, false, false);
                     if (entityinstance != null) {
                        entityinstance.setYRot(world.getRandom().nextFloat() * 360.0F);
                        if ((entity instanceof Mob _mobEntx ? _mobEntx.getTarget() : null) instanceof LivingEntity
                           && entityinstance instanceof TamableAnimal _toTame
                           && (entity instanceof Mob _mobEnt ? _mobEnt.getTarget() : null) instanceof Player _owner) {
                           _toTame.tame(_owner);
                        }

                        _serverLevelxxxxxxxx.addFreshEntity(entityinstance);
                     }
                  }

                  if (world instanceof Level _levelxxxxxxxxx) {
                     if (!_levelxxxxxxxxx.isClientSide()) {
                        _levelxxxxxxxxx.playSound(
                           null,
                           BlockPos.containing(x, y, z),
                           (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:item.cordyceps_finish")),
                           SoundSource.PLAYERS,
                           1.0F,
                           1.0F
                        );
                     } else {
                        _levelxxxxxxxxx.playLocalSound(
                           x,
                           y,
                           z,
                           (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:item.cordyceps_finish")),
                           SoundSource.PLAYERS,
                           1.0F,
                           1.0F,
                           false
                        );
                     }
                  }
               }

               if (entity instanceof DisarmedHuskCorpseEntity) {
                  if (!entity.level().isClientSide()) {
                     entity.discard();
                  }

                  if (world instanceof ServerLevel _serverLevelxxxxxxxxx) {
                     Entity entityinstance = ((EntityType)GoreEditionModEntities.DISARMED_CORDYCEPS_HUSK.get())
                        .create(_serverLevelxxxxxxxxx, null, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED, false, false);
                     if (entityinstance != null) {
                        entityinstance.setYRot(world.getRandom().nextFloat() * 360.0F);
                        if ((entity instanceof Mob _mobEntx ? _mobEntx.getTarget() : null) instanceof LivingEntity
                           && entityinstance instanceof TamableAnimal _toTame
                           && (entity instanceof Mob _mobEnt ? _mobEnt.getTarget() : null) instanceof Player _owner) {
                           _toTame.tame(_owner);
                        }

                        _serverLevelxxxxxxxxx.addFreshEntity(entityinstance);
                     }
                  }

                  if (world instanceof Level _levelxxxxxxxxxx) {
                     if (!_levelxxxxxxxxxx.isClientSide()) {
                        _levelxxxxxxxxxx.playSound(
                           null,
                           BlockPos.containing(x, y, z),
                           (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:item.cordyceps_finish")),
                           SoundSource.PLAYERS,
                           1.0F,
                           1.0F
                        );
                     } else {
                        _levelxxxxxxxxxx.playLocalSound(
                           x,
                           y,
                           z,
                           (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:item.cordyceps_finish")),
                           SoundSource.PLAYERS,
                           1.0F,
                           1.0F,
                           false
                        );
                     }
                  }
               }
            }
         }
      }
   }
}
