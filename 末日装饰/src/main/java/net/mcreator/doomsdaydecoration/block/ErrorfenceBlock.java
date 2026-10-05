/*
 * Decompiled with CFR 0.152.
 */
package net.mcreator.doomsdaydecoration.block;

import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;

public class ErrorfenceBlock
extends FenceBlock {
    public ErrorfenceBlock() {
        super(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.STONE).strength(-1.0f, 3600000.0f).requiresCorrectToolForDrops().dynamicShape().forceSolidOn());
    }
}

