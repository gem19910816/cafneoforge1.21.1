package net.mcreator.gore.procedures;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

public class AcidBlockAddedProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z) {
      if (world instanceof Level _level) {
         if (!_level.isClientSide()) {
            _level.playSound(
               null,
               BlockPos.containing(x, y, z),
               (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("item.bucket.fill_lava")),
               SoundSource.AMBIENT,
               1.0F,
               2.0F
            );
         } else {
            _level.playLocalSound(
               x, y, z, (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("item.bucket.fill_lava")), SoundSource.AMBIENT, 1.0F, 2.0F, false
            );
         }
      }
   }
}
