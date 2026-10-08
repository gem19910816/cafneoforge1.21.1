package net.mcreator.gore.procedures;

import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameType;

public class LightingOrbRightclickedProcedure {
   public static void execute(Entity entity, ItemStack itemstack) {
      if (entity != null
         && entity.level().dimension() == ResourceKey.create(Registries.DIMENSION, ResourceLocation.parse("gore_edition:the_xash"))
         && !entity.getPersistentData().getBoolean("ashes_escape_because_lighting_orb")) {
         entity.getPersistentData().putBoolean("ashes_escape_because_lighting_orb", true);
         if (!(new Object() {
                  public boolean checkGamemode(Entity _ent) {
                     if (_ent instanceof ServerPlayer _serverPlayer) {
                        return _serverPlayer.gameMode.getGameModeForPlayer() == GameType.CREATIVE;
                     } else {
                        return _ent.level().isClientSide() && _ent instanceof Player _player
                           ? Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()) != null
                              && Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()).getGameMode() == GameType.CREATIVE
                           : false;
                     }
                  }
               })
               .checkGamemode(entity)
            && !(new Object() {
                  public boolean checkGamemode(Entity _ent) {
                     if (_ent instanceof ServerPlayer _serverPlayer) {
                        return _serverPlayer.gameMode.getGameModeForPlayer() == GameType.SPECTATOR;
                     } else {
                        return _ent.level().isClientSide() && _ent instanceof Player _playerx
                           ? Minecraft.getInstance().getConnection().getPlayerInfo(_playerx.getGameProfile().getId()) != null
                              && Minecraft.getInstance().getConnection().getPlayerInfo(_playerx.getGameProfile().getId()).getGameMode() == GameType.SPECTATOR
                           : false;
                     }
                  }
               })
               .checkGamemode(entity)
            && entity instanceof Player _player) {
            _player.getInventory().clearOrCountMatchingItems(p -> itemstack.getItem() == p.getItem(), 1, _player.inventoryMenu.getCraftSlots());
         }
      }
   }
}
