package net.mcreator.survivalinstinct.procedures;

import net.mcreator.survivalinstinct.init.SurvivalInstinctModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.neoforged.neoforge.items.ItemHandlerHelper;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;

public class MREOpenProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         if (world instanceof Level _level) {
            if (!_level.isClientSide()) {
               _level.playSound(
                  null,
                  BlockPos.containing(x, y, z),
                  BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("item.armor.equip_leather")),
                  SoundSource.NEUTRAL,
                  1.0F,
                  1.0F
               );
            } else {
               _level.playLocalSound(
                  x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("item.armor.equip_leather")), SoundSource.NEUTRAL, 1.0F, 1.0F, false
               );
            }
         }

         if (entity instanceof Player _player) {
            ItemStack _setstack = new ItemStack(SurvivalInstinctModItems.RICE_COOKIE.get()).copy();
            _setstack.setCount(3);
            ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
         }

         if (entity instanceof Player _player) {
            ItemStack _setstack = new ItemStack(Items.BREAD).copy();
            _setstack.setCount(1);
            ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
         }

         if (entity instanceof Player _player) {
            ItemStack _setstack = new ItemStack(SurvivalInstinctModItems.MILITARY_CAN.get()).copy();
            _setstack.setCount(1);
            ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
         }

         if (entity instanceof Player _player) {
            ItemStack _stktoremove = new ItemStack(SurvivalInstinctModItems.MRE.get());
            _player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1, _player.inventoryMenu.getCraftSlots());
         }
      }
   }
}
