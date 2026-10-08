package net.mcreator.gore.procedures;

import java.util.Comparator;
import net.mcreator.gore.init.GoreEditionModBlocks;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

public class CreeperGrassOnTickUpdateProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         entity.getPersistentData().putDouble("spawn", entity.getPersistentData().getDouble("spawn") + 1.0);
         if (entity.getPersistentData().getDouble("spawn") >= 200.0) {
            Vec3 _center = new Vec3(x, y, z);

            for (Entity entityiterator : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(8.0), e -> true)
               .stream()
               .sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center)))
               .toList()) {
               if (entityiterator instanceof ServerPlayer || entityiterator instanceof Player) {
                  world.destroyBlock(BlockPos.containing(x, y, z), false);
                  if (world instanceof ServerLevel _level) {
                     _level.getServer()
                        .getCommands()
                        .performPrefixedCommand(
                           new CommandSourceStack(
                                 CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null
                              )
                              .withSuppressedOutput(),
                           "particle minecraft:block minecraft:grass ~ ~1 ~ 0.3 0.4 0.3 0.4 100"
                        );
                  }

                  if (world instanceof ServerLevel) {
                     ServerLevel _level = (ServerLevel)world;
                     Entity entityToSpawn = EntityType.CREEPER.spawn(_level, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED);
                     if (entityToSpawn != null) {
                        entityToSpawn.setYRot((float)Mth.nextDouble(RandomSource.create(), 0.0, 600.0));
                        entityToSpawn.setYBodyRot((float)Mth.nextDouble(RandomSource.create(), 0.0, 600.0));
                        entityToSpawn.setYHeadRot((float)Mth.nextDouble(RandomSource.create(), 0.0, 600.0));
                        entityToSpawn.setXRot((float)Mth.nextDouble(RandomSource.create(), 0.0, 600.0));
                     }
                  }
               }
            }
         }

         if (world.getBlockState(BlockPos.containing(x, y, z)).getBlock() != GoreEditionModBlocks.CREEPER_GRASS.get() && !entity.level().isClientSide()) {
            entity.discard();
         }
      }
   }
}
