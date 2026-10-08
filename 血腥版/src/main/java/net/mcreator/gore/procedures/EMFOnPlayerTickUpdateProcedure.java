package net.mcreator.gore.procedures;

import javax.annotation.Nullable;
import net.mcreator.gore.network.GoreEditionModVariables;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent.Pre;

@EventBusSubscriber
public class EMFOnPlayerTickUpdateProcedure {
   @SubscribeEvent
   public static void onPlayerTick(Pre event) {
      execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity());
   }

   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      execute(null, world, x, y, z, entity);
   }

   private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         if (GoreEditionModVariables.getPlayerVariables(entity).exarrack_monster_screen) {
            double _setval = GoreEditionModVariables.getPlayerVariables(entity).exarrack_monster_screen_frames + 1.0;
            GoreEditionModVariables.PlayerVariables capability = GoreEditionModVariables.getPlayerVariables(entity);
            capability.exarrack_monster_screen_frames = _setval;
            capability.syncPlayerVariables(entity);
         }

         if (GoreEditionModVariables.getPlayerVariables(entity).exarrack_monster_screen_frames == 1.0) {
            if (world instanceof Level _level) {
               if (!_level.isClientSide()) {
                  _level.playSound(
                     null,
                     BlockPos.containing(x, y, z),
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:entity.exarrack_monster_jumpscare")),
                     SoundSource.NEUTRAL,
                     4.0F,
                     1.0F
                  );
               } else {
                  _level.playLocalSound(
                     x,
                     y,
                     z,
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:entity.exarrack_monster_jumpscare")),
                     SoundSource.NEUTRAL,
                     4.0F,
                     1.0F,
                     false
                  );
               }
            }

            if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
               _entity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 59, 0));
            }
         }

         if (GoreEditionModVariables.getPlayerVariables(entity).exarrack_monster_screen_frames >= 60.0) {
            double _setval = 0.0;
            GoreEditionModVariables.PlayerVariables capability = GoreEditionModVariables.getPlayerVariables(entity);
            capability.exarrack_monster_screen_frames = _setval;
            capability.syncPlayerVariables(entity);
            boolean _setvalx = false;
            GoreEditionModVariables.PlayerVariables capabilityx = GoreEditionModVariables.getPlayerVariables(entity);
            capabilityx.exarrack_monster_screen = _setvalx;
            capabilityx.syncPlayerVariables(entity);
            if (world instanceof Level _levelx) {
               if (!_levelx.isClientSide()) {
                  _levelx.playSound(
                     null,
                     BlockPos.containing(x, y, z),
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore.devoration")),
                     SoundSource.HOSTILE,
                     1.0F,
                     1.0F
                  );
               } else {
                  _levelx.playLocalSound(
                     x,
                     y,
                     z,
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore.devoration")),
                     SoundSource.HOSTILE,
                     1.0F,
                     1.0F,
                     false
                  );
               }
            }

            entity.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.MOB_ATTACK)), 27.0F);
         }
      }
   }
}
