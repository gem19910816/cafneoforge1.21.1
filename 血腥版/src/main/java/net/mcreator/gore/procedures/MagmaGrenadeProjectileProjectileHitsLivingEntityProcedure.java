package net.mcreator.gore.procedures;

import net.mcreator.gore.init.GoreEditionModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelAccessor;

public class MagmaGrenadeProjectileProjectileHitsLivingEntityProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity immediatesourceentity) {
      if (immediatesourceentity != null && world instanceof ServerLevel _serverLevel) {
         Entity entityinstance = ((EntityType)GoreEditionModEntities.MAGMA_GRENADE_EXPLODE.get())
            .create(_serverLevel, null, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED, false, false);
         if (entityinstance != null) {
            entityinstance.setYRot(world.getRandom().nextFloat() * 360.0F);
            if (entityinstance instanceof TamableAnimal _toTame && immediatesourceentity instanceof Player _owner) {
               _toTame.tame(_owner);
            }

            _serverLevel.addFreshEntity(entityinstance);
         }
      }
   }
}
