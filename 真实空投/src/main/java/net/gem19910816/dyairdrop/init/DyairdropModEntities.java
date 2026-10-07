package net.gem19910816.dyairdrop.init;

import net.gem19910816.dyairdrop.DyairdropMod;
import net.gem19910816.dyairdrop.entity.AirdropEntity;
import net.gem19910816.dyairdrop.entity.FlareEntity;
import net.gem19910816.dyairdrop.entity.MedicalairdropEntity;
import net.gem19910816.dyairdrop.entity.PlaneEntity;
import net.gem19910816.dyairdrop.entity.SmallairdropEntity;
import net.gem19910816.dyairdrop.entity.TransportplaneEntity;
import net.gem19910816.dyairdrop.entity.WeaponairdropEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class DyairdropModEntities {
	public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(Registries.ENTITY_TYPE, DyairdropMod.MODID);
	public static final DeferredHolder<EntityType<?>, EntityType<PlaneEntity>> PLANE = register("plane",
			EntityType.Builder.of(PlaneEntity::new, MobCategory.MONSTER)
					.setShouldReceiveVelocityUpdates(true)
					.setTrackingRange(64)
					.setUpdateInterval(3)
					.sized(10.0F, 3.0F));
	public static final DeferredHolder<EntityType<?>, EntityType<AirdropEntity>> AIRDROP = register("airdrop",
			EntityType.Builder.of(AirdropEntity::new, MobCategory.MONSTER)
					.setShouldReceiveVelocityUpdates(true)
					.setTrackingRange(64)
					.setUpdateInterval(3)
					.sized(1.0F, 1.0F));
	public static final DeferredHolder<EntityType<?>, EntityType<SmallairdropEntity>> SMALLAIRDROP = register("smallairdrop",
			EntityType.Builder.of(SmallairdropEntity::new, MobCategory.CREATURE)
					.setShouldReceiveVelocityUpdates(true)
					.setTrackingRange(64)
					.setUpdateInterval(3)
					.sized(1.0F, 1.0F));
	public static final DeferredHolder<EntityType<?>, EntityType<WeaponairdropEntity>> WEAPONAIRDROP = register("weaponairdrop",
			EntityType.Builder.of(WeaponairdropEntity::new, MobCategory.MONSTER)
					.setShouldReceiveVelocityUpdates(true)
					.setTrackingRange(64)
					.setUpdateInterval(3)
					.sized(1.0F, 1.0F));
	public static final DeferredHolder<EntityType<?>, EntityType<MedicalairdropEntity>> MEDICALAIRDROP = register("medicalairdrop",
			EntityType.Builder.of(MedicalairdropEntity::new, MobCategory.MONSTER)
					.setShouldReceiveVelocityUpdates(true)
					.setTrackingRange(64)
					.setUpdateInterval(3)
					.sized(1.0F, 1.0F));
	public static final DeferredHolder<EntityType<?>, EntityType<TransportplaneEntity>> TRANSPORTPLANE = register("transportplane",
			EntityType.Builder.of(TransportplaneEntity::new, MobCategory.MONSTER)
					.setShouldReceiveVelocityUpdates(true)
					.setTrackingRange(64)
					.setUpdateInterval(3)
					.sized(10.0F, 4.0F));
	public static final DeferredHolder<EntityType<?>, EntityType<FlareEntity>> FLARE = register("projectile_flare",
			EntityType.Builder.<FlareEntity>of(FlareEntity::new, MobCategory.MISC)
					.setShouldReceiveVelocityUpdates(true)
					.setTrackingRange(64)
					.setUpdateInterval(1)
					.sized(0.5F, 0.5F));

	private static <T extends net.minecraft.world.entity.Entity> DeferredHolder<EntityType<?>, EntityType<T>> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
		return REGISTRY.register(registryname, () -> entityTypeBuilder.build(registryname));
	}

	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			PlaneEntity.init();
			AirdropEntity.init();
			SmallairdropEntity.init();
			WeaponairdropEntity.init();
			MedicalairdropEntity.init();
			TransportplaneEntity.init();
		});
	}

	@SubscribeEvent
	public static void registerAttributes(EntityAttributeCreationEvent event) {
		event.put(PLANE.get(), PlaneEntity.createAttributes().build());
		event.put(AIRDROP.get(), AirdropEntity.createAttributes().build());
		event.put(SMALLAIRDROP.get(), SmallairdropEntity.createAttributes().build());
		event.put(WEAPONAIRDROP.get(), WeaponairdropEntity.createAttributes().build());
		event.put(MEDICALAIRDROP.get(), MedicalairdropEntity.createAttributes().build());
		event.put(TRANSPORTPLANE.get(), TransportplaneEntity.createAttributes().build());
	}
}
