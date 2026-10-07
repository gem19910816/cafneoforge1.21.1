package net.gem19910816.dyairdrop.init;

import net.gem19910816.dyairdrop.entity.AirdropEntity;
import net.gem19910816.dyairdrop.entity.MedicalairdropEntity;
import net.gem19910816.dyairdrop.entity.SmallairdropEntity;
import net.gem19910816.dyairdrop.entity.TransportplaneEntity;
import net.gem19910816.dyairdrop.entity.WeaponairdropEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

@EventBusSubscriber
public class EntityAnimationFactory {
	@SubscribeEvent
	public static void onEntityTick(EntityTickEvent.Post event) {
		if (event.getEntity() instanceof AirdropEntity syncable) {
			String animation = syncable.getSyncedAnimation();
			if (!animation.equals("undefined")) {
				syncable.setAnimation("undefined");
				syncable.animationprocedure = animation;
			}
		}

		if (event.getEntity() instanceof SmallairdropEntity syncable) {
			String animation = syncable.getSyncedAnimation();
			if (!animation.equals("undefined")) {
				syncable.setAnimation("undefined");
				syncable.animationprocedure = animation;
			}
		}

		if (event.getEntity() instanceof WeaponairdropEntity syncable) {
			String animation = syncable.getSyncedAnimation();
			if (!animation.equals("undefined")) {
				syncable.setAnimation("undefined");
				syncable.animationprocedure = animation;
			}
		}

		if (event.getEntity() instanceof MedicalairdropEntity syncable) {
			String animation = syncable.getSyncedAnimation();
			if (!animation.equals("undefined")) {
				syncable.setAnimation("undefined");
				syncable.animationprocedure = animation;
			}
		}

		if (event.getEntity() instanceof TransportplaneEntity syncable) {
			String animation = syncable.getSyncedAnimation();
			if (!animation.equals("undefined")) {
				syncable.setAnimation("undefined");
				syncable.animationprocedure = animation;
			}
		}
	}
}
