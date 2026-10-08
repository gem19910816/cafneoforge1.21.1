package net.mcreator.gore.procedures;

import java.util.Comparator;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class TriviaOnEntityTickUpdateProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null
         && (entity instanceof Mob _mobEntx ? _mobEntx.getTarget() : null) != null
         && (entity instanceof Mob _mobEnt ? _mobEnt.getTarget() : null) instanceof LivingEntity) {
         Vec3 _center = new Vec3(x, y, z);

         for (Entity entityiterator : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(16.0), e -> true)
            .stream()
            .sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center)))
            .toList()) {
            if (entityiterator == (entity instanceof Mob _mobEntxx ? _mobEntxx.getTarget() : null) && entity.getPersistentData().getBoolean("attack")) {
               if (entity.getPersistentData().getDouble("lightning_bolt_spam") == 0.0 && world instanceof ServerLevel _level) {
                  Entity entityToSpawn = EntityType.LIGHTNING_BOLT
                     .spawn(
                        _level,
                        BlockPos.containing(
                           entityiterator.getX() + (double)Mth.nextInt(RandomSource.create(), -4, 4),
                           entityiterator.getY(),
                           entityiterator.getZ() + (double)Mth.nextInt(RandomSource.create(), -4, 4)
                        ),
                        MobSpawnType.MOB_SUMMONED
                     );
                  if (entityToSpawn != null) {
                     entityToSpawn.setYRot(world.getRandom().nextFloat() * 360.0F);
                  }
               }

               entity.getPersistentData().putDouble("lightning_bolt_spam", entity.getPersistentData().getDouble("lightning_bolt_spam") + 1.0);
               if (entity.getPersistentData().getDouble("lightning_bolt_spam") == 3.0) {
                  entity.getPersistentData().putDouble("lightning_bolt_spam", 0.0);
               }
            }
         }

         if (entity.getPersistentData().getDouble("lightning_attack_timer") == 0.0) {
            entity.getPersistentData().putBoolean("attack", true);
         }

         entity.getPersistentData().putDouble("lightning_attack_timer", entity.getPersistentData().getDouble("lightning_attack_timer") + 1.0);
         if (entity.getPersistentData().getDouble("lightning_attack_timer") == 60.0) {
            entity.getPersistentData().putBoolean("attack", false);
         }

         if (entity.getPersistentData().getDouble("lightning_attack_timer") == 120.0) {
            entity.getPersistentData().putDouble("lightning_attack_timer", 0.0);
         }
      }
   }
}
