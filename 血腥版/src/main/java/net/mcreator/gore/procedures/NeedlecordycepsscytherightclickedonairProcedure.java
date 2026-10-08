package net.mcreator.gore.procedures;

import net.mcreator.gore.network.GoreEditionModVariables;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

public class NeedlecordycepsscytherightclickedonairProcedure {
   public static void execute(Entity entity) {
      if (entity != null) {
         if (!entity.isShiftKeyDown()) {
            if (GoreEditionModVariables.getPlayerVariables(entity).nsc_order_for_c != 3.0) {
               double _setval = GoreEditionModVariables.getPlayerVariables(entity).nsc_order_for_c + 1.0;
               GoreEditionModVariables.PlayerVariables capability = GoreEditionModVariables.getPlayerVariables(entity);
               capability.nsc_order_for_c = _setval;
               capability.syncPlayerVariables(entity);
            } else {
               double _setval = 0.0;
               GoreEditionModVariables.PlayerVariables capability = GoreEditionModVariables.getPlayerVariables(entity);
               capability.nsc_order_for_c = _setval;
               capability.syncPlayerVariables(entity);
            }
         } else if (GoreEditionModVariables.getPlayerVariables(entity).nsc_cattack) {
            boolean _setval = false;
            GoreEditionModVariables.PlayerVariables capability = GoreEditionModVariables.getPlayerVariables(entity);
            capability.nsc_cattack = _setval;
            capability.syncPlayerVariables(entity);
         } else {
            boolean _setval = true;
            GoreEditionModVariables.PlayerVariables capability = GoreEditionModVariables.getPlayerVariables(entity);
            capability.nsc_cattack = _setval;
            capability.syncPlayerVariables(entity);
         }

         if (!GoreEditionModVariables.getPlayerVariables(entity).nsc_cattack) {
            if (GoreEditionModVariables.getPlayerVariables(entity).nsc_order_for_c == 0.0
               && entity instanceof Player _player
               && !_player.level().isClientSide()) {
               _player.displayClientMessage(Component.literal("Walk?"), true);
            }

            if (GoreEditionModVariables.getPlayerVariables(entity).nsc_order_for_c == 1.0
               && entity instanceof Player _player
               && !_player.level().isClientSide()) {
               _player.displayClientMessage(Component.literal("Stop?"), true);
            }

            if (GoreEditionModVariables.getPlayerVariables(entity).nsc_order_for_c == 2.0
               && entity instanceof Player _player
               && !_player.level().isClientSide()) {
               _player.displayClientMessage(Component.literal("Follow?"), true);
            }

            if (GoreEditionModVariables.getPlayerVariables(entity).nsc_order_for_c == 3.0
               && entity instanceof Player _player
               && !_player.level().isClientSide()) {
               _player.displayClientMessage(Component.literal("☠"), true);
            }
         }

         if (GoreEditionModVariables.getPlayerVariables(entity).nsc_cattack) {
            if (GoreEditionModVariables.getPlayerVariables(entity).nsc_order_for_c == 0.0
               && entity instanceof Player _player
               && !_player.level().isClientSide()) {
               _player.displayClientMessage(Component.literal("Walk, don't attack aggressors?"), true);
            }

            if (GoreEditionModVariables.getPlayerVariables(entity).nsc_order_for_c == 1.0
               && entity instanceof Player _player
               && !_player.level().isClientSide()) {
               _player.displayClientMessage(Component.literal("Stop, don't attack aggressors?"), true);
            }

            if (GoreEditionModVariables.getPlayerVariables(entity).nsc_order_for_c == 2.0
               && entity instanceof Player _player
               && !_player.level().isClientSide()) {
               _player.displayClientMessage(Component.literal("Follow, don't attack aggressors?"), true);
            }

            if (GoreEditionModVariables.getPlayerVariables(entity).nsc_order_for_c == 3.0
               && entity instanceof Player _player
               && !_player.level().isClientSide()) {
               _player.displayClientMessage(Component.literal("☠"), true);
            }
         }
      }
   }
}
