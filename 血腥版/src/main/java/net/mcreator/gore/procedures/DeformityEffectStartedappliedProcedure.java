package net.mcreator.gore.procedures;

import javax.annotation.Nullable;
import net.minecraft.client.Minecraft;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameType;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.ICancellableEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent.Remove;

@EventBusSubscriber
public class DeformityEffectStartedappliedProcedure {
   @SubscribeEvent
   public static void onMobEffectEvent(Remove event) {
      if (event != null && event.getEntity() != null && event.getEffectInstance() != null) {
         String effect = event.getEffectInstance().toString();
         int level = (new Object() {
            int convert(String s) {
               try {
                  return (int)Double.parseDouble(s.trim());
               } catch (Exception var3) {
                  return 0;
               }
            }
         }).convert(effect.substring(effect.indexOf("x ") + "x ".length(), effect.indexOf(",")));
         level = Math.max(1, level);
         int duration = (new Object() {
            int convert(String s) {
               try {
                  return (int)Double.parseDouble(s.trim());
               } catch (Exception var3) {
                  return 0;
               }
            }
         }).convert(effect.substring(effect.indexOf("Duration: ") + 10, effect.length()));
         effect = effect.replace("effect.", "").replace(".", ":").replace(",", "");
         effect = effect.substring(0, effect.indexOf(" "));
         execute(event, event.getEntity(), effect);
      }
   }

   public static void execute(Entity entity, String effect) {
      execute(null, entity, effect);
   }

   private static void execute(@Nullable Event event, Entity entity, String effect) {
      if (entity != null
         && effect != null
         && effect.equals("gore_edition:spiral_twist")
         && (
            (new Object() {
                     public boolean checkGamemode(Entity _ent) {
                        if (_ent instanceof ServerPlayer _serverPlayer) {
                           return _serverPlayer.gameMode.getGameModeForPlayer() == GameType.SURVIVAL;
                        } else {
                           return _ent.level().isClientSide() && _ent instanceof Player _player
                              ? Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()) != null
                                 && Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()).getGameMode() == GameType.SURVIVAL
                              : false;
                        }
                     }
                  })
                  .checkGamemode(entity)
               || (new Object() {
                     public boolean checkGamemode(Entity _ent) {
                        if (_ent instanceof ServerPlayer _serverPlayer) {
                           return _serverPlayer.gameMode.getGameModeForPlayer() == GameType.ADVENTURE;
                        } else {
                           return _ent.level().isClientSide() && _ent instanceof Player _player
                              ? Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()) != null
                                 && Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()).getGameMode() == GameType.ADVENTURE
                              : false;
                        }
                     }
                  })
                  .checkGamemode(entity)
         )
         && event instanceof ICancellableEvent _c) {
         _c.setCanceled(true);
      }
   }
}
