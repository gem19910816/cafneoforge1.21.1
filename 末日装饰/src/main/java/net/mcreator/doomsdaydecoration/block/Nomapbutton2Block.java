/*
 * Decompiled with CFR 0.152.
 */
package net.mcreator.doomsdaydecoration.block;

import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;

public class Nomapbutton2Block
extends ButtonBlock {
    public Nomapbutton2Block() {
        super(BlockSetType.STONE, 20, BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.STONE).strength(1.0f, 10.0f).requiresCorrectToolForDrops().dynamicShape());
    }
}

