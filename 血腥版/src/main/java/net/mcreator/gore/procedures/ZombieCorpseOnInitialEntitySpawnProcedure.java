package net.mcreator.gore.procedures;

import net.mcreator.gore.entity.CreeperCorpseEntity;
import net.mcreator.gore.entity.DisarmedHuskCorpseEntity;
import net.mcreator.gore.entity.DisarmedZombieCorpseEntity;
import net.mcreator.gore.entity.DismemberedSpiderICorpseEntity;
import net.mcreator.gore.entity.DismemberedSpiderIICorpseEntity;
import net.mcreator.gore.entity.DismemberedSpiderIIICorpseEntity;
import net.mcreator.gore.entity.HeadlessHuskCorpseEntity;
import net.mcreator.gore.entity.HeadlessZombieCorpseEntity;
import net.mcreator.gore.entity.HuskCorpseEntity;
import net.mcreator.gore.entity.HuskWithoutLegsAndArmCorpseEntity;
import net.mcreator.gore.entity.SeveredLegsHuskCorpseEntity;
import net.mcreator.gore.entity.SeveredLegsZombieCorpseEntity;
import net.mcreator.gore.entity.SpiderCorpseEntity;
import net.mcreator.gore.entity.ZombieCorpseEntity;
import net.mcreator.gore.entity.ZombieWithoutLegsAndArmCorpseEntity;
import net.minecraft.world.entity.Entity;

public class ZombieCorpseOnInitialEntitySpawnProcedure {
   public static void execute(Entity entity) {
      if (entity != null) {
         if (entity instanceof CreeperCorpseEntity) {
            ((CreeperCorpseEntity)entity).setAnimation("creeper.spawn");
         }

         if (entity instanceof DismemberedSpiderICorpseEntity) {
            ((DismemberedSpiderICorpseEntity)entity).setAnimation("death");
         }

         if (entity instanceof DismemberedSpiderIICorpseEntity) {
            ((DismemberedSpiderIICorpseEntity)entity).setAnimation("spawn");
         }

         if (entity instanceof DismemberedSpiderIIICorpseEntity) {
            ((DismemberedSpiderIIICorpseEntity)entity).setAnimation("death");
         }

         if (entity instanceof SpiderCorpseEntity) {
            ((SpiderCorpseEntity)entity).setAnimation("death");
         }

         if (entity instanceof ZombieCorpseEntity) {
            ((ZombieCorpseEntity)entity).setAnimation("zombie.spawn");
         }

         if (entity instanceof ZombieWithoutLegsAndArmCorpseEntity) {
            ((ZombieWithoutLegsAndArmCorpseEntity)entity).setAnimation("zombie.die");
         }

         if (entity instanceof SeveredLegsZombieCorpseEntity) {
            ((SeveredLegsZombieCorpseEntity)entity).setAnimation("zombie.death");
         }

         if (entity instanceof DisarmedZombieCorpseEntity) {
            ((DisarmedZombieCorpseEntity)entity).setAnimation("die");
         }

         if (entity instanceof HeadlessZombieCorpseEntity) {
            ((HeadlessZombieCorpseEntity)entity).setAnimation("zombie.spawn");
         }

         if (entity instanceof HuskCorpseEntity) {
            ((HuskCorpseEntity)entity).setAnimation("zombie.spawn");
         }

         if (entity instanceof HuskWithoutLegsAndArmCorpseEntity) {
            ((HuskWithoutLegsAndArmCorpseEntity)entity).setAnimation("zombie.die");
         }

         if (entity instanceof SeveredLegsHuskCorpseEntity) {
            ((SeveredLegsHuskCorpseEntity)entity).setAnimation("zombie.death");
         }

         if (entity instanceof DisarmedHuskCorpseEntity) {
            ((DisarmedHuskCorpseEntity)entity).setAnimation("die");
         }

         if (entity instanceof HeadlessHuskCorpseEntity) {
            ((HeadlessHuskCorpseEntity)entity).setAnimation("zombie.spawn");
         }
      }
   }
}
