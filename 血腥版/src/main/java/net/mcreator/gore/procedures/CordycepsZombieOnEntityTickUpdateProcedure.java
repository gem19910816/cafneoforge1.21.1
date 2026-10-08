package net.mcreator.gore.procedures;

import net.mcreator.gore.entity.CordycepsHeadlessHuskEntity;
import net.mcreator.gore.entity.CordycepsHeadlessZombieEntity;
import net.mcreator.gore.entity.CordycepsHuskEntity;
import net.mcreator.gore.entity.CordycepsZombieEntity;
import net.mcreator.gore.entity.DisarmedCordycepsHuskEntity;
import net.mcreator.gore.entity.DisarmedCordycepsZombieEntity;
import net.mcreator.gore.entity.SeveredLegsAndOneArmCordycepsHuskEntity;
import net.mcreator.gore.entity.SeveredLegsAndOneArmCordycepsZombieEntity;
import net.mcreator.gore.entity.SeveredLegsCordycepsHuskEntity;
import net.mcreator.gore.entity.SeveredLegsCordycepsZombieEntity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.TamableAnimal;

public class CordycepsZombieOnEntityTickUpdateProcedure {
   public static void execute(Entity entity) {
      if (entity != null) {
         if (entity.getPersistentData().getDouble("spawnanimationtick") == 0.0) {
            if (entity instanceof SeveredLegsCordycepsZombieEntity) {
               ((SeveredLegsCordycepsZombieEntity)entity).setAnimation("spawn");
            }

            if (entity instanceof SeveredLegsAndOneArmCordycepsZombieEntity) {
               ((SeveredLegsAndOneArmCordycepsZombieEntity)entity).setAnimation("spawn");
            }

            if (entity instanceof CordycepsZombieEntity) {
               ((CordycepsZombieEntity)entity).setAnimation("spawn");
            }

            if (entity instanceof CordycepsHeadlessZombieEntity) {
               ((CordycepsHeadlessZombieEntity)entity).setAnimation("spawn");
            }

            if (entity instanceof DisarmedCordycepsZombieEntity) {
               ((DisarmedCordycepsZombieEntity)entity).setAnimation("spawn");
            }

            if (entity instanceof CordycepsHuskEntity) {
               ((CordycepsHuskEntity)entity).setAnimation("spawn");
            }

            if (entity instanceof SeveredLegsCordycepsHuskEntity) {
               ((SeveredLegsCordycepsHuskEntity)entity).setAnimation("spawn");
            }

            if (entity instanceof CordycepsHeadlessHuskEntity) {
               ((CordycepsHeadlessHuskEntity)entity).setAnimation("spawn");
            }

            if (entity instanceof DisarmedCordycepsHuskEntity) {
               ((DisarmedCordycepsHuskEntity)entity).setAnimation("spawn");
            }

            if (entity instanceof SeveredLegsAndOneArmCordycepsHuskEntity) {
               ((SeveredLegsAndOneArmCordycepsHuskEntity)entity).setAnimation("spawn");
            }

            if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
               _entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 80, 255, false, false));
            }

            if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
               _entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 80, 255, false, false));
            }
         }

         if (!entity.getPersistentData().getBoolean("spawnanimationtickupdate")) {
            entity.getPersistentData().putDouble("spawnanimationtick", entity.getPersistentData().getDouble("spawnanimationtick") + 1.0);
         }

         if (entity.getPersistentData().getDouble("spawnanimationtick") == 1.0) {
            if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
               _entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 79, 255, false, false));
            }

            if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
               _entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 79, 255, false, false));
            }

            if (entity instanceof DisarmedCordycepsZombieEntity) {
               ((DisarmedCordycepsZombieEntity)entity).setAnimation("spawn");
            }

            if (entity instanceof SeveredLegsAndOneArmCordycepsZombieEntity) {
               ((SeveredLegsAndOneArmCordycepsZombieEntity)entity).setAnimation("spawn");
            }

            if (entity instanceof SeveredLegsCordycepsZombieEntity) {
               ((SeveredLegsCordycepsZombieEntity)entity).setAnimation("spawn");
            }

            if (entity instanceof CordycepsZombieEntity) {
               ((CordycepsZombieEntity)entity).setAnimation("spawn");
            }

            if (entity instanceof CordycepsHeadlessZombieEntity) {
               ((CordycepsHeadlessZombieEntity)entity).setAnimation("spawn");
            }

            if (entity instanceof CordycepsHuskEntity) {
               ((CordycepsHuskEntity)entity).setAnimation("spawn");
            }

            if (entity instanceof SeveredLegsCordycepsHuskEntity) {
               ((SeveredLegsCordycepsHuskEntity)entity).setAnimation("spawn");
            }

            if (entity instanceof CordycepsHeadlessHuskEntity) {
               ((CordycepsHeadlessHuskEntity)entity).setAnimation("spawn");
            }

            if (entity instanceof DisarmedCordycepsHuskEntity) {
               ((DisarmedCordycepsHuskEntity)entity).setAnimation("spawn");
            }

            if (entity instanceof SeveredLegsAndOneArmCordycepsHuskEntity) {
               ((SeveredLegsAndOneArmCordycepsHuskEntity)entity).setAnimation("spawn");
            }
         }

         if (entity.getPersistentData().getDouble("spawnanimationtick") >= 80.0) {
            entity.getPersistentData().putBoolean("spawnanimationtickupdate", true);
         }

         if ((entity instanceof TamableAnimal _tamEnt ? _tamEnt.getOwner() : null) instanceof LivingEntity) {
            if (entity.getPersistentData().getDouble("orders") == 2.0
               && (entity instanceof Mob _mobEntx ? _mobEntx.getTarget() : null) == null
               && !((entity instanceof Mob _mobEnt ? _mobEnt.getTarget() : null) instanceof LivingEntity)
               && entity instanceof Mob _entity) {
               _entity.getNavigation()
                  .moveTo(
                     (entity instanceof TamableAnimal _tamEntxxx ? _tamEntxxx.getOwner() : null).getX(),
                     (entity instanceof TamableAnimal _tamEntxx ? _tamEntxx.getOwner() : null).getY(),
                     (entity instanceof TamableAnimal _tamEntx ? _tamEntx.getOwner() : null).getZ(),
                     1.0
                  );
            }

            if (entity.getPersistentData().getDouble("orders") == 5.0 && entity instanceof Mob _entity) {
               _entity.getNavigation()
                  .moveTo(
                     (entity instanceof TamableAnimal _tamEntxxx ? _tamEntxxx.getOwner() : null).getX(),
                     (entity instanceof TamableAnimal _tamEntxx ? _tamEntxx.getOwner() : null).getY(),
                     (entity instanceof TamableAnimal _tamEntx ? _tamEntx.getOwner() : null).getZ(),
                     1.0
                  );
            }
         }
      }
   }
}
