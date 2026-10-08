package net.mcreator.gore.procedures;

import net.mcreator.gore.init.GoreEditionModItems;
import net.mcreator.gore.utils.ItemTagHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

public class VoodooRabbitRightclickedProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, ItemStack itemstack) {
      if (entity != null) {
         Entity selected_entity = null;
         double mob_count = 0.0;
         if ((
               (new Object() {
                        public boolean checkGamemode(Entity _ent) {
                           if (_ent instanceof ServerPlayer _serverPlayer) {
                              return _serverPlayer.gameMode.getGameModeForPlayer() == GameType.CREATIVE;
                           } else {
                              return _ent.level().isClientSide() && _ent instanceof Player _player
                                 ? Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()) != null
                                    && Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()).getGameMode()
                                       == GameType.CREATIVE
                                 : false;
                           }
                        }
                     })
                     .checkGamemode(entity)
                  || entity instanceof Player _playerHasItem
                     && _playerHasItem.getInventory().contains(new ItemStack((ItemLike)GoreEditionModItems.NEEDLE.get()))
            )
            && (
               (entity instanceof LivingEntity _livEntx ? _livEntx.getMainHandItem() : ItemStack.EMPTY).getItem() == itemstack.getItem()
                  || (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem() == itemstack.getItem()
            )
            && !entity.getPersistentData().getBoolean("locked")
            && !(ItemTagHelper.getDouble(itemstack, "click") >= 4.0)) {
            ItemTagHelper.getOrCreateTag(itemstack).putDouble("number_necesary", ItemTagHelper.getOrCreateTag(itemstack).getDouble("number_necesary") + 1.0);
            label67:
            if (ItemTagHelper.getDouble(itemstack, "number_necesary") > 4.0) {
               ItemTagHelper.getOrCreateTag(itemstack).putBoolean("logic_damage_timer", true);
               if (entity instanceof Player _player) {
                  _player.getCooldowns().addCooldown(itemstack.getItem(), 20);
               }

               if (entity instanceof Player _plr && _plr.getAbilities().instabuild) {
                  break label67;
               }

               if (ItemTagHelper.damageItem(itemstack, entity)) {
                  itemstack.shrink(1);
                  itemstack.setDamageValue(0);
               }

               if (entity instanceof Player _player) {
                  ItemStack _stktoremove = new ItemStack((ItemLike)GoreEditionModItems.NEEDLE.get());
                  _player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1, _player.inventoryMenu.getCraftSlots());
               }
            } else if (world instanceof Level _level) {
               if (!_level.isClientSide()) {
                  _level.playSound(
                     null,
                     BlockPos.containing(x, y, z),
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore_needle_ii")),
                     SoundSource.PLAYERS,
                     1.0F,
                     1.0F
                  );
               } else {
                  _level.playLocalSound(
                     x,
                     y,
                     z,
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore_needle_ii")),
                     SoundSource.PLAYERS,
                     1.0F,
                     1.0F,
                     false
                  );
               }
            }
         }

         if ((
               (entity instanceof LivingEntity _livEntxxx ? _livEntxxx.getMainHandItem() : ItemStack.EMPTY).getItem() == itemstack.getItem()
                  || (entity instanceof LivingEntity _livEntxx ? _livEntxx.getOffhandItem() : ItemStack.EMPTY).getItem() == itemstack.getItem()
            )
            && ItemTagHelper.getDouble(itemstack, "click") == 4.0
            && !entity.getPersistentData().getBoolean("locked")) {
            ItemTagHelper.getOrCreateTag(itemstack).putBoolean("clean", true);
            ItemTagHelper.putBoolean(itemstack, "locked", true);
         }
      }
   }
}
