package net.mcreator.gore.procedures;

import java.util.Comparator;
import net.mcreator.gore.init.GoreEditionModParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
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
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class GrenadeOfGreekFireGreekFireOnEntityTickUpdateProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         boolean found = false;
         double sx = 0.0;
         double sy = 0.0;
         double sz = 0.0;
         if (entity.getPersistentData().getDouble("starttickupdate") == 0.0) {
            if (entity.getPersistentData().getDouble("change_fire_animation") < 1.0) {
               if (world instanceof ServerLevel _level) {
                  _level.sendParticles((SimpleParticleType)GoreEditionModParticleTypes.FIRE_PARTICLE.get(), x, y, z, 70, 1.0, 0.0, 1.0, 1.4);
               }

               if (world instanceof ServerLevel _level) {
                  _level.sendParticles(ParticleTypes.LARGE_SMOKE, x, y, z, 100, 1.0, 0.0, 1.0, 1.4);
               }
            }

            if (entity.getPersistentData().getDouble("change_fire_animation") < 4.0) {
               if (world instanceof ServerLevel _level) {
                  _level.sendParticles((SimpleParticleType)GoreEditionModParticleTypes.FIRE_PARTICLE.get(), x, y, z, 60, 1.0, 0.0, 1.0, 0.7);
               }

               if (world instanceof ServerLevel _level) {
                  _level.sendParticles(ParticleTypes.LARGE_SMOKE, x, y, z, 70, 1.0, 0.0, 1.0, 0.7);
               }

               Vec3 _center = new Vec3(x, y, z);

               for (Entity entityiterator : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(1.5), e -> true)
                  .stream()
                  .sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center)))
                  .toList()) {
                  entityiterator.hurt(
                     new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.IN_FIRE)), 10.0F
                  );
                  entityiterator.igniteForSeconds(15.0F);
               }
            }

            if (entity.getPersistentData().getDouble("change_fire_animation") >= 4.0 && entity.getPersistentData().getDouble("change_fire_animation") < 8.0) {
               if (world instanceof ServerLevel _level) {
                  _level.sendParticles((SimpleParticleType)GoreEditionModParticleTypes.FIRE_PARTICLE.get(), x, y, z, 60, 2.0, 0.0, 2.0, 0.7);
               }

               if (world instanceof ServerLevel _level) {
                  _level.sendParticles(ParticleTypes.LARGE_SMOKE, x, y, z, 70, 1.0, 0.0, 2.0, 0.7);
               }

               Vec3 _center = new Vec3(x, y, z);

               for (Entity entityiterator : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(3.0), e -> true)
                  .stream()
                  .sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center)))
                  .toList()) {
                  entityiterator.hurt(
                     new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.IN_FIRE)), 10.0F
                  );
                  entityiterator.igniteForSeconds(15.0F);
               }
            }

            if (entity.getPersistentData().getDouble("change_fire_animation") >= 8.0 && entity.getPersistentData().getDouble("change_fire_animation") < 16.0) {
               if (world instanceof ServerLevel _level) {
                  _level.sendParticles((SimpleParticleType)GoreEditionModParticleTypes.FIRE_PARTICLE.get(), x, y, z, 60, 3.0, 0.0, 3.0, 0.7);
               }

               if (world instanceof ServerLevel _level) {
                  _level.sendParticles(ParticleTypes.LARGE_SMOKE, x, y, z, 70, 3.0, 0.0, 3.0, 0.7);
               }

               Vec3 _center = new Vec3(x, y, z);

               for (Entity entityiterator : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(4.5), e -> true)
                  .stream()
                  .sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center)))
                  .toList()) {
                  entityiterator.hurt(
                     new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.IN_FIRE)), 10.0F
                  );
                  entityiterator.igniteForSeconds(15.0F);
               }
            }

            if (entity.getPersistentData().getDouble("change_fire_animation") >= 16.0) {
               if (world instanceof ServerLevel _level) {
                  _level.sendParticles(ParticleTypes.LARGE_SMOKE, x, y, z, 70, 4.0, 0.0, 4.0, 0.7);
               }

               if (world instanceof ServerLevel _level) {
                  _level.sendParticles((SimpleParticleType)GoreEditionModParticleTypes.FIRE_PARTICLE.get(), x, y, z, 60, 4.0, 0.0, 4.0, 0.7);
               }

               Vec3 _center = new Vec3(x, y, z);

               for (Entity entityiterator : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(6.0), e -> true)
                  .stream()
                  .sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center)))
                  .toList()) {
                  entityiterator.hurt(
                     new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.IN_FIRE)), 10.0F
                  );
                  entityiterator.igniteForSeconds(15.0F);
               }
            }

            if (world instanceof Level _level) {
               if (!_level.isClientSide()) {
                  _level.playSound(
                     null,
                     BlockPos.containing(x, y, z),
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("item.firecharge.use")),
                     SoundSource.PLAYERS,
                     3.0F,
                     1.0F
                  );
               } else {
                  _level.playLocalSound(
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

            entity.getPersistentData().putDouble("change_fire_animation", entity.getPersistentData().getDouble("change_fire_animation") + 1.0);
         }

         entity.getPersistentData().putDouble("starttickupdate", entity.getPersistentData().getDouble("starttickupdate") + 1.0);
         if (entity.getPersistentData().getDouble("starttickupdate") == 2.0) {
            entity.getPersistentData().putDouble("starttickupdate", 0.0);
         }

         entity.getPersistentData().putDouble("final_tickupdate", entity.getPersistentData().getDouble("final_tickupdate") + 1.0);
         if (entity.getPersistentData().getDouble("final_tickupdate") == 200.0 && !entity.level().isClientSide()) {
            entity.discard();
         }
      }
   }
}
