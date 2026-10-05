/*
 * Decompiled with CFR 0.152.
 */
package net.mcreator.doomsdaydecoration.init;

import net.mcreator.doomsdaydecoration.block.entity.AcrateBlockEntity;
import net.mcreator.doomsdaydecoration.init.DoomsdayDecorationModBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.registries.DeferredHolder;

public class DoomsdayDecorationModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, (String)"doomsday_decoration");
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<?>> ACRATE = DoomsdayDecorationModBlockEntities.register("acrate", DoomsdayDecorationModBlocks.ACRATE, AcrateBlockEntity::new);

    private static DeferredHolder<BlockEntityType<?>, BlockEntityType<?>> register(String registryname, DeferredHolder<Block, Block> block, BlockEntityType.BlockEntitySupplier<?> supplier) {
        return REGISTRY.register(registryname, () -> BlockEntityType.Builder.of((BlockEntityType.BlockEntitySupplier)supplier, (Block[])new Block[]{(Block)block.get()}).build(null));
    }
}

