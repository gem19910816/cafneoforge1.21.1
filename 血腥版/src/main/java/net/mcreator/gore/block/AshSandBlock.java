package net.mcreator.gore.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;

public class AshSandBlock extends FallingBlock {
   public static final MapCodec<AshSandBlock> CODEC = MapCodec.unit(AshSandBlock::new);

   public MapCodec<AshSandBlock> codec() {
      return CODEC;
   }

   public AshSandBlock() {
      super(Properties.of().instrument(NoteBlockInstrument.SNARE).sound(SoundType.SAND).strength(2.0F, 10.0F).speedFactor(0.4F).jumpFactor(0.88F));
   }

   public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
      return 15;
   }
}
