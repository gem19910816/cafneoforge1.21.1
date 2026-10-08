package net.mcreator.gore.procedures;

import net.mcreator.gore.entity.TripofobicAcidEntity;
import net.mcreator.gore.init.GoreEditionModEntities;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

public class HengeyonAvalancheAttackProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         if (entity.getPersistentData().getDouble("tickreset_attack") == 0.0) {
            for (int index0 = 0; index0 < Mth.nextInt(RandomSource.create(), 7, 33); index0++) {
               if (world instanceof ServerLevel projectileLevel) {
                  Projectile _entityToSpawn = (new Object() {
                        public Projectile getArrow(Level level, Entity shooter, float damage, int knockback) {
                           AbstractArrow entityToSpawn = new TripofobicAcidEntity(
                              (EntityType<? extends TripofobicAcidEntity>)GoreEditionModEntities.ACID_ASHES.get(), level
                           );
                           entityToSpawn.setOwner(shooter);
                           entityToSpawn.setBaseDamage((double)damage);
                           entityToSpawn.setSilent(true);
                           return entityToSpawn;
                        }
                     })
                     .getArrow(projectileLevel, entity, 2.0F, 1);
                  _entityToSpawn.setPos(
                     x + Mth.nextDouble(RandomSource.create(), -6.0, 6.0),
                     y + Mth.nextDouble(RandomSource.create(), -10.0, 10.0),
                     z + Mth.nextDouble(RandomSource.create(), -6.0, 6.0)
                  );
                  _entityToSpawn.shoot(
                     (entity instanceof Mob _mobEntxx ? _mobEntxx.getTarget() : null).getX() - entity.getX(),
                     (entity instanceof Mob _mobEntx ? _mobEntx.getTarget() : null).getY() - entity.getY(),
                     (entity instanceof Mob _mobEnt ? _mobEnt.getTarget() : null).getZ() - entity.getZ(),
                     2.0F,
                     0.8F
                  );
                  projectileLevel.addFreshEntity(_entityToSpawn);
               }
            }
         }

         entity.getPersistentData().putDouble("tickreset_attack", entity.getPersistentData().getDouble("tickreset_attack") + 1.0);
         entity.getPersistentData().putDouble("freezing", entity.getPersistentData().getDouble("freezing") + 2.0);
         if (entity.getPersistentData().getDouble("tickreset_attack") == 1.0) {
            entity.getPersistentData().putDouble("tickreset_attack", 0.0);
         }
      }
   }
}
