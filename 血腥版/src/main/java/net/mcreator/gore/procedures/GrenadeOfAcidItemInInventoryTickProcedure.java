package net.mcreator.gore.procedures;

import net.mcreator.gore.init.GoreEditionModBlocks;
import net.mcreator.gore.utils.ItemTagHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;

public class GrenadeOfAcidItemInInventoryTickProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, ItemStack itemstack) {
      if (entity != null) {
         if (ItemTagHelper.getDouble(itemstack, "click") >= 1.0) {
            ItemTagHelper.getOrCreateTag(itemstack).putDouble("timer", ItemTagHelper.getOrCreateTag(itemstack).getDouble("timer") + 1.0);
         }

         if (ItemTagHelper.getDouble(itemstack, "timer") == 1.0) {
            ItemTagHelper.getOrCreateTag(itemstack).putDouble("addags", ItemTagHelper.getOrCreateTag(itemstack).getDouble("addags") + 1.0);
            if (world instanceof Level _level) {
               if (!_level.isClientSide()) {
                  _level.playSound(
                     null,
                     BlockPos.containing(x, y, z),
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:item.grenade.pin_pull")),
                     SoundSource.PLAYERS,
                     0.15F,
                     1.8F
                  );
               } else {
                  _level.playLocalSound(
                     x,
                     y,
                     z,
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:item.grenade.pin_pull")),
                     SoundSource.PLAYERS,
                     0.15F,
                     1.8F,
                     false
                  );
               }
            }
         }

         if (ItemTagHelper.getDouble(itemstack, "timer") == 60.0) {
            ItemTagHelper.getOrCreateTag(itemstack).putDouble("timer", 0.0);
            ItemTagHelper.putDouble(itemstack, "click", 0.0);
            world.setBlock(BlockPos.containing(x, y, z), ((Block)GoreEditionModBlocks.ACID.get()).defaultBlockState(), 3);
            world.setBlock(BlockPos.containing(x, y + 1.0, z), ((Block)GoreEditionModBlocks.ACID_BLOCK_TICK.get()).defaultBlockState(), 3);
            if (entity instanceof Player _player) {
               _player.getInventory().clearOrCountMatchingItems(p -> itemstack.getItem() == p.getItem(), 1, _player.inventoryMenu.getCraftSlots());
            }
         }
      }
   }
}
