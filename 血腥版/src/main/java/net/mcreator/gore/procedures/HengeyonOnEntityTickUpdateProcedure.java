package net.mcreator.gore.procedures;

import java.util.Comparator;
import net.mcreator.gore.entity.ExarrackHydraEntity;
import net.mcreator.gore.entity.FleshEaterEntity;
import net.mcreator.gore.entity.TheExarrackMonsterEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class HengeyonOnEntityTickUpdateProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         HengeyonLivingSoundProcedure.execute(world, x, y, z, entity);
         entity.getPersistentData().putDouble("distance", 90.0);
         HengeyonSpawnElevatingProcedure.execute(x, y, z, entity);
         if (!entity.getPersistentData().getBoolean("hengeyon_spawn_elevating")) {
            AshesNativesSetAttackTargetProcedure.execute(world, x, y, z, entity);
         }

         if (entity.isAlive()) {
            Vec3 _center = new Vec3(x, y, z);

            for (Entity entityiterator : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(8.5), e -> true)
               .stream()
               .sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center)))
               .toList()) {
               if (entityiterator != entity) {
                  entityiterator.hurt(
                     new DamageSource(
                        world.registryAccess()
                           .registryOrThrow(Registries.DAMAGE_TYPE)
                           .getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.parse("gore_edition:acid_damage"))),
                        entity
                     ),
                     10.0F
                  );
                  if (entity instanceof LivingEntity _entity) {
                     _entity.setHealth(
                        (entity instanceof LivingEntity _livEntx ? _livEntx.getHealth() : -1.0F)
                           + (entityiterator instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1.0F) * 0.0F
                     );
                  }
               }

               if (entityiterator instanceof FleshEaterEntity
                  || entityiterator instanceof ExarrackHydraEntity
                  || entityiterator instanceof TheExarrackMonsterEntity) {
                  if (entity instanceof LivingEntity _entity) {
                     _entity.setHealth(
                        (entity instanceof LivingEntity _livEntx ? _livEntx.getHealth() : -1.0F)
                           + (entityiterator instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1.0F)
                     );
                  }

                  entityiterator.hurt(
                     new DamageSource(
                        world.registryAccess()
                           .registryOrThrow(Registries.DAMAGE_TYPE)
                           .getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.parse("gore_edition:acid_damage")))
                     ),
                     300.0F
                  );
               }
            }
         }

         HengeyonTaskManagerProcedure.execute(world, x, y, z, entity);
      }
   }
}
