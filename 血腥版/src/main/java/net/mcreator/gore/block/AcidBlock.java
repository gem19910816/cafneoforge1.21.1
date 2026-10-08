package net.mcreator.gore.block;

import net.mcreator.gore.init.GoreEditionModFluids;
import net.mcreator.gore.procedures.AcidBlockAddedProcedure;
import net.mcreator.gore.procedures.AcidMobplayerCollidesBlockProcedure;
import net.mcreator.gore.procedures.AcidOnRandomClientDisplayTickProcedure;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

public class AcidBlock extends LiquidBlock {
   public AcidBlock() {
      super(
         (FlowingFluid)GoreEditionModFluids.ACID.get(),
         Properties.of()
            .mapColor(MapColor.WATER)
            .strength(100.0F)
            .noCollission()
            .noLootTable()
            .liquid()
            .pushReaction(PushReaction.DESTROY)
            .sound(SoundType.EMPTY)
            .replaceable()
      );
   }

   public void onPlace(BlockState blockstate, Level world, BlockPos pos, BlockState oldState, boolean moving) {
      super.onPlace(blockstate, world, pos, oldState, moving);
      AcidBlockAddedProcedure.execute(world, (double)pos.getX(), (double)pos.getY(), (double)pos.getZ());
   }

   public void entityInside(BlockState blockstate, Level world, BlockPos pos, Entity entity) {
      super.entityInside(blockstate, world, pos, entity);
      AcidMobplayerCollidesBlockProcedure.execute(entity);
   }

   @OnlyIn(Dist.CLIENT)
   public void animateTick(BlockState blockstate, Level world, BlockPos pos, RandomSource random) {
      super.animateTick(blockstate, world, pos, random);
      AcidOnRandomClientDisplayTickProcedure.execute(world, (double)pos.getX(), (double)pos.getY(), (double)pos.getZ());
   }
}
