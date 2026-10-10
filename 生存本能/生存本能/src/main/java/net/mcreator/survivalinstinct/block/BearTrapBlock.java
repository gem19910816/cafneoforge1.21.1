package net.mcreator.survivalinstinct.block;

import net.mcreator.survivalinstinct.procedures.BearTrapEntityWalksOnTheBlockProcedure;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BearTrapBlock extends Block {
   public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

   public BearTrapBlock() {
      super(
         Properties.of()
            .instrument(NoteBlockInstrument.BASEDRUM)
            .sound(SoundType.CHAIN)
            .strength(1.0F, 10.0F)
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
         case NORTH -> Shapes.or(
         box(5.0, 2.0, 6.0, 11.0, 3.0, 10.0),
         box(7.0, 1.0, 3.0, 9.0, 2.0, 13.0),
         box(6.0, 1.0, 13.0, 10.0, 2.0, 16.0),
         box(6.0, 0.0, 2.0, 10.0, 1.0, 14.0),
         box(4.0, 0.0, 0.0, 12.0, 1.0, 2.0),
         box(4.0, 0.0, 14.0, 12.0, 1.0, 16.0)
      );
         case EAST -> Shapes.or(
         box(6.0, 2.0, 5.0, 10.0, 3.0, 11.0),
         box(3.0, 1.0, 7.0, 13.0, 2.0, 9.0),
         box(0.0, 1.0, 6.0, 3.0, 2.0, 10.0),
         box(2.0, 0.0, 6.0, 14.0, 1.0, 10.0),
         box(14.0, 0.0, 4.0, 16.0, 1.0, 12.0),
         box(0.0, 0.0, 4.0, 2.0, 1.0, 12.0)
      );
         case WEST -> Shapes.or(
         box(6.0, 2.0, 5.0, 10.0, 3.0, 11.0),
         box(3.0, 1.0, 7.0, 13.0, 2.0, 9.0),
         box(13.0, 1.0, 6.0, 16.0, 2.0, 10.0),
         box(2.0, 0.0, 6.0, 14.0, 1.0, 10.0),
         box(0.0, 0.0, 4.0, 2.0, 1.0, 12.0),
         box(14.0, 0.0, 4.0, 16.0, 1.0, 12.0)
      );
         default -> Shapes.or(
         box(5.0, 2.0, 6.0, 11.0, 3.0, 10.0),
         box(7.0, 1.0, 3.0, 9.0, 2.0, 13.0),
         box(6.0, 1.0, 0.0, 10.0, 2.0, 3.0),
         box(6.0, 0.0, 2.0, 10.0, 1.0, 14.0),
         box(4.0, 0.0, 14.0, 12.0, 1.0, 16.0),
         box(4.0, 0.0, 0.0, 12.0, 1.0, 2.0)
      );
      };
   }

   @Override
   protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
      builder.add(FACING);
   }

   @Override
   public BlockState getStateForPlacement(BlockPlaceContext context) {
      return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
   }

   @Override
   public BlockState rotate(BlockState state, Rotation rot) {
      return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
   }

   @Override
   public BlockState mirror(BlockState state, Mirror mirrorIn) {
      return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
   }

   @Override
   public void entityInside(BlockState blockstate, Level world, BlockPos pos, Entity entity) {
      super.entityInside(blockstate, world, pos, entity);
      BearTrapEntityWalksOnTheBlockProcedure.execute(world, (double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), entity);
   }

   @Override
   public void stepOn(Level world, BlockPos pos, BlockState blockstate, Entity entity) {
      super.stepOn(world, pos, blockstate, entity);
      BearTrapEntityWalksOnTheBlockProcedure.execute(world, (double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), entity);
   }

   @Override
   public void onProjectileHit(Level world, BlockState blockstate, BlockHitResult hit, Projectile entity) {
      BearTrapEntityWalksOnTheBlockProcedure.execute(
         world, (double)hit.getBlockPos().getX(), (double)hit.getBlockPos().getY(), (double)hit.getBlockPos().getZ(), entity
      );
   }
}
