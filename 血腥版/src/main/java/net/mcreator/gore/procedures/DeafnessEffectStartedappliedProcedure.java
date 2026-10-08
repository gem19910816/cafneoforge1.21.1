package net.mcreator.gore.procedures;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

public class DeafnessEffectStartedappliedProcedure {
   public static void execute(Entity entity) {
      if (entity != null && entity.level().isClientSide() && entity instanceof Player) {
         Minecraft.getInstance().getSoundManager().stop();
      }
   }
}
