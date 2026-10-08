package net.mcreator.gore.procedures;

public class HengeyonNaturalEntitySpawningConditionProcedure {
   public static boolean execute(double x, double y, double z) {
      return y >= 45.0 && x < 500.0 && x > -500.0 && z < 500.0 && z > -500.0;
   }
}
