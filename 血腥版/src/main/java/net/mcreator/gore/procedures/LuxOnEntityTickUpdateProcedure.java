package net.mcreator.gore.procedures;

import java.util.Comparator;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level.ExplosionInteraction;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class LuxOnEntityTickUpdateProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         if ((entity instanceof Mob _mobEnt ? _mobEnt.getTarget() : null) instanceof LivingEntity) {
            if (entity instanceof Mob _entity) {
               _entity.getNavigation()
                  .moveTo(
                     (entity instanceof Mob _mobEntxxx ? _mobEntxxx.getTarget() : null).getX(),
                     (entity instanceof Mob _mobEntxx ? _mobEntxx.getTarget() : null).getY(),
                     (entity instanceof Mob _mobEntx ? _mobEntx.getTarget() : null).getZ(),
                     1.0
                  );
            }

            Vec3 _center = new Vec3(x, y, z);

            for (Entity entityiterator : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(0.35), e -> true)
               .stream()
               .sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center)))
               .toList()) {
               if (entityiterator == (entity instanceof Mob _mobEntx ? _mobEntx.getTarget() : null)) {
                  if (!entity.level().isClientSide()) {
                     entity.discard();
                  }

                  if (entityiterator instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
                     _entity.addEffect(new MobEffectInstance(MobEffects.GLOWING, 400, 0));
                  }

                  if (world instanceof Level _level && !_level.isClientSide()) {
                     _level.explode(null, x, y, z, 0.5F, ExplosionInteraction.NONE);
                  }
               }
            }
         }

         entity.getPersistentData().putDouble("distance", 8.0);
         AshesNativesSetAttackTargetProcedure.execute(world, x, y, z, entity);
      }
   }
}
