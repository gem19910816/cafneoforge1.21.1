package net.mcreator.gore.procedures;

import net.mcreator.gore.entity.ExarrackHydraEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

public class FleshEaterScreamProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         if ((entity instanceof Mob _mobEnt ? _mobEnt.getTarget() : null) != null) {
            if (!entity.getPersistentData().getBoolean("scream")) {
               entity.getPersistentData().putBoolean("scream", true);
               if (entity instanceof ExarrackHydraEntity && world instanceof Level _level) {
                  if (!_level.isClientSide()) {
                     _level.playSound(
                        null,
                        BlockPos.containing(x, y, z),
                        (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:entity.exarrack_hydra.scream")),
                        SoundSource.HOSTILE,
                        4.0F,
                        1.0F
                     );
                  } else {
                     _level.playLocalSound(
                        x,
                        y,
                        z,
                        (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:entity.exarrack_hydra.scream")),
                        SoundSource.HOSTILE,
                        4.0F,
                        1.0F,
                        false
                     );
                  }
               }
            }
         } else {
            entity.getPersistentData().putBoolean("scream", false);
         }
      }
   }
}
