package net.mcreator.gore.procedures;

import net.mcreator.gore.configuration.GeSpiralsConfiguration;
import net.mcreator.gore.init.GoreEditionModParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.LevelAccessor;

public class SpiralTornadoParticlesProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z) {
      if (world instanceof ServerLevel _level) {
         _level.sendParticles(
            (SimpleParticleType)GoreEditionModParticleTypes.SPIRAL_SMOKE.get(),
            x,
            y + 0.4,
            z,
            (int)((Double)GeSpiralsConfiguration.SPIRAL_TORNADO_FIELD.get() / 3.0),
            0.0,
            0.0,
            0.0,
            0.4
         );
      }

      if (world instanceof ServerLevel _level) {
         _level.sendParticles(
            (SimpleParticleType)GoreEditionModParticleTypes.SPIRAL_SMOKE.get(),
            x,
            y + 0.4,
            z,
            (int)((Double)GeSpiralsConfiguration.SPIRAL_TORNADO_EYE.get() / 3.0),
            0.0,
            0.0,
            0.0,
            0.0
         );
      }
   }
}
