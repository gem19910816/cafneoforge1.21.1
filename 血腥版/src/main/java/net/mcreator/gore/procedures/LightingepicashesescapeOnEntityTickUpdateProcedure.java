package net.mcreator.gore.procedures;

import javax.annotation.Nullable;
import net.mcreator.gore.network.GoreEditionModVariables;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.EntityTickEvent.Pre;

@EventBusSubscriber
public class LightingepicashesescapeOnEntityTickUpdateProcedure {
   @SubscribeEvent
   public static void onEntityTick(Pre event) {
      execute(event, event.getEntity().level(), event.getEntity());
   }

   public static void execute(LevelAccessor world, Entity entity) {
      execute(null, world, entity);
   }

   private static void execute(@Nullable Event event, LevelAccessor world, Entity entity) {
      if (entity != null && entity.getPersistentData().getBoolean("lighting_epic_ashes_escape")) {
         if (entity.getPersistentData().getDouble("LEAETICK") == 0.0) {
            if (!(entity instanceof ServerPlayer)) {
               entity.teleportTo(
                  entity.getPersistentData().getDouble("prev_xash_x"),
                  entity.getPersistentData().getDouble("prev_xash_y"),
                  entity.getPersistentData().getDouble("prev_xash_z")
               );
               if (entity instanceof ServerPlayer _serverPlayer) {
                  _serverPlayer.connection
                     .teleport(
                        entity.getPersistentData().getDouble("prev_xash_x"),
                        entity.getPersistentData().getDouble("prev_xash_y"),
                        entity.getPersistentData().getDouble("prev_xash_z"),
                        entity.getYRot(),
                        entity.getXRot()
                     );
               }
            } else {
               entity.teleportTo(
                  GoreEditionModVariables.getPlayerVariables(entity).prev_xash_x,
                  GoreEditionModVariables.getPlayerVariables(entity).prev_xash_y,
                  GoreEditionModVariables.getPlayerVariables(entity).prev_xash_z
               );
               if (entity instanceof ServerPlayer _serverPlayer) {
                  _serverPlayer.connection
                     .teleport(
                        GoreEditionModVariables.getPlayerVariables(entity).prev_xash_x,
                        GoreEditionModVariables.getPlayerVariables(entity).prev_xash_y,
                        GoreEditionModVariables.getPlayerVariables(entity).prev_xash_z,
                        entity.getYRot(),
                        entity.getXRot()
                     );
               }
            }

            if (world instanceof ServerLevel _level) {
               _level.getServer()
                  .getCommands()
                  .performPrefixedCommand(
                     new CommandSourceStack(
                           CommandSource.NULL,
                           new Vec3(entity.getX(), entity.getY(), entity.getZ()),
                           Vec2.ZERO,
                           _level,
                           4,
                           "",
                           Component.literal(""),
                           _level.getServer(),
                           null
                        )
                        .withSuppressedOutput(),
                     "summon lightning_bolt"
                  );
            }

            if (entity instanceof LivingEntity _entity) {
               _entity.removeAllEffects();
            }

            if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
               _entity.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 800, 0));
            }
         }

         entity.getPersistentData().putDouble("LEAETICK", entity.getPersistentData().getDouble("LEAETICK") + 1.0);
         if (entity.getPersistentData().getDouble("LEAETICK") == 60.0) {
            entity.getPersistentData().putDouble("LEAETICK", 0.0);
            entity.getPersistentData().putBoolean("lighting_epic_ashes_escape", false);
         }
      }
   }
}
