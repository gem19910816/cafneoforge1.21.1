package net.mcreator.gore.procedures;

import javax.annotation.Nullable;
import net.mcreator.gore.GoreEditionMod;
import net.mcreator.gore.configuration.GoreEditionModeSettingsConfiguration;
import net.mcreator.gore.network.GoreEditionModVariables;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.EntityTickEvent.Pre;

@EventBusSubscriber
public class WarningScreenFramesProcedure {
   @SubscribeEvent
   public static void onEntityTick(Pre event) {
      execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity());
   }

   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      execute(null, world, x, y, z, entity);
   }

   private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null && (Boolean)GoreEditionModeSettingsConfiguration.WARNING_SCREEN.get() && (entity instanceof ServerPlayer || entity instanceof Player)) {
         if (!GoreEditionModVariables.getPlayerVariables(entity).warning_screen_toggle && entity instanceof LivingEntity) {
            if (!entity.getPersistentData().getBoolean("wnow")) {
               GoreEditionMod.queueServerWork(
                  20,
                  () -> {
                     entity.getPersistentData().putBoolean("wnow", true);
                     if (world instanceof ServerLevel _levelx) {
                        _levelx.getServer()
                           .getCommands()
                           .performPrefixedCommand(
                              new CommandSourceStack(
                                    CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _levelx, 4, "", Component.literal(""), _levelx.getServer(), null
                                 )
                                 .withSuppressedOutput(),
                              "execute as @p run playsound minecraft:ui.toast.in master @s ~ ~ ~ 1 0"
                           );
                     }
                  }
               );
            }

            if (entity.getPersistentData().getBoolean("wnow") && GoreEditionModVariables.getPlayerVariables(entity).warning_screen_frames <= 8.0) {
               double _setval = GoreEditionModVariables.getPlayerVariables(entity).warning_screen_frames + 1.0;
               GoreEditionModVariables.PlayerVariables capability = GoreEditionModVariables.getPlayerVariables(entity);
               capability.warning_screen_frames = _setval;
               capability.syncPlayerVariables(entity);
            }
         } else if (world instanceof ServerLevel _level) {
            _level.getServer()
               .getCommands()
               .performPrefixedCommand(
                  new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null)
                     .withSuppressedOutput(),
                  "execute as @p run stopsound @s master gore:relaxing_city_ambiance"
               );
         }
      }
   }
}
