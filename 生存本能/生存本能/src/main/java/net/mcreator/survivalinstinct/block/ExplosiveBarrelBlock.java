package net.mcreator.survivalinstinct.block;

import net.mcreator.survivalinstinct.procedures.ExplosiveBarrelOnBlockHitByProjectileProcedure;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
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

public class ExplosiveBarrelBlock extends Block {
   public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

   public ExplosiveBarrelBlock() {
      super(
         Properties.of()
            .instrument(NoteBlockInstrument.BASEDRUM)
            .sound(SoundType.METAL)
            .strength(2.0F, 10.0F)
            .requiresCorrectToolForDrops()
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
         box(2.0, 2.0, 2.0, 14.0, 14.0, 14.0),
         box(1.0, 14.0, 1.0, 15.0, 16.0, 15.0),
         box(1.0, 0.0, 1.0, 15.0, 2.0, 15.0),
         box(1.0, 4.0, 1.0, 15.0, 6.0, 15.0),
         box(1.0, 10.0, 1.0, 15.0, 12.0, 15.0)
      );
         case EAST -> Shapes.or(
         box(2.0, 2.0, 2.0, 14.0, 14.0, 14.0),
         box(1.0, 14.0, 1.0, 15.0, 16.0, 15.0),
         box(1.0, 0.0, 1.0, 15.0, 2.0, 15.0),
         box(1.0, 4.0, 1.0, 15.0, 6.0, 15.0),
         box(1.0, 10.0, 1.0, 15.0, 12.0, 15.0)
      );
         case WEST -> Shapes.or(
         box(2.0, 2.0, 2.0, 14.0, 14.0, 14.0),
         box(1.0, 14.0, 1.0, 15.0, 16.0, 15.0),
         box(1.0, 0.0, 1.0, 15.0, 2.0, 15.0),
         box(1.0, 4.0, 1.0, 15.0, 6.0, 15.0),
         box(1.0, 10.0, 1.0, 15.0, 12.0, 15.0)
      );
         default -> Shapes.or(
         box(2.0, 2.0, 2.0, 14.0, 14.0, 14.0),
         box(1.0, 14.0, 1.0, 15.0, 16.0, 15.0),
         box(1.0, 0.0, 1.0, 15.0, 2.0, 15.0),
         box(1.0, 4.0, 1.0, 15.0, 6.0, 15.0),
         box(1.0, 10.0, 1.0, 15.0, 12.0, 15.0)
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
   public void neighborChanged(BlockState blockstate, Level world, BlockPos pos, Block neighborBlock, BlockPos fromPos, boolean moving) {
      super.neighborChanged(blockstate, world, pos, neighborBlock, fromPos, moving);
      if (world.getBestNeighborSignal(pos) > 0) {
         ExplosiveBarrelOnBlockHitByProjectileProcedure.execute(world, (double)pos.getX(), (double)pos.getY(), (double)pos.getZ());
      }
   }

   @Override
   public void wasExploded(Level world, BlockPos pos, Explosion e) {
      super.wasExploded(world, pos, e);
      ExplosiveBarrelOnBlockHitByProjectileProcedure.execute(world, (double)pos.getX(), (double)pos.getY(), (double)pos.getZ());
   }

   @Override
   public void onProjectileHit(Level world, BlockState blockstate, BlockHitResult hit, Projectile entity) {
      ExplosiveBarrelOnBlockHitByProjectileProcedure.execute(
         world, (double)hit.getBlockPos().getX(), (double)hit.getBlockPos().getY(), (double)hit.getBlockPos().getZ()
      );
   }
}
