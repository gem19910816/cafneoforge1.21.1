package net.mcreator.survivalinstinct.procedures;

import net.mcreator.survivalinstinct.init.SurvivalInstinctModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;

public class BearTrapCloseOnBlockRightClickedProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z) {
      world.setBlock(BlockPos.containing(x, y, z), SurvivalInstinctModBlocks.BEAR_TRAP.get().defaultBlockState(), 3);
      if (world instanceof Level _level) {
         if (!_level.isClientSide()) {
            _level.playSound(
               null,
               BlockPos.containing(x, y, z),
               BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("survival_instinct:bear_trap_close")),
               SoundSource.NEUTRAL,
               1.0F,
               1.0F
            );
         } else {
            _level.playLocalSound(
               x,
               y,
               z,
               BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("survival_instinct:bear_trap_close")),
               SoundSource.NEUTRAL,
               1.0F,
               1.0F,
               false
            );
         }
      }
   }
}
