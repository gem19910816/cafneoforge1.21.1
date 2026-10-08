package net.mcreator.gore.procedures;

import javax.annotation.Nullable;
import net.mcreator.gore.init.GoreEditionModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.BlockEvent.EntityPlaceEvent;

@EventBusSubscriber
public class BlockPlacedInAshesThatCauseLightProcedure {
   @SubscribeEvent
   public static void onBlockPlace(EntityPlaceEvent event) {
      execute(event, event.getLevel(), (double)event.getPos().getX(), (double)event.getPos().getY(), (double)event.getPos().getZ());
   }

   public static void execute(LevelAccessor world, double x, double y, double z) {
      execute(null, world, x, y, z);
   }

   private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z) {
      if ((world instanceof Level _lvl ? _lvl.dimension() : (world instanceof WorldGenLevel _wgl ? _wgl.getLevel().dimension() : Level.OVERWORLD))
            == ResourceKey.create(Registries.DIMENSION, ResourceLocation.parse("gore_edition:the_xash"))
         && world.getBlockState(BlockPos.containing(x, y, z)).getLightEmission(world, BlockPos.containing(x, y, z)) > 0
         && world.getBlockState(BlockPos.containing(x, y, z)).getBlock() != Blocks.REDSTONE_TORCH
         && world.getBlockState(BlockPos.containing(x, y, z)).getBlock() != Blocks.REDSTONE_LAMP
         && world.getBlockState(BlockPos.containing(x, y, z)).getBlock() != GoreEditionModBlocks.ASHTRAY_CYCLE.get()
         && world.getBlockState(BlockPos.containing(x, y, z)).getBlock() != GoreEditionModBlocks.ASHES_FLOWER.get()) {
         world.destroyBlock(BlockPos.containing(x, y, z), false);
      }
   }
}
