package net.mcreator.gore.procedures;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

public class GrenadeOfGreekFireRangedItemShootsProjectileProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, ItemStack itemstack) {
      if (entity != null) {
         if (!(new Object() {
                  public boolean checkGamemode(Entity _ent) {
                     if (_ent instanceof ServerPlayer _serverPlayer) {
                        return _serverPlayer.gameMode.getGameModeForPlayer() == GameType.CREATIVE;
                     } else {
                        return _ent.level().isClientSide() && _ent instanceof Player _playerx
                           ? Minecraft.getInstance().getConnection().getPlayerInfo(_playerx.getGameProfile().getId()) != null
                              && Minecraft.getInstance().getConnection().getPlayerInfo(_playerx.getGameProfile().getId()).getGameMode() == GameType.CREATIVE
                           : false;
                     }
                  }
               })
               .checkGamemode(entity)
            && entity instanceof Player _player) {
            _player.getInventory().clearOrCountMatchingItems(p -> itemstack.getItem() == p.getItem(), 1, _player.inventoryMenu.getCraftSlots());
         }

         if (world instanceof Level _level) {
            if (!_level.isClientSide()) {
               _level.playSound(
                  null,
                  BlockPos.containing(x, y, z),
                  (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:grenade_of_greek_fire_shoot")),
                  SoundSource.PLAYERS,
                  1.0F,
                  1.0F
               );
            } else {
               _level.playLocalSound(
                  x,
                  y,
                  z,
                  (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:grenade_of_greek_fire_shoot")),
                  SoundSource.PLAYERS,
                  1.0F,
                  1.0F,
                  false
               );
            }
         }
      }
   }
}
