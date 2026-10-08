package net.mcreator.gore.init;

import net.mcreator.gore.entity.AluxinationEntity;
import net.mcreator.gore.entity.AshesGatewayEntity;
import net.mcreator.gore.entity.AshesWitherSneerEntity;
import net.mcreator.gore.entity.CollapsingSkeletonEntity;
import net.mcreator.gore.entity.CordycepsHeadlessHuskEntity;
import net.mcreator.gore.entity.CordycepsHeadlessZombieEntity;
import net.mcreator.gore.entity.CordycepsHuskEntity;
import net.mcreator.gore.entity.CordycepsZombieEntity;
import net.mcreator.gore.entity.CreeperCorpseEntity;
import net.mcreator.gore.entity.CrushedHuskEntity;
import net.mcreator.gore.entity.CrushedZombieEntity;
import net.mcreator.gore.entity.CrushingSkeletonEntity;
import net.mcreator.gore.entity.DeformityForAshesEntity;
import net.mcreator.gore.entity.DisarmedCordycepsHuskEntity;
import net.mcreator.gore.entity.DisarmedCordycepsZombieEntity;
import net.mcreator.gore.entity.DisarmedHuskCorpseEntity;
import net.mcreator.gore.entity.DisarmedHuskEntity;
import net.mcreator.gore.entity.DisarmedZombieCorpseEntity;
import net.mcreator.gore.entity.DisarmedZombieEntity;
import net.mcreator.gore.entity.DismemberedSpiderICorpseEntity;
import net.mcreator.gore.entity.DismemberedSpiderIEntity;
import net.mcreator.gore.entity.DismemberedSpiderIICorpseEntity;
import net.mcreator.gore.entity.DismemberedSpiderIIEntity;
import net.mcreator.gore.entity.DismemberedSpiderIIICorpseEntity;
import net.mcreator.gore.entity.ExarrackHydraEntity;
import net.mcreator.gore.entity.ExplodedHeadSpiderEntity;
import net.mcreator.gore.entity.FakeExarrackMonsterEntity;
import net.mcreator.gore.entity.FleshEaterEntity;
import net.mcreator.gore.entity.HandOfAcidEntity;
import net.mcreator.gore.entity.HeadlessDrownedEntity;
import net.mcreator.gore.entity.HeadlessHuskCorpseEntity;
import net.mcreator.gore.entity.HeadlessHuskEntity;
import net.mcreator.gore.entity.HeadlessZombieCorpseEntity;
import net.mcreator.gore.entity.HeadlessZombieEntity;
import net.mcreator.gore.entity.HengeyonEntity;
import net.mcreator.gore.entity.HorizontallyCuttedHuskEntity;
import net.mcreator.gore.entity.HorizontallyCuttedSpiderEntity;
import net.mcreator.gore.entity.HorizontallycuttedZombieEntity;
import net.mcreator.gore.entity.HuskAboutToDieEntity;
import net.mcreator.gore.entity.HuskCorpseEntity;
import net.mcreator.gore.entity.HuskWithoutLegsAndArmCorpseEntity;
import net.mcreator.gore.entity.LuxEntity;
import net.mcreator.gore.entity.LuxSycaridaeEntity;
import net.mcreator.gore.entity.NowindEntity;
import net.mcreator.gore.entity.SeveredLegsAndArmHuskEntity;
import net.mcreator.gore.entity.SeveredLegsAndArmZombieEntity;
import net.mcreator.gore.entity.SeveredLegsAndOneArmCordycepsHuskEntity;
import net.mcreator.gore.entity.SeveredLegsAndOneArmCordycepsZombieEntity;
import net.mcreator.gore.entity.SeveredLegsCordycepsHuskEntity;
import net.mcreator.gore.entity.SeveredLegsCordycepsZombieEntity;
import net.mcreator.gore.entity.SeveredLegsHuskCorpseEntity;
import net.mcreator.gore.entity.SeveredLegsSkeletonEntity;
import net.mcreator.gore.entity.SeveredLegsZombieCorpseEntity;
import net.mcreator.gore.entity.SeveredlegsHuskEntity;
import net.mcreator.gore.entity.SeveredlegsZombieEntity;
import net.mcreator.gore.entity.SkeletonBackboneEntity;
import net.mcreator.gore.entity.SkeletonBodyPartIEntity;
import net.mcreator.gore.entity.SkeletonCorpseEntity;
import net.mcreator.gore.entity.SkeletonCorpseIIEntity;
import net.mcreator.gore.entity.SkeletonCorpseWithoutRightArmEntity;
import net.mcreator.gore.entity.SkeletonHeadEntity;
import net.mcreator.gore.entity.SkeletonLeftArmEntity;
import net.mcreator.gore.entity.SkeletonLeftLegEntity;
import net.mcreator.gore.entity.SkeletonPelvisEntity;
import net.mcreator.gore.entity.SkeletonRightArmEntity;
import net.mcreator.gore.entity.SkeletonRightLegEntity;
import net.mcreator.gore.entity.SkeletonWithoutArmEntity;
import net.mcreator.gore.entity.SkeletonWithoutLeftArmEntity;
import net.mcreator.gore.entity.SpiderCorpseEntity;
import net.mcreator.gore.entity.TheExarrackMonsterEntity;
import net.mcreator.gore.entity.VerticalcuttedHuskEntity;
import net.mcreator.gore.entity.VerticalcuttedZombieEntity;
import net.mcreator.gore.entity.VerticallyCuttedSkeletonEntity;
import net.mcreator.gore.entity.VerticallyCuttedSpiderEntity;
import net.mcreator.gore.entity.ZombieAboutToDieEntity;
import net.mcreator.gore.entity.ZombieCorpseEntity;
import net.mcreator.gore.entity.ZombieWithoutLegsAndArmCorpseEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.EntityTickEvent.Pre;

@EventBusSubscriber
public class EntityAnimationFactory {
   @SubscribeEvent
   public static void onEntityTick(Pre event) {
      if (event != null && event.getEntity() != null) {
         if (event.getEntity() instanceof HeadlessZombieEntity syncable) {
            String animation = syncable.getSyncedAnimation();
            if (!animation.equals("undefined")) {
               syncable.setAnimation("undefined");
               syncable.animationprocedure = animation;
            }
         }

         if (event.getEntity() instanceof HorizontallycuttedZombieEntity syncablex) {
            String animation = syncablex.getSyncedAnimation();
            if (!animation.equals("undefined")) {
               syncablex.setAnimation("undefined");
               syncablex.animationprocedure = animation;
            }
         }

         if (event.getEntity() instanceof VerticalcuttedZombieEntity syncablexx) {
            String animation = syncablexx.getSyncedAnimation();
            if (!animation.equals("undefined")) {
               syncablexx.setAnimation("undefined");
               syncablexx.animationprocedure = animation;
            }
         }

         if (event.getEntity() instanceof CrushedZombieEntity syncablexxx) {
            String animation = syncablexxx.getSyncedAnimation();
            if (!animation.equals("undefined")) {
               syncablexxx.setAnimation("undefined");
               syncablexxx.animationprocedure = animation;
            }
         }

         if (event.getEntity() instanceof SeveredlegsZombieEntity syncablexxxx) {
            String animation = syncablexxxx.getSyncedAnimation();
            if (!animation.equals("undefined")) {
               syncablexxxx.setAnimation("undefined");
               syncablexxxx.animationprocedure = animation;
            }
         }

         if (event.getEntity() instanceof DisarmedZombieEntity syncablexxxxx) {
            String animation = syncablexxxxx.getSyncedAnimation();
            if (!animation.equals("undefined")) {
               syncablexxxxx.setAnimation("undefined");
               syncablexxxxx.animationprocedure = animation;
            }
         }

         if (event.getEntity() instanceof SeveredLegsAndArmZombieEntity syncablexxxxxx) {
            String animation = syncablexxxxxx.getSyncedAnimation();
            if (!animation.equals("undefined")) {
               syncablexxxxxx.setAnimation("undefined");
               syncablexxxxxx.animationprocedure = animation;
            }
         }

         if (event.getEntity() instanceof HorizontallyCuttedSpiderEntity syncablexxxxxxx) {
            String animation = syncablexxxxxxx.getSyncedAnimation();
            if (!animation.equals("undefined")) {
               syncablexxxxxxx.setAnimation("undefined");
               syncablexxxxxxx.animationprocedure = animation;
            }
         }

         if (event.getEntity() instanceof VerticallyCuttedSpiderEntity syncablexxxxxxxx) {
            String animation = syncablexxxxxxxx.getSyncedAnimation();
            if (!animation.equals("undefined")) {
               syncablexxxxxxxx.setAnimation("undefined");
               syncablexxxxxxxx.animationprocedure = animation;
            }
         }

         if (event.getEntity() instanceof ExplodedHeadSpiderEntity syncablexxxxxxxxx) {
            String animation = syncablexxxxxxxxx.getSyncedAnimation();
            if (!animation.equals("undefined")) {
               syncablexxxxxxxxx.setAnimation("undefined");
               syncablexxxxxxxxx.animationprocedure = animation;
            }
         }

         if (event.getEntity() instanceof ZombieAboutToDieEntity syncablexxxxxxxxxx) {
            String animation = syncablexxxxxxxxxx.getSyncedAnimation();
            if (!animation.equals("undefined")) {
               syncablexxxxxxxxxx.setAnimation("undefined");
               syncablexxxxxxxxxx.animationprocedure = animation;
            }
         }

         if (event.getEntity() instanceof HeadlessHuskEntity syncablexxxxxxxxxxx) {
            String animation = syncablexxxxxxxxxxx.getSyncedAnimation();
            if (!animation.equals("undefined")) {
               syncablexxxxxxxxxxx.setAnimation("undefined");
               syncablexxxxxxxxxxx.animationprocedure = animation;
            }
         }

         if (event.getEntity() instanceof HorizontallyCuttedHuskEntity syncablexxxxxxxxxxxx) {
            String animation = syncablexxxxxxxxxxxx.getSyncedAnimation();
            if (!animation.equals("undefined")) {
               syncablexxxxxxxxxxxx.setAnimation("undefined");
               syncablexxxxxxxxxxxx.animationprocedure = animation;
            }
         }

         if (event.getEntity() instanceof VerticalcuttedHuskEntity syncablexxxxxxxxxxxxx) {
            String animation = syncablexxxxxxxxxxxxx.getSyncedAnimation();
            if (!animation.equals("undefined")) {
               syncablexxxxxxxxxxxxx.setAnimation("undefined");
               syncablexxxxxxxxxxxxx.animationprocedure = animation;
            }
         }

         if (event.getEntity() instanceof CrushedHuskEntity syncablexxxxxxxxxxxxxx) {
            String animation = syncablexxxxxxxxxxxxxx.getSyncedAnimation();
            if (!animation.equals("undefined")) {
               syncablexxxxxxxxxxxxxx.setAnimation("undefined");
               syncablexxxxxxxxxxxxxx.animationprocedure = animation;
            }
         }

         if (event.getEntity() instanceof SeveredlegsHuskEntity syncablexxxxxxxxxxxxxxx) {
            String animation = syncablexxxxxxxxxxxxxxx.getSyncedAnimation();
            if (!animation.equals("undefined")) {
               syncablexxxxxxxxxxxxxxx.setAnimation("undefined");
               syncablexxxxxxxxxxxxxxx.animationprocedure = animation;
            }
         }

         if (event.getEntity() instanceof SeveredLegsAndArmHuskEntity syncablexxxxxxxxxxxxxxxx) {
            String animation = syncablexxxxxxxxxxxxxxxx.getSyncedAnimation();
            if (!animation.equals("undefined")) {
               syncablexxxxxxxxxxxxxxxx.setAnimation("undefined");
               syncablexxxxxxxxxxxxxxxx.animationprocedure = animation;
            }
         }

         if (event.getEntity() instanceof HuskAboutToDieEntity syncablexxxxxxxxxxxxxxxxx) {
            String animation = syncablexxxxxxxxxxxxxxxxx.getSyncedAnimation();
            if (!animation.equals("undefined")) {
               syncablexxxxxxxxxxxxxxxxx.setAnimation("undefined");
               syncablexxxxxxxxxxxxxxxxx.animationprocedure = animation;
            }
         }

         if (event.getEntity() instanceof DisarmedHuskEntity syncablexxxxxxxxxxxxxxxxxx) {
            String animation = syncablexxxxxxxxxxxxxxxxxx.getSyncedAnimation();
            if (!animation.equals("undefined")) {
               syncablexxxxxxxxxxxxxxxxxx.setAnimation("undefined");
               syncablexxxxxxxxxxxxxxxxxx.animationprocedure = animation;
            }
         }

         if (event.getEntity() instanceof HeadlessDrownedEntity syncablexxxxxxxxxxxxxxxxxxx) {
            String animation = syncablexxxxxxxxxxxxxxxxxxx.getSyncedAnimation();
            if (!animation.equals("undefined")) {
               syncablexxxxxxxxxxxxxxxxxxx.setAnimation("undefined");
               syncablexxxxxxxxxxxxxxxxxxx.animationprocedure = animation;
            }
         }

         if (event.getEntity() instanceof DismemberedSpiderIEntity syncablexxxxxxxxxxxxxxxxxxxx) {
            String animation = syncablexxxxxxxxxxxxxxxxxxxx.getSyncedAnimation();
            if (!animation.equals("undefined")) {
               syncablexxxxxxxxxxxxxxxxxxxx.setAnimation("undefined");
               syncablexxxxxxxxxxxxxxxxxxxx.animationprocedure = animation;
            }
         }

         if (event.getEntity() instanceof ZombieCorpseEntity syncablexxxxxxxxxxxxxxxxxxxxx) {
            String animation = syncablexxxxxxxxxxxxxxxxxxxxx.getSyncedAnimation();
            if (!animation.equals("undefined")) {
               syncablexxxxxxxxxxxxxxxxxxxxx.setAnimation("undefined");
               syncablexxxxxxxxxxxxxxxxxxxxx.animationprocedure = animation;
            }
         }

         if (event.getEntity() instanceof ZombieWithoutLegsAndArmCorpseEntity syncablexxxxxxxxxxxxxxxxxxxxxx) {
            String animation = syncablexxxxxxxxxxxxxxxxxxxxxx.getSyncedAnimation();
            if (!animation.equals("undefined")) {
               syncablexxxxxxxxxxxxxxxxxxxxxx.setAnimation("undefined");
               syncablexxxxxxxxxxxxxxxxxxxxxx.animationprocedure = animation;
            }
         }

         if (event.getEntity() instanceof SeveredLegsZombieCorpseEntity syncablexxxxxxxxxxxxxxxxxxxxxxx) {
            String animation = syncablexxxxxxxxxxxxxxxxxxxxxxx.getSyncedAnimation();
            if (!animation.equals("undefined")) {
               syncablexxxxxxxxxxxxxxxxxxxxxxx.setAnimation("undefined");
               syncablexxxxxxxxxxxxxxxxxxxxxxx.animationprocedure = animation;
            }
         }

         if (event.getEntity() instanceof CreeperCorpseEntity syncablexxxxxxxxxxxxxxxxxxxxxxxx) {
            String animation = syncablexxxxxxxxxxxxxxxxxxxxxxxx.getSyncedAnimation();
            if (!animation.equals("undefined")) {
               syncablexxxxxxxxxxxxxxxxxxxxxxxx.setAnimation("undefined");
               syncablexxxxxxxxxxxxxxxxxxxxxxxx.animationprocedure = animation;
            }
         }

         if (event.getEntity() instanceof DismemberedSpiderIIEntity syncablexxxxxxxxxxxxxxxxxxxxxxxxx) {
            String animation = syncablexxxxxxxxxxxxxxxxxxxxxxxxx.getSyncedAnimation();
            if (!animation.equals("undefined")) {
               syncablexxxxxxxxxxxxxxxxxxxxxxxxx.setAnimation("undefined");
               syncablexxxxxxxxxxxxxxxxxxxxxxxxx.animationprocedure = animation;
            }
         }

         if (event.getEntity() instanceof FleshEaterEntity syncablexxxxxxxxxxxxxxxxxxxxxxxxxx) {
            String animation = syncablexxxxxxxxxxxxxxxxxxxxxxxxxx.getSyncedAnimation();
            if (!animation.equals("undefined")) {
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxx.setAnimation("undefined");
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxx.animationprocedure = animation;
            }
         }

         if (event.getEntity() instanceof DisarmedZombieCorpseEntity syncablexxxxxxxxxxxxxxxxxxxxxxxxxxx) {
            String animation = syncablexxxxxxxxxxxxxxxxxxxxxxxxxxx.getSyncedAnimation();
            if (!animation.equals("undefined")) {
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxx.setAnimation("undefined");
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxx.animationprocedure = animation;
            }
         }

         if (event.getEntity() instanceof HeadlessZombieCorpseEntity syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxx) {
            String animation = syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxx.getSyncedAnimation();
            if (!animation.equals("undefined")) {
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxx.setAnimation("undefined");
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxx.animationprocedure = animation;
            }
         }

         if (event.getEntity() instanceof HuskCorpseEntity syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxx) {
            String animation = syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxx.getSyncedAnimation();
            if (!animation.equals("undefined")) {
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxx.setAnimation("undefined");
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxx.animationprocedure = animation;
            }
         }

         if (event.getEntity() instanceof HuskWithoutLegsAndArmCorpseEntity syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxx) {
            String animation = syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.getSyncedAnimation();
            if (!animation.equals("undefined")) {
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.setAnimation("undefined");
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.animationprocedure = animation;
            }
         }

         if (event.getEntity() instanceof SeveredLegsHuskCorpseEntity syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx) {
            String animation = syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.getSyncedAnimation();
            if (!animation.equals("undefined")) {
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.setAnimation("undefined");
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.animationprocedure = animation;
            }
         }

         if (event.getEntity() instanceof DisarmedHuskCorpseEntity syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx) {
            String animation = syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.getSyncedAnimation();
            if (!animation.equals("undefined")) {
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.setAnimation("undefined");
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.animationprocedure = animation;
            }
         }

         if (event.getEntity() instanceof HeadlessHuskCorpseEntity syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx) {
            String animation = syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.getSyncedAnimation();
            if (!animation.equals("undefined")) {
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.setAnimation("undefined");
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.animationprocedure = animation;
            }
         }

         if (event.getEntity() instanceof SkeletonHeadEntity syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx) {
            String animation = syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.getSyncedAnimation();
            if (!animation.equals("undefined")) {
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.setAnimation("undefined");
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.animationprocedure = animation;
            }
         }

         if (event.getEntity() instanceof SkeletonBodyPartIEntity syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx) {
            String animation = syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.getSyncedAnimation();
            if (!animation.equals("undefined")) {
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.setAnimation("undefined");
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.animationprocedure = animation;
            }
         }

         if (event.getEntity() instanceof SkeletonPelvisEntity syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx) {
            String animation = syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.getSyncedAnimation();
            if (!animation.equals("undefined")) {
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.setAnimation("undefined");
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.animationprocedure = animation;
            }
         }

         if (event.getEntity() instanceof SkeletonBackboneEntity syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx) {
            String animation = syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.getSyncedAnimation();
            if (!animation.equals("undefined")) {
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.setAnimation("undefined");
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.animationprocedure = animation;
            }
         }

         if (event.getEntity() instanceof SkeletonRightArmEntity syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx) {
            String animation = syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.getSyncedAnimation();
            if (!animation.equals("undefined")) {
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.setAnimation("undefined");
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.animationprocedure = animation;
            }
         }

         if (event.getEntity() instanceof SkeletonLeftArmEntity syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx) {
            String animation = syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.getSyncedAnimation();
            if (!animation.equals("undefined")) {
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.setAnimation("undefined");
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.animationprocedure = animation;
            }
         }

         if (event.getEntity() instanceof SkeletonLeftLegEntity syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx) {
            String animation = syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.getSyncedAnimation();
            if (!animation.equals("undefined")) {
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.setAnimation("undefined");
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.animationprocedure = animation;
            }
         }

         if (event.getEntity() instanceof SkeletonRightLegEntity syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx) {
            String animation = syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.getSyncedAnimation();
            if (!animation.equals("undefined")) {
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.setAnimation("undefined");
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.animationprocedure = animation;
            }
         }

         if (event.getEntity() instanceof SkeletonWithoutArmEntity syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx) {
            String animation = syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.getSyncedAnimation();
            if (!animation.equals("undefined")) {
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.setAnimation("undefined");
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.animationprocedure = animation;
            }
         }

         if (event.getEntity() instanceof SkeletonWithoutLeftArmEntity syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx) {
            String animation = syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.getSyncedAnimation();
            if (!animation.equals("undefined")) {
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.setAnimation("undefined");
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.animationprocedure = animation;
            }
         }

         if (event.getEntity() instanceof SkeletonCorpseEntity syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx) {
            String animation = syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.getSyncedAnimation();
            if (!animation.equals("undefined")) {
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.setAnimation("undefined");
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.animationprocedure = animation;
            }
         }

         if (event.getEntity() instanceof SkeletonCorpseIIEntity syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx) {
            String animation = syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.getSyncedAnimation();
            if (!animation.equals("undefined")) {
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.setAnimation("undefined");
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.animationprocedure = animation;
            }
         }

         if (event.getEntity() instanceof SkeletonCorpseWithoutRightArmEntity syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx) {
            String animation = syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.getSyncedAnimation();
            if (!animation.equals("undefined")) {
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.setAnimation("undefined");
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.animationprocedure = animation;
            }
         }

         if (event.getEntity() instanceof DismemberedSpiderICorpseEntity syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx) {
            String animation = syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.getSyncedAnimation();
            if (!animation.equals("undefined")) {
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.setAnimation("undefined");
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.animationprocedure = animation;
            }
         }

         if (event.getEntity() instanceof LuxSycaridaeEntity syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx) {
            String animation = syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.getSyncedAnimation();
            if (!animation.equals("undefined")) {
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.setAnimation("undefined");
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.animationprocedure = animation;
            }
         }

         if (event.getEntity() instanceof AluxinationEntity syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx) {
            String animation = syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.getSyncedAnimation();
            if (!animation.equals("undefined")) {
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.setAnimation("undefined");
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.animationprocedure = animation;
            }
         }

         if (event.getEntity() instanceof DismemberedSpiderIICorpseEntity syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx) {
            String animation = syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.getSyncedAnimation();
            if (!animation.equals("undefined")) {
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.setAnimation("undefined");
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.animationprocedure = animation;
            }
         }

         if (event.getEntity() instanceof DismemberedSpiderIIICorpseEntity syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx) {
            String animation = syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.getSyncedAnimation();
            if (!animation.equals("undefined")) {
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.setAnimation("undefined");
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.animationprocedure = animation;
            }
         }

         if (event.getEntity() instanceof CollapsingSkeletonEntity syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx) {
            String animation = syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.getSyncedAnimation();
            if (!animation.equals("undefined")) {
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.setAnimation("undefined");
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.animationprocedure = animation;
            }
         }

         if (event.getEntity() instanceof NowindEntity syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx) {
            String animation = syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.getSyncedAnimation();
            if (!animation.equals("undefined")) {
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.setAnimation("undefined");
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.animationprocedure = animation;
            }
         }

         if (event.getEntity() instanceof HengeyonEntity syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx) {
            String animation = syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.getSyncedAnimation();
            if (!animation.equals("undefined")) {
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.setAnimation("undefined");
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.animationprocedure = animation;
            }
         }

         if (event.getEntity() instanceof FakeExarrackMonsterEntity syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx) {
            String animation = syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.getSyncedAnimation();
            if (!animation.equals("undefined")) {
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.setAnimation("undefined");
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.animationprocedure = animation;
            }
         }

         if (event.getEntity() instanceof CordycepsZombieEntity syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx) {
            String animation = syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.getSyncedAnimation();
            if (!animation.equals("undefined")) {
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.setAnimation("undefined");
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.animationprocedure = animation;
            }
         }

         if (event.getEntity() instanceof SeveredLegsCordycepsZombieEntity syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx) {
            String animation = syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.getSyncedAnimation();
            if (!animation.equals("undefined")) {
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.setAnimation("undefined");
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.animationprocedure = animation;
            }
         }

         if (event.getEntity() instanceof CordycepsHeadlessZombieEntity syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx) {
            String animation = syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.getSyncedAnimation();
            if (!animation.equals("undefined")) {
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.setAnimation("undefined");
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.animationprocedure = animation;
            }
         }

         if (event.getEntity() instanceof SeveredLegsAndOneArmCordycepsZombieEntity syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx) {
            String animation = syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.getSyncedAnimation();
            if (!animation.equals("undefined")) {
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.setAnimation("undefined");
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.animationprocedure = animation;
            }
         }

         if (event.getEntity() instanceof DisarmedCordycepsZombieEntity syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx) {
            String animation = syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.getSyncedAnimation();
            if (!animation.equals("undefined")) {
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.setAnimation("undefined");
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.animationprocedure = animation;
            }
         }

         if (event.getEntity() instanceof CordycepsHuskEntity syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx) {
            String animation = syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.getSyncedAnimation();
            if (!animation.equals("undefined")) {
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.setAnimation("undefined");
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.animationprocedure = animation;
            }
         }

         if (event.getEntity() instanceof SeveredLegsCordycepsHuskEntity syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx) {
            String animation = syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.getSyncedAnimation();
            if (!animation.equals("undefined")) {
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.setAnimation("undefined");
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.animationprocedure = animation;
            }
         }

         if (event.getEntity() instanceof CordycepsHeadlessHuskEntity syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx) {
            String animation = syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.getSyncedAnimation();
            if (!animation.equals("undefined")) {
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.setAnimation("undefined");
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.animationprocedure = animation;
            }
         }

         if (event.getEntity() instanceof SeveredLegsAndOneArmCordycepsHuskEntity syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx) {
            String animation = syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.getSyncedAnimation();
            if (!animation.equals("undefined")) {
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.setAnimation("undefined");
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.animationprocedure = animation;
            }
         }

         if (event.getEntity() instanceof DisarmedCordycepsHuskEntity syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx) {
            String animation = syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.getSyncedAnimation();
            if (!animation.equals("undefined")) {
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.setAnimation("undefined");
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.animationprocedure = animation;
            }
         }

         if (event.getEntity() instanceof TheExarrackMonsterEntity syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx) {
            String animation = syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.getSyncedAnimation();
            if (!animation.equals("undefined")) {
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.setAnimation("undefined");
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.animationprocedure = animation;
            }
         }

         if (event.getEntity() instanceof SpiderCorpseEntity syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx) {
            String animation = syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.getSyncedAnimation();
            if (!animation.equals("undefined")) {
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.setAnimation("undefined");
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.animationprocedure = animation;
            }
         }

         if (event.getEntity() instanceof DeformityForAshesEntity syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx) {
            String animation = syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.getSyncedAnimation();
            if (!animation.equals("undefined")) {
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.setAnimation("undefined");
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.animationprocedure = animation;
            }
         }

         if (event.getEntity() instanceof CrushingSkeletonEntity syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx) {
            String animation = syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.getSyncedAnimation();
            if (!animation.equals("undefined")) {
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.setAnimation("undefined");
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.animationprocedure = animation;
            }
         }

         if (event.getEntity() instanceof SeveredLegsSkeletonEntity syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx) {
            String animation = syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.getSyncedAnimation();
            if (!animation.equals("undefined")) {
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.setAnimation("undefined");
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.animationprocedure = animation;
            }
         }

         if (event.getEntity() instanceof LuxEntity syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx) {
            String animation = syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.getSyncedAnimation();
            if (!animation.equals("undefined")) {
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.setAnimation("undefined");
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.animationprocedure = animation;
            }
         }

         if (event.getEntity() instanceof AshesGatewayEntity syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx) {
            String animation = syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.getSyncedAnimation();
            if (!animation.equals("undefined")) {
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.setAnimation("undefined");
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.animationprocedure = animation;
            }
         }

         if (event.getEntity() instanceof ExarrackHydraEntity syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx) {
            String animation = syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.getSyncedAnimation();
            if (!animation.equals("undefined")) {
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.setAnimation("undefined");
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.animationprocedure = animation;
            }
         }

         if (event.getEntity() instanceof VerticallyCuttedSkeletonEntity syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx) {
            String animation = syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.getSyncedAnimation();
            if (!animation.equals("undefined")) {
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.setAnimation("undefined");
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.animationprocedure = animation;
            }
         }

         if (event.getEntity() instanceof AshesWitherSneerEntity syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx) {
            String animation = syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.getSyncedAnimation();
            if (!animation.equals("undefined")) {
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.setAnimation("undefined");
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.animationprocedure = animation;
            }
         }

         if (event.getEntity() instanceof HandOfAcidEntity syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx) {
            String animation = syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.getSyncedAnimation();
            if (!animation.equals("undefined")) {
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.setAnimation("undefined");
               syncablexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.animationprocedure = animation;
            }
         }
      }
   }
}
