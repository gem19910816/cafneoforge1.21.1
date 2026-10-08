package net.mcreator.gore.procedures;

import net.mcreator.gore.init.GoreEditionModMobEffects;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

public class ObsidianKnifeLivingEntityIsHitWithItemProcedure {
   public static void execute(LevelAccessor world, Entity entity, Entity sourceentity, ItemStack itemstack) {
      if (entity != null && sourceentity != null) {
         label63:
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
            .checkGamemode(sourceentity)) {
            if (entity instanceof LivingEntity _livEnt1 && _livEnt1.isBlocking()) {
               if (world instanceof Level _level) {
                  if (!_level.isClientSide()) {
                     _level.playSound(
                        null,
                        BlockPos.containing(sourceentity.getX(), sourceentity.getY(), sourceentity.getZ()),
                        (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.glass.break")),
                        SoundSource.AMBIENT,
                        1.4F,
                        0.0F
                     );
                  } else {
                     _level.playLocalSound(
                        sourceentity.getX(),
                        sourceentity.getY(),
                        sourceentity.getZ(),
                        (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.glass.break")),
                        SoundSource.AMBIENT,
                        1.4F,
                        0.0F,
                        false
                     );
                  }
               }

               if (sourceentity instanceof Player _player) {
                  _player.getInventory().clearOrCountMatchingItems(p -> itemstack.getItem() == p.getItem(), 1, _player.inventoryMenu.getCraftSlots());
               }
               break label63;
            }

            if (Math.random() < 0.7) {
               if (world instanceof Level _levelx) {
                  if (!_levelx.isClientSide()) {
                     _levelx.playSound(
                        null,
                        BlockPos.containing(sourceentity.getX(), sourceentity.getY(), sourceentity.getZ()),
                        (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.glass.break")),
                        SoundSource.AMBIENT,
                        1.4F,
                        0.0F
                     );
                  } else {
                     _levelx.playLocalSound(
                        sourceentity.getX(),
                        sourceentity.getY(),
                        sourceentity.getZ(),
                        (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.glass.break")),
                        SoundSource.AMBIENT,
                        1.4F,
                        0.0F,
                        false
                     );
                  }
               }

               if (sourceentity instanceof Player _player) {
                  _player.getInventory().clearOrCountMatchingItems(p -> itemstack.getItem() == p.getItem(), 1, _player.inventoryMenu.getCraftSlots());
               }
            }
         }

         if (entity instanceof LivingEntity _livEnt14 && _livEnt14.isBlocking()) {
            return;
         }

         if (world instanceof Level _levelxx) {
            if (!_levelxx.isClientSide()) {
               _levelxx.playSound(
                  null,
                  BlockPos.containing(entity.getX(), entity.getY(), entity.getZ()),
                  (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore.brutality_stab")),
                  SoundSource.AMBIENT,
                  2.0F,
                  2.0F
               );
            } else {
               _levelxx.playLocalSound(
                  entity.getX(),
                  entity.getY(),
                  entity.getZ(),
                  (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore.brutality_stab")),
                  SoundSource.AMBIENT,
                  2.0F,
                  2.0F,
                  false
               );
            }
         }

         if (!itemstack.isEnchanted()) {
            if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
               _entity.addEffect(new MobEffectInstance(GoreEditionModMobEffects.VULNERABILITY, 80, 0));
            }
         } else if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
            _entity.addEffect(new MobEffectInstance(GoreEditionModMobEffects.VULNERABILITY, 140, 1));
         }
      }
   }
}
