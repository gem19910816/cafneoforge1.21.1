package net.mcreator.dyairdrop.procedures;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.levelgen.structure.Structure;

public class FindNearestStructureProcedure {
   public FindNearestStructureProcedure() {
   }

   public static String findNearestStructure(Entity player, String tag) {
      if (player.level() instanceof ServerLevel serverLevel) {
         TagKey<Structure> structureTag = TagKey.create(Registries.STRUCTURE, ResourceLocation.parse(tag));
         BlockPos entityPos = new BlockPos(player.getBlockX(), player.getBlockY(), player.getBlockZ());
         BlockPos structurePos = serverLevel.findNearestMapStructure(structureTag, entityPos, 100, false);
         return structurePos.getX() + ", " + structurePos.getY() + ", " + structurePos.getZ();
      } else {
         return "";
      }
   }
}
