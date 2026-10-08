package net.mcreator.gore.procedures;

import java.util.Locale;
import net.mcreator.gore.utils.ItemTagHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

public class SpectralExecutionerAxeGiftRightclickedProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, ItemStack itemstack) {
      if (entity != null && !ItemTagHelper.getString(itemstack, "item").equals("") && ItemTagHelper.getOrCreateTag(itemstack).getDouble("value") == 0.0) {
         ItemTagHelper.getOrCreateTag(itemstack).putDouble("value", ItemTagHelper.getOrCreateTag(itemstack).getDouble("value") + 1.0);
         if (world instanceof Level _level) {
            if (!_level.isClientSide()) {
               _level.playSound(
                  null,
                  BlockPos.containing(x, y, z),
                  (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:octarm_spectral_gift_open")),
                  SoundSource.PLAYERS,
                  1.0F,
                  1.0F
               );
            } else {
               _level.playLocalSound(
                  x,
                  y,
                  z,
                  (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:octarm_spectral_gift_open")),
                  SoundSource.PLAYERS,
                  1.0F,
                  1.0F,
                  false
               );
            }
         }

         if (world instanceof Level _levelx) {
            if (!_levelx.isClientSide()) {
               _levelx.playSound(
                  null,
                  BlockPos.containing(x, y, z),
                  (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gift_open")),
                  SoundSource.PLAYERS,
                  1.0F,
                  1.0F
               );
            } else {
               _levelx.playLocalSound(
                  x,
                  y,
                  z,
                  (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gift_open")),
                  SoundSource.PLAYERS,
                  1.0F,
                  1.0F,
                  false
               );
            }
         }

         if (world.isClientSide()) {
            Minecraft.getInstance()
               .gameRenderer
               .displayItemActivation(
                  new ItemStack(
                     (ItemLike)BuiltInRegistries.ITEM.get(ResourceLocation.parse(ItemTagHelper.getString(itemstack, "item").toLowerCase(Locale.ENGLISH)))
                  )
               );
         }

         if (entity instanceof Player _player) {
            _player.getCooldowns().addCooldown(itemstack.getItem(), 30);
         }
      }
   }
}
