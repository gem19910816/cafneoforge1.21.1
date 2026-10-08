package net.mcreator.gore.init;

import java.util.ArrayList;
import java.util.List;
import net.mcreator.gore.entity.AluxinationEntity;
import net.mcreator.gore.entity.AshesBlackHoleEntity;
import net.mcreator.gore.entity.AshesGatewayEntity;
import net.mcreator.gore.entity.AshesTorchRemoverEntity;
import net.mcreator.gore.entity.AshesWitherSneerEntity;
import net.mcreator.gore.entity.BabySpiderEntity;
import net.mcreator.gore.entity.BurningDeathNecesaryEntity;
import net.mcreator.gore.entity.CollapsingSkeletonEntity;
import net.mcreator.gore.entity.CordycepsHeadlessHuskEntity;
import net.mcreator.gore.entity.CordycepsHeadlessZombieEntity;
import net.mcreator.gore.entity.CordycepsHuskEntity;
import net.mcreator.gore.entity.CordycepsZombieEntity;
import net.mcreator.gore.entity.CreeperCorpseEntity;
import net.mcreator.gore.entity.CreeperGeneratorEntity;
import net.mcreator.gore.entity.CreeperGrassGeneratorEntity;
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
import net.mcreator.gore.entity.DismemberedSpiderIIIEntity;
import net.mcreator.gore.entity.ExarrackHydraEntity;
import net.mcreator.gore.entity.ExarrackHydraNaturalSpawnEntity;
import net.mcreator.gore.entity.ExplodedHeadSpiderEntity;
import net.mcreator.gore.entity.FakeExarrackMonsterEntity;
import net.mcreator.gore.entity.FakeExarrackMonsterNaturalSpawnEntity;
import net.mcreator.gore.entity.FireDeathNecesaryEntity;
import net.mcreator.gore.entity.FleshEaterEntity;
import net.mcreator.gore.entity.FleshEaterNaturalSpawnEntity;
import net.mcreator.gore.entity.GrenadeOfGreekFireGreekFireEntity;
import net.mcreator.gore.entity.GrenadeOfGreekFireProjectileEntity;
import net.mcreator.gore.entity.HandOfAcidEntity;
import net.mcreator.gore.entity.HeadlessDrownedEntity;
import net.mcreator.gore.entity.HeadlessHuskCorpseEntity;
import net.mcreator.gore.entity.HeadlessHuskEntity;
import net.mcreator.gore.entity.HeadlessZombieCorpseEntity;
import net.mcreator.gore.entity.HeadlessZombieEntity;
import net.mcreator.gore.entity.HengeyonEntity;
import net.mcreator.gore.entity.HengeyonEntityNaturalSpawnEntity;
import net.mcreator.gore.entity.HorizontallyCuttedHuskEntity;
import net.mcreator.gore.entity.HorizontallyCuttedSpiderEntity;
import net.mcreator.gore.entity.HorizontallycuttedZombieEntity;
import net.mcreator.gore.entity.HuskAboutToDieEntity;
import net.mcreator.gore.entity.HuskCorpseEntity;
import net.mcreator.gore.entity.HuskWithoutLegsAndArmCorpseEntity;
import net.mcreator.gore.entity.InsuredAcidgrenadeEntity;
import net.mcreator.gore.entity.LuxEntity;
import net.mcreator.gore.entity.LuxSycaridaeEntity;
import net.mcreator.gore.entity.MagmaGrenadeExplodeEntity;
import net.mcreator.gore.entity.MagmaGrenadeProjectileEntity;
import net.mcreator.gore.entity.NowindEntity;
import net.mcreator.gore.entity.OldFireDeathNecesaryEntity;
import net.mcreator.gore.entity.ProjectileSquitchgunEntity;
import net.mcreator.gore.entity.ProjectileWitherSkullEntity;
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
import net.mcreator.gore.entity.SkeletonWithoutArmArmProjectileEntity;
import net.mcreator.gore.entity.SkeletonWithoutArmEntity;
import net.mcreator.gore.entity.SkeletonWithoutArmHeadProjectileEntity;
import net.mcreator.gore.entity.SkeletonWithoutLeftArmEntity;
import net.mcreator.gore.entity.SpiderCorpseEntity;
import net.mcreator.gore.entity.SpiralTornadoEntity;
import net.mcreator.gore.entity.TheExarrackMonsterEntity;
import net.mcreator.gore.entity.TheExarrackMonsterNaturalSpawnEntity;
import net.mcreator.gore.entity.TripofobicAcidEntity;
import net.mcreator.gore.entity.VerticalcuttedHuskEntity;
import net.mcreator.gore.entity.VerticalcuttedZombieEntity;
import net.mcreator.gore.entity.VerticallyCuttedSkeletonEntity;
import net.mcreator.gore.entity.VerticallyCuttedSpiderEntity;
import net.mcreator.gore.entity.ZombieAboutToDieEntity;
import net.mcreator.gore.entity.ZombieCorpseEntity;
import net.mcreator.gore.entity.ZombieWithoutLegsAndArmCorpseEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementType;
import net.minecraft.world.entity.EntityType.Builder;
import net.minecraft.world.entity.SpawnPlacements.SpawnPredicate;
import net.minecraft.world.level.levelgen.Heightmap.Types;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.EventBusSubscriber.Bus;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent.Operation;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@EventBusSubscriber(
   bus = Bus.MOD
)
public class GoreEditionModEntities {
   public static final List<GoreEditionModEntities.SpawnEntry<?>> SPAWN_PLACEMENTS = new ArrayList<>();
   public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, "gore_edition");
   public static final DeferredHolder<EntityType<?>, EntityType<HeadlessZombieEntity>> HEADLESS_ZOMBIE = register(
      "headless_zombie",
      Builder.of(HeadlessZombieEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(32)
         .setUpdateInterval(3)
         .sized(0.6F, 1.5F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<HorizontallycuttedZombieEntity>> HORIZONTALLY_CUTTED_ZOMBIE = register(
      "horizontally_cutted_zombie",
      Builder.of(HorizontallycuttedZombieEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(32)
         .setUpdateInterval(3)
         .sized(0.6F, 1.8F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<VerticalcuttedZombieEntity>> VERTICAL_CUTTED_ZOMBIE = register(
      "vertical_cutted_zombie",
      Builder.of(VerticalcuttedZombieEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(32)
         .setUpdateInterval(3)
         .sized(0.6F, 1.8F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<CrushedZombieEntity>> CRUSHED_ZOMBIE = register(
      "crushed_zombie",
      Builder.of(CrushedZombieEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(32)
         .setUpdateInterval(3)
         .sized(0.6F, 1.8F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<SeveredlegsZombieEntity>> SEVEREDLEGS_ZOMBIE = register(
      "severedlegs_zombie",
      Builder.of(SeveredlegsZombieEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(3)
         .sized(0.6F, 0.6F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<DisarmedZombieEntity>> DISARMED_ZOMBIE = register(
      "disarmed_zombie",
      Builder.of(DisarmedZombieEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(3)
         .sized(0.6F, 1.8F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<SeveredLegsAndArmZombieEntity>> SEVERED_LEGS_AND_ARM_ZOMBIE = register(
      "severed_legs_and_arm_zombie",
      Builder.of(SeveredLegsAndArmZombieEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(3)
         .sized(0.6F, 0.6F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<HorizontallyCuttedSpiderEntity>> HORIZONTALLY_CUTTED_SPIDER = register(
      "horizontally_cutted_spider",
      Builder.of(HorizontallyCuttedSpiderEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(32)
         .setUpdateInterval(3)
         .sized(1.4F, 0.9F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<VerticallyCuttedSpiderEntity>> VERTICALLY_CUTTED_SPIDER = register(
      "vertically_cutted_spider",
      Builder.of(VerticallyCuttedSpiderEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(32)
         .setUpdateInterval(3)
         .sized(1.4F, 0.9F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<ExplodedHeadSpiderEntity>> EXPLODED_HEAD_SPIDER = register(
      "exploded_head_spider",
      Builder.of(ExplodedHeadSpiderEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(32)
         .setUpdateInterval(3)
         .sized(1.4F, 0.9F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<FireDeathNecesaryEntity>> FIRE_DEATH_NECESARY = register(
      "fire_death_necesary",
      Builder.of(FireDeathNecesaryEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(3)
         .fireImmune()
         .sized(0.0F, 0.0F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<BabySpiderEntity>> BABY_SPIDER = register(
      "baby_spider",
      Builder.of(BabySpiderEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).sized(1.4F, 0.9F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<BurningDeathNecesaryEntity>> BURNING_DEATH_NECESARY = register(
      "burning_death_necesary",
      Builder.of(BurningDeathNecesaryEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(3)
         .fireImmune()
         .sized(0.0F, 0.0F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<ZombieAboutToDieEntity>> ZOMBIE_ABOUT_TO_DIE = register(
      "zombie_about_to_die",
      Builder.of(ZombieAboutToDieEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(32)
         .setUpdateInterval(3)
         .sized(0.6F, 1.8F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<HeadlessHuskEntity>> HEADLESS_HUSK = register(
      "headless_husk",
      Builder.of(HeadlessHuskEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(32)
         .setUpdateInterval(3)
         .sized(0.6F, 1.6F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<HorizontallyCuttedHuskEntity>> HORIZONTALLY_CUTTED_HUSK = register(
      "horizontally_cutted_husk",
      Builder.of(HorizontallyCuttedHuskEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(32)
         .setUpdateInterval(3)
         .sized(0.6F, 1.9F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<VerticalcuttedHuskEntity>> VERTICALCUTTED_HUSK = register(
      "verticalcutted_husk",
      Builder.of(VerticalcuttedHuskEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(32)
         .setUpdateInterval(3)
         .sized(0.6F, 1.9F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<CrushedHuskEntity>> CRUSHED_HUSK = register(
      "crushed_husk",
      Builder.of(CrushedHuskEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(32).setUpdateInterval(3).sized(0.6F, 1.9F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<SeveredlegsHuskEntity>> SEVEREDLEGS_HUSK = register(
      "severedlegs_husk",
      Builder.of(SeveredlegsHuskEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(3)
         .sized(0.6F, 0.6F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<SeveredLegsAndArmHuskEntity>> SEVERED_LEGS_AND_ARM_HUSK = register(
      "severed_legs_and_arm_husk",
      Builder.of(SeveredLegsAndArmHuskEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(3)
         .sized(0.6F, 0.6F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<HuskAboutToDieEntity>> HUSK_ABOUT_TO_DIE = register(
      "husk_about_to_die",
      Builder.of(HuskAboutToDieEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(32)
         .setUpdateInterval(3)
         .sized(0.6F, 1.9F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<DisarmedHuskEntity>> DISARMED_HUSK = register(
      "disarmed_husk",
      Builder.of(DisarmedHuskEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(3)
         .sized(0.6F, 1.9F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<HeadlessDrownedEntity>> HEADLESS_DROWNED = register(
      "headless_drowned",
      Builder.of(HeadlessDrownedEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(32)
         .setUpdateInterval(3)
         .sized(0.6F, 1.5F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<DismemberedSpiderIEntity>> DISMEMBERED_SPIDER_I = register(
      "dismembered_spider_i",
      Builder.of(DismemberedSpiderIEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(3)
         .sized(1.4F, 0.9F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<ZombieCorpseEntity>> ZOMBIE_CORPSE = register(
      "zombie_corpse",
      Builder.of(ZombieCorpseEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(3)
         .sized(1.0F, 0.6F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<ZombieWithoutLegsAndArmCorpseEntity>> ZOMBIE_WITHOUT_LEGS_AND_ARM_CORPSE = register(
      "zombie_without_legs_and_arm_corpse",
      Builder.of(ZombieWithoutLegsAndArmCorpseEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(3)
         .sized(1.0F, 0.6F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<SeveredLegsZombieCorpseEntity>> SEVERED_LEGS_ZOMBIE_CORPSE = register(
      "severed_legs_zombie_corpse",
      Builder.of(SeveredLegsZombieCorpseEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(3)
         .sized(1.0F, 0.6F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<CreeperCorpseEntity>> CREEPER_CORPSE = register(
      "creeper_corpse",
      Builder.of(CreeperCorpseEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(3)
         .sized(1.0F, 0.6F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<DismemberedSpiderIIEntity>> DISMEMBERED_SPIDER_II = register(
      "dismembered_spider_ii",
      Builder.of(DismemberedSpiderIIEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(3)
         .sized(1.4F, 0.9F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<OldFireDeathNecesaryEntity>> OLD_FIRE_DEATH_NECESARY = register(
      "old_fire_death_necesary",
      Builder.of(OldFireDeathNecesaryEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(3)
         .fireImmune()
         .sized(0.0F, 0.0F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<FleshEaterEntity>> EXARRACK_MONSTER = register(
      "exarrack_monster",
      Builder.of(FleshEaterEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(3)
         .fireImmune()
         .sized(1.5F, 1.5F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<DisarmedZombieCorpseEntity>> DISARMED_ZOMBIE_CORPSE = register(
      "disarmed_zombie_corpse",
      Builder.of(DisarmedZombieCorpseEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(3)
         .sized(1.0F, 0.6F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<InsuredAcidgrenadeEntity>> GRENADE_OF_ACID = register(
      "grenade_of_acid",
      Builder.<InsuredAcidgrenadeEntity>of((_t, _l) -> new InsuredAcidgrenadeEntity(_t, _l), MobCategory.MISC)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(1)
         .sized(0.5F, 0.5F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<HeadlessZombieCorpseEntity>> HEADLESS_ZOMBIE_CORPSE = register(
      "headless_zombie_corpse",
      Builder.of(HeadlessZombieCorpseEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(3)
         .sized(1.0F, 0.6F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<HuskCorpseEntity>> HUSK_CORPSE = register(
      "husk_corpse",
      Builder.of(HuskCorpseEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).sized(1.0F, 0.6F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<HuskWithoutLegsAndArmCorpseEntity>> HUSK_WITHOUT_LEGS_AND_ARM_CORPSE = register(
      "husk_without_legs_and_arm_corpse",
      Builder.of(HuskWithoutLegsAndArmCorpseEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(3)
         .sized(1.0F, 0.6F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<SeveredLegsHuskCorpseEntity>> SEVERED_LEGS_HUSK_CORPSE = register(
      "severed_legs_husk_corpse",
      Builder.of(SeveredLegsHuskCorpseEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(3)
         .sized(1.0F, 0.6F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<DisarmedHuskCorpseEntity>> DISARMED_HUSK_CORPSE = register(
      "disarmed_husk_corpse",
      Builder.of(DisarmedHuskCorpseEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(3)
         .sized(1.0F, 0.6F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<HeadlessHuskCorpseEntity>> HEADLESS_HUSK_CORPSE = register(
      "headless_husk_corpse",
      Builder.of(HeadlessHuskCorpseEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(3)
         .sized(1.0F, 0.6F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<DismemberedSpiderIIIEntity>> DISMEMBERED_SPIDER_III = register(
      "dismembered_spider_iii",
      Builder.of(DismemberedSpiderIIIEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(3)
         .sized(1.4F, 0.9F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<SkeletonHeadEntity>> SKELETON_HEAD = register(
      "skeleton_head",
      Builder.of(SkeletonHeadEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(3)
         .sized(0.5F, 0.5F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<SkeletonBodyPartIEntity>> SKELETON_RIBS = register(
      "skeleton_ribs",
      Builder.of(SkeletonBodyPartIEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(3)
         .sized(0.5F, 0.4F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<SkeletonPelvisEntity>> SKELETON_PELVIS = register(
      "skeleton_pelvis",
      Builder.of(SkeletonPelvisEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(3)
         .sized(0.5F, 0.4F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<SkeletonBackboneEntity>> SKELETON_SPINAL_COLUMN = register(
      "skeleton_spinal_column",
      Builder.of(SkeletonBackboneEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(3)
         .sized(0.5F, 0.63F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<SkeletonRightArmEntity>> SKELETON_RIGHT_ARM = register(
      "skeleton_right_arm",
      Builder.of(SkeletonRightArmEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(3)
         .sized(0.5F, 0.4F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<SkeletonLeftArmEntity>> SKELETON_LEFT_ARM = register(
      "skeleton_left_arm",
      Builder.of(SkeletonLeftArmEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(3)
         .sized(0.5F, 0.4F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<SkeletonLeftLegEntity>> SKELETON_LEFT_LEG = register(
      "skeleton_left_leg",
      Builder.of(SkeletonLeftLegEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(3)
         .sized(0.5F, 0.4F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<SkeletonRightLegEntity>> SKELETON_RIGHT_LEG = register(
      "skeleton_right_leg",
      Builder.of(SkeletonRightLegEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(3)
         .sized(0.5F, 0.4F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<CreeperGeneratorEntity>> CREEPER_GENERATOR = register(
      "creeper_generator",
      Builder.of(CreeperGeneratorEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(3)
         .fireImmune()
         .sized(0.0F, 0.0F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<CreeperGrassGeneratorEntity>> CREEPER_GRASS_GENERATOR = register(
      "creeper_grass_generator",
      Builder.of(CreeperGrassGeneratorEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(3)
         .fireImmune()
         .sized(0.0F, 0.0F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<SkeletonWithoutArmEntity>> DISARMED_SKELETON_RIGHT_ARM = register(
      "disarmed_skeleton_right_arm",
      Builder.of(SkeletonWithoutArmEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(3)
         .sized(0.6F, 1.4F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<SkeletonWithoutArmHeadProjectileEntity>> SKELETON_WITHOUT_ARM_HEAD_PROJECTILE = register(
      "skeleton_without_arm_head_projectile",
      Builder.<SkeletonWithoutArmHeadProjectileEntity>of((_t, _l) -> new SkeletonWithoutArmHeadProjectileEntity(_t, _l), MobCategory.MISC)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(1)
         .sized(0.5F, 0.5F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<SkeletonWithoutArmArmProjectileEntity>> SKELETON_WITHOUT_ARM_ARM_PROJECTILE = register(
      "skeleton_without_arm_arm_projectile",
      Builder.<SkeletonWithoutArmArmProjectileEntity>of((_t, _l) -> new SkeletonWithoutArmArmProjectileEntity(_t, _l), MobCategory.MISC)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(1)
         .sized(0.5F, 0.5F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<SkeletonWithoutLeftArmEntity>> DISARMED_SKELETON_LEFT_ARM = register(
      "disarmed_skeleton_left_arm",
      Builder.of(SkeletonWithoutLeftArmEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(3)
         .sized(0.6F, 1.4F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<SkeletonCorpseEntity>> SKELETON_CORPSE = register(
      "skeleton_corpse",
      Builder.of(SkeletonCorpseEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(3)
         .sized(1.0F, 0.6F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<SkeletonCorpseIIEntity>> SKELETON_CORPSE_III = register(
      "skeleton_corpse_iii",
      Builder.of(SkeletonCorpseIIEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(3)
         .sized(1.0F, 0.6F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<SkeletonCorpseWithoutRightArmEntity>> SKELETON_CORPSE_II = register(
      "skeleton_corpse_ii",
      Builder.of(SkeletonCorpseWithoutRightArmEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(3)
         .sized(1.0F, 0.6F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<GrenadeOfGreekFireProjectileEntity>> GRENADE_OF_GREEK_FIRE_PROJECTILE = register(
      "grenade_of_greek_fire_projectile",
      Builder.<GrenadeOfGreekFireProjectileEntity>of((_t, _l) -> new GrenadeOfGreekFireProjectileEntity(_t, _l), MobCategory.MISC)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(1)
         .sized(0.5F, 0.5F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<GrenadeOfGreekFireGreekFireEntity>> GRENADE_OF_GREEK_FIRE_GREEK_FIRE = register(
      "grenade_of_greek_fire_greek_fire",
      Builder.<GrenadeOfGreekFireGreekFireEntity>of((_t, _l) -> new GrenadeOfGreekFireGreekFireEntity(_t, _l), MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(0)
         .setUpdateInterval(3)
         .fireImmune()
         .sized(0.0F, 0.0F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<DismemberedSpiderICorpseEntity>> DISMEMBERED_SPIDER_I_CORPSE = register(
      "dismembered_spider_i_corpse",
      Builder.of(DismemberedSpiderICorpseEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(3)
         .sized(1.4F, 0.9F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<LuxSycaridaeEntity>> LUX_SYCARIDE = register(
      "lux_sycaride",
      Builder.of(LuxSycaridaeEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(3)
         .fireImmune()
         .sized(1.0F, 1.0F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<AluxinationEntity>> ALUXINATION = register(
      "aluxination",
      Builder.of(AluxinationEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(3)
         .fireImmune()
         .sized(0.3F, 0.3F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<DismemberedSpiderIICorpseEntity>> DISMEMBERED_SPIDER_II_CORPSE = register(
      "dismembered_spider_ii_corpse",
      Builder.of(DismemberedSpiderIICorpseEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(3)
         .sized(1.4F, 0.9F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<DismemberedSpiderIIICorpseEntity>> DISMEMBERED_SPIDER_III_CORPSE = register(
      "dismembered_spider_iii_corpse",
      Builder.of(DismemberedSpiderIIICorpseEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(3)
         .sized(1.4F, 0.9F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<CollapsingSkeletonEntity>> COLLAPSING_SKELETON = register(
      "collapsing_skeleton",
      Builder.of(CollapsingSkeletonEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(3)
         .sized(0.6F, 1.4F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<NowindEntity>> NOWIND = register(
      "nowind",
      Builder.of(NowindEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(16)
         .setUpdateInterval(3)
         .fireImmune()
         .sized(0.77F, 0.6F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<HengeyonEntity>> HENGEYON = register(
      "hengeyon",
      Builder.of(HengeyonEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(32)
         .setUpdateInterval(3)
         .fireImmune()
         .sized(15.0F, 15.0F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<TripofobicAcidEntity>> ACID_ASHES = register(
      "acid_ashes",
      Builder.<TripofobicAcidEntity>of((_t, _l) -> new TripofobicAcidEntity(_t, _l), MobCategory.MISC)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(1)
         .sized(0.5F, 0.5F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<HengeyonEntityNaturalSpawnEntity>> NS_HENGEYON = register(
      "ns_hengeyon",
      Builder.of(HengeyonEntityNaturalSpawnEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(3)
         .fireImmune()
         .sized(3.0F, 3.0F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<FleshEaterNaturalSpawnEntity>> NS_EXARACK_RUNNER = register(
      "ns_exarack_runner",
      Builder.of(FleshEaterNaturalSpawnEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(3)
         .fireImmune()
         .sized(1.0F, 1.0F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<FakeExarrackMonsterEntity>> EXARRACK_JUMPSCARE = register(
      "exarrack_jumpscare",
      Builder.of(FakeExarrackMonsterEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(3)
         .fireImmune()
         .sized(1.5F, 2.3F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<FakeExarrackMonsterNaturalSpawnEntity>> NS_EXARACK_MONSTER = register(
      "ns_exarack_monster",
      Builder.of(FakeExarrackMonsterNaturalSpawnEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(3)
         .fireImmune()
         .sized(1.0F, 1.0F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<CordycepsZombieEntity>> CORDYCEPS_ZOMBIE = register(
      "cordyceps_zombie",
      Builder.of(CordycepsZombieEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(3)
         .sized(0.6F, 1.8F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<SeveredLegsCordycepsZombieEntity>> SEVERED_LEGS_CORDYCEPS_ZOMBIE = register(
      "severed_legs_cordyceps_zombie",
      Builder.of(SeveredLegsCordycepsZombieEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(3)
         .sized(0.6F, 0.6F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<CordycepsHeadlessZombieEntity>> ZOMBIE_CORDYCEPS_HEADLESS = register(
      "zombie_cordyceps_headless",
      Builder.of(CordycepsHeadlessZombieEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(3)
         .sized(0.6F, 1.8F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<SeveredLegsAndOneArmCordycepsZombieEntity>> SEVERED_LEGS_AND_ONE_ARM_CORDYCEPS_ZOMBIE = register(
      "severed_legs_and_one_arm_cordyceps_zombie",
      Builder.of(SeveredLegsAndOneArmCordycepsZombieEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(3)
         .sized(0.6F, 0.6F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<DisarmedCordycepsZombieEntity>> DISARMED_CORDYCEPS_ZOMBIE = register(
      "disarmed_cordyceps_zombie",
      Builder.of(DisarmedCordycepsZombieEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(3)
         .sized(0.6F, 1.8F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<CordycepsHuskEntity>> CORDYCEPS_HUSK = register(
      "cordyceps_husk",
      Builder.of(CordycepsHuskEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(3)
         .sized(0.6F, 1.8F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<SeveredLegsCordycepsHuskEntity>> SEVERED_LEGS_CORDYCEPS_HUSK = register(
      "severed_legs_cordyceps_husk",
      Builder.of(SeveredLegsCordycepsHuskEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(3)
         .sized(0.6F, 0.6F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<CordycepsHeadlessHuskEntity>> CORDYCEPS_HEADLESS_HUSK = register(
      "cordyceps_headless_husk",
      Builder.of(CordycepsHeadlessHuskEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(3)
         .sized(0.6F, 1.8F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<SeveredLegsAndOneArmCordycepsHuskEntity>> SEVERED_LEGS_AND_ONE_ARM_CORDYCEPS_HUSK = register(
      "severed_legs_and_one_arm_cordyceps_husk",
      Builder.of(SeveredLegsAndOneArmCordycepsHuskEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(3)
         .sized(0.6F, 0.6F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<DisarmedCordycepsHuskEntity>> DISARMED_CORDYCEPS_HUSK = register(
      "disarmed_cordyceps_husk",
      Builder.of(DisarmedCordycepsHuskEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(3)
         .sized(0.6F, 1.8F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<TheExarrackMonsterEntity>> EXARRACK_DEMON = register(
      "exarrack_demon",
      Builder.of(TheExarrackMonsterEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(3)
         .fireImmune()
         .sized(3.15F, 2.4F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<TheExarrackMonsterNaturalSpawnEntity>> NS_EXARACK_DEMON = register(
      "ns_exarack_demon",
      Builder.of(TheExarrackMonsterNaturalSpawnEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(3)
         .fireImmune()
         .sized(0.6F, 1.8F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<SpiderCorpseEntity>> SPIDER_CORPSE = register(
      "spider_corpse",
      Builder.of(SpiderCorpseEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(3)
         .sized(1.4F, 0.9F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<AshesTorchRemoverEntity>> ASHES_TORCH_REMOVER = register(
      "ashes_torch_remover",
      Builder.of(AshesTorchRemoverEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(3)
         .fireImmune()
         .sized(0.0F, 0.0F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<DeformityForAshesEntity>> THE_HELL_ZONE_THING = register(
      "the_hell_zone_thing",
      Builder.of(DeformityForAshesEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(128)
         .setUpdateInterval(3)
         .sized(3.0F, 2.0F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<MagmaGrenadeProjectileEntity>> MAGMA_GRENADE_PROJECTILE = register(
      "magma_grenade_projectile",
      Builder.<MagmaGrenadeProjectileEntity>of((_t, _l) -> new MagmaGrenadeProjectileEntity(_t, _l), MobCategory.MISC)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(1)
         .sized(0.5F, 0.5F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<MagmaGrenadeExplodeEntity>> MAGMA_GRENADE_EXPLODE = register(
      "magma_grenade_explode",
      Builder.<MagmaGrenadeExplodeEntity>of((_t, _l) -> new MagmaGrenadeExplodeEntity(_t, _l), MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(3)
         .fireImmune()
         .sized(0.0F, 0.0F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<ProjectileWitherSkullEntity>> PROJECTILE_WITHER_SKULL = register(
      "projectile_wither_skull",
      Builder.<ProjectileWitherSkullEntity>of((_t, _l) -> new ProjectileWitherSkullEntity(_t, _l), MobCategory.MISC)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(1)
         .sized(0.5F, 0.5F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<CrushingSkeletonEntity>> CRUSHING_SKELETON = register(
      "crushing_skeleton",
      Builder.of(CrushingSkeletonEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(3)
         .sized(0.6F, 1.4F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<SeveredLegsSkeletonEntity>> SEVERED_LEGS_SKELETON = register(
      "severed_legs_skeleton",
      Builder.of(SeveredLegsSkeletonEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(3)
         .sized(0.6F, 0.6F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<LuxEntity>> LUX = register(
      "lux",
      Builder.of(LuxEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(3)
         .fireImmune()
         .sized(0.3F, 0.3F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<AshesGatewayEntity>> ASHES_GATEWAY = register(
      "ashes_gateway",
      Builder.of(AshesGatewayEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(320)
         .setUpdateInterval(3)
         .fireImmune()
         .sized(0.25F, 0.25F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<ExarrackHydraEntity>> EXARRACK_HYDRA = register(
      "exarrack_hydra",
      Builder.of(ExarrackHydraEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(3)
         .fireImmune()
         .sized(1.5F, 2.0F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<ExarrackHydraNaturalSpawnEntity>> EXARRACK_HYDRA_NATURAL_SPAWN = register(
      "exarrack_hydra_natural_spawn",
      Builder.of(ExarrackHydraNaturalSpawnEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(3)
         .fireImmune()
         .sized(0.6F, 1.8F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<SpiralTornadoEntity>> SPIRAL_TORNADO = register(
      "spiral_tornado",
      Builder.of(SpiralTornadoEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(3)
         .sized(0.85F, 1.0F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<VerticallyCuttedSkeletonEntity>> VERTICALLY_CUTTED_SKELETON = register(
      "vertically_cutted_skeleton",
      Builder.of(VerticallyCuttedSkeletonEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(32)
         .setUpdateInterval(3)
         .sized(0.6F, 1.8F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<AshesWitherSneerEntity>> ASHES_WITHER_SNEER = register(
      "ashes_wither_sneer",
      Builder.of(AshesWitherSneerEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(86)
         .setUpdateInterval(3)
         .fireImmune()
         .sized(1.0F, 3.0F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<HandOfAcidEntity>> HAND_OF_ACID = register(
      "hand_of_acid",
      Builder.of(HandOfAcidEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(3)
         .fireImmune()
         .sized(1.4F, 1.4F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<ProjectileSquitchgunEntity>> PROJECTILE_SQUISHED = register(
      "projectile_squished",
      Builder.<ProjectileSquitchgunEntity>of((_t, _l) -> new ProjectileSquitchgunEntity(_t, _l), MobCategory.MISC)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(1)
         .sized(0.5F, 0.5F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<AshesBlackHoleEntity>> ASHES_BLACK_HOLE = register(
      "ashes_black_hole",
      Builder.<AshesBlackHoleEntity>of((_t, _l) -> new AshesBlackHoleEntity(_t, _l), MobCategory.MISC)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(1)
         .sized(0.5F, 0.5F)
   );

   @SubscribeEvent
   public static void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {
      for (GoreEditionModEntities.SpawnEntry<?> e : SPAWN_PLACEMENTS) {
         registerSpawnPlacement(event, e);
      }
   }

   private static <T extends Entity> void registerSpawnPlacement(RegisterSpawnPlacementsEvent event, GoreEditionModEntities.SpawnEntry<T> e) {
      event.register(e.type(), e.placement(), e.heightmap(), e.predicate(), Operation.REPLACE);
   }

   private static <T extends Entity> DeferredHolder<EntityType<?>, EntityType<T>> register(String registryname, Builder<T> entityTypeBuilder) {
      return REGISTRY.register(registryname, () -> entityTypeBuilder.build(registryname));
   }

   @SubscribeEvent
   public static void init(FMLCommonSetupEvent event) {
      event.enqueueWork(() -> {
         HeadlessZombieEntity.init();
         HorizontallycuttedZombieEntity.init();
         VerticalcuttedZombieEntity.init();
         CrushedZombieEntity.init();
         SeveredlegsZombieEntity.init();
         DisarmedZombieEntity.init();
         SeveredLegsAndArmZombieEntity.init();
         HorizontallyCuttedSpiderEntity.init();
         VerticallyCuttedSpiderEntity.init();
         ExplodedHeadSpiderEntity.init();
         FireDeathNecesaryEntity.init();
         BabySpiderEntity.init();
         BurningDeathNecesaryEntity.init();
         ZombieAboutToDieEntity.init();
         HeadlessHuskEntity.init();
         HorizontallyCuttedHuskEntity.init();
         VerticalcuttedHuskEntity.init();
         CrushedHuskEntity.init();
         SeveredlegsHuskEntity.init();
         SeveredLegsAndArmHuskEntity.init();
         HuskAboutToDieEntity.init();
         DisarmedHuskEntity.init();
         HeadlessDrownedEntity.init();
         DismemberedSpiderIEntity.init();
         ZombieCorpseEntity.init();
         ZombieWithoutLegsAndArmCorpseEntity.init();
         SeveredLegsZombieCorpseEntity.init();
         CreeperCorpseEntity.init();
         DismemberedSpiderIIEntity.init();
         OldFireDeathNecesaryEntity.init();
         FleshEaterEntity.init();
         DisarmedZombieCorpseEntity.init();
         HeadlessZombieCorpseEntity.init();
         HuskCorpseEntity.init();
         HuskWithoutLegsAndArmCorpseEntity.init();
         SeveredLegsHuskCorpseEntity.init();
         DisarmedHuskCorpseEntity.init();
         HeadlessHuskCorpseEntity.init();
         DismemberedSpiderIIIEntity.init();
         SkeletonHeadEntity.init();
         SkeletonBodyPartIEntity.init();
         SkeletonPelvisEntity.init();
         SkeletonBackboneEntity.init();
         SkeletonRightArmEntity.init();
         SkeletonLeftArmEntity.init();
         SkeletonLeftLegEntity.init();
         SkeletonRightLegEntity.init();
         CreeperGeneratorEntity.init();
         CreeperGrassGeneratorEntity.init();
         SkeletonWithoutArmEntity.init();
         SkeletonWithoutLeftArmEntity.init();
         SkeletonCorpseEntity.init();
         SkeletonCorpseIIEntity.init();
         SkeletonCorpseWithoutRightArmEntity.init();
         GrenadeOfGreekFireGreekFireEntity.init();
         DismemberedSpiderICorpseEntity.init();
         LuxSycaridaeEntity.init();
         AluxinationEntity.init();
         DismemberedSpiderIICorpseEntity.init();
         DismemberedSpiderIIICorpseEntity.init();
         CollapsingSkeletonEntity.init();
         NowindEntity.init();
         HengeyonEntity.init();
         HengeyonEntityNaturalSpawnEntity.init();
         FleshEaterNaturalSpawnEntity.init();
         FakeExarrackMonsterEntity.init();
         FakeExarrackMonsterNaturalSpawnEntity.init();
         CordycepsZombieEntity.init();
         SeveredLegsCordycepsZombieEntity.init();
         CordycepsHeadlessZombieEntity.init();
         SeveredLegsAndOneArmCordycepsZombieEntity.init();
         DisarmedCordycepsZombieEntity.init();
         CordycepsHuskEntity.init();
         SeveredLegsCordycepsHuskEntity.init();
         CordycepsHeadlessHuskEntity.init();
         SeveredLegsAndOneArmCordycepsHuskEntity.init();
         DisarmedCordycepsHuskEntity.init();
         TheExarrackMonsterEntity.init();
         TheExarrackMonsterNaturalSpawnEntity.init();
         SpiderCorpseEntity.init();
         AshesTorchRemoverEntity.init();
         DeformityForAshesEntity.init();
         MagmaGrenadeExplodeEntity.init();
         CrushingSkeletonEntity.init();
         SeveredLegsSkeletonEntity.init();
         LuxEntity.init();
         AshesGatewayEntity.init();
         ExarrackHydraEntity.init();
         ExarrackHydraNaturalSpawnEntity.init();
         SpiralTornadoEntity.init();
         VerticallyCuttedSkeletonEntity.init();
         AshesWitherSneerEntity.init();
         HandOfAcidEntity.init();
      });
   }

   @SubscribeEvent
   public static void registerAttributes(EntityAttributeCreationEvent event) {
      event.put((EntityType)HEADLESS_ZOMBIE.get(), HeadlessZombieEntity.createAttributes().build());
      event.put((EntityType)HORIZONTALLY_CUTTED_ZOMBIE.get(), HorizontallycuttedZombieEntity.createAttributes().build());
      event.put((EntityType)VERTICAL_CUTTED_ZOMBIE.get(), VerticalcuttedZombieEntity.createAttributes().build());
      event.put((EntityType)CRUSHED_ZOMBIE.get(), CrushedZombieEntity.createAttributes().build());
      event.put((EntityType)SEVEREDLEGS_ZOMBIE.get(), SeveredlegsZombieEntity.createAttributes().build());
      event.put((EntityType)DISARMED_ZOMBIE.get(), DisarmedZombieEntity.createAttributes().build());
      event.put((EntityType)SEVERED_LEGS_AND_ARM_ZOMBIE.get(), SeveredLegsAndArmZombieEntity.createAttributes().build());
      event.put((EntityType)HORIZONTALLY_CUTTED_SPIDER.get(), HorizontallyCuttedSpiderEntity.createAttributes().build());
      event.put((EntityType)VERTICALLY_CUTTED_SPIDER.get(), VerticallyCuttedSpiderEntity.createAttributes().build());
      event.put((EntityType)EXPLODED_HEAD_SPIDER.get(), ExplodedHeadSpiderEntity.createAttributes().build());
      event.put((EntityType)FIRE_DEATH_NECESARY.get(), FireDeathNecesaryEntity.createAttributes().build());
      event.put((EntityType)BABY_SPIDER.get(), BabySpiderEntity.createAttributes().build());
      event.put((EntityType)BURNING_DEATH_NECESARY.get(), BurningDeathNecesaryEntity.createAttributes().build());
      event.put((EntityType)ZOMBIE_ABOUT_TO_DIE.get(), ZombieAboutToDieEntity.createAttributes().build());
      event.put((EntityType)HEADLESS_HUSK.get(), HeadlessHuskEntity.createAttributes().build());
      event.put((EntityType)HORIZONTALLY_CUTTED_HUSK.get(), HorizontallyCuttedHuskEntity.createAttributes().build());
      event.put((EntityType)VERTICALCUTTED_HUSK.get(), VerticalcuttedHuskEntity.createAttributes().build());
      event.put((EntityType)CRUSHED_HUSK.get(), CrushedHuskEntity.createAttributes().build());
      event.put((EntityType)SEVEREDLEGS_HUSK.get(), SeveredlegsHuskEntity.createAttributes().build());
      event.put((EntityType)SEVERED_LEGS_AND_ARM_HUSK.get(), SeveredLegsAndArmHuskEntity.createAttributes().build());
      event.put((EntityType)HUSK_ABOUT_TO_DIE.get(), HuskAboutToDieEntity.createAttributes().build());
      event.put((EntityType)DISARMED_HUSK.get(), DisarmedHuskEntity.createAttributes().build());
      event.put((EntityType)HEADLESS_DROWNED.get(), HeadlessDrownedEntity.createAttributes().build());
      event.put((EntityType)DISMEMBERED_SPIDER_I.get(), DismemberedSpiderIEntity.createAttributes().build());
      event.put((EntityType)ZOMBIE_CORPSE.get(), ZombieCorpseEntity.createAttributes().build());
      event.put((EntityType)ZOMBIE_WITHOUT_LEGS_AND_ARM_CORPSE.get(), ZombieWithoutLegsAndArmCorpseEntity.createAttributes().build());
      event.put((EntityType)SEVERED_LEGS_ZOMBIE_CORPSE.get(), SeveredLegsZombieCorpseEntity.createAttributes().build());
      event.put((EntityType)CREEPER_CORPSE.get(), CreeperCorpseEntity.createAttributes().build());
      event.put((EntityType)DISMEMBERED_SPIDER_II.get(), DismemberedSpiderIIEntity.createAttributes().build());
      event.put((EntityType)OLD_FIRE_DEATH_NECESARY.get(), OldFireDeathNecesaryEntity.createAttributes().build());
      event.put((EntityType)EXARRACK_MONSTER.get(), FleshEaterEntity.createAttributes().build());
      event.put((EntityType)DISARMED_ZOMBIE_CORPSE.get(), DisarmedZombieCorpseEntity.createAttributes().build());
      event.put((EntityType)HEADLESS_ZOMBIE_CORPSE.get(), HeadlessZombieCorpseEntity.createAttributes().build());
      event.put((EntityType)HUSK_CORPSE.get(), HuskCorpseEntity.createAttributes().build());
      event.put((EntityType)HUSK_WITHOUT_LEGS_AND_ARM_CORPSE.get(), HuskWithoutLegsAndArmCorpseEntity.createAttributes().build());
      event.put((EntityType)SEVERED_LEGS_HUSK_CORPSE.get(), SeveredLegsHuskCorpseEntity.createAttributes().build());
      event.put((EntityType)DISARMED_HUSK_CORPSE.get(), DisarmedHuskCorpseEntity.createAttributes().build());
      event.put((EntityType)HEADLESS_HUSK_CORPSE.get(), HeadlessHuskCorpseEntity.createAttributes().build());
      event.put((EntityType)DISMEMBERED_SPIDER_III.get(), DismemberedSpiderIIIEntity.createAttributes().build());
      event.put((EntityType)SKELETON_HEAD.get(), SkeletonHeadEntity.createAttributes().build());
      event.put((EntityType)SKELETON_RIBS.get(), SkeletonBodyPartIEntity.createAttributes().build());
      event.put((EntityType)SKELETON_PELVIS.get(), SkeletonPelvisEntity.createAttributes().build());
      event.put((EntityType)SKELETON_SPINAL_COLUMN.get(), SkeletonBackboneEntity.createAttributes().build());
      event.put((EntityType)SKELETON_RIGHT_ARM.get(), SkeletonRightArmEntity.createAttributes().build());
      event.put((EntityType)SKELETON_LEFT_ARM.get(), SkeletonLeftArmEntity.createAttributes().build());
      event.put((EntityType)SKELETON_LEFT_LEG.get(), SkeletonLeftLegEntity.createAttributes().build());
      event.put((EntityType)SKELETON_RIGHT_LEG.get(), SkeletonRightLegEntity.createAttributes().build());
      event.put((EntityType)CREEPER_GENERATOR.get(), CreeperGeneratorEntity.createAttributes().build());
      event.put((EntityType)CREEPER_GRASS_GENERATOR.get(), CreeperGrassGeneratorEntity.createAttributes().build());
      event.put((EntityType)DISARMED_SKELETON_RIGHT_ARM.get(), SkeletonWithoutArmEntity.createAttributes().build());
      event.put((EntityType)DISARMED_SKELETON_LEFT_ARM.get(), SkeletonWithoutLeftArmEntity.createAttributes().build());
      event.put((EntityType)SKELETON_CORPSE.get(), SkeletonCorpseEntity.createAttributes().build());
      event.put((EntityType)SKELETON_CORPSE_III.get(), SkeletonCorpseIIEntity.createAttributes().build());
      event.put((EntityType)SKELETON_CORPSE_II.get(), SkeletonCorpseWithoutRightArmEntity.createAttributes().build());
      event.put((EntityType)GRENADE_OF_GREEK_FIRE_GREEK_FIRE.get(), GrenadeOfGreekFireGreekFireEntity.createAttributes().build());
      event.put((EntityType)DISMEMBERED_SPIDER_I_CORPSE.get(), DismemberedSpiderICorpseEntity.createAttributes().build());
      event.put((EntityType)LUX_SYCARIDE.get(), LuxSycaridaeEntity.createAttributes().build());
      event.put((EntityType)ALUXINATION.get(), AluxinationEntity.createAttributes().build());
      event.put((EntityType)DISMEMBERED_SPIDER_II_CORPSE.get(), DismemberedSpiderIICorpseEntity.createAttributes().build());
      event.put((EntityType)DISMEMBERED_SPIDER_III_CORPSE.get(), DismemberedSpiderIIICorpseEntity.createAttributes().build());
      event.put((EntityType)COLLAPSING_SKELETON.get(), CollapsingSkeletonEntity.createAttributes().build());
      event.put((EntityType)NOWIND.get(), NowindEntity.createAttributes().build());
      event.put((EntityType)HENGEYON.get(), HengeyonEntity.createAttributes().build());
      event.put((EntityType)NS_HENGEYON.get(), HengeyonEntityNaturalSpawnEntity.createAttributes().build());
      event.put((EntityType)NS_EXARACK_RUNNER.get(), FleshEaterNaturalSpawnEntity.createAttributes().build());
      event.put((EntityType)EXARRACK_JUMPSCARE.get(), FakeExarrackMonsterEntity.createAttributes().build());
      event.put((EntityType)NS_EXARACK_MONSTER.get(), FakeExarrackMonsterNaturalSpawnEntity.createAttributes().build());
      event.put((EntityType)CORDYCEPS_ZOMBIE.get(), CordycepsZombieEntity.createAttributes().build());
      event.put((EntityType)SEVERED_LEGS_CORDYCEPS_ZOMBIE.get(), SeveredLegsCordycepsZombieEntity.createAttributes().build());
      event.put((EntityType)ZOMBIE_CORDYCEPS_HEADLESS.get(), CordycepsHeadlessZombieEntity.createAttributes().build());
      event.put((EntityType)SEVERED_LEGS_AND_ONE_ARM_CORDYCEPS_ZOMBIE.get(), SeveredLegsAndOneArmCordycepsZombieEntity.createAttributes().build());
      event.put((EntityType)DISARMED_CORDYCEPS_ZOMBIE.get(), DisarmedCordycepsZombieEntity.createAttributes().build());
      event.put((EntityType)CORDYCEPS_HUSK.get(), CordycepsHuskEntity.createAttributes().build());
      event.put((EntityType)SEVERED_LEGS_CORDYCEPS_HUSK.get(), SeveredLegsCordycepsHuskEntity.createAttributes().build());
      event.put((EntityType)CORDYCEPS_HEADLESS_HUSK.get(), CordycepsHeadlessHuskEntity.createAttributes().build());
      event.put((EntityType)SEVERED_LEGS_AND_ONE_ARM_CORDYCEPS_HUSK.get(), SeveredLegsAndOneArmCordycepsHuskEntity.createAttributes().build());
      event.put((EntityType)DISARMED_CORDYCEPS_HUSK.get(), DisarmedCordycepsHuskEntity.createAttributes().build());
      event.put((EntityType)EXARRACK_DEMON.get(), TheExarrackMonsterEntity.createAttributes().build());
      event.put((EntityType)NS_EXARACK_DEMON.get(), TheExarrackMonsterNaturalSpawnEntity.createAttributes().build());
      event.put((EntityType)SPIDER_CORPSE.get(), SpiderCorpseEntity.createAttributes().build());
      event.put((EntityType)ASHES_TORCH_REMOVER.get(), AshesTorchRemoverEntity.createAttributes().build());
      event.put((EntityType)THE_HELL_ZONE_THING.get(), DeformityForAshesEntity.createAttributes().build());
      event.put((EntityType)MAGMA_GRENADE_EXPLODE.get(), MagmaGrenadeExplodeEntity.createAttributes().build());
      event.put((EntityType)CRUSHING_SKELETON.get(), CrushingSkeletonEntity.createAttributes().build());
      event.put((EntityType)SEVERED_LEGS_SKELETON.get(), SeveredLegsSkeletonEntity.createAttributes().build());
      event.put((EntityType)LUX.get(), LuxEntity.createAttributes().build());
      event.put((EntityType)ASHES_GATEWAY.get(), AshesGatewayEntity.createAttributes().build());
      event.put((EntityType)EXARRACK_HYDRA.get(), ExarrackHydraEntity.createAttributes().build());
      event.put((EntityType)EXARRACK_HYDRA_NATURAL_SPAWN.get(), ExarrackHydraNaturalSpawnEntity.createAttributes().build());
      event.put((EntityType)SPIRAL_TORNADO.get(), SpiralTornadoEntity.createAttributes().build());
      event.put((EntityType)VERTICALLY_CUTTED_SKELETON.get(), VerticallyCuttedSkeletonEntity.createAttributes().build());
      event.put((EntityType)ASHES_WITHER_SNEER.get(), AshesWitherSneerEntity.createAttributes().build());
      event.put((EntityType)HAND_OF_ACID.get(), HandOfAcidEntity.createAttributes().build());
   }

   public static record SpawnEntry<T extends Entity>(EntityType<T> type, SpawnPlacementType placement, Types heightmap, SpawnPredicate<T> predicate) {
   }
}
