package net.mcreator.gore.api;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.Event;

/** Posted only after a successful shove; useful for stamina and skill XP. */
public final class ZombieShoveResultEvent extends Event {
   private final Player player;
   private final Mob target;
   private final boolean knockedDown;

   public ZombieShoveResultEvent(Player player, Mob target, boolean knockedDown) {
      this.player = player;
      this.target = target;
      this.knockedDown = knockedDown;
   }

   public Player getPlayer() { return player; }
   public Mob getTarget() { return target; }
   public boolean isKnockedDown() { return knockedDown; }
}
