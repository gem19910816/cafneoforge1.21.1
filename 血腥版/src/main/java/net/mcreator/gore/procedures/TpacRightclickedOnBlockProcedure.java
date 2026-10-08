package net.mcreator.gore.procedures;

import net.mcreator.gore.init.GoreEditionModBlocks;
import net.mcreator.gore.init.GoreEditionModEntities;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;

public class TpacRightclickedOnBlockProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, ItemStack itemstack) {
      if (entity != null
         && world.getBlockState(BlockPos.containing(x, y, z)).getBlock() == GoreEditionModBlocks.EXARRACK.get()
         && (world instanceof Level _lvl ? _lvl.dimension() : (world instanceof WorldGenLevel _wgl ? _wgl.getLevel().dimension() : Level.OVERWORLD))
            == ResourceKey.create(Registries.DIMENSION, ResourceLocation.parse("gore_edition:the_xash"))) {
         if (world instanceof ServerLevel _level) {
            Entity entityToSpawn = ((EntityType)GoreEditionModEntities.HENGEYON.get()).spawn(_level, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED);
            if (entityToSpawn != null) {
               entityToSpawn.setYRot(world.getRandom().nextFloat() * 360.0F);
            }
         }

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
                              return _ent.level().isClientSide() && _ent instanceof Player _playerx
                                 ? Minecraft.getInstance().getConnection().getPlayerInfo(_playerx.getGameProfile().getId()) != null
                                    && Minecraft.getInstance().getConnection().getPlayerInfo(_playerx.getGameProfile().getId()).getGameMode()
                                       == GameType.ADVENTURE
                                 : false;
                           }
                        }
                     })
                     .checkGamemode(entity)
            )
            && entity instanceof Player _player) {
            _player.getInventory().clearOrCountMatchingItems(p -> itemstack.getItem() == p.getItem(), 1, _player.inventoryMenu.getCraftSlots());
         }
      }
   }
}
