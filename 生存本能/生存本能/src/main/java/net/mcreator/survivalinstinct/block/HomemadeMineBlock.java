package net.mcreator.survivalinstinct.block;

import net.mcreator.survivalinstinct.procedures.HomemadeMineExploteProcedure;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class HomemadeMineBlock extends Block {
   public HomemadeMineBlock() {
      super(
         Properties.of()
            .instrument(NoteBlockInstrument.BASEDRUM)
            .sound(SoundType.METAL)
            .strength(4.0F, 10.0F)
            .requiresCorrectToolForDrops()
            .noOcclusion()
            .isRedstoneConductor((bs, br, bp) -> false)
      );
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
      return box(5.0, 0.0, 5.0, 11.0, 1.0, 11.0);
   }

   @Override
   public void entityInside(BlockState blockstate, Level world, BlockPos pos, Entity entity) {
      super.entityInside(blockstate, world, pos, entity);
      HomemadeMineExploteProcedure.execute(world, (double)pos.getX(), (double)pos.getY(), (double)pos.getZ());
   }

   @Override
   public void stepOn(Level world, BlockPos pos, BlockState blockstate, Entity entity) {
      super.stepOn(world, pos, blockstate, entity);
      HomemadeMineExploteProcedure.execute(world, (double)pos.getX(), (double)pos.getY(), (double)pos.getZ());
   }

   @Override
   public void onProjectileHit(Level world, BlockState blockstate, BlockHitResult hit, Projectile entity) {
      HomemadeMineExploteProcedure.execute(world, (double)hit.getBlockPos().getX(), (double)hit.getBlockPos().getY(), (double)hit.getBlockPos().getZ());
   }
}
