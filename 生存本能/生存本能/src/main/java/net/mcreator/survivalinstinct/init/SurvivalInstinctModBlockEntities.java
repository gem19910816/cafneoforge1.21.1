package net.mcreator.survivalinstinct.init;

import net.mcreator.survivalinstinct.block.entity.CashRegisterBlockEntity;
import net.mcreator.survivalinstinct.block.entity.RefrigeratorBlockEntity;
import net.mcreator.survivalinstinct.block.entity.TrashCanBlockEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.BlockEntityType.BlockEntitySupplier;
import net.minecraft.world.level.block.entity.BlockEntityType.Builder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredHolder;

public class SurvivalInstinctModBlockEntities {
   public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, "survival_instinct");
   public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<?>> REFRIGERATOR = register(
      "refrigerator", SurvivalInstinctModBlocks.REFRIGERATOR, RefrigeratorBlockEntity::new
   );
   public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<?>> CASH_REGISTER = register(
      "cash_register", SurvivalInstinctModBlocks.CASH_REGISTER, CashRegisterBlockEntity::new
   );
   public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<?>> TRASH_CAN = register("trash_can", SurvivalInstinctModBlocks.TRASH_CAN, TrashCanBlockEntity::new);

   private static DeferredHolder<BlockEntityType<?>, BlockEntityType<?>> register(String registryname, DeferredHolder<Block, Block> block, BlockEntitySupplier<?> supplier) {
      return REGISTRY.register(registryname, () -> Builder.of(supplier, block.get()).build(null));
   }
}
