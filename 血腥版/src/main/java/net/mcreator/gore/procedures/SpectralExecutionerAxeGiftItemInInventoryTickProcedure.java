package net.mcreator.gore.procedures;

import java.util.Locale;
import net.mcreator.gore.utils.ItemTagHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.items.ItemHandlerHelper;

public class SpectralExecutionerAxeGiftItemInInventoryTickProcedure {
   public static void execute(Entity entity, ItemStack itemstack) {
      if (entity != null) {
         ItemStack t = ItemStack.EMPTY;
         if (!ItemTagHelper.getString(itemstack, "item").equals("")) {
            ItemTagHelper.getOrCreateTag(itemstack).putDouble("click", 0.0);
         } else {
            ItemTagHelper.putDouble(itemstack, "click", 1.0);
         }

         if (!ItemTagHelper.getBoolean(itemstack, "first_gift")) {
            ItemTagHelper.getOrCreateTag(itemstack).putString("item", "gore_edition:spectral_executioners_axe");
            ItemTagHelper.putBoolean(itemstack, "first_gift", true);
         }

         if (ItemTagHelper.getDouble(itemstack, "value") == 1.0) {
            ItemTagHelper.getOrCreateTag(itemstack).putDouble("tick", ItemTagHelper.getOrCreateTag(itemstack).getDouble("tick") + 1.0);
         }

         if (ItemTagHelper.getDouble(itemstack, "tick") == 30.0) {
            if (entity instanceof Player _player) {
               ItemStack _setstack = new ItemStack(
                     (ItemLike)BuiltInRegistries.ITEM.get(ResourceLocation.parse(ItemTagHelper.getString(itemstack, "item").toLowerCase(Locale.ENGLISH)))
                  )
                  .copy();
               _setstack.setCount(1);
               ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
            }

            ItemTagHelper.putString(itemstack, "item", "");
            if ((
                  (new Object() {
                           public boolean checkGamemode(Entity _ent) {
                              if (_ent instanceof ServerPlayer _serverPlayer) {
                                 return _serverPlayer.gameMode.getGameModeForPlayer() == GameType.SURVIVAL;
                              } else {
                                 return _ent.level().isClientSide() && _ent instanceof Player _player
                                    ? Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()) != null
                                       && Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()).getGameMode()
                                          == GameType.SURVIVAL
                                    : false;
                              }
                           }
                        })
                        .checkGamemode(entity)
                     || (new Object() {
                           public boolean checkGamemode(Entity _ent) {
                              if (_ent instanceof ServerPlayer _serverPlayer) {
                                 return _serverPlayer.gameMode.getGameModeForPlayer() == GameType.ADVENTURE;
                              } else {
                                 return _ent.level().isClientSide() && _ent instanceof Player _player
                                    ? Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()) != null
                                       && Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()).getGameMode()
                                          == GameType.ADVENTURE
                                    : false;
                              }
                           }
                        })
                        .checkGamemode(entity)
               )
               && ItemTagHelper.damageItem(itemstack, entity)) {
               itemstack.shrink(1);
               itemstack.setDamageValue(0);
            }

            ItemTagHelper.putBoolean(itemstack, "recovery", true);
         }

         if (ItemTagHelper.getBoolean(itemstack, "recovery")) {
            ItemTagHelper.getOrCreateTag(itemstack).putDouble("rise_time", ItemTagHelper.getOrCreateTag(itemstack).getDouble("rise_time") + 1.0);
            if (ItemTagHelper.getDouble(itemstack, "rise_time") >= 20.0) {
               ItemTagHelper.getOrCreateTag(itemstack).putBoolean("recovery", false);
               ItemTagHelper.putDouble(itemstack, "tick", 0.0);
               ItemTagHelper.putDouble(itemstack, "rise_time", 0.0);
               ItemTagHelper.putDouble(itemstack, "value", 0.0);
            }
         }
      }
   }
}
