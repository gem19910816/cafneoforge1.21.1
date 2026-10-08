package net.mcreator.gore.block;

import net.mcreator.gore.init.GoreEditionModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.OffsetType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public class AshesFlowerBlock extends FlowerBlock {
   public AshesFlowerBlock() {
      super(
         MobEffects.UNLUCK,
         100.0F,
         Properties.of()
            .mapColor(MapColor.PLANT)
            .sound(SoundType.GRASS)
            .strength(0.1F, 0.0F)
            .lightLevel(s -> 7)
            .noCollission()
            .offsetType(OffsetType.XZ)
            .pushReaction(PushReaction.DESTROY)
      );
   }

   public int getEffectDuration() {
      return 100;
   }

   public boolean mayPlaceOn(BlockState groundState, BlockGetter worldIn, BlockPos pos) {
      return groundState.is((Block)GoreEditionModBlocks.EXARRACK.get())
         || groundState.is((Block)GoreEditionModBlocks.EXARRACK_ART_DOWN_LEFT.get())
         || groundState.is((Block)GoreEditionModBlocks.EXARRACK_ART_DOWN_CENTER.get())
         || groundState.is((Block)GoreEditionModBlocks.EXARRACK_ART_DOWN_RIGHT.get())
         || groundState.is((Block)GoreEditionModBlocks.EXARRACK_ART_TOP_LEFT.get())
         || groundState.is((Block)GoreEditionModBlocks.EXARRACK_ART_TOP_CENTER.get())
         || groundState.is((Block)GoreEditionModBlocks.EXARRACK_ART_TOP_RIGHT.get());
   }

   public boolean canSurvive(BlockState blockstate, LevelReader worldIn, BlockPos pos) {
      BlockPos blockpos = pos.below();
      BlockState groundState = worldIn.getBlockState(blockpos);
      return this.mayPlaceOn(groundState, worldIn, blockpos);
   }
}
