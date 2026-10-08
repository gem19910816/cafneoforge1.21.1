package net.mcreator.gore.procedures;

import javax.annotation.Nullable;
import net.mcreator.gore.configuration.GoreEditionSoundsConfigurationConfiguration;
import net.mcreator.gore.entity.ExarrackHydraEntity;
import net.mcreator.gore.entity.FakeExarrackMonsterEntity;
import net.mcreator.gore.entity.FleshEaterEntity;
import net.mcreator.gore.entity.TheExarrackMonsterEntity;
import net.mcreator.gore.utils.ItemTagHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

@EventBusSubscriber
public class HurtedByFleshEaterProcedure {
   @SubscribeEvent
   public static void onEntityAttacked(LivingIncomingDamageEvent event) {
      if (event != null && event.getEntity() != null) {
         execute(event, event.getEntity().level(), event.getEntity(), event.getSource().getEntity(), (double)event.getAmount());
      }
   }

   public static void execute(LevelAccessor world, Entity entity, Entity sourceentity, double amount) {
      execute(null, world, entity, sourceentity, amount);
   }

   private static void execute(@Nullable Event event, LevelAccessor world, Entity entity, Entity sourceentity, double amount) {
      if (entity != null
         && sourceentity != null
         && (
            sourceentity instanceof FleshEaterEntity
               || sourceentity instanceof FakeExarrackMonsterEntity
               || sourceentity instanceof TheExarrackMonsterEntity
               || sourceentity instanceof ExarrackHydraEntity
         )) {
         if (entity instanceof LivingEntity _livEnt4 && _livEnt4.isBlocking()) {
            if (world instanceof Level _level) {
               if (!_level.isClientSide()) {
                  _level.playSound(
                     null,
                     BlockPos.containing(entity.getX(), entity.getY(), entity.getZ()),
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore.devoration")),
                     SoundSource.AMBIENT,
                     (float)((Double)GoreEditionSoundsConfigurationConfiguration.GORE_BITE.get()).doubleValue(),
                     2.0F
                  );
               } else {
                  _level.playLocalSound(
                     entity.getX(),
                     entity.getY(),
                     entity.getZ(),
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore.devoration")),
                     SoundSource.AMBIENT,
                     (float)((Double)GoreEditionSoundsConfigurationConfiguration.GORE_BITE.get()).doubleValue(),
                     2.0F,
                     false
                  );
               }
            }

            if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == Items.SHIELD) {
               ItemStack _ist = entity instanceof LivingEntity _livEntx ? _livEntx.getMainHandItem() : ItemStack.EMPTY;
               if (ItemTagHelper.damageItemAmount(_ist, (int)amount, entity)) {
                  _ist.shrink(1);
                  _ist.setDamageValue(0);
               }
            }

            if ((entity instanceof LivingEntity _livEntx ? _livEntx.getOffhandItem() : ItemStack.EMPTY).getItem() == Items.SHIELD) {
               ItemStack _ist = entity instanceof LivingEntity _livEntxx ? _livEntxx.getOffhandItem() : ItemStack.EMPTY;
               if (ItemTagHelper.damageItemAmount(_ist, (int)amount, entity)) {
                  _ist.shrink(1);
                  _ist.setDamageValue(0);
                  return;
               }
            }

            return;
         }

         if (world instanceof Level _levelx) {
            if (!_levelx.isClientSide()) {
               _levelx.playSound(
                  null,
                  BlockPos.containing(entity.getX(), entity.getY(), entity.getZ()),
                  (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore.brutality_series.2")),
                  SoundSource.AMBIENT,
                  1.0F,
                  1.0F
               );
            } else {
               _levelx.playLocalSound(
                  entity.getX(),
                  entity.getY(),
                  entity.getZ(),
                  (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore.brutality_series.2")),
                  SoundSource.AMBIENT,
                  1.0F,
                  1.0F,
                  false
               );
            }
         }

         if (world instanceof Level _levelxx) {
            if (!_levelxx.isClientSide()) {
               _levelxx.playSound(
                  null,
                  BlockPos.containing(entity.getX(), entity.getY(), entity.getZ()),
                  (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore.bite")),
                  SoundSource.AMBIENT,
                  (float)((Double)GoreEditionSoundsConfigurationConfiguration.GORE_BITE.get()).doubleValue(),
                  1.0F
               );
            } else {
               _levelxx.playLocalSound(
                  entity.getX(),
                  entity.getY(),
                  entity.getZ(),
                  (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore.bite")),
                  SoundSource.AMBIENT,
                  (float)((Double)GoreEditionSoundsConfigurationConfiguration.GORE_BITE.get()).doubleValue(),
                  1.0F,
                  false
               );
            }
         }
      }
   }
}
