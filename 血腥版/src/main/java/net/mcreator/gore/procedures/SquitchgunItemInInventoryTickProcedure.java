package net.mcreator.gore.procedures;

import net.mcreator.gore.entity.ProjectileSquitchgunEntity;
import net.mcreator.gore.init.GoreEditionModEntities;
import net.mcreator.gore.init.GoreEditionModItems;
import net.mcreator.gore.item.SquitchgunItem;
import net.mcreator.gore.utils.ItemTagHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class SquitchgunItemInInventoryTickProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, ItemStack itemstack) {
      if (entity != null) {
         if (!ItemTagHelper.getBoolean(itemstack, "charged")
            && (
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
                     && _playerHasItem.getInventory().contains(new ItemStack((ItemLike)GoreEditionModItems.GEART.get()))
            )) {
            ItemTagHelper.putDouble(itemstack, "timer", ItemTagHelper.getOrCreateTag(itemstack).getDouble("timer") + 1.0);
            if (ItemTagHelper.getOrCreateTag(itemstack).getDouble("timer") == 1.0) {
               for (int index0 = 0; index0 < 30; index0++) {
                  if (itemstack.getItem() instanceof SquitchgunItem) {
                     ItemTagHelper.putString(itemstack, "geckoAnim", "squitchgun.charge");
                  }
               }
            }

            if (ItemTagHelper.getDouble(itemstack, "timer") == 6.0 && world instanceof Level _level) {
               if (!_level.isClientSide()) {
                  _level.playSound(
                     null,
                     BlockPos.containing(x, y, z),
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:squitchgun.receive_heart")),
                     SoundSource.PLAYERS,
                     1.0F,
                     1.0F
                  );
               } else {
                  _level.playLocalSound(
                     x,
                     y,
                     z,
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:squitchgun.receive_heart")),
                     SoundSource.PLAYERS,
                     1.0F,
                     1.0F,
                     false
                  );
               }
            }

            if (ItemTagHelper.getDouble(itemstack, "timer") == 19.0 && world instanceof Level _levelx) {
               if (!_levelx.isClientSide()) {
                  _levelx.playSound(
                     null,
                     BlockPos.containing(x, y, z),
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:squitchgun.heart_impact")),
                     SoundSource.PLAYERS,
                     1.0F,
                     1.0F
                  );
               } else {
                  _levelx.playLocalSound(
                     x,
                     y,
                     z,
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:squitchgun.heart_impact")),
                     SoundSource.PLAYERS,
                     1.0F,
                     1.0F,
                     false
                  );
               }
            }

            if (ItemTagHelper.getOrCreateTag(itemstack).getDouble("timer") == 21.0) {
               if (!(new Object() {
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
                  && entity instanceof Player _player) {
                  ItemStack _stktoremove = new ItemStack((ItemLike)GoreEditionModItems.GEART.get());
                  _player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1, _player.inventoryMenu.getCraftSlots());
               }

               ItemTagHelper.putBoolean(itemstack, "charged", true);
               ItemTagHelper.putDouble(itemstack, "timer", 0.0);
            }
         } else {
            BlockPos _pos = BlockPos.containing(x, y, z);
            BlockState _bs = world.getBlockState(_pos);
            if (_bs.getBlock().getStateDefinition().getProperty("animation") instanceof IntegerProperty _integerProp) {
               world.setBlock(_pos, (BlockState)_bs.setValue(_integerProp, 0), 3);
            }

            ItemTagHelper.putDouble(itemstack, "timer", 0.0);
         }

         if (ItemTagHelper.getBoolean(itemstack, "shoot")) {
            if (ItemTagHelper.getOrCreateTag(itemstack).getDouble("sqgtimer") == 0.0) {
               if (entity instanceof Player _player) {
                  _player.getCooldowns().addCooldown(itemstack.getItem(), 51);
               }

               for (int index1 = 0; index1 < 20; index1++) {
                  if (itemstack.getItem() instanceof SquitchgunItem) {
                     ItemTagHelper.putString(itemstack, "geckoAnim", "squitchgun.shoot");
                  }
               }

               if (world instanceof Level _levelxx) {
                  if (!_levelxx.isClientSide()) {
                     _levelxx.playSound(
                        null,
                        BlockPos.containing(x, y, z),
                        (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:squitchgun.shooting")),
                        SoundSource.PLAYERS,
                        1.0F,
                        1.0F
                     );
                  } else {
                     _levelxx.playLocalSound(
                        x,
                        y,
                        z,
                        (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:squitchgun.shooting")),
                        SoundSource.PLAYERS,
                        1.0F,
                        1.0F,
                        false
                     );
                  }
               }
            }

            ItemTagHelper.putDouble(itemstack, "sqgtimer", ItemTagHelper.getOrCreateTag(itemstack).getDouble("sqgtimer") + 1.0);
            if (ItemTagHelper.getDouble(itemstack, "sqgtimer") == 10.0 && world instanceof Level _levelxxx) {
               if (!_levelxxx.isClientSide()) {
                  _levelxxx.playSound(
                     null,
                     BlockPos.containing(x, y, z),
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:squitchgun.shooting")),
                     SoundSource.PLAYERS,
                     1.0F,
                     1.2F
                  );
               } else {
                  _levelxxx.playLocalSound(
                     x,
                     y,
                     z,
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:squitchgun.shooting")),
                     SoundSource.PLAYERS,
                     1.0F,
                     1.2F,
                     false
                  );
               }
            }

            if (ItemTagHelper.getDouble(itemstack, "sqgtimer") == 20.0 && world instanceof Level _levelxxxx) {
               if (!_levelxxxx.isClientSide()) {
                  _levelxxxx.playSound(
                     null,
                     BlockPos.containing(x, y, z),
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:squitchgun.shooting")),
                     SoundSource.PLAYERS,
                     1.0F,
                     1.4F
                  );
               } else {
                  _levelxxxx.playLocalSound(
                     x,
                     y,
                     z,
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:squitchgun.shooting")),
                     SoundSource.PLAYERS,
                     1.0F,
                     1.4F,
                     false
                  );
               }
            }

            if (ItemTagHelper.getDouble(itemstack, "sqgtimer") == 31.0) {
               if (world instanceof Level _levelxxxxx) {
                  if (!_levelxxxxx.isClientSide()) {
                     _levelxxxxx.playSound(
                        null,
                        BlockPos.containing(x, y, z),
                        (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:grenade_of_greek_fire_shoot")),
                        SoundSource.PLAYERS,
                        3.0F,
                        1.0F
                     );
                  } else {
                     _levelxxxxx.playLocalSound(
                        x,
                        y,
                        z,
                        (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:grenade_of_greek_fire_shoot")),
                        SoundSource.PLAYERS,
                        3.0F,
                        1.0F,
                        false
                     );
                  }
               }

               Level projectileLevel = entity.level();
               if (!projectileLevel.isClientSide()) {
                  Projectile _entityToSpawn = (new Object() {
                        public Projectile getArrow(Level level, float damage, int knockback) {
                           AbstractArrow entityToSpawn = new ProjectileSquitchgunEntity(
                              (EntityType<? extends ProjectileSquitchgunEntity>)GoreEditionModEntities.PROJECTILE_SQUISHED.get(), level
                           );
                           entityToSpawn.setBaseDamage((double)damage);
                           entityToSpawn.setSilent(true);
                           return entityToSpawn;
                        }
                     })
                     .getArrow(projectileLevel, 16.0F, 0);
                  _entityToSpawn.setPos(entity.getX(), entity.getEyeY() - 0.1, entity.getZ());
                  _entityToSpawn.shoot(entity.getLookAngle().x, entity.getLookAngle().y, entity.getLookAngle().z, 2.3F, 0.1F);
                  projectileLevel.addFreshEntity(_entityToSpawn);
               }

               if (ItemTagHelper.damageItem(itemstack, entity)) {
                  itemstack.shrink(1);
                  itemstack.setDamageValue(0);
               }
            }

            if (ItemTagHelper.getDouble(itemstack, "sqgtimer") == 50.0) {
               ItemTagHelper.getOrCreateTag(itemstack).putDouble("sqgtimer", 0.0);
               ItemTagHelper.putBoolean(itemstack, "charged", false);
               ItemTagHelper.putBoolean(itemstack, "shoot", false);
            }
         }
      }
   }
}
