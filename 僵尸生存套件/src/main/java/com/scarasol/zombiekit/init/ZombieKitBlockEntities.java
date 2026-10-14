package com.scarasol.zombiekit.init;

import com.scarasol.zombiekit.ZombieKitMod;
import com.scarasol.zombiekit.block.entity.InjectorBlockEntity;
import com.scarasol.zombiekit.block.entity.MortarRackBlockEntity;
import com.scarasol.zombiekit.block.entity.ShortwaveRadioBlockEntity;
import com.scarasol.zombiekit.block.entity.VacuumPackagingMachineBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.BiFunction;

public class ZombieKitBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, ZombieKitMod.MODID);
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<?>> INJECTOR = register("injector", ZombieKitBlocks.INJECTOR, InjectorBlockEntity::new);
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<?>> SHORTWAVE_RADIO = register("shortwave_radio", ZombieKitBlocks.SHORTWAVE_RADIO, ShortwaveRadioBlockEntity::new);
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<?>> VACUUM_PACKAGING_MACHINE = register("vacuum_packaging_machine", ZombieKitBlocks.VACUUM_PACKAGING_MACHINE, VacuumPackagingMachineBlockEntity::new);
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<?>> MORTAR_RACK = register("mortar_rack", ZombieKitBlocks.MORTAR_RACK, MortarRackBlockEntity::new);


    // 1.21 中 BlockEntityType.BlockEntitySupplier 变为私有接口，改用 BiFunction 传构造器引用
    private static DeferredHolder<BlockEntityType<?>, BlockEntityType<?>> register(String registryName, DeferredHolder<Block, Block> block, BiFunction<BlockPos, BlockState, ? extends net.minecraft.world.level.block.entity.BlockEntity> supplier) {
        return REGISTRY.register(registryName, () -> BlockEntityType.Builder.of(supplier::apply, block.get()).build(null));
    }

}
