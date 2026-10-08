package net.mcreator.gore.procedures;

import java.util.Comparator;
import net.mcreator.gore.utils.ItemTagHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class VoodooRabbitTickCleanProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, ItemStack itemstack) {
      if (entity != null) {
         if (ItemTagHelper.getDouble(itemstack, "cleaning_Tick") == 1.0) {
            if (world instanceof Level _level) {
               if (!_level.isClientSide()) {
                  _level.playSound(
                     null,
                     BlockPos.containing(x, y, z),
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore_needle_iii")),
                     SoundSource.PLAYERS,
                     1.0F,
                     1.0F
                  );
               } else {
                  _level.playLocalSound(
                     x,
                     y,
                     z,
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore_needle_iii")),
                     SoundSource.PLAYERS,
                     1.0F,
                     1.0F,
                     false
                  );
               }
            }

            Vec3 _center = new Vec3(x, y, z);

            for (Entity entityiterator : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(7.0), e -> true)
               .stream()
               .sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center)))
               .toList()) {
               if (entityiterator.getPersistentData().getBoolean(entity.getStringUUID() + "voodo")) {
                  entityiterator.hurt(
                     new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.MAGIC), entity), 13.0F
                  );
               }
            }

            if ((!(entity instanceof Player _plr) || !_plr.getAbilities().instabuild) && ItemTagHelper.damageItem(itemstack, entity)) {
               itemstack.shrink(1);
               itemstack.setDamageValue(0);
            }
         }

         if (ItemTagHelper.getDouble(itemstack, "cleaning_Tick") == 11.0) {
            if (world instanceof Level _levelx) {
               if (!_levelx.isClientSide()) {
                  _levelx.playSound(
                     null,
                     BlockPos.containing(x, y, z),
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore_needle_iii")),
                     SoundSource.PLAYERS,
                     1.0F,
                     1.0F
                  );
               } else {
                  _levelx.playLocalSound(
                     x,
                     y,
                     z,
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore_needle_iii")),
                     SoundSource.PLAYERS,
                     1.0F,
                     1.0F,
                     false
                  );
               }
            }

            Vec3 _center = new Vec3(x, y, z);

            for (Entity entityiteratorx : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(7.0), e -> true)
               .stream()
               .sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center)))
               .toList()) {
               if (entityiteratorx.getPersistentData().getBoolean(entity.getStringUUID() + "voodo")) {
                  entityiteratorx.hurt(
                     new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.MAGIC), entity), 13.0F
                  );
               }
            }

            if ((!(entity instanceof Player _plr) || !_plr.getAbilities().instabuild) && ItemTagHelper.damageItem(itemstack, entity)) {
               itemstack.shrink(1);
               itemstack.setDamageValue(0);
            }
         }

         if (ItemTagHelper.getDouble(itemstack, "cleaning_Tick") == 21.0) {
            if (world instanceof Level _levelxx) {
               if (!_levelxx.isClientSide()) {
                  _levelxx.playSound(
                     null,
                     BlockPos.containing(x, y, z),
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore_needle_iii")),
                     SoundSource.PLAYERS,
                     1.0F,
                     1.0F
                  );
               } else {
                  _levelxx.playLocalSound(
                     x,
                     y,
                     z,
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore_needle_iii")),
                     SoundSource.PLAYERS,
                     1.0F,
                     1.0F,
                     false
                  );
               }
            }

            Vec3 _center = new Vec3(x, y, z);

            for (Entity entityiteratorxx : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(7.0), e -> true)
               .stream()
               .sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center)))
               .toList()) {
               if (entityiteratorxx.getPersistentData().getBoolean(entity.getStringUUID() + "voodo")) {
                  entityiteratorxx.hurt(
                     new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.MAGIC), entity), 13.0F
                  );
               }
            }

            if ((!(entity instanceof Player _plr) || !_plr.getAbilities().instabuild) && ItemTagHelper.damageItem(itemstack, entity)) {
               itemstack.shrink(1);
               itemstack.setDamageValue(0);
            }
         }

         if (ItemTagHelper.getDouble(itemstack, "cleaning_Tick") == 31.0) {
            if (world instanceof Level _levelxxx) {
               if (!_levelxxx.isClientSide()) {
                  _levelxxx.playSound(
                     null,
                     BlockPos.containing(x, y, z),
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore_needle_iii")),
                     SoundSource.PLAYERS,
                     1.0F,
                     1.0F
                  );
               } else {
                  _levelxxx.playLocalSound(
                     x,
                     y,
                     z,
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore_needle_iii")),
                     SoundSource.PLAYERS,
                     1.0F,
                     1.0F,
                     false
                  );
               }
            }

            Vec3 _center = new Vec3(x, y, z);

            for (Entity entityiteratorxxx : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(7.0), e -> true)
               .stream()
               .sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center)))
               .toList()) {
               if (entityiteratorxxx.getPersistentData().getBoolean(entity.getStringUUID() + "voodo")) {
                  entityiteratorxxx.hurt(
                     new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.MAGIC), entity), 13.0F
                  );
               }
            }

            if ((!(entity instanceof Player _plr) || !_plr.getAbilities().instabuild) && ItemTagHelper.damageItem(itemstack, entity)) {
               itemstack.shrink(1);
               itemstack.setDamageValue(0);
            }
         }

         if (ItemTagHelper.getDouble(itemstack, "cleaning_Tick") == 41.0) {
            ItemTagHelper.getOrCreateTag(itemstack).putBoolean("clean", false);
            ItemTagHelper.putDouble(itemstack, "click", 5.0);
            ItemTagHelper.putDouble(itemstack, "cleaning_Tick", 6000.0);
         }
      }
   }
}
