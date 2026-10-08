package net.mcreator.gore.procedures;

import javax.annotation.Nullable;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

@EventBusSubscriber
public class ToughnessDeathConfigProcedure {
   @SubscribeEvent
   public static void onEntityAttacked(LivingIncomingDamageEvent event) {
      if (event != null && event.getEntity() != null) {
         execute(event, event.getSource(), event.getEntity(), (double)event.getAmount());
      }
   }

   public static void execute(DamageSource damagesource, Entity entity, double amount) {
      execute(null, damagesource, entity, amount);
   }

   private static void execute(@Nullable Event event, DamageSource damagesource, Entity entity, double amount) {
      if (damagesource != null && entity != null) {
         double type = 0.0;
         if (!damagesource.is(DamageTypes.EXPLOSION)
            && !damagesource.is(DamageTypes.PLAYER_EXPLOSION)
            && amount >= (double)(entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1.0F)
            && !damagesource.is(DamageTypes.FALL)) {
            entity.getPersistentData().putDouble("type", (double)Mth.nextInt(RandomSource.create(), 999, 999));
         }

         if (entity.getPersistentData().getDouble("type") != 999.0) {
            if (entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("minecraft:true_zombie")))
               && entity.getPersistentData().getBoolean("toughness")) {
               if (!damagesource.is(DamageTypes.LAVA)
                  && !damagesource.is(DamageTypes.FALL)
                  && !damagesource.is(DamageTypes.IN_FIRE)
                  && !damagesource.is(DamageTypes.ON_FIRE)) {
                  entity.getPersistentData().putDouble("type", (double)Mth.nextInt(RandomSource.create(), 1, 4));
               }

               if (damagesource.is(DamageTypes.FALL)) {
                  entity.getPersistentData().putDouble("type", 5.0);
               }
            }

            if (entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("minecraft:true_husk")))
               && entity.getPersistentData().getBoolean("toughness")) {
               if (!damagesource.is(DamageTypes.LAVA)
                  && !damagesource.is(DamageTypes.FALL)
                  && !damagesource.is(DamageTypes.IN_FIRE)
                  && !damagesource.is(DamageTypes.ON_FIRE)) {
                  entity.getPersistentData().putDouble("type", (double)Mth.nextInt(RandomSource.create(), 1, 4));
               }

               if (damagesource.is(DamageTypes.FALL)) {
                  entity.getPersistentData().putDouble("type", 5.0);
               }
            }

            if (entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("minecraft:true_spider")))
               && entity.getPersistentData().getBoolean("toughness")
               && !damagesource.is(DamageTypes.LAVA)
               && !damagesource.is(DamageTypes.FALL)
               && !damagesource.is(DamageTypes.IN_FIRE)
               && !damagesource.is(DamageTypes.ON_FIRE)) {
               entity.getPersistentData().putDouble("type", (double)Mth.nextInt(RandomSource.create(), 3, 5));
            }

            if (entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("minecraft:true_skeleton")))
               && entity.getPersistentData().getBoolean("toughness")
               && !damagesource.is(DamageTypes.LAVA)
               && !damagesource.is(DamageTypes.FALL)
               && !damagesource.is(DamageTypes.IN_FIRE)
               && !damagesource.is(DamageTypes.ON_FIRE)) {
               entity.getPersistentData().putDouble("type", (double)Mth.nextInt(RandomSource.create(), 1, 4));
            }
         } else if (!entity.getPersistentData().getBoolean("toughness")
            && entity.getPersistentData().getDouble("type") == 999.0
            && (!(entity instanceof LivingEntity _livEnt45) || !_livEnt45.isBaby())) {
            entity.getPersistentData().putDouble("death_animation_type", (double)Mth.nextInt(RandomSource.create(), 1, 3));
         }
      }
   }
}
