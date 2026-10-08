package net.mcreator.gore.procedures;

import java.util.Comparator;
import net.mcreator.gore.init.GoreEditionModParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class MagmaGrenadeExplodeOnEntityTickUpdateProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         if (entity.getPersistentData().getDouble("duringggtick") == 0.0) {
            if (world instanceof Level _level) {
               if (!_level.isClientSide()) {
                  _level.playSound(
                     null,
                     BlockPos.containing(x, y + 1.0, z),
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("item.firecharge.use")),
                     SoundSource.PLAYERS,
                     3.0F,
                     1.0F
                  );
               } else {
                  _level.playLocalSound(
                     x,
                     y + 1.0,
                     z,
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("item.firecharge.use")),
                     SoundSource.PLAYERS,
                     3.0F,
                     1.0F,
                     false
                  );
               }
            }

            if (world instanceof ServerLevel _levelx) {
               _levelx.sendParticles((SimpleParticleType)GoreEditionModParticleTypes.FIRE_PARTICLE.get(), x, y, z, 90, 1.5, 0.0, 1.5, 0.3);
            }

            Vec3 _center = new Vec3(x, y + 1.0, z);

            for (Entity entityiterator : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(4.5), e -> true)
               .stream()
               .sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center)))
               .toList()) {
               entityiterator.hurt(
                  new DamageSource(
                     world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.IN_FIRE),
                     entity instanceof TamableAnimal _tamEnt ? _tamEnt.getOwner() : null
                  ),
                  9.0F
               );
               entityiterator.igniteForSeconds(15.0F);
            }
         }

         entity.getPersistentData().putDouble("duringggtick", entity.getPersistentData().getDouble("duringggtick") + 1.0);
         if (entity.getPersistentData().getDouble("duringggtick") == 4.0) {
            if (world instanceof Level _levelx) {
               if (!_levelx.isClientSide()) {
                  _levelx.playSound(
                     null,
                     BlockPos.containing(x, y, z),
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("item.firecharge.use")),
                     SoundSource.PLAYERS,
                     3.0F,
                     1.0F
                  );
               } else {
                  _levelx.playLocalSound(
                     x,
                     y,
                     z,
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("item.firecharge.use")),
                     SoundSource.PLAYERS,
                     3.0F,
                     1.0F,
                     false
                  );
               }
            }

            if (world instanceof ServerLevel _levelxx) {
               _levelxx.sendParticles((SimpleParticleType)GoreEditionModParticleTypes.FIRE_PARTICLE.get(), x, y + 2.0, z, 90, 1.5, 0.0, 1.5, 0.3);
            }

            Vec3 _center = new Vec3(x, y + 1.0, z);

            for (Entity entityiterator : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(4.5), e -> true)
               .stream()
               .sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center)))
               .toList()) {
               entityiterator.hurt(
                  new DamageSource(
                     world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.IN_FIRE),
                     entity instanceof TamableAnimal _tamEnt ? _tamEnt.getOwner() : null
                  ),
                  9.0F
               );
               entityiterator.igniteForSeconds(15.0F);
            }
         }

         if (entity.getPersistentData().getDouble("duringggtick") == 8.0) {
            if (world instanceof Level _levelxx) {
               if (!_levelxx.isClientSide()) {
                  _levelxx.playSound(
                     null,
                     BlockPos.containing(x, y, z),
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("item.firecharge.use")),
                     SoundSource.PLAYERS,
                     3.0F,
                     1.0F
                  );
               } else {
                  _levelxx.playLocalSound(
                     x,
                     y,
                     z,
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("item.firecharge.use")),
                     SoundSource.PLAYERS,
                     3.0F,
                     1.0F,
                     false
                  );
               }
            }

            if (world instanceof ServerLevel _levelxxx) {
               _levelxxx.sendParticles((SimpleParticleType)GoreEditionModParticleTypes.FIRE_PARTICLE.get(), x, y + 3.0, z, 90, 1.5, 0.0, 1.5, 0.3);
            }

            Vec3 _center = new Vec3(x, y + 1.0, z);

            for (Entity entityiterator : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(4.5), e -> true)
               .stream()
               .sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center)))
               .toList()) {
               entityiterator.hurt(
                  new DamageSource(
                     world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.IN_FIRE),
                     entity instanceof TamableAnimal _tamEnt ? _tamEnt.getOwner() : null
                  ),
                  9.0F
               );
               entityiterator.igniteForSeconds(15.0F);
            }
         }

         if (entity.getPersistentData().getDouble("duringggtick") == 12.0) {
            if (world instanceof Level _levelxxx) {
               if (!_levelxxx.isClientSide()) {
                  _levelxxx.playSound(
                     null,
                     BlockPos.containing(x, y, z),
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("item.firecharge.use")),
                     SoundSource.PLAYERS,
                     3.0F,
                     1.0F
                  );
               } else {
                  _levelxxx.playLocalSound(
                     x,
                     y,
                     z,
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("item.firecharge.use")),
                     SoundSource.PLAYERS,
                     3.0F,
                     1.0F,
                     false
                  );
               }
            }

            if (world instanceof ServerLevel _levelxxxx) {
               _levelxxxx.sendParticles((SimpleParticleType)GoreEditionModParticleTypes.FIRE_PARTICLE.get(), x, y + 4.0, z, 90, 1.5, 0.0, 1.5, 0.3);
            }

            Vec3 _center = new Vec3(x, y + 1.0, z);

            for (Entity entityiterator : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(4.5), e -> true)
               .stream()
               .sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center)))
               .toList()) {
               entityiterator.hurt(
                  new DamageSource(
                     world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.IN_FIRE),
                     entity instanceof TamableAnimal _tamEnt ? _tamEnt.getOwner() : null
                  ),
                  9.0F
               );
               entityiterator.igniteForSeconds(15.0F);
            }
         }

         if (entity.getPersistentData().getDouble("duringggtick") == 16.0) {
            if (world instanceof Level _levelxxxx) {
               if (!_levelxxxx.isClientSide()) {
                  _levelxxxx.playSound(
                     null,
                     BlockPos.containing(x, y, z),
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("item.firecharge.use")),
                     SoundSource.PLAYERS,
                     3.0F,
                     1.0F
                  );
               } else {
                  _levelxxxx.playLocalSound(
                     x,
                     y,
                     z,
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("item.firecharge.use")),
                     SoundSource.PLAYERS,
                     3.0F,
                     1.0F,
                     false
                  );
               }
            }

            if (world instanceof ServerLevel _levelxxxxx) {
               _levelxxxxx.sendParticles((SimpleParticleType)GoreEditionModParticleTypes.FIRE_PARTICLE.get(), x, y + 3.0, z, 90, 1.5, 0.0, 1.5, 0.3);
            }

            Vec3 _center = new Vec3(x, y + 1.0, z);

            for (Entity entityiterator : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(4.5), e -> true)
               .stream()
               .sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center)))
               .toList()) {
               entityiterator.hurt(
                  new DamageSource(
                     world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.IN_FIRE),
                     entity instanceof TamableAnimal _tamEnt ? _tamEnt.getOwner() : null
                  ),
                  9.0F
               );
               entityiterator.igniteForSeconds(15.0F);
            }
         }

         if (entity.getPersistentData().getDouble("duringggtick") == 20.0) {
            if (world instanceof Level _levelxxxxx) {
               if (!_levelxxxxx.isClientSide()) {
                  _levelxxxxx.playSound(
                     null,
                     BlockPos.containing(x, y, z),
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("item.firecharge.use")),
                     SoundSource.PLAYERS,
                     3.0F,
                     1.0F
                  );
               } else {
                  _levelxxxxx.playLocalSound(
                     x,
                     y,
                     z,
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("item.firecharge.use")),
                     SoundSource.PLAYERS,
                     3.0F,
                     1.0F,
                     false
                  );
               }
            }

            if (world instanceof ServerLevel _levelxxxxxx) {
               _levelxxxxxx.sendParticles((SimpleParticleType)GoreEditionModParticleTypes.FIRE_PARTICLE.get(), x, y + 2.0, z, 90, 1.5, 0.0, 1.5, 0.3);
            }

            Vec3 _center = new Vec3(x, y + 1.0, z);

            for (Entity entityiterator : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(4.5), e -> true)
               .stream()
               .sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center)))
               .toList()) {
               entityiterator.hurt(
                  new DamageSource(
                     world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.IN_FIRE),
                     entity instanceof TamableAnimal _tamEnt ? _tamEnt.getOwner() : null
                  ),
                  9.0F
               );
               entityiterator.igniteForSeconds(15.0F);
            }
         }

         if (entity.getPersistentData().getDouble("duringggtick") == 24.0) {
            if (world instanceof Level _levelxxxxxx) {
               if (!_levelxxxxxx.isClientSide()) {
                  _levelxxxxxx.playSound(
                     null,
                     BlockPos.containing(x, y, z),
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("item.firecharge.use")),
                     SoundSource.PLAYERS,
                     3.0F,
                     1.0F
                  );
               } else {
                  _levelxxxxxx.playLocalSound(
                     x,
                     y,
                     z,
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("item.firecharge.use")),
                     SoundSource.PLAYERS,
                     3.0F,
                     1.0F,
                     false
                  );
               }
            }

            if (world instanceof ServerLevel _levelxxxxxxx) {
               _levelxxxxxxx.sendParticles((SimpleParticleType)GoreEditionModParticleTypes.FIRE_PARTICLE.get(), x, y + 1.0, z, 90, 1.5, 0.0, 1.5, 0.3);
            }

            Vec3 _center = new Vec3(x, y + 1.0, z);

            for (Entity entityiterator : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(4.5), e -> true)
               .stream()
               .sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center)))
               .toList()) {
               entityiterator.hurt(
                  new DamageSource(
                     world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.IN_FIRE),
                     entity instanceof TamableAnimal _tamEnt ? _tamEnt.getOwner() : null
                  ),
                  9.0F
               );
               entityiterator.igniteForSeconds(15.0F);
            }

            if (!entity.level().isClientSide()) {
               entity.discard();
            }
         }
      }
   }
}
