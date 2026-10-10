package net.mcreator.survivalinstinct.block;

import net.mcreator.survivalinstinct.procedures.ExplosiveBarrelOnBlockHitByProjectileProcedure;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
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

public class PropaneBlock extends Block {
   public PropaneBlock() {
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
      return Shapes.or(
         box(3.0, 2.0, 3.0, 13.0, 12.0, 13.0),
         box(4.0, 0.0, 4.0, 12.0, 2.0, 12.0),
         box(7.0, 12.0, 7.0, 9.0, 14.0, 9.0),
         box(7.0, 12.5, 5.0, 8.0, 13.5, 7.0),
         box(6.0, 14.0, 6.0, 10.0, 15.0, 10.0)
      );
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
