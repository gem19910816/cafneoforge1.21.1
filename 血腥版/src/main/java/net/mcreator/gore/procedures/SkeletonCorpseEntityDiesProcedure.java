package net.mcreator.gore.procedures;

import net.mcreator.gore.entity.SkeletonCorpseEntity;
import net.mcreator.gore.entity.SkeletonCorpseIIEntity;
import net.mcreator.gore.entity.SkeletonCorpseWithoutRightArmEntity;
import net.minecraft.world.entity.Entity;

public class SkeletonCorpseEntityDiesProcedure {
   public static void execute(Entity entity) {
      if (entity != null) {
         for (int index0 = 0; index0 < 30; index0++) {
            if (entity instanceof SkeletonCorpseEntity) {
               ((SkeletonCorpseEntity)entity).setAnimation("spawn");
            }

            if (entity instanceof SkeletonCorpseIIEntity) {
               ((SkeletonCorpseIIEntity)entity).setAnimation("spawn");
            }

            if (entity instanceof SkeletonCorpseWithoutRightArmEntity) {
               ((SkeletonCorpseWithoutRightArmEntity)entity).setAnimation("spawn");
            }
         }
      }
   }
}
