package net.mcreator.survivalinstinct.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BarricadeBlock extends Block {
   public static final DirectionProperty FACING = DirectionalBlock.FACING;

   public BarricadeBlock() {
      super(
         Properties.of()
            .ignitedByLava()
            .instrument(NoteBlockInstrument.BASS)
            .sound(SoundType.WOOD)
            .strength(3.0F, 10.0F)
            .noOcclusion()
            .isRedstoneConductor((bs, br, bp) -> false)
      );
      this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
   }

   @Override
   public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
      return true;
   }

   @Override
   public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
      return 0;
   }

   @Override
   public VoxelShape getVisualShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
      return Shapes.empty();
   }

   @Override
   public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
      return switch ((Direction)state.getValue(FACING)) {
         case NORTH -> Shapes.or(box(0.0, 6.0, 15.0, 16.0, 10.0, 16.0), box(0.0, 6.0, 14.0, 16.0, 10.0, 15.0));
         case EAST -> Shapes.or(box(0.0, 6.0, 0.0, 1.0, 10.0, 16.0), box(1.0, 6.0, 0.0, 2.0, 10.0, 16.0));
         case WEST -> Shapes.or(box(15.0, 6.0, 0.0, 16.0, 10.0, 16.0), box(14.0, 6.0, 0.0, 15.0, 10.0, 16.0));
         case UP -> Shapes.or(box(0.0, 0.0, 6.0, 16.0, 1.0, 10.0), box(0.0, 1.0, 6.0, 16.0, 2.0, 10.0));
         case DOWN -> Shapes.or(box(0.0, 15.0, 6.0, 16.0, 16.0, 10.0), box(0.0, 14.0, 6.0, 16.0, 15.0, 10.0));
         default -> Shapes.or(box(0.0, 6.0, 0.0, 16.0, 10.0, 1.0), box(0.0, 6.0, 1.0, 16.0, 10.0, 2.0));
      };
   }

   @Override
   protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
      builder.add(FACING);
   }

   @Override
   public BlockState getStateForPlacement(BlockPlaceContext context) {
      return this.defaultBlockState().setValue(FACING, context.getNearestLookingDirection().getOpposite());
   }

   @Override
   public BlockState rotate(BlockState state, Rotation rot) {
      return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
   }

   @Override
   public BlockState mirror(BlockState state, Mirror mirrorIn) {
      return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
   }
}
