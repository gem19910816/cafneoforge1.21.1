package net.mcreator.gore.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;

public class VoidstoneBlock extends Block {
   public VoidstoneBlock() {
      super(
         Properties.of()
            .instrument(NoteBlockInstrument.BASEDRUM)
            .sound(
               new SoundType(
                  1.0F,
                  1.0F,
                  (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.stone.break")),
                  (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:block.exarrack.step")),
                  (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.stone.place")),
                  (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.stone.hit")),
                  (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.stone.fall"))
               )
            )
            .strength(3.0F, 7.0F)
      );
   }

   public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
      return 15;
   }
}
