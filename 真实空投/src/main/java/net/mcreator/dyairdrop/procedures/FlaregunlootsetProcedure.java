package net.mcreator.dyairdrop.procedures;

import net.gem19910816.dyairdrop.core.Vars;

import net.gem19910816.dyairdrop.core.Projectiles;

import net.mcreator.dyairdrop.configuration.AirdropconfigConfiguration;
import net.mcreator.dyairdrop.entity.FlareEntity;
import net.mcreator.dyairdrop.init.DyairdropModEntities;
import net.mcreator.dyairdrop.init.DyairdropModItems;
import net.mcreator.dyairdrop.network.DyairdropModVariables;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.neoforged.fml.ModList;

public class FlaregunlootsetProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, ItemStack itemstack) {
      if (entity != null) {
         String modname = "";
         double random = 0.0;
         double w = 0.0;
         double m = 0.0;
         double s = 0.0;
         if (ModList.get().isLoaded("zombiekit")) {
            modname = "zombiekit";
         } else {
            modname = "dyairdrop";
         }

         if (world instanceof Level _level) {
            if (!_level.isClientSide()) {
               _level.playSound(
                  null,
                  BlockPos.containing(x, y, z),
                  SoundEvents.FIREWORK_ROCKET_LAUNCH,
                  SoundSource.NEUTRAL,
                  1.0F,
                  1.0F
               );
            } else {
               _level.playLocalSound(
                  x,
                  y,
                  z,
                  SoundEvents.FIREWORK_ROCKET_LAUNCH,
                  SoundSource.NEUTRAL,
                  1.0F,
                  1.0F,
                  false
               );
            }
         }

         Entity _shootFrom = entity;
         Level projectileLevel = _shootFrom.level();
         if (!projectileLevel.isClientSide()) {
            Projectile _entityToSpawn = Projectiles.flare(projectileLevel, entity, 5.0F);
            _entityToSpawn.setPos(_shootFrom.getX(), _shootFrom.getEyeY() - 0.1, _shootFrom.getZ());
            _entityToSpawn.shoot(_shootFrom.getLookAngle().x, _shootFrom.getLookAngle().y, _shootFrom.getLookAngle().z, 3.0F, 0.0F);
            projectileLevel.addFreshEntity(_entityToSpawn);
         }

         w = (Double)AirdropconfigConfiguration.WEAPONAIRDROPWEIGHT.get();
         m = (Double)AirdropconfigConfiguration.MEDICALAIRDROPWEIGHT.get();
         s = (Double)AirdropconfigConfiguration.SMALLAIRDROPWEIGHT.get();
         w = 10.0 * w / (w + m + s);
         m = 10.0 * m / (w + m + s);
         s = 10.0 * s / (w + m + s);
         random = Mth.nextDouble(RandomSource.create(), 0.0, 10.0);
         if (random < s) {
            String _setval = "dyairdrop:airdropsmall";
            Vars.of(entity).ifPresentData(capability -> {
               capability.airdropblock = _setval;
               capability.syncPlayerVariables(entity);
            });
         } else if (random < w + s) {
            String _setval = "dyairdrop:airdropweapon";
            Vars.of(entity).ifPresentData(capability -> {
               capability.airdropblock = _setval;
               capability.syncPlayerVariables(entity);
            });
         } else {
            String _setval = "dyairdrop:airdropmedical";
            Vars.of(entity).ifPresentData(capability -> {
               capability.airdropblock = _setval;
               capability.syncPlayerVariables(entity);
            });
         }

         String _setval = modname
            + ":chests/"
            + Vars.of(entity)
               .airdropblock
               .replace("dyairdrop:airdrop", "")
            + "airdrop"
            + BuiltInRegistries.ITEM.getKey(itemstack.getItem()).toString().replace("dyairdrop:flaregun", "");
         Vars.of(entity).ifPresentData(capability -> {
            capability.airdroploot = _setval;
            capability.syncPlayerVariables(entity);
         });
         itemstack.shrink(1);
         if (entity instanceof Player _player) {
            _player.getCooldowns().addCooldown((Item)DyairdropModItems.FLAREGUN1.get(), 300);
         }

         if (entity instanceof Player _player) {
            _player.getCooldowns().addCooldown((Item)DyairdropModItems.FLAREGUN2.get(), 300);
         }

         if (entity instanceof Player _player) {
            _player.getCooldowns().addCooldown((Item)DyairdropModItems.FLAREGUN3.get(), 300);
         }

         if (entity instanceof Player _player) {
            _player.getCooldowns().addCooldown((Item)DyairdropModItems.FLAREGUN4.get(), 300);
         }

         if (entity instanceof Player _player) {
            _player.getCooldowns().addCooldown((Item)DyairdropModItems.FLAREGUN5.get(), 300);
         }
      }
   }
}
