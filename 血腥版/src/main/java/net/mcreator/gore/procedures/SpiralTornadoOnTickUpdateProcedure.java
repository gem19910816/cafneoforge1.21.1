package net.mcreator.gore.procedures;

import net.mcreator.gore.configuration.GeSpiralsConfiguration;
import net.mcreator.gore.init.GoreEditionModParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.LevelAccessor;

public class SpiralTornadoOnTickUpdateProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z) {
      if (world instanceof ServerLevel _level) {
         _level.sendParticles(
            (SimpleParticleType)GoreEditionModParticleTypes.SPIRAL_SMOKE.get(),
            x,
            y + 2.0,
            z,
            (int)((Double)GeSpiralsConfiguration.SPIRAL_TORNADO_FIELD.get()).doubleValue(),
            0.0,
            2.0,
            0.0,
            0.7
         );
      }

      if (world instanceof ServerLevel _level) {
         _level.sendParticles(
            (SimpleParticleType)GoreEditionModParticleTypes.SPIRAL_SMOKE.get(),
            x,
            y + 2.0,
            z,
            (int)((Double)GeSpiralsConfiguration.SPIRAL_TORNADO_EYE.get()).doubleValue(),
            0.05,
            2.0,
            0.05,
            0.0
         );
      }
   }
}
