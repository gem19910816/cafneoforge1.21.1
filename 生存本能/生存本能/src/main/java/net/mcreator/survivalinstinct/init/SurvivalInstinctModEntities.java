package net.mcreator.survivalinstinct.init;

import net.mcreator.survivalinstinct.entity.HomemadeBombProyectileEntity;
import net.mcreator.survivalinstinct.entity.MolotovEntity;
import net.mcreator.survivalinstinct.entity.NailProyectileEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType.Builder;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.EventBusSubscriber.Bus;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredHolder;

@EventBusSubscriber(
   bus = Bus.MOD
)
public class SurvivalInstinctModEntities {
   public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(Registries.ENTITY_TYPE, "survival_instinct");
   public static final DeferredHolder<EntityType<?>, EntityType<HomemadeBombProyectileEntity>> HOMEMADE_BOMB_PROYECTILE = register(
      "homemade_bomb_proyectile",
      Builder.<HomemadeBombProyectileEntity>of(HomemadeBombProyectileEntity::new, MobCategory.MISC)
         
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(1)
         .sized(0.5F, 0.5F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<MolotovEntity>> MOLOTOV = register(
      "molotov",
      Builder.<MolotovEntity>of(MolotovEntity::new, MobCategory.MISC)
         
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(1)
         .sized(0.5F, 0.5F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<NailProyectileEntity>> NAIL_PROYECTILE = register(
      "nail_proyectile",
      Builder.<NailProyectileEntity>of(NailProyectileEntity::new, MobCategory.MISC)
         
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(1)
         .sized(0.5F, 0.5F)
   );

   private static <T extends Entity> DeferredHolder<EntityType<?>, EntityType<T>> register(String registryname, Builder<T> entityTypeBuilder) {
      return REGISTRY.register(registryname, () -> entityTypeBuilder.build(registryname));
   }

   @SubscribeEvent
   public static void init(FMLCommonSetupEvent event) {
      event.enqueueWork(() -> {
      });
   }

   @SubscribeEvent
   public static void registerAttributes(EntityAttributeCreationEvent event) {
   }
}
