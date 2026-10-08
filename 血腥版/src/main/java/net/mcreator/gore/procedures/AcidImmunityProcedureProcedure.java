package net.mcreator.gore.procedures;

import javax.annotation.Nullable;
import net.mcreator.gore.init.GoreEditionModMobEffects;
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
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent.Start;
import net.neoforged.neoforge.items.ItemHandlerHelper;

@EventBusSubscriber
public class AcidImmunityProcedureProcedure {
   @SubscribeEvent
   public static void onUseItemStart(Start event) {
      if (event != null && event.getEntity() != null) {
         execute(
            event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity(), event.getItem()
         );
      }
   }

   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, ItemStack itemstack) {
      execute(null, world, x, y, z, entity, itemstack);
   }

   private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity, ItemStack itemstack) {
      if (entity != null
         && itemstack.getItem() == Items.MILK_BUCKET
         && entity instanceof LivingEntity _livEnt2
         && _livEnt2.hasEffect(GoreEditionModMobEffects.XASH_ACID)
         && (
            (new Object() {
                     public boolean checkGamemode(Entity _ent) {
                        if (_ent instanceof ServerPlayer _serverPlayer) {
                           return _serverPlayer.gameMode.getGameModeForPlayer() == GameType.SURVIVAL;
                        } else {
                           return _ent.level().isClientSide() && _ent instanceof Player _player
                              ? Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()) != null
                                 && Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()).getGameMode() == GameType.SURVIVAL
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
                                 && Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()).getGameMode() == GameType.ADVENTURE
                              : false;
                        }
                     }
                  })
                  .checkGamemode(entity)
         )) {
         if (world instanceof Level _level) {
            if (!_level.isClientSide()) {
               _level.playSound(
                  null,
                  BlockPos.containing(x, y, z),
                  (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.fire.extinguish")),
                  SoundSource.PLAYERS,
                  1.0F,
                  1.0F
               );
            } else {
               _level.playLocalSound(
                  x,
                  y,
                  z,
                  (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.fire.extinguish")),
                  SoundSource.PLAYERS,
                  1.0F,
                  1.0F,
                  false
               );
            }
         }

         if (entity instanceof Player _player) {
            _player.getInventory().clearOrCountMatchingItems(p -> itemstack.getItem() == p.getItem(), 1, _player.inventoryMenu.getCraftSlots());
         }

         if (entity instanceof Player _player) {
            ItemStack _setstack = new ItemStack(Items.BUCKET).copy();
            _setstack.setCount(1);
            ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
         }
      }
   }
}
