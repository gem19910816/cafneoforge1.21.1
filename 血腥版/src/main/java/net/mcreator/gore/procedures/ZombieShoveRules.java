package net.mcreator.gore.procedures;

/** Geometry and roll rules kept independent of entity state for verification. */
public final class ZombieShoveRules {
   private ZombieShoveRules() {
   }

   public static boolean inCone(double dx, double dz, double verticalDistance,
      double forwardX, double forwardZ, double reach, double halfAngleDegrees) {
      double distanceSquared = dx * dx + dz * dz;
      if (verticalDistance > 1.25D || distanceSquared > reach * reach) {
         return false;
      }
      if (distanceSquared < 0.01D) {
         return true;
      }
      double forwardLength = Math.hypot(forwardX, forwardZ);
      if (forwardLength < 0.0001D) {
         return false;
      }
      double dot = (dx * forwardX + dz * forwardZ) / (Math.sqrt(distanceSquared) * forwardLength);
      return dot >= Math.cos(Math.toRadians(halfAngleDegrees));
   }

   public static boolean shouldKnockDown(double chance, double roll) {
      return roll < chance;
   }
}
