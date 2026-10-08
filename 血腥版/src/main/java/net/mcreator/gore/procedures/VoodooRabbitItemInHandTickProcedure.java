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
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class VoodooRabbitItemInHandTickProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, ItemStack itemstack) {
      if (entity != null) {
         double nasdfn = 0.0;
         if (ItemTagHelper.getDouble(itemstack, "number_necesary") > 4.0) {
            ItemTagHelper.getOrCreateTag(itemstack).putDouble("number_necesary", 0.0);
         }

         if (ItemTagHelper.getDouble(itemstack, "click") == 4.0) {
            ItemTagHelper.getOrCreateTag(itemstack).putBoolean("locked", true);
         }

         if (ItemTagHelper.getDouble(itemstack, "number_necesary") > 0.0 && ItemTagHelper.getOrCreateTag(itemstack).getDouble("timer_number_necesary") == 0.0) {
            ItemTagHelper.getOrCreateTag(itemstack).putDouble("number_necesary", ItemTagHelper.getOrCreateTag(itemstack).getDouble("number_necesary") - 1.0);
         }

         ItemTagHelper.putDouble(itemstack, "timer_number_necesary", ItemTagHelper.getOrCreateTag(itemstack).getDouble("timer_number_necesary") + 1.0);
         if (ItemTagHelper.getDouble(itemstack, "timer_number_necesary") == 7.0) {
            ItemTagHelper.getOrCreateTag(itemstack).putDouble("timer_number_necesary", 0.0);
         }

         if (ItemTagHelper.getBoolean(itemstack, "logic_damage_timer")) {
            ItemTagHelper.getOrCreateTag(itemstack).putDouble("damage_timer", ItemTagHelper.getOrCreateTag(itemstack).getDouble("damage_timer") + 1.0);
         }

         if (ItemTagHelper.getDouble(itemstack, "damage_timer") == 3.0) {
            Vec3 _center = new Vec3(x, y, z);

            for (Entity entityiterator : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(7.0), e -> true)
               .stream()
               .sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center)))
               .toList()) {
               if (entityiterator.getPersistentData().getBoolean(entity.getStringUUID() + "voodo")) {
                  if (world instanceof Level) {
                     Level _level = (Level)world;
                     if (!_level.isClientSide()) {
                        _level.playSound(
                           null,
                           BlockPos.containing(x, y, z),
                           (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore.brutality_stab")),
                           SoundSource.PLAYERS,
                           3.0F,
                           1.0F
                        );
                     } else {
                        _level.playLocalSound(
                           x,
                           y,
                           z,
                           (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore.brutality_stab")),
                           SoundSource.PLAYERS,
                           3.0F,
                           1.0F,
                           false
                        );
                     }
                  }

                  entityiterator.hurt(
                     new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.MAGIC), entity), 7.0F
                  );
               }
            }
         }

         if (ItemTagHelper.getDouble(itemstack, "damage_timer") == 10.0) {
            ItemTagHelper.getOrCreateTag(itemstack).putDouble("click", ItemTagHelper.getOrCreateTag(itemstack).getDouble("click") + 1.0);
            if (world instanceof Level _levelx) {
               if (!_levelx.isClientSide()) {
                  _levelx.playSound(
                     null,
                     BlockPos.containing(x, y, z),
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore_needle")),
                     SoundSource.PLAYERS,
                     1.0F,
                     1.0F
                  );
               } else {
                  _levelx.playLocalSound(
                     x,
                     y,
                     z,
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore_needle")),
                     SoundSource.PLAYERS,
                     1.0F,
                     1.0F,
                     false
                  );
               }
            }
         }

         if (ItemTagHelper.getDouble(itemstack, "damage_timer") == 13.0) {
            Vec3 _center = new Vec3(x, y, z);

            for (Entity entityiteratorx : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(7.0), e -> true)
               .stream()
               .sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center)))
               .toList()) {
               if (entityiteratorx.getPersistentData().getBoolean(entity.getStringUUID() + "voodo")) {
                  if (world instanceof Level) {
                     Level _levelxx = (Level)world;
                     if (!_levelxx.isClientSide()) {
                        _levelxx.playSound(
                           null,
                           BlockPos.containing(x, y, z),
                           (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore.brutality_stab")),
                           SoundSource.PLAYERS,
                           3.0F,
                           1.0F
                        );
                     } else {
                        _levelxx.playLocalSound(
                           x,
                           y,
                           z,
                           (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore.brutality_stab")),
                           SoundSource.PLAYERS,
                           3.0F,
                           1.0F,
                           false
                        );
                     }
                  }

                  entityiteratorx.hurt(
                     new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.MAGIC), entity), 7.0F
                  );
               }
            }
         }

         if (ItemTagHelper.getDouble(itemstack, "damage_timer") == 21.0) {
            ItemTagHelper.getOrCreateTag(itemstack).putDouble("damage_timer", 0.0);
            ItemTagHelper.putBoolean(itemstack, "logic_damage_timer", false);
         }

         if (!(ItemTagHelper.getDouble(itemstack, "cleaning_Tick") >= 500.0) && ItemTagHelper.getBoolean(itemstack, "clean")) {
            ItemTagHelper.getOrCreateTag(itemstack).putDouble("cleaning_Tick", ItemTagHelper.getOrCreateTag(itemstack).getDouble("cleaning_Tick") + 1.0);
         }

         if (ItemTagHelper.getBoolean(itemstack, "clean")) {
            VoodooRabbitTickCleanProcedure.execute(world, x, y, z, entity, itemstack);
         }
      }
   }
}
