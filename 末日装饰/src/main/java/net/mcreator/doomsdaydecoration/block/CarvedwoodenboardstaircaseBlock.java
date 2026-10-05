/*
 * Decompiled with CFR 0.152.
 */
package net.mcreator.doomsdaydecoration.block;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;

public class CarvedwoodenboardstaircaseBlock
extends StairBlock {
    public CarvedwoodenboardstaircaseBlock() {
        super(Blocks.AIR.defaultBlockState(), BlockBehaviour.Properties.of().ignitedByLava().instrument(NoteBlockInstrument.BASS).sound(SoundType.WOOD).strength(1.0f, 10.0f).requiresCorrectToolForDrops().dynamicShape());
    }

    public float getExplosionResistance() {
        return 10.0f;
    }

    public boolean isRandomlyTicking(BlockState state) {
        return false;
    }
}

