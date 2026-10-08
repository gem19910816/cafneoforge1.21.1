package net.mcreator.gore.procedures;

import javax.annotation.Nullable;
import net.mcreator.gore.init.GoreEditionModBlocks;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent.RightClickItem;

@EventBusSubscriber
public class EquipExarrackMonsterHeadProcedure {
   @SubscribeEvent
   public static void onRightClickItem(RightClickItem event) {
      if (event.getHand() == event.getEntity().getUsedItemHand()) {
         execute(event, event.getLevel(), (double)event.getPos().getX(), (double)event.getPos().getY(), (double)event.getPos().getZ(), event.getEntity());
      }
   }

   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      execute(null, world, x, y, z, entity);
   }

   private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null
         && (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem()
            == ((Block)GoreEditionModBlocks.EXARRACK_MONSTER_HEAD.get()).asItem()
         && (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY).getItem() == Blocks.AIR.asItem()) {
         if (world instanceof ServerLevel _level) {
            _level.getServer()
               .getCommands()
               .performPrefixedCommand(
                  new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null)
                     .withSuppressedOutput(),
                  "item replace entity @p armor.head with gore_edition:exarrack_monster_head"
               );
         }

         if (world instanceof Level _level) {
            if (!_level.isClientSide()) {
               _level.playSound(
                  null,
                  BlockPos.containing(x, y, z),
                  (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("item.armor.equip_generic")),
                  SoundSource.PLAYERS,
                  1.0F,
                  0.0F
               );
            } else {
               _level.playLocalSound(
                  x,
                  y,
                  z,
                  (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("item.armor.equip_generic")),
                  SoundSource.PLAYERS,
                  1.0F,
                  0.0F,
                  false
               );
            }
         }

         if ((
               (new Object() {
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
                  || (new Object() {
                        public boolean checkGamemode(Entity _ent) {
                           if (_ent instanceof ServerPlayer _serverPlayer) {
                              return _serverPlayer.gameMode.getGameModeForPlayer() == GameType.SURVIVAL;
                           } else {
                              return _ent.level().isClientSide() && _ent instanceof Player _playerx
                                 ? Minecraft.getInstance().getConnection().getPlayerInfo(_playerx.getGameProfile().getId()) != null
                                    && Minecraft.getInstance().getConnection().getPlayerInfo(_playerx.getGameProfile().getId()).getGameMode()
                                       == GameType.SURVIVAL
                                 : false;
                           }
                        }
                     })
                     .checkGamemode(entity)
            )
            && entity instanceof Player _player) {
            ItemStack _stktoremove;
            ItemStack var18 = _stktoremove = entity instanceof LivingEntity _livEntx ? _livEntx.getMainHandItem() : ItemStack.EMPTY;
            _player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1, _player.inventoryMenu.getCraftSlots());
         }
      }
   }
}
