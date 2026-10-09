package net.mcreator.dyairdrop.compat;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.storage.loot.LootTable;

/**
 * Replaces the "setblock ~ ~ ~ <block>open[facing=…]{LootTable:"…"} replace" command that the
 * 1.20.1 mod ran from the password panel.
 *
 * <p>Two reasons this exists:
 * <ul>
 *   <li>Modifying the world through the command dispatcher pulls every mod that hooks commands,
 *       block placement or chunk access into the panel's flow; the direct block API does not.</li>
 *   <li>The old code also reached the world-mutating branch on the <em>client</em> (MCreator runs
 *       the button procedure locally as well as on the server). Replacing a block that owns a
 *       block entity client-side is what made the password panel trip over chunk / block-entity
 *       mods (Xaero &amp; friends aside, C2ME is exactly that class of mod). Everything here is
 *       server-authoritative and refuses to run on a client or on an unloaded chunk.</li>
 * </ul>
 */
public class CrateCompat {
   public CrateCompat() {
   }

   /**
    * Swaps the crate/safe at {@code pos} for its "&lt;id&gt;open" variant and hands it the loot table
    * stored in the block entity's persistent {@code loot} tag - the same net effect as the old
    * command, without the command dispatcher.
    */
   public static void openCrate(LevelAccessor world, BlockPos pos) {
      if (world == null || world.isClientSide() || !(world instanceof ServerLevel level)) {
         return;
      }
      if (!level.isLoaded(pos)) {
         return;
      }

      BlockState state = level.getBlockState(pos);
      ResourceLocation id = BuiltInRegistries.BLOCK.getKey(state.getBlock());
      if (id == null) {
         return;
      }
      Block openBlock = BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(id.getNamespace(), id.getPath() + "open"));
      if (openBlock == null || openBlock == Blocks.AIR) {
         // some crates have no open variant (e.g. safe_2); the old command silently failed too
         return;
      }

      String loot = "";
      BlockEntity old = level.getBlockEntity(pos);
      if (old != null) {
         loot = old.getPersistentData().getString("loot");
      }

      BlockState openState = openBlock.defaultBlockState();
      if (state.getBlock().getStateDefinition().getProperty("facing") instanceof DirectionProperty facing
         && openState.hasProperty(facing)) {
         openState = openState.setValue(facing, state.getValue(facing));
      } else if (openState.hasProperty(net.minecraft.world.level.block.HorizontalDirectionalBlock.FACING)) {
         DirectionProperty openFacing = net.minecraft.world.level.block.HorizontalDirectionalBlock.FACING;
         openState = openState.setValue(openFacing, Direction.NORTH);
      }

      level.setBlock(pos, openState, 3);

      if (!loot.isEmpty() && level.getBlockEntity(pos) instanceof RandomizableContainerBlockEntity container) {
         ResourceLocation lootId = ResourceLocation.tryParse(loot);
         if (lootId != null) {
            container.setLootTable(ResourceKey.create(Registries.LOOT_TABLE, lootId));
            container.setChanged();
         }
      }
   }

   /** Server-side, chunk-safe block write used by the panel animations. */
   public static void setBlockAnimation(LevelAccessor world, BlockPos pos, int value) {
      if (world == null || world.isClientSide() || !(world instanceof ServerLevel level) || !level.isLoaded(pos)) {
         return;
      }
      BlockState state = level.getBlockState(pos);
      if (state.getBlock().getStateDefinition().getProperty("animation") instanceof net.minecraft.world.level.block.state.properties.IntegerProperty animation
         && animation.getPossibleValues().contains(value)) {
         level.setBlock(pos, state.setValue(animation, value), 3);
      }
   }

   /** Server-side persistent-data write (never touches a client block entity). */
   public static boolean putBlockData(LevelAccessor world, BlockPos pos, String key, String value) {
      return putBlockData(world, pos, key, value, null);
   }

   public static boolean putBlockData(LevelAccessor world, BlockPos pos, String key, String value, String lootTable) {
      if (world == null || world.isClientSide() || !(world instanceof ServerLevel level) || !level.isLoaded(pos)) {
         return false;
      }
      BlockEntity blockEntity = level.getBlockEntity(pos);
      if (blockEntity == null) {
         return false;
      }
      if (value != null) {
         blockEntity.getPersistentData().putString(key, value);
      }
      if (lootTable != null) {
         blockEntity.getPersistentData().putString("loot", lootTable);
      }
      level.sendBlockUpdated(pos, level.getBlockState(pos), level.getBlockState(pos), 3);
      return true;
   }
}
