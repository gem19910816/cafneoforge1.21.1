package net.mcreator.gore;

import net.mcreator.gore.init.GoreEditionModMobEffects;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.sound.PlaySoundEvent;

@EventBusSubscriber({Dist.CLIENT})
public class DeafnessCode {
   @SubscribeEvent
   public static void onPlaySound(PlaySoundEvent event) {
      Minecraft mc = Minecraft.getInstance();
      Player player = mc.player;
      if (player != null && !mc.isPaused() && player.hasEffect(GoreEditionModMobEffects.DEAFNESS)) {
         event.setSound(null);
      }
   }
}
