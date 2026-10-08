package net.mcreator.gore.procedures;

import net.mcreator.gore.configuration.GoreEditionOtherConfigurationsConfiguration;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;

public class SeveredLegsAndArmZombieNaturalEntitySpawningConditionProcedure {
   public static boolean execute(LevelAccessor world) {
      return (Boolean)GoreEditionOtherConfigurationsConfiguration.NATURALLY_TOUGHNESS_CREATURES_SPAWNING.get()
         && (world instanceof Level _lvl ? _lvl.dimension() : (world instanceof WorldGenLevel _wgl ? _wgl.getLevel().dimension() : Level.OVERWORLD))
            == Level.OVERWORLD;
   }
}
