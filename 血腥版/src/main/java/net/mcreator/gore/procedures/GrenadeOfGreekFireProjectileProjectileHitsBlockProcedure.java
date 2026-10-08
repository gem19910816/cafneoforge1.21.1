package net.mcreator.gore.procedures;

import net.mcreator.gore.init.GoreEditionModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.LevelAccessor;

public class GrenadeOfGreekFireProjectileProjectileHitsBlockProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z) {
      if (world instanceof ServerLevel _level) {
         Entity entityToSpawn = ((EntityType)GoreEditionModEntities.GRENADE_OF_GREEK_FIRE_GREEK_FIRE.get())
            .spawn(_level, BlockPos.containing(x, y + 1.0, z), MobSpawnType.MOB_SUMMONED);
         if (entityToSpawn != null) {
            entityToSpawn.setYRot(world.getRandom().nextFloat() * 360.0F);
         }
      }
   }
}
