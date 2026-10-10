package net.mcreator.survivalinstinct.procedures;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.LevelAccessor;

public class MolotovWhileProjectileFlyingTickProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z) {
      world.addParticle(ParticleTypes.SMOKE, x, y, z, 0.0, 0.5, 0.0);
      world.addParticle(ParticleTypes.SMALL_FLAME, x, y, z, 0.0, 0.5, 0.0);
   }
}
