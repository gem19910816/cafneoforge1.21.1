package net.mcreator.gore.procedures;

import net.mcreator.gore.configuration.GoreEditionSoundsConfigurationConfiguration;
import net.mcreator.gore.utils.ItemTagHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

public class ExecutionersAxLivingEntityIsHitWithToolProcedure {
   public static void execute(LevelAccessor world, Entity entity, Entity sourceentity, ItemStack itemstack) {
      if (entity != null && sourceentity != null) {
         double effect = 0.0;
         if (!entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_corpses")))
            && (entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1.0F) <= 0.0F) {
            itemstack.setDamageValue(0);
            ItemTagHelper.putDouble(itemstack, "timer", 0.0);
            ItemTagHelper.putDouble(
               itemstack, "speed_effect_provider_gift", ItemTagHelper.getOrCreateTag(itemstack).getDouble("speed_effect_provider_gift") + 1.0
            );
            if (ItemTagHelper.getDouble(itemstack, "speed_effect_provider_gift") >= 4.0
               && ItemTagHelper.getOrCreateTag(itemstack).getDouble("speed_effect_provider_gift") < 8.0) {
               ItemTagHelper.getOrCreateTag(itemstack).putBoolean("accustom_the_axe", true);
            }

            if (ItemTagHelper.getDouble(itemstack, "speed_effect_provider_gift") >= 8.0
               && ItemTagHelper.getOrCreateTag(itemstack).getDouble("speed_effect_provider_gift") < 16.0
               && sourceentity instanceof LivingEntity _entity
               && !_entity.level().isClientSide()) {
               _entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 120, 0));
            }

            if (ItemTagHelper.getDouble(itemstack, "speed_effect_provider_gift") >= 16.0
               && ItemTagHelper.getOrCreateTag(itemstack).getDouble("speed_effect_provider_gift") < 20.0
               && sourceentity instanceof LivingEntity _entity
               && !_entity.level().isClientSide()) {
               _entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 120, 1));
            }

            if (ItemTagHelper.getDouble(itemstack, "speed_effect_provider_gift") >= 20.0
               && sourceentity instanceof LivingEntity _entity
               && !_entity.level().isClientSide()) {
               int var10005;
               if (entity instanceof LivingEntity _livEntx && _livEntx.hasEffect(MobEffects.MOVEMENT_SPEED)) {
                  var10005 = _livEntx.getEffect(MobEffects.MOVEMENT_SPEED).getAmplifier();
               } else {
                  var10005 = 0;
               }

               _entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 120, var10005 + 1));
            }
         }

         if (world instanceof Level _level) {
            if (!_level.isClientSide()) {
               _level.playSound(
                  null,
                  BlockPos.containing(entity.getX(), entity.getY(), entity.getZ()),
                  (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore.brutality_series.1")),
                  SoundSource.AMBIENT,
                  (float)((Double)GoreEditionSoundsConfigurationConfiguration.GORE_BRUTALITY_SERIES_1.get()).doubleValue(),
                  0.0F
               );
            } else {
               _level.playLocalSound(
                  entity.getX(),
                  entity.getY(),
                  entity.getZ(),
                  (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore.brutality_series.1")),
                  SoundSource.AMBIENT,
                  (float)((Double)GoreEditionSoundsConfigurationConfiguration.GORE_BRUTALITY_SERIES_1.get()).doubleValue(),
                  0.0F,
                  false
               );
            }
         }
      }
   }
}
