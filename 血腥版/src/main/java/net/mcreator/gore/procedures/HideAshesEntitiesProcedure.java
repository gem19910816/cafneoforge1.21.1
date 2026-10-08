package net.mcreator.gore.procedures;

import javax.annotation.Nullable;
import net.mcreator.gore.entity.FleshEaterEntity;
import net.mcreator.gore.entity.TheExarrackMonsterEntity;
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
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.EntityTickEvent.Pre;

@EventBusSubscriber
public class HideAshesEntitiesProcedure {
   @SubscribeEvent
   public static void onEntityTick(Pre event) {
      execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity());
   }

   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      execute(null, world, x, y, z, entity);
   }

   private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null && entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("gore_edition:ashes_natives")))) {
         if (entity.getPersistentData().getBoolean("hide_ashes_natives")) {
            if (entity.getPersistentData().getDouble("hiding_tick") == 0.0) {
               if (entity instanceof FleshEaterEntity) {
                  ((FleshEaterEntity)entity).setAnimation("hide");
               }

               if (entity instanceof TheExarrackMonsterEntity) {
                  ((TheExarrackMonsterEntity)entity).setAnimation("hide");
               }

               if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
                  _entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 90, 254, false, false));
               }

               if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
                  _entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 90, 254, false, false));
               }
            }

            entity.getPersistentData().putDouble("hiding_tick", entity.getPersistentData().getDouble("hiding_tick") + 1.0);
         }

         if (entity.getPersistentData().getDouble("hiding_tick") >= 80.0) {
            if (world instanceof Level _level) {
               if (!_level.isClientSide()) {
                  _level.playSound(
                     null,
                     BlockPos.containing(x, y, z),
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.stone.break")),
                     SoundSource.HOSTILE,
                     1.0F,
                     0.0F
                  );
               } else {
                  _level.playLocalSound(
                     x,
                     y,
                     z,
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.stone.break")),
                     SoundSource.HOSTILE,
                     1.0F,
                     0.0F,
                     false
                  );
               }
            }

            if (!entity.level().isClientSide()) {
               entity.discard();
            }
         }
      }
   }
}
