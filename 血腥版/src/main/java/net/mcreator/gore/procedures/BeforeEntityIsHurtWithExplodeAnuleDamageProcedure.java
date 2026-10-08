package net.mcreator.gore.procedures;

import javax.annotation.Nullable;
import net.mcreator.gore.configuration.GoreEditionModeSettingsConfiguration;
import net.mcreator.gore.utils.ItemTagHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.ICancellableEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

@EventBusSubscriber
public class BeforeEntityIsHurtWithExplodeAnuleDamageProcedure {
   @SubscribeEvent
   public static void onEntityAttacked(LivingIncomingDamageEvent event) {
      if (event != null && event.getEntity() != null) {
         execute(
            event,
            event.getEntity().level(),
            event.getEntity().getX(),
            event.getEntity().getY(),
            event.getEntity().getZ(),
            event.getSource(),
            event.getEntity(),
            (double)event.getAmount()
         );
      }
   }

   public static void execute(LevelAccessor world, double x, double y, double z, DamageSource damagesource, Entity entity, double amount) {
      execute(null, world, x, y, z, damagesource, entity, amount);
   }

   private static void execute(
      @Nullable Event event, LevelAccessor world, double x, double y, double z, DamageSource damagesource, Entity entity, double amount
   ) {
      if (damagesource != null
         && entity != null
         && (Boolean)GoreEditionModeSettingsConfiguration.SHIELDSBLOCKANYEXPLODES.get()
         && (entity instanceof LivingEntity _entUseItem1 ? _entUseItem1.getUseItem() : ItemStack.EMPTY).getItem() == Items.SHIELD
         && (damagesource.is(DamageTypes.EXPLOSION) || damagesource.is(DamageTypes.PLAYER_EXPLOSION))) {
         ItemStack _ist = entity instanceof LivingEntity _entUseItem5 ? _entUseItem5.getUseItem() : ItemStack.EMPTY;
         if (ItemTagHelper.damageItemAmount(_ist, (int)amount, entity)) {
            _ist.shrink(1);
            _ist.setDamageValue(0);
         }

         if (world instanceof Level _level) {
            if (!_level.isClientSide()) {
               _level.playSound(
                  null,
                  BlockPos.containing(x, y, z),
                  (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("item.shield.block")),
                  SoundSource.NEUTRAL,
                  1.0F,
                  1.0F
               );
            } else {
               _level.playLocalSound(
                  x, y, z, (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("item.shield.block")), SoundSource.NEUTRAL, 1.0F, 1.0F, false
               );
            }
         }

         if (event instanceof ICancellableEvent _c) {
            _c.setCanceled(true);
         }
      }
   }
}
