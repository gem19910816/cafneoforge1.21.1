package net.mcreator.gore.block;

import net.mcreator.gore.init.GoreEditionModBlocks;
import net.mcreator.gore.procedures.CreeperGrassPlantAddedProcedure;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.GrassColor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.OffsetType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent.Block;

public class CreeperGrassBlock extends FlowerBlock {
   public CreeperGrassBlock() {
      super(
         MobEffects.CONFUSION,
         100.0F,
         Properties.of()
            .mapColor(MapColor.PLANT)
            .sound(SoundType.GRASS)
            .strength(6.0F, 0.0F)
            .noCollission()
            .offsetType(OffsetType.XZ)
            .pushReaction(PushReaction.DESTROY)
      );
   }

   public int getEffectDuration() {
      return 100;
   }

   public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
      return 100;
   }

   public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
      return 60;
   }

   public void onPlace(BlockState blockstate, Level world, BlockPos pos, BlockState oldState, boolean moving) {
      super.onPlace(blockstate, world, pos, oldState, moving);
      CreeperGrassPlantAddedProcedure.execute(world, (double)pos.getX(), (double)pos.getY(), (double)pos.getZ());
   }

   @OnlyIn(Dist.CLIENT)
   public static void blockColorLoad(Block event) {
      event.getBlockColors()
         .register(
            (bs, world, pos, index) -> world != null && pos != null ? BiomeColors.getAverageGrassColor(world, pos) : GrassColor.get(0.5, 1.0),
            new net.minecraft.world.level.block.Block[]{(net.minecraft.world.level.block.Block)GoreEditionModBlocks.CREEPER_GRASS.get()}
         );
   }
}
