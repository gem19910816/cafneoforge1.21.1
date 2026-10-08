package net.mcreator.gore.init;

import net.mcreator.gore.block.entity.AcidBlockTickBlockEntity;
import net.mcreator.gore.block.entity.AshesUnknownSkullBlockEntity;
import net.mcreator.gore.block.entity.DustLampBlockEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.BlockEntityType.BlockEntitySupplier;
import net.minecraft.world.level.block.entity.BlockEntityType.Builder;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.EventBusSubscriber.Bus;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.capabilities.Capabilities.ItemHandler;
import net.neoforged.neoforge.items.wrapper.SidedInvWrapper;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@EventBusSubscriber(
   bus = Bus.MOD
)
public class GoreEditionModBlockEntities {
   public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, "gore_edition");
   public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<?>> ACID_BLOCK_TICK = register(
      "acid_block_tick", GoreEditionModBlocks.ACID_BLOCK_TICK, AcidBlockTickBlockEntity::new
   );
   public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<?>> ASHTRAY_CYCLE = register(
      "ashtray_cycle", GoreEditionModBlocks.ASHTRAY_CYCLE, DustLampBlockEntity::new
   );
   public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<?>> ASHED_UNKNOWN_SKULL = register(
      "ashed_unknown_skull", GoreEditionModBlocks.ASHED_UNKNOWN_SKULL, AshesUnknownSkullBlockEntity::new
   );

   @SubscribeEvent
   public static void registerCapabilities(RegisterCapabilitiesEvent event) {
      // 1.20.1 exposed a per-face SidedInvWrapper via BlockEntity#getCapability (null face -> nothing).
      event.registerBlockEntity(
         ItemHandler.BLOCK, (BlockEntityType<AcidBlockTickBlockEntity>)ACID_BLOCK_TICK.get(),
         (be, side) -> side == null ? null : new SidedInvWrapper(be, side)
      );
      event.registerBlockEntity(
         ItemHandler.BLOCK, (BlockEntityType<DustLampBlockEntity>)ASHTRAY_CYCLE.get(),
         (be, side) -> side == null ? null : new SidedInvWrapper(be, side)
      );
      event.registerBlockEntity(
         ItemHandler.BLOCK, (BlockEntityType<AshesUnknownSkullBlockEntity>)ASHED_UNKNOWN_SKULL.get(),
         (be, side) -> side == null ? null : new SidedInvWrapper(be, side)
      );
   }

   private static DeferredHolder<BlockEntityType<?>, BlockEntityType<?>> register(
      String registryname, DeferredHolder<Block, Block> block, BlockEntitySupplier<?> supplier
   ) {
      return REGISTRY.register(registryname, () -> Builder.of(supplier, new Block[]{(Block)block.get()}).build(null));
   }
}
