package net.mcreator.dyairdrop.init;

import net.mcreator.dyairdrop.entity.AirdropEntity;
import net.mcreator.dyairdrop.entity.MedicalairdropEntity;
import net.mcreator.dyairdrop.entity.SmallairdropEntity;
import net.mcreator.dyairdrop.entity.TransportplaneEntity;
import net.mcreator.dyairdrop.entity.WeaponairdropEntity;
// UNPORTED-IMPORT import net.minecraftforge.event.entity.living.LivingEvent.LivingTickEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

@EventBusSubscriber
public class EntityAnimationFactory {
   public EntityAnimationFactory() {
   }

   @SubscribeEvent
   public static void onEntityTick(EntityTickEvent.Post event) {
      if (event != null && event.getEntity() != null) {
         if (event.getEntity() instanceof AirdropEntity syncable) {
            String animation = syncable.getSyncedAnimation();
            if (!animation.equals("undefined")) {
               syncable.setAnimation("undefined");
               syncable.animationprocedure = animation;
            }
         }

         if (event.getEntity() instanceof SmallairdropEntity syncablex) {
            String animation = syncablex.getSyncedAnimation();
            if (!animation.equals("undefined")) {
               syncablex.setAnimation("undefined");
               syncablex.animationprocedure = animation;
            }
         }

         if (event.getEntity() instanceof WeaponairdropEntity syncablexx) {
            String animation = syncablexx.getSyncedAnimation();
            if (!animation.equals("undefined")) {
               syncablexx.setAnimation("undefined");
               syncablexx.animationprocedure = animation;
            }
         }

         if (event.getEntity() instanceof MedicalairdropEntity syncablexxx) {
            String animation = syncablexxx.getSyncedAnimation();
            if (!animation.equals("undefined")) {
               syncablexxx.setAnimation("undefined");
               syncablexxx.animationprocedure = animation;
            }
         }

         if (event.getEntity() instanceof TransportplaneEntity syncablexxxx) {
            String animation = syncablexxxx.getSyncedAnimation();
            if (!animation.equals("undefined")) {
               syncablexxxx.setAnimation("undefined");
               syncablexxxx.animationprocedure = animation;
            }
         }
      }
   }
}
