package net.mcreator.gore.procedures;

import net.mcreator.gore.init.GoreEditionModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.LevelAccessor;

public class GrenadeOfGreekFireProjectileProjectileHitsLivingEntityProcedure {
   public static void execute(LevelAccessor world, Entity entity) {
      if (entity != null && world instanceof ServerLevel _level) {
         Entity entityToSpawn = ((EntityType)GoreEditionModEntities.GRENADE_OF_GREEK_FIRE_GREEK_FIRE.get())
            .spawn(_level, BlockPos.containing(entity.getX(), entity.getY(), entity.getZ()), MobSpawnType.MOB_SUMMONED);
         if (entityToSpawn != null) {
            entityToSpawn.setYRot(world.getRandom().nextFloat() * 360.0F);
         }
      }
   }
}
