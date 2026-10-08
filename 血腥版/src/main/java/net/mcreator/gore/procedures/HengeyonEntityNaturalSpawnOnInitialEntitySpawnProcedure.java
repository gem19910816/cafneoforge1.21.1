package net.mcreator.gore.procedures;

import net.mcreator.gore.entity.HengeyonEntity;
import net.mcreator.gore.init.GoreEditionModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class HengeyonEntityNaturalSpawnOnInitialEntitySpawnProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         if (!entity.level().isClientSide()) {
            entity.discard();
         }

         if (world.getEntitiesOfClass(HengeyonEntity.class, AABB.ofSize(new Vec3(x, y, z), 256.0, 256.0, 256.0), e -> true).isEmpty()
            && Math.random() < 0.4
            && world instanceof ServerLevel _level) {
            Entity entityToSpawn = ((EntityType)GoreEditionModEntities.HENGEYON.get()).spawn(_level, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED);
            if (entityToSpawn != null) {
               entityToSpawn.setYRot(world.getRandom().nextFloat() * 360.0F);
            }
         }
      }
   }
}
