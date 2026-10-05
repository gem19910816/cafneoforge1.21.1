/*
 * Decompiled with CFR 0.152.
 */
package net.mcreator.doomsdaydecoration.block;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class Ironsheetwall1Block
extends WallBlock {
    public Ironsheetwall1Block() {
        super(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(1.0f, 10.0f).requiresCorrectToolForDrops().dynamicShape().forceSolidOn());
    }
}

